package proj.multimedia.twocar.model.objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import proj.multimedia.twocar.model.Renderable;
import proj.multimedia.twocar.model.World;

/**
 * Created by KinG on 9/22/2015.
 */
public class Coin extends ObjectOnLane {
    public Coin(Context context, World world, int position, Bitmap bm) {
        super(context, world);
        loadBitmap(bm);
        setPosition(position);
        setToStart();
    }

    private void setToStart() {
        mRect.offsetTo(mRect.left, -mBm.getHeight());
    }


    @Override
    public void draw(Canvas c) {
        c.drawBitmap(mBm, mRect.left, mRect.top, getmPaint());
    }

    @Override
    public void update(double timeElapsed) {
        mRect.offset(0, mSpeed);
        checkToDelete();
    }
}
