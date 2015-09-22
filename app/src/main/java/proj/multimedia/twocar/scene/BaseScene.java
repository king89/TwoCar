package proj.multimedia.twocar.scene;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

import proj.multimedia.twocar.model.World;
import proj.multimedia.twocar.util.SceneManager;

/**
 * Created by KinG on 9/22/2015.
 */
public abstract class BaseScene {
    protected World mWorld;
    protected SceneManager.SceneType mSceneType;
    protected Context mContext;
    public BaseScene(Context context) {
        mContext = context;
        createWorld();
        createBackGround();
        createObjcets();
        addObjectsToTheWorld();
    }

    protected abstract void addObjectsToTheWorld();

    protected abstract void createWorld();

    protected abstract void createBackGround();

    protected abstract void createObjcets();

    public SceneManager.SceneType getSceneType() {
        return mSceneType;
    }

    public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    public abstract void render(Canvas c, double timeElapsed);
}
