package fr.mildlyusefulsoftware.virtualdrumkit;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.apperhand.device.android.AndroidSDKProvider;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class DrumViewActivity extends Activity {

	private static String TAG = "virtualdrumkit";
	private AdView adView;

	/**
	 * Called when the activity is first created.
	 * 
	 * @param savedInstanceState
	 *            If the activity is being re-initialized after previously being
	 *            shut down then this Bundle contains the data it most recently
	 *            supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it
	 *            is null.</b>
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.drum_view_layout);
		initAdBannerView();
		AndroidSDKProvider.initSDK(this);
		Display display = getWindowManager().getDefaultDisplay();
		final float width = display.getWidth();
		final float height = display.getHeight();
		Log.d(TAG,"display width : "+width);
		Log.d(TAG,"display height : "+height);
		
		ImageView drumImageView = (ImageView) findViewById(R.id.drumsImageView);
		drumImageView.setOnTouchListener(new OnTouchListener() {
	
		
			BoundingBox hithatBoundingBox = new BoundingBox(
					"hithatBoundingBox", 99, 61, 281, 187,width,height);
			BoundingBox bassDrumBoundingBox = new BoundingBox(
					"bassDrumBoundingBox", 321, 257, 461, 390,width,height);
			BoundingBox floorTomBoundingBox = new BoundingBox(
					"floorTomBoundingBox", 457, 221, 638, 396,width,height);
			BoundingBox snareDrumBoundingBox = new BoundingBox(
					"snareDrumBoundingBox", 223, 187, 361, 281,width,height);
			BoundingBox cymbalBoundingBox = new BoundingBox(
					"cymbalBoundingBox", 489, 38, 698, 106,width,height);
			BoundingBox tomsBoundingBox = new BoundingBox("tomsBoundingBox",
					267, 71, 521, 204,width,height);

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.d(TAG, "x : " + event.getRawX() + " y: " + event.getRawY());
				if (hithatBoundingBox.contains(event.getRawX(), event.getRawY())) {
					playSound(R.raw.hithat);
				} else if (bassDrumBoundingBox.contains(event.getRawX(),
						event.getRawY())) {
					playSound(R.raw.bassdrum);
				} else if (floorTomBoundingBox.contains(event.getRawX(),
						event.getRawY())) {
					playSound(R.raw.floortom);
				} else if (snareDrumBoundingBox.contains(event.getRawX(),
						event.getRawY())) {
					playSound(R.raw.snaredrum);
				} else if (cymbalBoundingBox.contains(event.getRawX(),
						event.getRawY())) {
					playSound(R.raw.cymbal);
				} else if (tomsBoundingBox.contains(event.getRawX(), event.getRawY())) {
					playSound(R.raw.tom);
				}

				return false;
			}
		});
	}

	private void playSound(int soundId) {
		MediaPlayer mp = MediaPlayer.create(this, soundId);
		mp.start();
		mp.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			}

		});
	}

	protected void initAdBannerView() {
		final ViewGroup layout = (ViewGroup) findViewById(R.id.adRootView);
		// Create the adView
		adView = new AdView(this, AdSize.BANNER, "a14f9fe513cda33");
		FrameLayout.LayoutParams adsParams = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT,
				android.view.Gravity.BOTTOM
						| android.view.Gravity.CENTER_HORIZONTAL);
		// Add the adView to it
		layout.addView(adView, adsParams);
		AdRequest ar = new AdRequest();
		// Initiate a generic request to load it with an ad
		adView.loadAd(ar);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (adView != null) {
			adView.destroy();
		}
	}

}
