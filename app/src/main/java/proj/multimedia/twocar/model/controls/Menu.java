package proj.multimedia.twocar.model.controls;

import android.content.Context;
import android.graphics.Canvas;

import java.util.List;

import proj.multimedia.twocar.model.Renderable;
import proj.multimedia.twocar.model.objects.BackGround;

/**
 * Created by KinG on 9/26/2015.
 */
public class Menu extends Renderable{
    protected List<Renderable> mRenderableList;
    protected BackGround mBackGround;
    public Menu(Context context) {
        super(context);
    }

    @Override
    public void draw(Canvas c) {

    }
}
