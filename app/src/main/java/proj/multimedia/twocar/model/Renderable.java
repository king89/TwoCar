package proj.multimedia.twocar.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by KinG on 9/22/2015.
 */
public abstract class Renderable {
    protected float x;
    protected float y;
    protected Rect mRect;
    protected Context mContext;

    public Renderable(Context context){
        mContext = context;
    }
    public abstract void draw(Canvas c);

    public Rect getmRect(){
        return mRect;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
