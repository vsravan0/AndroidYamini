package com.yamini.training;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.yamini.training.utils.AppUtils;

public class ActivityAnimation extends AppCompatActivity implements Animation.AnimationListener{

    private Animation mAnimClock, mAnimMyAnim,mAnimBlink;

    ImageView image ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_animation);
         image = (ImageView)findViewById(R.id.imageView);
        mAnimClock = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.clockwise);
        mAnimClock.setDuration(100*1000);
        mAnimMyAnim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.myanimation);
        mAnimBlink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        image.startAnimation(mAnimClock);
        mAnimClock.setAnimationListener(this);
        mAnimBlink.setAnimationListener(this);
        mAnimMyAnim.setAnimationListener(this);




    }



    public void clockwise(View view){

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.myanimation);
        image.startAnimation(animation);
    }

    public void zoom(View view){
        ImageView image = (ImageView)findViewById(R.id.imageView);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.clockwise);
        image.startAnimation(animation1);
    }

    public void fade(View view){
        ImageView image = (ImageView)findViewById(R.id.imageView);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fade);
        image.startAnimation(animation1);
    }

    public void blink(View view){
        ImageView image = (ImageView)findViewById(R.id.imageView);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.blink);
        image.startAnimation(animation1);
    }

    public void move(View view){
        ImageView image = (ImageView)findViewById(R.id.imageView);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        image.startAnimation(animation1);
    }

    public void slide(View view){
        ImageView image = (ImageView)findViewById(R.id.imageView);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
        image.startAnimation(animation1);
    }

    @Override
    public void onAnimationStart(Animation animation) {

        AppUtils.toast(getApplicationContext(), "Animation started ");
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        AppUtils.toast(getApplicationContext(), "Animation end ");
        if(animation==mAnimClock ){
            image.startAnimation(mAnimMyAnim);
        } else if(animation==mAnimMyAnim){
            image.startAnimation(mAnimBlink);
        }


    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
