package proj.multimedia.twocar.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.provider.MediaStore;


import proj.multimedia.twocar.R;
import proj.multimedia.twocar.activity.GameActivity;

/**
 * Created by KinG on 9/20/2015.
 */
public class ResourcesManager {
    private static final ResourcesManager INSTANCE = new ResourcesManager();

    private SoundPool mAudioSoundPool;
    private int soundId;
    private boolean isSoundLoaded;
    private MediaPlayer mMediaPlayer;

    private ResourcesManager() {
    }

    public static ResourcesManager getInstance() {
        return INSTANCE;
    }


    public Bitmap getBitmapFromAsset(Context context, String fileName) {
        try {
            Bitmap bm = BitmapFactory.decodeStream(context.getAssets().open(fileName));
            return bm;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected MediaPlayer getMediaPlayer(Context context) {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(context, R.raw.bgm);
            mMediaPlayer.setLooping(true);
        }
        return mMediaPlayer;
    }

    protected SoundPool getSoundPool(Context context) {
        if (mAudioSoundPool == null) {
            mAudioSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 100);
            soundId = getSoundPool(context).load(context, R.raw.coin, 1);
            mAudioSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    isSoundLoaded = true;
                }
            });
        }
        return mAudioSoundPool;
    }

    public void playCollectedCoinSound(Context context) {
        getSoundPool(context);
        if (isSoundLoaded) {
            float lVolunm = 1;
            float rVolunm = 1;
            getSoundPool(context).play(soundId, lVolunm, rVolunm, 1, 0, 1);
        }
    }

    public void playBackgroundMusic(Context context) {
        MediaPlayer mp = getMediaPlayer(context);
        if (!mp.isPlaying()) {
            mp.start();
        }
    }

    public void stopBackgroundMusic(Context context) {
        MediaPlayer mp = getMediaPlayer(context);
        if (mp.isPlaying()) {
            mp.pause();
            mp.seekTo(0);
        }
    }

    public void releaseBackgroundMusic(Context context){
        mMediaPlayer.release();
        mMediaPlayer = null;
    }
}
