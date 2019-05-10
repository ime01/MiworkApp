package com.example.flowz.miworkapp;

public class word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private int mAudioResourceId;

    private static final int NO_IMAGE_PROVIDED = -1;

    public word (String defaultTranslation, String miwokTranslation, int AudioResourceId){

        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation  = miwokTranslation;
        mAudioResourceId = AudioResourceId;
    }


    public word (String defaultTranslation, String miwokTranslation, int ImageResourceId, int AudioResourceId){

        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation  = miwokTranslation;
        mImageResourceId = ImageResourceId;
        mAudioResourceId = AudioResourceId;

    }


    public  String getmDefaultTranslation (){
        return mDefaultTranslation;
    }

    public  String getmMiwokTranslation (){
        return mMiwokTranslation;
    }
    public int getmImageResourceId (){
        return mImageResourceId;
    }
    public int getmAudioResourceId (){
        return mAudioResourceId;
    }

    public boolean hasImage (){
        return mImageResourceId != NO_IMAGE_PROVIDED;

    }






}
