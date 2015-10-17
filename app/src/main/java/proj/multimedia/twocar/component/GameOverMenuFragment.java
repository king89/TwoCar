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

    @Override
    protected View initialView(View view) {
        View v = super.initialView(view);
        resumeBt.setVisibility(View.GONE);
        tv.setText("GameOver");
        return v;
    }
}
