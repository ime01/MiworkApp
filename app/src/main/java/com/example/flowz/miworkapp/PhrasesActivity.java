package com.example.flowz.miworkapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

public class PhrasesActivity extends AppCompatActivity {

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
        words.add(new word("Where are you going","minto wukuss",R.raw.phrase_where_are_you_going));
        words.add(new word("What is your name","tinna oyaase'na", R.raw.phrase_what_is_your_name));
        words.add(new word("My name is....","oyaaset...", R.raw.phrase_my_name_is));
        words.add(new word("How are you feeling?","michaksas?", R.raw.phrase_how_are_you_feeling));
        words.add(new word("I'm feeling good.","kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new word("Are you coming","aanas'aa?",R.raw.phrase_are_you_coming));
        words.add(new word("Yes, I'm coming.","haa' aanam",R.raw.phrase_yes_im_coming));
        words.add(new word("I'm comimg.","aanam",R.raw.phrase_lets_go));
        words.add(new word("Let's go.","yoowutis", R.raw.phrase_lets_go));
        words.add(new word("Come here.","anni' nem", R.raw.phrase_come_here));


        WordAdapter numbers = new WordAdapter(this, words, R.color.category_phrases);
        ListView showNumbers =(ListView) findViewById(R.id.list);
        showNumbers.setAdapter(numbers);

        showNumbers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                word getWordClicked = words.get(i);

                releaseMediaPlayer();


                int result = ManagaePlayingSound.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    playAudio = MediaPlayer.create(PhrasesActivity.this, getWordClicked.getmAudioResourceId());

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
