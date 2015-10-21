package proj.multimedia.twocar.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import proj.multimedia.twocar.R;
import proj.multimedia.twocar.util.SettingManager;

/**
 * Created by KinG on 10/17/2015.
 */
public class GameOverMenuFragment extends GamePauseMenuFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_menu_gameover, container, false);
        view = initialView(view);
        tv.setText("Game Over");

        //score textview
        TextView scoreTextView = (TextView)view.findViewById(R.id.scoreTextView);
        StringBuilder sb = new StringBuilder();
        sb.append("High Score: " + SettingManager.getInstance().getHighScore(getActivity()));
        sb.append("\n");
        sb.append("Your Score: " + SettingManager.getInstance().getScore());
        scoreTextView.setText(sb.toString());
        scoreTextView.setVisibility(View.VISIBLE);
        resumeBt.setVisibility(View.GONE);
        return view;
    }
}
