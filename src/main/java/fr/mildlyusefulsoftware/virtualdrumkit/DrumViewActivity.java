package fr.mildlyusefulsoftware.virtualdrumkit;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class DrumViewActivity extends Activity {

	private static String TAG = "virtualdrumkit";
	private AdView adView;

	private static BoundingBox hithatBoundingBox = new BoundingBox(207, 77,
			300, 142);
	private static BoundingBox bassDrumBoundingBox = new BoundingBox(345, 183,
			440, 324);
	private static BoundingBox floorTomBoundingBox = new BoundingBox(444, 168,
			582, 336);
	private static BoundingBox snareDrumBoundingBox = new BoundingBox(268, 141,
			362, 213);
	private static BoundingBox cymbalBoundingBox = new BoundingBox(473, 15, 603,
			100);
	private static BoundingBox tomsBoundingBox = new BoundingBox(290, 72, 480,
			152);

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
		ImageView drumImageView = (ImageView) findViewById(R.id.drumsImageView);
		drumImageView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.d(TAG, "x : " + event.getX() + " y: " + event.getY());
				if (hithatBoundingBox.contains(event.getX(), event.getY())) {
					playSound(R.raw.hithat);
				} else if (bassDrumBoundingBox.contains(event.getX(),
						event.getY())) {
					playSound(R.raw.bassdrum);
				} else if (floorTomBoundingBox.contains(event.getX(),
						event.getY())) {
					playSound(R.raw.floortom);
				}else if (snareDrumBoundingBox.contains(event.getX(),
						event.getY())) {
					playSound(R.raw.snaredrum);
				}else if (cymbalBoundingBox.contains(event.getX(),
						event.getY())) {
					playSound(R.raw.cymbal);
				}else if (tomsBoundingBox.contains(event.getX(),
						event.getY())) {
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
		layout.addView(adView,adsParams);
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
