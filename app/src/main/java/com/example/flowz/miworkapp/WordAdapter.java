package com.example.flowz.miworkapp;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<word> {


    private int mColorResourceId;

    public WordAdapter (Activity context, ArrayList<word>words, int ColorResourceId){

        super(context,0,words);
        mColorResourceId = ColorResourceId;
    }




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View ListItemView = convertView;
        if ( ListItemView == null) {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        }
        word currentWord = getItem(position);

        TextView miwokTextView = (TextView) ListItemView.findViewById(R.id.miwok_text_view);

       miwokTextView.setText(currentWord.getmMiwokTranslation());

       TextView defaultTextView = (TextView)ListItemView.findViewById(R.id.default_text_view);

       defaultTextView.setText(currentWord.getmDefaultTranslation());




        ImageView ImageDescription = (ImageView)ListItemView.findViewById(R.id.UsedImage);
//setimage to appear if there is one
        if (currentWord.hasImage()){
        ImageDescription.setImageResource(currentWord.getmImageResourceId());
        ImageDescription.setVisibility(View.VISIBLE);
        }
        else {
//            set image view to dissappear if there is no image with the word
            ImageDescription.setVisibility(View.GONE);
        }
        View textContainer = ListItemView.findViewById(R.id.NestedLinearLayout);
        int color = ContextCompat.getColor(getContext(),mColorResourceId);
        textContainer.setBackgroundColor(color);


       return ListItemView;




    }
}
