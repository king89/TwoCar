package proj.multimedia.twocar.activity;


import android.os.Bundle;
import android.view.WindowManager;

import proj.multimedia.twocar.view.BaseGameView;
import proj.multimedia.twocar.util.ResourcesManager;

/**
 * Created by KinG on 9/20/2015.
 */
public class GameActivity extends BaseActivity {
    private static int CAMERA_WIDTH = 800;
    private static int CAMERA_HEIGHT = 480;
    private ResourcesManager mResourcesManager;
    private BaseGameView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mView = new BaseGameView(this);
        setContentView(mView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mView != null) {
            mView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mView != null) {
            mView.resume();
        }
    }


}
