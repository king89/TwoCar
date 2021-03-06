package proj.multimedia.twocar.util;

import android.content.Context;

import proj.multimedia.twocar.scene.BaseScene;
import proj.multimedia.twocar.scene.GameScene;

/**
 * Created by KinG on 9/20/2015.
 */
public class SceneManager {
    private static final SceneManager INSTANCE = new SceneManager();
    private SceneManager(){}
    private SceneType currentSceneType = SceneType.SCENE_GAME;
    private BaseScene currentScene;

    public enum SceneType
    {
        //SCENE_SPLASH,
        SCENE_GAME,
        //SCENE_LOADING,
    }

    public void setScene(BaseScene scene)
    {
        currentScene = scene;
        currentSceneType = scene.getSceneType();
    }

    public static SceneManager getInstance()
    {
        return INSTANCE;
    }

    public SceneType getCurrentSceneType()
    {
        return currentSceneType;
    }

    public BaseScene getCurrentScene()
    {
        return currentScene;
    }

    public BaseScene createGameScene(Context context){
        BaseScene scene = new GameScene(context);
        currentScene = scene;
        currentSceneType = SceneType.SCENE_GAME;
        return scene;
    }

}
