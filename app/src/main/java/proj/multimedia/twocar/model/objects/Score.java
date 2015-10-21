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
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(48f);
        mPaint.setColor(Color.RED);
    }


    public void addScore(int n) {
        mScore += n;
    }

    public int getmScore() {
        return mScore;
    }

    @Override
    public void draw(Canvas c) {
        c.drawText(mScore + "", getX(), getY(), mPaint);
    }
}
