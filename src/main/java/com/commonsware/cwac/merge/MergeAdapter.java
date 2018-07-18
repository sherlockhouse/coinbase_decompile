package com.commonsware.cwac.merge;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.SectionIndexer;
import com.commonsware.cwac.sacklist.SackOfViewsAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MergeAdapter extends BaseAdapter implements SectionIndexer {
    protected PieceStateRoster pieces = new PieceStateRoster();

    private class CascadeDataSetObserver extends DataSetObserver {
        private CascadeDataSetObserver() {
        }

        public void onChanged() {
            MergeAdapter.this.notifyDataSetChanged();
        }

        public void onInvalidated() {
            MergeAdapter.this.notifyDataSetInvalidated();
        }
    }

    private static class EnabledSackAdapter extends SackOfViewsAdapter {
        public EnabledSackAdapter(List<View> views) {
            super(views);
        }

        public boolean areAllItemsEnabled() {
            return true;
        }

        public boolean isEnabled(int position) {
            return true;
        }
    }

    private static class PieceState {
        ListAdapter adapter;
        boolean isActive = true;

        PieceState(ListAdapter adapter, boolean isActive) {
            this.adapter = adapter;
            this.isActive = isActive;
        }
    }

    private static class PieceStateRoster {
        protected ArrayList<ListAdapter> active;
        protected ArrayList<PieceState> pieces;

        private PieceStateRoster() {
            this.pieces = new ArrayList();
            this.active = null;
        }

        void add(ListAdapter adapter) {
            this.pieces.add(new PieceState(adapter, true));
        }

        List<PieceState> getRawPieces() {
            return this.pieces;
        }

        List<ListAdapter> getPieces() {
            if (this.active == null) {
                this.active = new ArrayList();
                Iterator i$ = this.pieces.iterator();
                while (i$.hasNext()) {
                    PieceState state = (PieceState) i$.next();
                    if (state.isActive) {
                        this.active.add(state.adapter);
                    }
                }
            }
            return this.active;
        }
    }

    public void addAdapter(ListAdapter adapter) {
        this.pieces.add(adapter);
        adapter.registerDataSetObserver(new CascadeDataSetObserver());
    }

    public void addView(View view) {
        addView(view, false);
    }

    public void addView(View view, boolean enabled) {
        ArrayList<View> list = new ArrayList(1);
        list.add(view);
        addViews(list, enabled);
    }

    public void addViews(List<View> views, boolean enabled) {
        if (enabled) {
            addAdapter(new EnabledSackAdapter(views));
        } else {
            addAdapter(new SackOfViewsAdapter(views));
        }
    }

    public Object getItem(int position) {
        for (ListAdapter piece : getPieces()) {
            int size = piece.getCount();
            if (position < size) {
                return piece.getItem(position);
            }
            position -= size;
        }
        return null;
    }

    public int getCount() {
        int total = 0;
        for (ListAdapter piece : getPieces()) {
            total += piece.getCount();
        }
        return total;
    }

    public int getViewTypeCount() {
        int total = 0;
        for (PieceState piece : this.pieces.getRawPieces()) {
            total += piece.adapter.getViewTypeCount();
        }
        return Math.max(total, 1);
    }

    public int getItemViewType(int position) {
        int typeOffset = 0;
        for (PieceState piece : this.pieces.getRawPieces()) {
            if (piece.isActive) {
                int size = piece.adapter.getCount();
                if (position < size) {
                    return typeOffset + piece.adapter.getItemViewType(position);
                }
                position -= size;
            }
            typeOffset += piece.adapter.getViewTypeCount();
        }
        return -1;
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    public boolean isEnabled(int position) {
        for (ListAdapter piece : getPieces()) {
            int size = piece.getCount();
            if (position < size) {
                return piece.isEnabled(position);
            }
            position -= size;
        }
        return false;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        for (ListAdapter piece : getPieces()) {
            int size = piece.getCount();
            if (position < size) {
                return piece.getView(position, convertView, parent);
            }
            position -= size;
        }
        return null;
    }

    public long getItemId(int position) {
        for (ListAdapter piece : getPieces()) {
            int size = piece.getCount();
            if (position < size) {
                return piece.getItemId(position);
            }
            position -= size;
        }
        return -1;
    }

    public int getPositionForSection(int section) {
        int position = 0;
        for (ListAdapter piece : getPieces()) {
            if (piece instanceof SectionIndexer) {
                Object[] sections = ((SectionIndexer) piece).getSections();
                int numSections = 0;
                if (sections != null) {
                    numSections = sections.length;
                }
                if (section < numSections) {
                    return ((SectionIndexer) piece).getPositionForSection(section) + position;
                }
                if (sections != null) {
                    section -= numSections;
                }
            }
            position += piece.getCount();
        }
        return 0;
    }

    public int getSectionForPosition(int position) {
        int section = 0;
        for (ListAdapter piece : getPieces()) {
            int size = piece.getCount();
            if (position >= size) {
                if (piece instanceof SectionIndexer) {
                    Object[] sections = ((SectionIndexer) piece).getSections();
                    if (sections != null) {
                        section += sections.length;
                    }
                }
                position -= size;
            } else if (piece instanceof SectionIndexer) {
                return ((SectionIndexer) piece).getSectionForPosition(position) + section;
            } else {
                return 0;
            }
        }
        return 0;
    }

    public Object[] getSections() {
        ArrayList<Object> sections = new ArrayList();
        for (ListAdapter piece : getPieces()) {
            if (piece instanceof SectionIndexer) {
                Object[] curSections = ((SectionIndexer) piece).getSections();
                if (curSections != null) {
                    Collections.addAll(sections, curSections);
                }
            }
        }
        if (sections.size() == 0) {
            return new String[0];
        }
        return sections.toArray(new Object[0]);
    }

    protected List<ListAdapter> getPieces() {
        return this.pieces.getPieces();
    }
}
