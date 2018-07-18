package com.bumptech.glide.request.transition;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.request.transition.Transition.ViewAdapter;

public class NoTransition<R> implements Transition<R> {
    static final NoTransition<?> NO_ANIMATION = new NoTransition();
    private static final TransitionFactory<?> NO_ANIMATION_FACTORY = new NoAnimationFactory();

    public static class NoAnimationFactory<R> implements TransitionFactory<R> {
        public Transition<R> build(DataSource dataSource, boolean isFirstResource) {
            return NoTransition.NO_ANIMATION;
        }
    }

    public static <R> TransitionFactory<R> getFactory() {
        return NO_ANIMATION_FACTORY;
    }

    public boolean transition(Object current, ViewAdapter adapter) {
        return false;
    }
}
