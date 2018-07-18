package android.support.transition;

import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class TransitionManager {
    private static Transition sDefaultTransition = new AutoTransition();
    private static ArrayList<ViewGroup> sPendingTransitions = new ArrayList();
    private static ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>>> sRunningTransitions = new ThreadLocal();
    private ArrayMap<Scene, ArrayMap<Scene, Transition>> mScenePairTransitions = new ArrayMap();
    private ArrayMap<Scene, Transition> mSceneTransitions = new ArrayMap();

    private static class MultiListener implements OnAttachStateChangeListener, OnPreDrawListener {
        ViewGroup mSceneRoot;
        Transition mTransition;

        MultiListener(Transition transition, ViewGroup sceneRoot) {
            this.mTransition = transition;
            this.mSceneRoot = sceneRoot;
        }

        private void removeListeners() {
            this.mSceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
            this.mSceneRoot.removeOnAttachStateChangeListener(this);
        }

        public void onViewAttachedToWindow(View v) {
        }

        public void onViewDetachedFromWindow(View v) {
            removeListeners();
            TransitionManager.sPendingTransitions.remove(this.mSceneRoot);
            ArrayList<Transition> runningTransitions = (ArrayList) TransitionManager.getRunningTransitions().get(this.mSceneRoot);
            if (runningTransitions != null && runningTransitions.size() > 0) {
                Iterator it = runningTransitions.iterator();
                while (it.hasNext()) {
                    ((Transition) it.next()).resume(this.mSceneRoot);
                }
            }
            this.mTransition.clearValues(true);
        }

        public boolean onPreDraw() {
            removeListeners();
            if (TransitionManager.sPendingTransitions.remove(this.mSceneRoot)) {
                final ArrayMap<ViewGroup, ArrayList<Transition>> runningTransitions = TransitionManager.getRunningTransitions();
                ArrayList<Transition> currentTransitions = (ArrayList) runningTransitions.get(this.mSceneRoot);
                ArrayList<Transition> previousRunningTransitions = null;
                if (currentTransitions == null) {
                    currentTransitions = new ArrayList();
                    runningTransitions.put(this.mSceneRoot, currentTransitions);
                } else if (currentTransitions.size() > 0) {
                    previousRunningTransitions = new ArrayList(currentTransitions);
                }
                currentTransitions.add(this.mTransition);
                this.mTransition.addListener(new TransitionListenerAdapter() {
                    public void onTransitionEnd(Transition transition) {
                        ((ArrayList) runningTransitions.get(MultiListener.this.mSceneRoot)).remove(transition);
                    }
                });
                this.mTransition.captureValues(this.mSceneRoot, false);
                if (previousRunningTransitions != null) {
                    Iterator it = previousRunningTransitions.iterator();
                    while (it.hasNext()) {
                        ((Transition) it.next()).resume(this.mSceneRoot);
                    }
                }
                this.mTransition.playTransition(this.mSceneRoot);
            }
            return true;
        }
    }

    static ArrayMap<ViewGroup, ArrayList<Transition>> getRunningTransitions() {
        WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>> runningTransitions = (WeakReference) sRunningTransitions.get();
        if (runningTransitions == null || runningTransitions.get() == null) {
            runningTransitions = new WeakReference(new ArrayMap());
            sRunningTransitions.set(runningTransitions);
        }
        return (ArrayMap) runningTransitions.get();
    }

    private static void sceneChangeRunTransition(ViewGroup sceneRoot, Transition transition) {
        if (transition != null && sceneRoot != null) {
            MultiListener listener = new MultiListener(transition, sceneRoot);
            sceneRoot.addOnAttachStateChangeListener(listener);
            sceneRoot.getViewTreeObserver().addOnPreDrawListener(listener);
        }
    }

    private static void sceneChangeSetup(ViewGroup sceneRoot, Transition transition) {
        ArrayList<Transition> runningTransitions = (ArrayList) getRunningTransitions().get(sceneRoot);
        if (runningTransitions != null && runningTransitions.size() > 0) {
            Iterator it = runningTransitions.iterator();
            while (it.hasNext()) {
                ((Transition) it.next()).pause(sceneRoot);
            }
        }
        if (transition != null) {
            transition.captureValues(sceneRoot, true);
        }
        Scene previousScene = Scene.getCurrentScene(sceneRoot);
        if (previousScene != null) {
            previousScene.exit();
        }
    }

    public static void beginDelayedTransition(ViewGroup sceneRoot, Transition transition) {
        if (!sPendingTransitions.contains(sceneRoot) && ViewCompat.isLaidOut(sceneRoot)) {
            sPendingTransitions.add(sceneRoot);
            if (transition == null) {
                transition = sDefaultTransition;
            }
            Transition transitionClone = transition.clone();
            sceneChangeSetup(sceneRoot, transitionClone);
            Scene.setCurrentScene(sceneRoot, null);
            sceneChangeRunTransition(sceneRoot, transitionClone);
        }
    }
}
