package proj.multimedia.twocar.model;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import proj.multimedia.twocar.model.objects.BackGround;
import proj.multimedia.twocar.model.objects.Car;
import proj.multimedia.twocar.model.objects.Coin;
import proj.multimedia.twocar.model.objects.ObjectOnLane;
import proj.multimedia.twocar.model.objects.Obstacle;

/**
 * Created by KinG on 9/22/2015.
 */
public class World {


    protected List<Renderable> mRenderableList;
    protected List<Renderable> mToBeDeletedObjList;
    protected Renderable mBackground;
    protected Context mContext;

    public World(Context context) {
        mContext = context;
        mToBeDeletedObjList = new ArrayList<>();
    }

    public void draw(Canvas c) {
        //Delete objs in to be deleted List
        toRemoveObjInRemoveList();

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
        //Log.i("RenderObj", "Count:" + mRenderableList.size());
    }

    public void update(double timeElapsed){
        update(timeElapsed,-1);
    }
    public void update(double timeElapsed, int speed) {
        //Draw Other objects
        if (mRenderableList != null) {
            for (Renderable r : mRenderableList) {
                if (r instanceof ObjectOnLane && speed > 0)
                {
                    ((ObjectOnLane)r).setSpeed(speed);
                }
                r.update(timeElapsed);
            }
        }
    }

    public void setBackgound(BackGround bg) {
        mBackground = bg;
    }


    public void addRenderableObject(Renderable obj) {
        if (mRenderableList == null) {
            mRenderableList = new ArrayList<>();
        }
        mRenderableList.add(obj);
    }

    public void removerObject(Renderable obj) {
        if (mRenderableList != null) {
            mRenderableList.remove(obj);
        }
    }

    protected void toRemoveObjInRemoveList() {
        for (Renderable ojb : mToBeDeletedObjList) {
            mRenderableList.remove(ojb);
        }
        mToBeDeletedObjList.clear();
    }

    public void addToRemoveList(Renderable obj) {
        mToBeDeletedObjList.add(obj);
    }

    public int checkCollideWithCoins(Car c) {
        int count = 0;
        for (Renderable r : mRenderableList) {
            if (r instanceof Coin) {
                if (r.collideWith(c)) {
                    count++;
                    addToRemoveList(r);
                }
            }
        }
        return count;
    }

    public int checkCollideWithObstacle(Car c) {
        int count = 0;
        for (Renderable r : mRenderableList) {
            if (r instanceof Obstacle) {
                if (r.collideWith(c)) {
                    count++;
                    //addToRemoveList(r);
                }
            }
        }
        return count;
    }
}
