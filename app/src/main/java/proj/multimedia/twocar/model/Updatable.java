package proj.multimedia.twocar.model;

import android.content.Context;
import android.graphics.Canvas;

/**
 * Created by KinG on 9/22/2015.
 */
public abstract class Updatable extends Renderable {
    public Updatable(Context context) {
        super(context);
    }

    public abstract void update(double timeElapsed);
}
