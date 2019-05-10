package com.example.flowz.miworkapp;

import android.content.Context;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RemoteControlClient;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;




public class NumbersActivity extends AppCompatActivity {

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
        words.add(new word("one", "lutti", R.drawable.number_one,R.raw.number_one));
        words.add(new word("two", "otiito", R.drawable.number_two,R.raw.number_two));
        words.add(new word("three", "tolookosu", R.drawable.number_three,R.raw.number_three));
        words.add(new word("four", "oyyisa", R.drawable.number_four,R.raw.number_four));
        words.add(new word("five", "massokka", R.drawable.number_five,R.raw.number_five));
        words.add(new word("six", "temmokaku", R.drawable.number_six, R.raw.number_six));
        words.add(new word("seven", "kenekaku", R.drawable.number_seven,R.raw.number_seven));
        words.add(new word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new word("nine", "wo e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new word("ten", "na'aacha", R.drawable.number_ten,R.raw.number_ten));


        WordAdapter numbers = new WordAdapter(this, words, R.color.category_numbers);
        ListView showNumbers = (ListView) findViewById(R.id.list);
        showNumbers.setAdapter(numbers);


        showNumbers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               word getWordClicked = words.get(i);

               releaseMediaPlayer();

//               Request Audio focus for playback
               int result = ManagaePlayingSound.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

               if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
//                   ManagaePlayingSound.registerMediaButtonEventReceiver(RemoteControlClient);

//                   We have Audio focus now so we call music to start
                playAudio = MediaPlayer.create(NumbersActivity.this, getWordClicked.getmAudioResourceId());

                playAudio.start();

                playAudio.setOnCompletionListener(mCompletionListener);

               }

            }
        });








//        LinearLayout RootView = (LinearLayout)findViewById(R.id.rootview);
//
////        int index = 0;
//
//        for ( int index = 0; index<words.size(); index++ )
//        {
//            TextView Numbers = new TextView(this);
//            Numbers.setText(words.get(index));
//            RootView.addView(Numbers);
//        }




//        while (index<words.size()){
//
//            TextView Numbers = new TextView(this);
//            Numbers.setText(words.get(index));
//            RootView.addView(Numbers);
//            index++;
//        }

//        TextView Numbers = new TextView(this);
//        Numbers.setText(words.get(index));
//        RootView.addView(Numbers);
//
//        index = index+1;
//
//        TextView Numbers1 = new TextView(this);
//        Numbers1.setText(words.get(index));
//        RootView.addView(Numbers1);
//
//        index = index+1;
//
//        TextView Numbers2 = new TextView(this);
//        Numbers2.setText(words.get(index));
//        RootView.addView(Numbers2);

        


//        String [] words = new String[10];
//        words[0] = "One";
//        words[1] = "Two";
//        words[2] = "Three";
//        words[3] = "Four";
//        words[4] = "Five";
//        words[5] = "Six";
//        words[6] = "Seven";
//        words[7] = "Eight";
//        words[8] = "Nine";
//        words[9] = "Ten";
//
//        Log.v("NumbersActivity", "words at index 0:" + words.get(0));
//        Log.v("NumbersActivity", "words at index 1:" + words.get(1));
//        Log.v("NumbersActivity", "words at index 2:" + words.get(2));
//        Log.v("NumbersActivity", "words at index 3:" + words.get(3));
//        Log.v("NumbersActivity", "words at index 4:" + words.get(4));
//        Log.v("NumbersActivity", "words at index 5:" + words.get(5));
//        Log.v("NumbersActivity", "words at index 6:" + words.get(6));
//        Log.v("NumbersActivity", "words at index 7:" + words.get(7));
//        Log.v("NumbersActivity", "words at index 8:" + words.get(8));
//        Log.v("NumbersActivity", "words at index 9:" + words.get(9));

//        String [] words = {"One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten"};
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
