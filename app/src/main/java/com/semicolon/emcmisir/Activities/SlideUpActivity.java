package com.semicolon.emcmisir.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.listeners.ActionClickListener;
import com.semicolon.emcmisir.R;

public class SlideUpActivity extends Activity implements AnimationListener {

	ImageView imgPoster;
	Button btnStart;
	public boolean isFirstStart;

	// Animation
	Animation animSlideUp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide_up);
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
					Intent i = new Intent(SlideUpActivity.this, MyIntro.class);
					startActivity(i);
					SharedPreferences.Editor e = getSharedPreferences.edit();
					e.putBoolean("firstStart", false);
					e.apply();
				}
			}
		});
		t.start();
		imgPoster = (ImageView) findViewById(R.id.imgLogo);
	//	btnStart = (Button) findViewById(R.id.btnStart);

		// load the animation
		animSlideUp = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.slide_up);

		// set animation listener
		animSlideUp.setAnimationListener(this);
		imgPoster.setVisibility(View. VISIBLE);
		imgPoster.startAnimation(animSlideUp);
		animSlideUp.setDuration(3000);
		// button click event
//		btnStart.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// start the animation
//
//			}
//		});

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// Take any action after completing the animation

		// check for zoom in animation
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

	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

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
