/*
package com.cc.ui.karaoke.ui.activity.player;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.zhaiyifan.lyric.LyricUtils;
import cn.zhaiyifan.lyric.widget.LyricView;
import mmobile.com.karaoke.R;
import com.cc.ui.karaoke.utils.DebugLog;

public class URLMediaPlayerActivity extends AppCompatActivity {

    public static final String BUNDLE_AUDIO_URL = "BUNDLE_AUDIO_URL";
    public static final String BUNDLE_TITLE_SONG = "BUNDLE_TITLE_SONG";
    public static final String BUNDLE_LYRIC_SONG = "BUNDLE_LYRIC_SONG";
    public static final String BUNDLE_IMG_URL = "BUNDLE_IMG_URL";
    public static final String BUNDLE_LRC_STRING = "BUNDLE_LRC_STRING";
    private static final String TAG = "URLMediaPlayerActivity";
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private LyricView mLyricView;
    private int currentIndex;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // remove title and go full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // get data from main activity intent
        Intent intent = getIntent();
        final String audioFile = intent.getStringExtra(BUNDLE_AUDIO_URL);
        final String coverImage = intent.getStringExtra(BUNDLE_IMG_URL);
        String lyricSong = intent.getStringExtra(BUNDLE_LYRIC_SONG);
        final String titleSong = intent.getStringExtra(BUNDLE_TITLE_SONG);
        String contentLrc = intent.getStringExtra(BUNDLE_LRC_STRING);


        // create a media player
        mediaPlayer = new MediaPlayer();

        // try to load data and play
        try {

            // give data to mediaPlayer
            mediaPlayer.setDataSource(audioFile);
            // media player asynchronous preparation
            mediaPlayer.prepareAsync();

            // create a progress dialog (waiting media player preparation)
            final ProgressDialog dialog = new ProgressDialog(URLMediaPlayerActivity.this);

            // set message of the dialog
            dialog.setMessage(getString(R.string.loading));

            // show dialog at the bottom
            dialog.getWindow().setGravity(Gravity.CENTER);

            // show dialog
            dialog.show();


            // inflate layout
            setContentView(R.layout.activity_media_player);

            // display title
            ((TextView) findViewById(R.id.now_playing_text)).setText(titleSong);
            ImageView imageView =  (ImageView) findViewById(R.id.img_bg);

            // display lyric
            mLyricView = (LyricView) findViewById(R.id.lyric_view);
            mLyricView.setEnabled(false);
             // You can call setLyric anytime to change the lyric to another
            if (TextUtils.isEmpty(contentLrc))
                mLyricView.setText(lyricSong);
            else {
                InputStream inputStream = new ByteArrayInputStream(contentLrc.getBytes("UTF-8"));
                mLyricView.setLyric(LyricUtils.parseLyric(inputStream, "UTF-8"));
                mLyricView.setLyricIndex(0);
            }



            /// Load cover image (we use Picasso Library)

            // Get image view
            ImageView mImageView = (ImageView) findViewById(R.id.coverImage);

            // Image url
            String image_url = coverImage;


            Glide.with(getApplicationContext()).load(image_url).into(mImageView);

            ///


            // execute this code at the end of asynchronous media player preparation
            mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
                public void onPrepared(final MediaPlayer mp) {


                    //start media player
                    mp.start();
                    mLyricView.play();
                    // link seekbar to bar view
                    seekBar = (SeekBar) findViewById(R.id.seekBar);

                    //update seekbar
                    mRunnable.run();

                    //dismiss dialog
                    dialog.dismiss();
                }
            });


        } catch (IOException e) {
            Activity a = this;
            a.finish();
            Toast.makeText(this, getString(R.string.file_not_found), Toast.LENGTH_SHORT).show();
        }


    }


    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {

        @Override
        public void run() {
            if (mediaPlayer != null) {

                //set max value
                int mDuration = mediaPlayer.getDuration();
                seekBar.setMax(mDuration);

                //update total time text view
                TextView totalTime = (TextView) findViewById(R.id.totalTime);
                totalTime.setText(getTimeString(mDuration));

                //set progress to current position
                int mCurrentPosition = mediaPlayer.getCurrentPosition();
                seekBar.setProgress(mCurrentPosition);

                //update current time text view
                TextView currentTime = (TextView) findViewById(R.id.currentTime);
                currentTime.setText(getTimeString(mCurrentPosition));

                //handle drag on seekbar
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (mediaPlayer != null && fromUser) {
                            mediaPlayer.seekTo(progress);
                        }
                    }
                });


            }

            //repeat above code every second
            mHandler.postDelayed(this, 10);
        }
    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void play(View view) {
        mLyricView.play();
        mediaPlayer.start();
    }


    public void pause(View view) {

        mLyricView.stop();
        mediaPlayer.pause();
*/
/*        currentIndex = (int) mLyricView.get;
        mLyricView.setLyricIndex(currentIndex);*//*

        DebugLog.e(TAG, "currentIndex = " + currentIndex);
    }

    public void stop(View view) {

        mediaPlayer.seekTo(0);
        mediaPlayer.pause();

    }


    public void seekForward(View view) {

        //set seek time
        int seekForwardTime = 5000;

        // get current song position
        int currentPosition = mediaPlayer.getCurrentPosition();
        // check if seekForward time is lesser than song duration
        if (currentPosition + seekForwardTime <= mediaPlayer.getDuration()) {
            // forward song
            mediaPlayer.seekTo(currentPosition + seekForwardTime);
        } else {
            // forward to end position
            mediaPlayer.seekTo(mediaPlayer.getDuration());
        }

    }

    public void seekBackward(View view) {

        //set seek time
        int seekBackwardTime = 5000;

        // get current song position
        int currentPosition = mediaPlayer.getCurrentPosition();
        // check if seekBackward time is greater than 0 sec
        if (currentPosition - seekBackwardTime >= 0) {
            // forward song
            mediaPlayer.seekTo(currentPosition - seekBackwardTime);
        } else {
            // backward to starting position
            mediaPlayer.seekTo(0);
        }

    }


    public void onBackPressed() {
        super.onBackPressed();

        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        finish();
    }

    private String getTimeString(long millis) {
        StringBuffer buf = new StringBuffer();

        long hours = millis / (1000 * 60 * 60);
        long minutes = (millis % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = ((millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000;

        buf.append(String.format("%02d", hours))
                .append(":")
                .append(String.format("%02d", minutes))
                .append(":")
                .append(String.format("%02d", seconds));

        return buf.toString();
    }
}*/
