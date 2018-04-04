package com.yamini.training;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class ActivityTextToSpeech extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private EditText mEtText;
    private final String TAG="AndroidAPI";
    private TextToSpeech mTextToSpeech;
    private Context mCtx;
    private TextView mTvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_anon);
        Log.i(TAG," onCreate ");

        mEtText= (EditText)findViewById(R.id.id_et_text);
        mCtx = getApplicationContext();
        mTextToSpeech= new TextToSpeech(mCtx,this);
        mTvData=(TextView)findViewById(R.id.id_tv_returned_data);
    }


    public void convertToSpeech(View v){
        Log.i(TAG," convertToSpeech ");

        if(v.getId()==R.id.id_btn_speech){
            String textToPlay= mEtText.getText().toString().trim();
            mTextToSpeech.speak(textToPlay,TextToSpeech.QUEUE_ADD,null);

            /*
            TextToSpeech.QUEUE_FLUSH : Will removed current playing speech
            TextToSpeech.ADD : Will append latest text to  currently playing speech
             */

        } else if(v.getId()==R.id.id_btn_speech_to_text){

            startSpeechToText();
        }


    }




    private void startSpeechToText(){

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT," Yamini, Speak something");
         startActivityForResult(intent,10001);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.v(TAG," onActivityResult requestCode:"+requestCode+" resultCode:"+resultCode+" data :"+data);

       if(10001== requestCode&& resultCode==RESULT_OK){


           ArrayList<String> result = data
                   .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);


           mTvData.setText(" Total retruned Data size "+result.size());

           for(String val : result){
               mTvData.append("\n "+val);
           }







       } else{

           mTvData.setText(" Some Error");
       }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        mTextToSpeech.shutdown();
        super.onDestroy();

    }

    @Override
    public void onInit(int status) {

        Log.i(TAG," Init status :"+status);
        if(TextToSpeech.ERROR != status){
            mTextToSpeech.setLanguage(Locale.ENGLISH);
            mTextToSpeech.setPitch(1.0f);
            mTextToSpeech.setSpeechRate(1.0f);
        }

    }


    @Override
    public void onBackPressed() {


        Log.v(TAG," Back button pressed ");


        return;
    }


}
