package proj.multimedia.twocar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import proj.multimedia.twocar.R;
import proj.multimedia.twocar.model.controls.ImageButton;
import proj.multimedia.twocar.util.SettingManager;

public class SettingActivity extends BaseActivity {

    private boolean vibrateChange;
    private boolean soundChange;
    private boolean musicChange;
    private SettingManager mSettingManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mSettingManager = SettingManager.getInstance();
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupNavigationButton();
        configureImageButton();

    }

    private void configureImageButton() {
        final android.widget.ImageButton vibrateButton = (android.widget.ImageButton) findViewById(R.id.vibrate);
        vibrateButton.setBackgroundResource(R.mipmap.vibrate);
        vibrateChange = mSettingManager.getVibrateState(this);
        setVibrateImage(vibrateButton);
        vibrateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrateChange = !vibrateChange;
                setVibrateImage(vibrateButton);
                mSettingManager.setVibrateState(SettingActivity.this, vibrateChange);
            }
        });

        final android.widget.ImageButton soundButton = (android.widget.ImageButton) findViewById(R.id.sound);
        soundButton.setBackgroundResource(R.mipmap.sound);
        soundChange = mSettingManager.getSoundState(this);
        setSoundImage(soundButton);
        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundChange = !soundChange;
                setSoundImage(soundButton);
                mSettingManager.setSoundState(SettingActivity.this, soundChange);
            }
        });

        final android.widget.ImageButton musicButton = (android.widget.ImageButton) findViewById(R.id.music);
        soundButton.setBackgroundResource(R.mipmap.music);
        musicChange = mSettingManager.getMusicState(this);
        setMusicImage(musicButton);
        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicChange = !musicChange;
                setMusicImage(musicButton);
                mSettingManager.setMusicState(SettingActivity.this, musicChange);
            }
        });
    }

    private void setMusicImage(android.widget.ImageButton musicButton) {
        if (musicChange) {
            musicButton.setImageResource(R.mipmap.music);
        } else {
            musicButton.setImageResource(R.mipmap.music_close);
        }
    }

    private void setSoundImage(android.widget.ImageButton soundButton) {
        if (soundChange) {
            soundButton.setImageResource(R.mipmap.sound);
        } else {
            soundButton.setImageResource(R.mipmap.soud_close);
        }
    }

    private void setVibrateImage(android.widget.ImageButton vibrateButton) {
        if (vibrateChange) {
            vibrateButton.setImageResource(R.mipmap.vibrate);
        } else {
            vibrateButton.setImageResource(R.mipmap.vibrate_close);
        }
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
