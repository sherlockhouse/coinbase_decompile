package android.support.transition;

import android.view.View;
import android.view.ViewGroup;

public class Scene {
    private Runnable mExitAction;
    private ViewGroup mSceneRoot;

    public void exit() {
        if (getCurrentScene(this.mSceneRoot) == this && this.mExitAction != null) {
            this.mExitAction.run();
        }
    }

    static void setCurrentScene(View view, Scene scene) {
        view.setTag(R.id.transition_current_scene, scene);
    }

    static Scene getCurrentScene(View view) {
        return (Scene) view.getTag(R.id.transition_current_scene);
    }
}
