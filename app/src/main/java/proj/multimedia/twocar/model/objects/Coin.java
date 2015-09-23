package proj.multimedia.twocar.model.objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import proj.multimedia.twocar.model.Renderable;

/**
 * Created by KinG on 9/22/2015.
 */
public class Coin extends ObjectOnLane {
    private Bitmap mBitmap;
    public Coin(Context context, float x, float y) {
        super(context, x, y);
    }

    @Override
    public void draw(Canvas c) {

    }
}
