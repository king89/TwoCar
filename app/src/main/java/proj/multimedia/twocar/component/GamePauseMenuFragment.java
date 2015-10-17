package proj.multimedia.twocar.component;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import proj.multimedia.twocar.R;

/**
 * Created by KinG on 10/17/2015.
 */
public class GamePauseMenuFragment extends BaseGameMenuFragment {
    protected TextView tv;
    protected ImageButton resumeBt,restartBt,homeBt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_menu_gameover, container, false);
        initialView(view);
        return view;
    }

    protected View initialView(View view){
        tv = (TextView) view.findViewById(R.id.text);
        tv.setText("Pause");
        resumeBt = (ImageButton)view.findViewById(R.id.resumeButton);
        resumeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resume();
            }
        });

        restartBt = (ImageButton)view.findViewById(R.id.restartButton);
        restartBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart();
                Log.i("Click","restart");
            }
        });

        homeBt = (ImageButton)view.findViewById(R.id.homeButton);
        homeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();
            }
        });

        return view;
    }
}
