package proj.multimedia.twocar.model.objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;

import proj.multimedia.twocar.model.Renderable;
import proj.multimedia.twocar.model.World;

/**
 * Created by KinG on 9/22/2015.
 */
public class ObjectOnLane extends Renderable {

    protected World mWorld;
    protected int mPosition;
    protected float[] POSITION_X;
    protected Bitmap mBm;
    protected int POSITION_Y_TOP;
    protected int POSITION_Y_BOTTOM;
    protected Paint mPaint;

    public ObjectOnLane(Context context, World world) {
        this(context, world, 0, 0);

    }

    public ObjectOnLane(Context context, World world, float x, float y) {
        super(context, x, y);
        mWorld = world;

    }

    protected void delete() {
        mWorld.addToRemoveList(this);
    }

    protected Paint getmPaint() {
        if (mPaint == null) {
            mPaint = new Paint();
        }
        return mPaint;
    }

    protected void loadBitmap(Bitmap bm) {
        mBm = bm;
        if (mBm != null) {
            mRect = new Rect(0, 0, mBm.getWidth(), mBm.getHeight());
            calPostions();
        }

    }

    @Override
    public void update(double timeElapsed) {
        super.update(timeElapsed);
    }

    @Override
    public void draw(Canvas c) {
        if (mBm != null) {
            c.drawBitmap(mBm, mRect.left, mRect.top, getmPaint());
        }
    }

    protected void checkToDelete() {
        if (mRect.top > POSITION_Y_BOTTOM) {
            delete();
        }
    }

    protected void calPostions() {
        if (mBm != null) {
            int bmX = mBm.getWidth() / 2;
            int bmY = mBm.getHeight();

            DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
            float DeviceTotalWidth = metrics.widthPixels;
            float DeviceTotalHeight = metrics.heightPixels;
            float laneHalfW = (DeviceTotalWidth / 4.0f) / 2;
            float startPointX1 = (int) (DeviceTotalWidth / 4.0f);
            float startPointX2 = (int) (DeviceTotalWidth / 2.0f);
            float startPointX3 = (int) (DeviceTotalWidth / 4.0f * 3.0f);

            int top = (int) (DeviceTotalHeight - bmY);
            int bottom = (int) (DeviceTotalHeight);

            POSITION_X = new float[4];
            POSITION_X[0] = startPointX1 - laneHalfW;
            POSITION_X[1] = startPointX2 - laneHalfW;
            POSITION_X[2] = startPointX3 - laneHalfW;
            POSITION_X[3] = startPointX3 + laneHalfW;

            POSITION_Y_TOP = top;
            POSITION_Y_BOTTOM = bottom;
        }
    }

    public void setPosition(int position) {
        //calculate the postion in the screen

        int bmX = mBm.getWidth() / 2;
        int bmY = mBm.getHeight();
        switch (position) {
            case 1:
                mRect = new Rect((int) (POSITION_X[position - 1] - bmX), POSITION_Y_TOP, (int) (POSITION_X[position - 1] + bmX), POSITION_Y_BOTTOM);
                break;
            case 2:
                mRect = new Rect((int) (POSITION_X[position - 1] - bmX), POSITION_Y_TOP, (int) (POSITION_X[position - 1] + bmX), POSITION_Y_TOP);
                break;
            case 3:
                mRect = new Rect((int) (POSITION_X[position - 1] - bmX), POSITION_Y_TOP, (int) (POSITION_X[position - 1] + bmX), POSITION_Y_TOP);
                break;
            case 4:
                mRect = new Rect((int) (POSITION_X[position - 1] - bmX), POSITION_Y_TOP, (int) (POSITION_X[position - 1] + bmX), POSITION_Y_TOP);
                break;
            default:
                break;
        }
        mPosition = position;
    }

}
