package proj.multimedia.twocar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import proj.multimedia.twocar.model.World;
import proj.multimedia.twocar.scene.BaseScene;
import proj.multimedia.twocar.util.SceneManager;

/**
 * Created by KinG on 9/20/2015.
 */
public class BaseGameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread mThread;
    private SceneManager mSceneManager;

    public BaseGameView(Context context) {
        super(context);
        setKeepScreenOn(true);
        getHolder().addCallback(this);
        mSceneManager = SceneManager.getInstance();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mThread = new MainThread(holder, mSceneManager.createGameScene(getContext()));
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mThread.quit();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        BaseScene scene = mSceneManager.getCurrentScene();
        if (scene != null) {
            return scene.onTouchEvent(event);
        } else {
            return super.onTouchEvent(event);
        }
    }

    public void pause() {
        mThread.pause();
    }

    public void resume() {
        if (mThread != null && mThread.isAlive()) {
            mThread.unPause();
        }
    }

    class MainThread extends Thread {

        private SurfaceHolder mSurfaceHolder;
        private BaseScene mScene;
        private boolean mQuit;
        private boolean mPause;

        public MainThread(SurfaceHolder holder, BaseScene scene) {
            mSurfaceHolder = holder;
            mScene = scene;
        }

        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            mScene.onSurfaceChanged(holder, format, width, height);
        }

        @Override
        public void run() {
            mQuit = false;
            double dt = 1 / 60.0; // upper-bound 60fps
            double currentTime = SystemClock.elapsedRealtime();
            while (!mQuit) {
                double newTime = SystemClock.elapsedRealtime();
                double frameTime = (newTime - currentTime) / 1000.0f;
                currentTime = newTime;

                while (frameTime > 0.0) {
                    double deltaTime = Math.min(frameTime, dt);
                    frameTime -= deltaTime;
                    if (!mPause) {
                        render(null, deltaTime);
                    }
                }

            }

        }

        public void quit() {
            mQuit = true;
            try {
                mThread.join();
            } catch (InterruptedException e) {
                //
            }
        }

        public void pause() {
            mPause = true;
        }

        private void render(Rect dirty, double timeElapsed) {
            Canvas c = mSurfaceHolder.lockCanvas(null);
            mScene.render(c, timeElapsed);
            mSurfaceHolder.unlockCanvasAndPost(c);
        }

        public void unPause() {
            mPause = false;
        }
    }
}
