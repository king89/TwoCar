package proj.multimedia.twocar.model.objects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import proj.multimedia.twocar.model.Renderable;

/**
 * Created by KinG on 9/22/2015.
 */
public class Score extends Renderable {
    int mScore = 0;

    public Score(Context context, float x, float y, int score) {
        super(context);
        this.x = x;
        this.y = y;
        this.mScore = score;
    }


    public void addScore(int n){
        mScore += n;
    }

    @Override
    public void draw(Canvas c) {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setTextSize(48f);
        p.setColor(Color.RED);
        c.drawText(mScore + "", getX(), getY(), p);
    }
}
