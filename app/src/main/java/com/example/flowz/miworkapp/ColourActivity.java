package com.example.flowz.miworkapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

public class ColourActivity extends AppCompatActivity {

    MediaPlayer playAudio;

    AudioManager ManagaePlayingSound;

    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {

        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT||
                    focusChange ==AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                playAudio.pause();
                playAudio.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                playAudio.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
                // Stop playback
            }
        };
    };


    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ManagaePlayingSound = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

       final ArrayList<word> words = new ArrayList<word>();

//        words.add("One");
//        word w = new word("one","lutti");
        words.add(new word("red","wetetti",R.drawable.color_red, R.raw.color_red));
        words.add(new word("mustard yellow","chiwiita",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        words.add(new word("dusty yellow","topoiisa", R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new word("green","chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new word("brown","takaakki", R.drawable.color_brown,R.raw.color_brown));
        words.add(new word("gray","topoppi", R.drawable.color_gray,R.raw.color_gray));
        words.add(new word("black","kululli", R.drawable.color_black,R.raw.color_black));
        words.add(new word("white","kelelli", R.drawable.color_white, R.raw.color_white));
//        words.add(new word("nine","wo e"));
//        words.add(new word("ten","na'aacha"));

//


        WordAdapter numbers = new WordAdapter(this, words,R.color.category_colors);
        ListView showNumbers =(ListView) findViewById(R.id.list);
        showNumbers.setAdapter(numbers);

        showNumbers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                word getWordClicked = words.get(i);

                releaseMediaPlayer();

                int result = ManagaePlayingSound.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//                   ManagaePlayingSound.registerMediaButtonEventReceiver(RemoteControlClient);

//                   We have Audio focus now so we call music to start

                    playAudio = MediaPlayer.create(ColourActivity.this, getWordClicked.getmAudioResourceId());

                    playAudio.start();

                    playAudio.setOnCompletionListener(mCompletionListener);
                }

            }
        });

    } private void releaseMediaPlayer (){
        if (playAudio != null){
            playAudio.release();

            playAudio = null;
            ManagaePlayingSound.abandonAudioFocus(afChangeListener);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaPlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
