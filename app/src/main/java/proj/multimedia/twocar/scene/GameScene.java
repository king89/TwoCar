package proj.multimedia.twocar.scene;

import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import proj.multimedia.twocar.model.World;
import proj.multimedia.twocar.model.objects.BackGround;
import proj.multimedia.twocar.model.objects.Car;
import proj.multimedia.twocar.model.objects.Score;
import proj.multimedia.twocar.util.ResourcesManager;

/**
 * Created by KinG on 9/22/2015.
 */
public class GameScene extends BaseScene {


    private Score mScore;
    private boolean mIsGameOver;
    private Car mLCar, mRCar;

    private int firstPointerId = -1;
    private int secondPointerId = -1;

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
        mScore = new Score(mContext, 100, 100, 1);
        mWorld.addRenderableObject(mScore);

        String rCarFile = "gfx/r_car.png";
        String bCarFile = "gfx/b_car.png";
        mLCar = new Car(mContext,ResourcesManager.getInstance().getBitmapFromAsset(mContext,rCarFile), 2);
        mWorld.addRenderableObject(mLCar);

        mRCar = new Car(mContext,ResourcesManager.getInstance().getBitmapFromAsset(mContext,bCarFile), 3);
        mWorld.addRenderableObject(mRCar);
    }

    @Override
    public void render(Canvas c, double timeElapsed) {
        increaseLevel();
        mWorld.update(timeElapsed);
        checkGameOver();
        if (mIsGameOver) {
            drawGameOver(c);
        } else {
            checkGetScore();
            mWorld.draw(c);
        }
    }

    private void drawGameOver(Canvas c) {

    }

    private void increaseLevel() {

    }

    private void checkGetScore() {

    }

    private void checkGameOver() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        int actionCode = action & MotionEvent.ACTION_MASK;
        int pid = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
        int fingerid = event.getPointerId(pid);

        int x = (int) event.getX(pid);
        int y = (int) event.getY(pid);

        switch (actionCode) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                checkCarTurn(x);
                break;
            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_UP:

                //mScore.addScore(1);
                break;
            default:
        }
        return true; //processed
    }

    private void checkCarTurn(float touched_x) {
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        float DeviceTotalWidth = metrics.widthPixels;
        if (touched_x > DeviceTotalWidth / 2){
            mRCar.turn();
        }else {
            mLCar.turn();
        }
    }
}
