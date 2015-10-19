package proj.multimedia.twocar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import proj.multimedia.twocar.R;
import proj.multimedia.twocar.model.controls.ImageButton;

public class SettingActivity extends BaseActivity {

    private boolean vibrateChange;
    private boolean soundChange;
    private boolean musicChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupNavigationButton();
        configureImageButton();
    }

    private void configureImageButton() {
        final android.widget.ImageButton vibrateButton = (android.widget.ImageButton) findViewById(R.id.vibrate);
        vibrateButton.setBackgroundResource(R.mipmap.vibrate);
        vibrateChange = false;
        vibrateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vibrateChange) {
                    vibrateButton.setImageResource(R.mipmap.vibrate);
                    vibrateChange = false;
                } else {
                    vibrateButton.setImageResource(R.mipmap.vibrate_close);
                    vibrateChange = true;
                }
            }
        });

        final android.widget.ImageButton soundButton =(android.widget.ImageButton) findViewById(R.id.sound);
        soundButton.setBackgroundResource(R.mipmap.sound);
        soundChange = false;
        soundButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (soundChange) {
                    soundButton.setImageResource(R.mipmap.sound);
                    soundChange = false;
                } else {
                    soundButton.setImageResource(R.mipmap.soud_close);
                    soundChange = true;
                }
            }
        });

        final android.widget.ImageButton musicButton =(android.widget.ImageButton) findViewById(R.id.music);
        soundButton.setBackgroundResource(R.mipmap.music);
        musicChange = false;
        musicButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (musicChange) {
                    musicButton.setImageResource(R.mipmap.music);
                    musicChange = false;
                } else {
                    musicButton.setImageResource(R.mipmap.music_close);
                    musicChange = true;
                }
            }
        });
    }

    private void setupNavigationButton() {
        android.widget.ImageButton okButton = (android.widget.ImageButton) findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (android.R.id.home == id) {
            Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
