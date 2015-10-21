package proj.multimedia.twocar.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import proj.multimedia.twocar.R;

/**
 * Created by KinG on 10/20/2015.
 */
public class SettingManager {
    private static final SettingManager INSTANCE = new SettingManager();

    private SettingManager() {
    }

    public static SettingManager getInstance() {
        return INSTANCE;
    }

    public void setHighScore(Context context, int score) {
        SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = s.edit();
        editor.putInt(context.getString(R.string.pref_key_highscore), score);
        editor.commit();
    }

    public int getHighScore(Context context) {
        SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(context);
        return s.getInt(context.getString(R.string.pref_key_highscore), 0);
    }

    //music state
    public void setMusicState(Context context, boolean b) {
        SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = s.edit();
        editor.putBoolean(context.getString(R.string.pref_key_music_state), b);
        editor.commit();
    }

    public boolean getMusicState(Context context) {
        SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(context);
        return s.getBoolean(context.getString(R.string.pref_key_music_state), true);
    }

    //sound state
    public void setSoundState(Context context, boolean b) {
        SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = s.edit();
        editor.putBoolean(context.getString(R.string.pref_key_sound_state), b);
        editor.commit();
    }

    public boolean getSoundState(Context context) {
        SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(context);
        return s.getBoolean(context.getString(R.string.pref_key_sound_state), true);
    }

    //vibrate state
    public void setVibrateState(Context context, boolean b) {
        SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = s.edit();
        editor.putBoolean(context.getString(R.string.pref_key_vibrate_state), b);
        editor.commit();
    }

    public boolean getVibrateState(Context context) {
        SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(context);
        return s.getBoolean(context.getString(R.string.pref_key_vibrate_state), true);
    }
}
