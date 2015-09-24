package proj.multimedia.twocar.model.objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;

import proj.multimedia.twocar.model.Renderable;

/**
 * Created by KinG on 9/22/2015.
 */
public class Car extends ObjectOnLane {

    private int SPEEDX = 20;
    private int dstPosition;
    private int mSpeed = SPEEDX;

    public Car(Context context, Bitmap bm, int position) {
        super(context, null);
        loadBitmap(bm);
        calPostions();
        if (mBm != null) {
            setPosition(position);
            dstPosition = position;
        }

    }


    public void turn() {
        switch (dstPosition) {
            case 1:
                //setPosition(2);
                dstPosition = 2;
                mSpeed = SPEEDX;
                break;
            case 2:
//                setPosition(1);
                dstPosition = 1;
                mSpeed = -SPEEDX;
                break;
            case 3:
//                setPosition(4);
                dstPosition = 4;
                mSpeed = SPEEDX;
                break;
            case 4:
//                setPosition(3);
                dstPosition = 3;
                mSpeed = -SPEEDX;
                break;
            default:
                break;
        }
        return;
    }

    @Override
    public void update(double timeElapsed) {
        if (!checkReachDestination(dstPosition)) {
            mRect.offset(mSpeed, 0);
        }
    }

    private boolean checkReachDestination(int dstPosition) {
        if (Math.abs(mRect.centerX() - POSITION_X[dstPosition - 1]) <= SPEEDX) {
            mPosition = dstPosition;
            setPosition(mPosition);
            return true;
        } else {
            return false;
        }
    }

    public int getHeight(){
        return mBm.getHeight();
    }
}
