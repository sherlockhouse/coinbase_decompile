package com.bluelinelabs.conductor;

import android.os.Bundle;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

class Backstack implements Iterable<RouterTransaction> {
    private final Deque<RouterTransaction> backstack = new ArrayDeque();

    Backstack() {
    }

    boolean isEmpty() {
        return this.backstack.isEmpty();
    }

    int size() {
        return this.backstack.size();
    }

    RouterTransaction root() {
        return this.backstack.size() > 0 ? (RouterTransaction) this.backstack.getLast() : null;
    }

    public Iterator<RouterTransaction> iterator() {
        return this.backstack.iterator();
    }

    Iterator<RouterTransaction> reverseIterator() {
        return this.backstack.descendingIterator();
    }

    RouterTransaction pop() {
        RouterTransaction popped = (RouterTransaction) this.backstack.pop();
        popped.controller.destroy();
        return popped;
    }

    RouterTransaction peek() {
        return (RouterTransaction) this.backstack.peek();
    }

    void remove(RouterTransaction transaction) {
        this.backstack.removeFirstOccurrence(transaction);
    }

    void push(RouterTransaction transaction) {
        this.backstack.push(transaction);
    }

    List<RouterTransaction> popAll() {
        List<RouterTransaction> list = new ArrayList();
        while (!isEmpty()) {
            list.add(pop());
        }
        return list;
    }

    void setBackstack(List<RouterTransaction> backstack) {
        for (RouterTransaction existingTransaction : this.backstack) {
            boolean contains = false;
            for (RouterTransaction newTransaction : backstack) {
                if (existingTransaction.controller == newTransaction.controller) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                existingTransaction.controller.destroy();
            }
        }
        this.backstack.clear();
        for (RouterTransaction transaction : backstack) {
            this.backstack.push(transaction);
        }
    }

    boolean contains(RouterTransaction transaction) {
        return this.backstack.contains(transaction);
    }

    void saveInstanceState(Bundle outState) {
        ArrayList<Bundle> entryBundles = new ArrayList(this.backstack.size());
        for (RouterTransaction entry : this.backstack) {
            entryBundles.add(entry.saveInstanceState());
        }
        outState.putParcelableArrayList("Backstack.entries", entryBundles);
    }

    void restoreInstanceState(Bundle savedInstanceState) {
        ArrayList<Bundle> entryBundles = savedInstanceState.getParcelableArrayList("Backstack.entries");
        if (entryBundles != null) {
            Collections.reverse(entryBundles);
            Iterator it = entryBundles.iterator();
            while (it.hasNext()) {
                this.backstack.push(new RouterTransaction((Bundle) it.next()));
            }
        }
    }
}
