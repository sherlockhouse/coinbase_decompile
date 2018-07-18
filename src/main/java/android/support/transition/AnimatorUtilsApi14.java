package android.support.transition;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import java.util.ArrayList;

class AnimatorUtilsApi14 implements AnimatorUtilsImpl {

    interface AnimatorPauseListenerCompat {
        void onAnimationPause(Animator animator);

        void onAnimationResume(Animator animator);
    }

    AnimatorUtilsApi14() {
    }

    public void addPauseListener(Animator animator, AnimatorListenerAdapter listener) {
    }

    public void pause(Animator animator) {
        ArrayList<AnimatorListener> listeners = animator.getListeners();
        if (listeners != null) {
            int size = listeners.size();
            for (int i = 0; i < size; i++) {
                AnimatorListener listener = (AnimatorListener) listeners.get(i);
                if (listener instanceof AnimatorPauseListenerCompat) {
                    ((AnimatorPauseListenerCompat) listener).onAnimationPause(animator);
                }
            }
        }
    }

    public void resume(Animator animator) {
        ArrayList<AnimatorListener> listeners = animator.getListeners();
        if (listeners != null) {
            int size = listeners.size();
            for (int i = 0; i < size; i++) {
                AnimatorListener listener = (AnimatorListener) listeners.get(i);
                if (listener instanceof AnimatorPauseListenerCompat) {
                    ((AnimatorPauseListenerCompat) listener).onAnimationResume(animator);
                }
            }
        }
    }
}
