package proj.multimedia.twocar.activity;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import proj.multimedia.twocar.R;
import proj.multimedia.twocar.component.BaseGameMenuFragment;
import proj.multimedia.twocar.component.GameOverMenuFragment;
import proj.multimedia.twocar.component.GamePauseMenuFragment;
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
    private Button bt, resumeBt, backHomeBt;
    private BaseGameMenuFragment currFrame;
    private FrameLayout menuLayout;


    private Handler mGameOverHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FragmentManager fm = getSupportFragmentManager();
            currFrame = new GameOverMenuFragment();
            currFrame.setMenuCallBack(gameMenuCallBack);
            fm.beginTransaction().replace(R.id.menuContainer, currFrame).commit();
            //menuLayout.setVisibility(View.VISIBLE);
            bt.performClick();
        }
    };

    private BaseGameMenuFragment.GameMenuCallBack gameMenuCallBack = new BaseGameMenuFragment.GameMenuCallBack() {
        @Override
        public void resume() {
            mView.resume();
            menuLayout.setVisibility(View.INVISIBLE);
        }

        @Override
        public void restart() {
            mView.restart();
            menuLayout.setVisibility(View.INVISIBLE);
        }

        @Override
        public void home() {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        mView = new BaseGameView(this);
        mView.setGameOvoerHandler(mGameOverHandler);
        setContentView(R.layout.activity_game);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.container);
        menuLayout = (FrameLayout) findViewById(R.id.menuContainer);
        menuLayout.setVisibility(View.INVISIBLE);

        rl.addView(mView);

        bt = (Button) findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.pause();
                menuLayout.setVisibility(View.VISIBLE);
            }
        });

//        resumeBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mView.resume();
//                menuLayout.setVisibility(View.INVISIBLE);
//            }
//        });
//
//        backHomeBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mView.restart();
//                menuLayout.setVisibility(View.INVISIBLE);
//            }
//        });
        //menuLayout.removeAllViews();
        FragmentManager fm = getSupportFragmentManager();
        currFrame = new GamePauseMenuFragment();
        currFrame.setMenuCallBack(gameMenuCallBack);
        fm.beginTransaction().replace(R.id.menuContainer,currFrame ).commit();
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
