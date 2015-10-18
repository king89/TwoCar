package proj.multimedia.twocar.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import proj.multimedia.twocar.R;

/**
 * Created by KinG on 10/17/2015.
 */
public class GameOverMenuFragment extends GamePauseMenuFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_menu_gameover, container, false);
        view = initialView(view);
        tv.setText("GameOver");
        resumeBt.setVisibility(View.GONE);
        return view;
    }
}
