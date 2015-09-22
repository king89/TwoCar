package proj.multimedia.twocar.scene;

import android.content.Context;
import android.graphics.Canvas;

import proj.multimedia.twocar.model.World;
import proj.multimedia.twocar.model.objects.BackGround;
import proj.multimedia.twocar.model.objects.Score;

/**
 * Created by KinG on 9/22/2015.
 */
public class GameScene extends BaseScene {


    public GameScene(Context context) {
        super(context);
    }

    @Override
    protected void addObjectsToTheWorld() {

    }

    @Override
    protected void createWorld() {
        mWorld = new World(mContext);
    }

    @Override
    protected void createBackGround() {
        mWorld.setBackgound(new BackGround(mContext));
    }

    @Override
    protected void createObjcets() {
        mWorld.addUpdatableObject(new Score(mContext, 100, 100, 1));
    }

    @Override
    public void render(Canvas c, double timeElapsed) {
        mWorld.update(timeElapsed);
        mWorld.draw(c);
    }
}
