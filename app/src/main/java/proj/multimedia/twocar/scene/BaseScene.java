package proj.multimedia.twocar.scene;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import proj.multimedia.twocar.model.World;
import proj.multimedia.twocar.util.ResourcesManager;
import proj.multimedia.twocar.util.SceneManager;

/**
 * Created by KinG on 9/22/2015.
 */
public abstract class BaseScene {
    protected World mWorld;
    protected SceneManager.SceneType mSceneType;
    protected Context mContext;
    protected boolean mIsGameOver;
    protected GameState mGameState;

    public BaseScene(Context context) {
        mContext = context;
        setGameState(GameState.RUNNING);
        createWorld();
        createBackGround();
        createObjcets();
        addObjectsToTheWorld();
    }

    public void setGameState(GameState s){
        mGameState = s;
    }

    public GameState getGameState(){
        return mGameState;
    }
    public enum GameState{
        RUNNING,
        PAUSE,
        GAMEOVER,
    }

    public void resetGame(){}
    public boolean getGameOver(){
        return mIsGameOver;
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

    public boolean onTouchEvent(MotionEvent event){return  false;};
}
