package com.semicolon.emcmisir.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;

import com.daimajia.androidanimations.library.Techniques;
import com.semicolon.emcmisir.R;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.listeners.ActionClickListener;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.model.ConfigSplash;

//import com.daimajia.androidanimations.library.Techniques;
/*
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.model.ConfigSplash;
*/


public class Splach extends AwesomeSplash {
    public boolean isFirstStart;

    //DO NOT OVERRIDE onCreate()!
    //if you need to start some services do it in initSplash()!
    @Override
    public void initSplash(ConfigSplash configSplash) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Intro App Initialize SharedPreferences
                SharedPreferences getSharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());

                //  Create a new boolean and preference and set it to true
                isFirstStart = getSharedPreferences.getBoolean("firstStart", true);

                //  Check either activity or app is open very first time or not and do action
                if (isFirstStart) {

                    //  Launch application introduction screen
                    Intent i = new Intent(Splach.this, MyIntro.class);
                    startActivity(i);
                    SharedPreferences.Editor e = getSharedPreferences.edit();
                    e.putBoolean("firstStart", false);
                    e.apply();
                }
            }
        });
        t.start();
   /* you don't have to override every property */

        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.sp); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        // configSplash.setRevealFlagX(MediaBrowserCompat.MediaItem.Flags.REVEAL_LEFT);  //or Flags.REVEAL_LEFT
        // configSplash.setRevealFlagY(MediaBrowserCompat.MediaItem.Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.semi); //or any other drawable
        configSplash.setAnimLogoSplashDuration(2000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce);
        //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)
          //Customize Path
        //  configSplash.setPathSplash(Constants.lo); //set path String
        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(3000);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.sp); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(3000);
        configSplash.setPathSplashFillColor(R.color.sp); //path object filling color


        //Customize Title
     /*   configSplash.setTitleSplash("  semicolonsoft               " +
                "للحلول البرمجية والتقنية\n");
        configSplash.setTitleTextColor(R.color.colorAccent);
         configSplash.setTitleTextSize(22f); //float value
          configSplash.setAnimTitleDuration(3000);
         configSplash.setAnimTitleTechnique(Techniques.FlipInX);
*/
        //  configSplash.setTitleFont("fonts/myfont.ttf"); //provide string to your font located in assets/fonts/

    }

    @Override
    public void animationsFinished() {
        boolean isConnected = Network_aviliable();
        if (isConnected == true)
        {
            Intent i =new Intent(this,Category.class);
            startActivity(i);
        }
        else
            {
                SnackbarManager.show(com.nispok.snackbar.Snackbar.with(this)
                .actionLabel("Undo")
                .actionColor(ContextCompat.getColor(this,R.color.colorPrimary))
                .text("تحقق من الاتصال بالانترنت")
                .actionListener(new ActionClickListener() {
                    @Override
                    public void onActionClicked(Snackbar snackbar) {
                        finish();
                    }
                })
                 .color(ContextCompat.getColor(this,R.color.yellow))
                 .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                 .animation(false)

                );
            }

        //transit to another activity here
        //or do whatever you want
    }
    private boolean Network_aviliable()
    {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        boolean data = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();

        if (!wifi && !data)
        {
            return false;
        }
        else {
            return true;
        }
    }
}
