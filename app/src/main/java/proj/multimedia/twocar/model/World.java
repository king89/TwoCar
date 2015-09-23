package proj.multimedia.twocar.model;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

import proj.multimedia.twocar.model.objects.BackGround;

/**
 * Created by KinG on 9/22/2015.
 */
public class World {


    protected List<Renderable> mRenderableList;
    protected Renderable mBackground;

    protected Context mContext;

    public World(Context context){
        mContext = context;
    }

    public void draw(Canvas c) {
        //Draw Background
        if (mBackground != null) {
            mBackground.draw(c);
        }

        //Draw Other objects
        if (mRenderableList != null) {
            for (Renderable r : mRenderableList) {
                r.draw(c);
            }
        }
    }

    public void update(double timeElapsed) {
        //Draw Other objects
        if (mRenderableList != null) {
            for (Renderable r : mRenderableList) {
                r.update(timeElapsed);
            }
        }
    }

    public void setBackgound(BackGround bg) {
        mBackground = bg;
    }


    public void addRenderableObject(Renderable obj){
        if (mRenderableList == null){
            mRenderableList = new ArrayList<>();
        }
        mRenderableList.add(obj);
    }

    public void removerObject(Renderable obj){
        if (mRenderableList != null){
            mRenderableList.remove(obj);
        }
    }
}
