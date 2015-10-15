package proj.multimedia.twocar.model.controls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by KinG on 9/26/2015.
 */
public class ImageButton extends Clickable {
    protected Bitmap mBitmap;

    public ImageButton(Context context, Bitmap bm) {
        super(context);
        mBitmap = bm;
        mRect = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());

    }

    @Override
    public void draw(Canvas c) {
        if (mBitmap != null) {
            Paint p = getPaint();
            c.drawBitmap(mBitmap, mRect.left, mRect.top, p);
        }
    }
}
