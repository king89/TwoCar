package proj.multimedia.twocar.scene;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

import proj.multimedia.twocar.model.Renderable;
import proj.multimedia.twocar.model.World;
import proj.multimedia.twocar.model.objects.BackGround;
import proj.multimedia.twocar.model.objects.Car;
import proj.multimedia.twocar.model.objects.Coin;
import proj.multimedia.twocar.model.objects.ObjectOnLane;
import proj.multimedia.twocar.model.objects.Obstacle;
import proj.multimedia.twocar.model.objects.Score;
import proj.multimedia.twocar.util.ResourcesManager;
import proj.multimedia.twocar.util.SettingManager;

/**
 * Created by KinG on 9/22/2015.
 */
public class GameScene extends BaseScene {


    private static final double FPS = 60;
    private static final double MAXTIMEELAPSE = 2;
    private Score mScore;

    private Car mLCar, mRCar;

    private Coin mCoint;

    private double mTimeCountLeft = 0;
    private double mTimeCountRight = 0;
    private int mSpeed = 15;
    private double mIntervalFactor = 3.5;

    private Random mRandom = new Random();

    public GameScene(Context context) {
        super(context);
        ResourcesManager.getInstance().playBackgroundMusic(mContext);
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
        mScore = new Score(mContext, 100, 100, 0);
        mWorld.addRenderableObject(mScore);

        String rCarFile = "gfx/r_car.png";
        String bCarFile = "gfx/b_car.png";
        mLCar = new Car(mContext, ResourcesManager.getInstance().getBitmapFromAsset(mContext, rCarFile), 2);
        mWorld.addRenderableObject(mLCar);

        mRCar = new Car(mContext, ResourcesManager.getInstance().getBitmapFromAsset(mContext, bCarFile), 3);
        mWorld.addRenderableObject(mRCar);
    }

    @Override
    public void render(Canvas c, double timeElapsed) {
        mIsGameOver = checkGameOver();
        if (mIsGameOver) {
            mWorld.draw(c);
            drawGameOver(c);

        } else {
            increaseLevel();
            generateObjects(timeElapsed);
            checkGetScore();
            mWorld.update(timeElapsed);
            mWorld.draw(c);
        }
    }

    private void generateObjects(double timeElapsed) {

        if (mTimeCountLeft * getSpeed() * FPS > mIntervalFactor * mLCar.getHeight()) {
            if (isCreate(mTimeCountLeft)) {
                mTimeCountLeft = 0;
                int tPos = mRandom.nextInt(2) + 1;
                generateCoinOrObstacle(tPos);
            }
        } else {
            mTimeCountLeft += timeElapsed;
        }

//
        if (mTimeCountRight * getSpeed() * FPS > mIntervalFactor * mLCar.getHeight()) {
            if (isCreate(mTimeCountRight)) {
                mTimeCountRight = 0;
                int tPos = mRandom.nextInt(2) + 3;
                generateCoinOrObstacle(tPos);
            }
        } else {
            mTimeCountRight += timeElapsed;
        }
    }

    private void generateCoinOrObstacle(int tPos) {
        String[] circlePath = new String[]{"gfx/r_circle.png", "gfx/r_circle.png", "gfx/b_circle.png", "gfx/b_circle.png"};
        String[] rectPath = new String[]{"gfx/r_rect.png", "gfx/r_rect.png", "gfx/b_rect.png", "gfx/b_rect.png"};
        String useBMP = "";
        //circle
        ObjectOnLane ob;
        if (mRandom.nextInt(2) == 0) {
            useBMP = circlePath[tPos - 1];
            ob = new Coin(mContext, mWorld, tPos, ResourcesManager.getInstance().getBitmapFromAsset(mContext, useBMP));
        } else {
            useBMP = rectPath[tPos - 1];
            ob = new Obstacle(mContext, mWorld, tPos, ResourcesManager.getInstance().getBitmapFromAsset(mContext, useBMP));
        }
        ob.setSpeed(getSpeed());
        mWorld.addRenderableObject(ob);
    }

    private boolean isCreate(double nowTimeElapsed) {
        double v = mRandom.nextDouble();
        if (v > (MAXTIMEELAPSE - nowTimeElapsed) / MAXTIMEELAPSE) {
            return true;
        } else {
            return false;
        }
    }

    private int getSpeed() {
        return mSpeed;
    }

    private void drawGameOver(Canvas c) {
//        Paint p = new Paint();
//        p.setColor(Color.BLUE);
//        p.setTextSize(48);
//        c.drawText("GameOver", 200, 100, p);
    }


    private void increaseLevel() {
        mSpeed = 15 + (int) (mScore.getmScore() / 10);
        mIntervalFactor = Math.max(2, 4 - (mScore.getmScore() / 10));
    }

    private void checkGetScore() {
        int score = mWorld.checkCollideWithCoins(mLCar);
        score += mWorld.checkCollideWithCoins(mRCar);
        mScore.addScore(score);
        if (score > 0) {
            ResourcesManager.getInstance().playCollectedCoinSound(mContext);
        }
    }

    private boolean checkGameOver() {

        if (mWorld.checkCollideWithObstacle(mLCar) > 0 || mWorld.checkCollideWithObstacle(mRCar) > 0) {
            if (!mIsGameOver && getGameState() == GameState.RUNNING) {
                if (mSettingManager.getVibrateState(mContext)) {
                    Vibrator v = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(500);
                }
                ResourcesManager.getInstance().stopBackgroundMusic(mContext);
                setGameState(GameState.GAMEOVER);

                //save high score
                int highScore = SettingManager.getInstance().getHighScore(mContext);
                if (highScore < mScore.getmScore()) {
                    highScore = mScore.getmScore();
                    SettingManager.getInstance().setHighScore(mContext, highScore);
                }
                //save score
                SettingManager.getInstance().setScore(mScore.getmScore());
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getGameState() == GameState.RUNNING) {
            int action = event.getAction();
            int actionCode = action & MotionEvent.ACTION_MASK;
            int pid = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
            int fingerid = event.getPointerId(pid);

            int x = (int) event.getX(pid);
            int y = (int) event.getY(pid);

            switch (actionCode) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    if (mIsGameOver) {
                        //resetGame();
                        mIsGameOver = false;
                    } else {
                        checkCarTurn(x);
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;

                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_OUTSIDE:
                case MotionEvent.ACTION_UP:

                    break;
                default:
            }
        }
        return true; //processed
    }

    @Override
    public void resetGame() {
        createWorld();
        createBackGround();
        createObjcets();
        addObjectsToTheWorld();
        ResourcesManager.getInstance().playBackgroundMusic(mContext);
    }

    public int getScore() {
        return mScore.getmScore();
    }

    private void checkCarTurn(float touched_x) {
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        float DeviceTotalWidth = metrics.widthPixels;
        if (touched_x > DeviceTotalWidth / 2) {
            mRCar.turn();
        } else {
            mLCar.turn();
        }
    }
}
