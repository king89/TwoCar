package proj.multimedia.twocar.model.objects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import proj.multimedia.twocar.model.Renderable;

/**
 * Created by KinG on 9/22/2015.
 */
public class BackGround extends Renderable {
    public BackGround(Context context) {
        super(context);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.WHITE);
    }

    @Override
    public void draw(Canvas c) {
        c.drawColor(Color.BLACK);
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        float DeviceTotalWidth = metrics.widthPixels;
        float DeviceTotalHeight = metrics.heightPixels;
        float startPointX1 = (int) (DeviceTotalWidth / 4.0f);
        float startPointX2 = (int) (DeviceTotalWidth / 2.0f);
        float startPointX3 = (int) (DeviceTotalWidth / 4.0f * 3.0f);

        c.drawLine(startPointX1, 0, startPointX1, DeviceTotalHeight, mPaint);
        c.drawLine(startPointX3, 0, startPointX3, DeviceTotalHeight, mPaint);
        mPaint.setStrokeWidth(4);
        c.drawLine(startPointX2, 0, startPointX2, DeviceTotalHeight, mPaint);
    }
}
