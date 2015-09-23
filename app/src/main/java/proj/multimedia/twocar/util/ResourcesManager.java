package proj.multimedia.twocar.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;


import proj.multimedia.twocar.activity.GameActivity;

/**
 * Created by KinG on 9/20/2015.
 */
public class ResourcesManager {
    private static final ResourcesManager INSTANCE = new ResourcesManager();

    public static ResourcesManager getInstance() {
        return INSTANCE;
    }


    public Bitmap getBitmapFromAsset(Context context, String fileName){
        try {
            Bitmap bm = BitmapFactory.decodeStream(context.getAssets().open(fileName));
            return bm;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
