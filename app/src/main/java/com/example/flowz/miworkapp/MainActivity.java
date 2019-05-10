package com.example.flowz.miworkapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView Numbers = (TextView) findViewById(R.id.numbers);
        TextView Colours = (TextView)findViewById(R.id.colors) ;
        TextView Phrases = (TextView)findViewById(R.id.phrases) ;
        TextView FamilyMembers = (TextView)findViewById(R.id.family) ;

        Numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent openNumbersActivity = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(openNumbersActivity);


            }
        });


        Colours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent OpenColours = new Intent(MainActivity.this, ColourActivity.class);
                startActivity(OpenColours);
            }
        });



        Phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent OpenPhrases = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(OpenPhrases);
            }
        });

        FamilyMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent OpenFamilyMembers = new Intent(MainActivity.this, FamilyMembersActivity.class);
                startActivity(OpenFamilyMembers);
            }
        });


    }
    
//
//    public void OpenNumbers(View view) {
//        Intent goToNumbers = new Intent(this,NumbersActivity.class);
//        startActivity(goToNumbers);
//    }

//    public void OpenFamily(View view) {
//        Intent goToFamily = new Intent(this,FamilyMembersActivity.class);
//        startActivity(goToFamily);
//    }
//
//    public void OpenPhrases(View view) {
//        Intent goToPhrases = new Intent(this,PhrasesActivity.class);
//        startActivity(goToPhrases);
//    }
////
//    public void OpenColour(View view) {
//        Intent goToColour = new Intent(this,ColourActivity.class);
//        startActivity(goToColour);
//
//    }
}
