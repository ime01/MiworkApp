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

public class FamilyMembersActivity extends AppCompatActivity {

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
        words.add(new word("father","ebe",R.drawable.family_father,R.raw.family_father));
        words.add(new word("mother","ata",R.drawable.family_mother,R.raw.family_mother));
        words.add(new word("son","angsi",R.drawable.family_son,R.raw.family_son));
        words.add(new word("daughter","tune", R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new word("older brother","taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new word("younger brother","chalitti", R.drawable.family_younger_brother, R.raw.family_younger_sister));
        words.add(new word("older sister","tete",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new word("younger sister","kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new word("grandmother","ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new word("grandfather","paapa",R.drawable.family_grandfather, R.raw.family_grandfather));


        WordAdapter numbers = new WordAdapter(this, words,R.color.category_family);
        ListView showNumbers =(ListView) findViewById(R.id.list);
        showNumbers.setAdapter(numbers);

        showNumbers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                word getWordClicked = words.get(i);

                releaseMediaPlayer();

                int result = ManagaePlayingSound.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    playAudio = MediaPlayer.create(FamilyMembersActivity.this, getWordClicked.getmAudioResourceId());

                    playAudio.start();

                    playAudio.setOnCompletionListener(mCompletionListener);

                }

            }
        });
    }private void releaseMediaPlayer (){
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
