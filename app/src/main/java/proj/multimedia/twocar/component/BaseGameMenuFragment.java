package proj.multimedia.twocar.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by KinG on 10/17/2015.
 */
public class BaseGameMenuFragment extends Fragment {
    protected GameMenuCallBack menuCallBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    public void setMenuCallBack(GameMenuCallBack callBack){
        menuCallBack = callBack;
    }

    public void resume(){
        if (menuCallBack != null){
            menuCallBack.resume();
        }
    }
    public void restart(){
        if (menuCallBack != null){
            menuCallBack.restart();
        }
    }
    public void home(){
        if (menuCallBack != null){
            menuCallBack.home();
        }
    }
    public interface GameMenuCallBack{
        void resume();
        void restart();
        void home();
    }
}
