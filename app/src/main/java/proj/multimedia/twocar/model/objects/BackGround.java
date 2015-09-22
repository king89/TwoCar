package proj.multimedia.twocar.model.objects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

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
        c.drawColor(Color.BLUE);
    }
}
