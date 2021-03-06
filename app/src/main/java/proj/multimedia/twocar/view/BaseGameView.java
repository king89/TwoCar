package proj.multimedia.twocar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import proj.multimedia.twocar.model.World;
import proj.multimedia.twocar.scene.BaseScene;
import proj.multimedia.twocar.scene.GameScene;
import proj.multimedia.twocar.util.ResourcesManager;
import proj.multimedia.twocar.util.SceneManager;

/**
 * Created by KinG on 9/20/2015.
 */
public class BaseGameView extends SurfaceView implements SurfaceHolder.Callback {

    protected MainThread mThread;
    protected SceneManager mSceneManager;
    protected Handler mGameOvoerHandler;

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

    public void restart() {
        mThread.resetGame();
    }

    public void setGameOvoerHandler(Handler h) {
        mGameOvoerHandler = h;
    }

    class MainThread extends Thread {

        protected SurfaceHolder mSurfaceHolder;
        protected BaseScene mScene;
        protected boolean mQuit;
        protected boolean mPause;

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
            ResourcesManager.getInstance().stopBackgroundMusic(getContext());
            try {
                mThread.join();
            } catch (InterruptedException e) {
                //
            }
        }

        public void pause() {
            mPause = true;
            if (mScene.getGameState() != BaseScene.GameState.GAMEOVER) {
                mScene.setGameState(BaseScene.GameState.PAUSE);
            }
        }

        protected void render(Rect dirty, double timeElapsed) {
            Canvas c = mSurfaceHolder.lockCanvas(null);
            mScene.render(c, timeElapsed);
            mSurfaceHolder.unlockCanvasAndPost(c);
            if (mScene.getGameOver()) {
                gameOver();
            }
        }

        public void gameOver() {
            Message ms = new Message();
            ms.what = 0;
            ms.obj = ((GameScene) mScene).getScore();
            if (mGameOvoerHandler != null) {
                mGameOvoerHandler.sendMessage(ms);
            }
        }

        public void resetGame() {
            mScene = mSceneManager.createGameScene(getContext());
            mPause = false;
            //pause();
        }

        public void unPause() {
            mPause = false;
            if (mScene.getGameState() != BaseScene.GameState.GAMEOVER) {
                mScene.setGameState(BaseScene.GameState.RUNNING);
            }
        }
    }
}
