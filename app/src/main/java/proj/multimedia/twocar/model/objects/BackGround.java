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

        Paint p = new Paint();
        p.setStrokeWidth(2);
        p.setColor(Color.WHITE);
        c.drawLine(startPointX1, 0, startPointX1, DeviceTotalHeight, p);
        c.drawLine(startPointX3, 0, startPointX3, DeviceTotalHeight, p);
        p.setStrokeWidth(4);
        c.drawLine(startPointX2, 0, startPointX2, DeviceTotalHeight, p);
    }
}
