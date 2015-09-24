package proj.multimedia.twocar.model.objects;

import android.content.Context;
import android.graphics.Bitmap;

import proj.multimedia.twocar.model.World;

/**
 * Created by KinG on 9/24/2015.
 */
public class Obstacle extends Coin {

    public Obstacle(Context context, World world, int position, Bitmap bm) {
        super(context, world, position, bm);
    }

    @Override
    public void update(double timeElapsed) {
        super.update(timeElapsed);
    }
}
