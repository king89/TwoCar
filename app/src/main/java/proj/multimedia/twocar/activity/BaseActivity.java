package proj.multimedia.twocar.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by KinG on 9/20/2015.
 */
public class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
    }
}
