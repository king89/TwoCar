package proj.multimedia.twocar.model.controls;

import android.content.Context;
import android.graphics.Canvas;

import proj.multimedia.twocar.model.Renderable;

/**
 * Created by KinG on 9/26/2015.
 */
public abstract class Clickable extends Renderable {
    protected OnClickListener mOnClickListener;

    public Clickable(Context context) {
        super(context);
    }


    public void setOnClickListener(OnClickListener l) {
        mOnClickListener = l;
    }

    public void click(float x, float y) {
        if (mRect != null && mRect.contains((int) x, (int) y)) {
            if (mOnClickListener != null) {
                mOnClickListener.onClick();
            }
        }
    }

    public interface OnClickListener {
        public void onClick();
    }
}
