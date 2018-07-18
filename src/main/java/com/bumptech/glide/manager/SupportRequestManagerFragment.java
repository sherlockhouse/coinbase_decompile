package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SupportRequestManagerFragment extends Fragment {
    private final HashSet<SupportRequestManagerFragment> childRequestManagerFragments;
    private final ActivityFragmentLifecycle lifecycle;
    private Fragment parentFragmentHint;
    private RequestManager requestManager;
    private final RequestManagerTreeNode requestManagerTreeNode;
    private SupportRequestManagerFragment rootRequestManagerFragment;

    private class SupportFragmentRequestManagerTreeNode implements RequestManagerTreeNode {
        SupportFragmentRequestManagerTreeNode() {
        }

        public Set<RequestManager> getDescendants() {
            Set<SupportRequestManagerFragment> descendantFragments = SupportRequestManagerFragment.this.getDescendantRequestManagerFragments();
            HashSet<RequestManager> descendants = new HashSet(descendantFragments.size());
            for (SupportRequestManagerFragment fragment : descendantFragments) {
                if (fragment.getRequestManager() != null) {
                    descendants.add(fragment.getRequestManager());
                }
            }
            return descendants;
        }

        public String toString() {
            return super.toString() + "{fragment=" + SupportRequestManagerFragment.this + "}";
        }
    }

    public SupportRequestManagerFragment() {
        this(new ActivityFragmentLifecycle());
    }

    @SuppressLint({"ValidFragment"})
    public SupportRequestManagerFragment(ActivityFragmentLifecycle lifecycle) {
        this.requestManagerTreeNode = new SupportFragmentRequestManagerTreeNode();
        this.childRequestManagerFragments = new HashSet();
        this.lifecycle = lifecycle;
    }

    public void setRequestManager(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    ActivityFragmentLifecycle getGlideLifecycle() {
        return this.lifecycle;
    }

    public RequestManager getRequestManager() {
        return this.requestManager;
    }

    public RequestManagerTreeNode getRequestManagerTreeNode() {
        return this.requestManagerTreeNode;
    }

    private void addChildRequestManagerFragment(SupportRequestManagerFragment child) {
        this.childRequestManagerFragments.add(child);
    }

    private void removeChildRequestManagerFragment(SupportRequestManagerFragment child) {
        this.childRequestManagerFragments.remove(child);
    }

    public Set<SupportRequestManagerFragment> getDescendantRequestManagerFragments() {
        if (this.rootRequestManagerFragment == null) {
            return Collections.emptySet();
        }
        if (this.rootRequestManagerFragment == this) {
            return Collections.unmodifiableSet(this.childRequestManagerFragments);
        }
        HashSet<SupportRequestManagerFragment> descendants = new HashSet();
        for (SupportRequestManagerFragment fragment : this.rootRequestManagerFragment.getDescendantRequestManagerFragments()) {
            if (isDescendant(fragment.getParentFragmentUsingHint())) {
                descendants.add(fragment);
            }
        }
        return Collections.unmodifiableSet(descendants);
    }

    void setParentFragmentHint(Fragment parentFragmentHint) {
        this.parentFragmentHint = parentFragmentHint;
        if (parentFragmentHint != null && parentFragmentHint.getActivity() != null) {
            registerFragmentWithRoot(parentFragmentHint.getActivity());
        }
    }

    private Fragment getParentFragmentUsingHint() {
        Fragment fragment = getParentFragment();
        return fragment != null ? fragment : this.parentFragmentHint;
    }

    private boolean isDescendant(Fragment fragment) {
        Fragment root = getParentFragmentUsingHint();
        while (fragment.getParentFragment() != null) {
            if (fragment.getParentFragment() == root) {
                return true;
            }
            fragment = fragment.getParentFragment();
        }
        return false;
    }

    private void registerFragmentWithRoot(FragmentActivity activity) {
        unregisterFragmentWithRoot();
        this.rootRequestManagerFragment = Glide.get(activity).getRequestManagerRetriever().getSupportRequestManagerFragment(activity.getSupportFragmentManager(), null);
        if (this.rootRequestManagerFragment != this) {
            this.rootRequestManagerFragment.addChildRequestManagerFragment(this);
        }
    }

    private void unregisterFragmentWithRoot() {
        if (this.rootRequestManagerFragment != null) {
            this.rootRequestManagerFragment.removeChildRequestManagerFragment(this);
            this.rootRequestManagerFragment = null;
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            registerFragmentWithRoot(getActivity());
        } catch (IllegalStateException e) {
            if (Log.isLoggable("SupportRMFragment", 5)) {
                Log.w("SupportRMFragment", "Unable to register fragment with root", e);
            }
        }
    }

    public void onDetach() {
        super.onDetach();
        this.parentFragmentHint = null;
        unregisterFragmentWithRoot();
    }

    public void onStart() {
        super.onStart();
        this.lifecycle.onStart();
    }

    public void onStop() {
        super.onStop();
        this.lifecycle.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
        this.lifecycle.onDestroy();
        unregisterFragmentWithRoot();
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (this.requestManager != null) {
            this.requestManager.onLowMemory();
        }
    }

    public String toString() {
        return super.toString() + "{parent=" + getParentFragmentUsingHint() + "}";
    }
}
