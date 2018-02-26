package com.cncoderx.game.magictower;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.cncoderx.game.magictower.utils.Global;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AndroidLauncher extends AndroidApplication {

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		MobileAds.initialize(getApplicationContext(), "ca-app-pub-9599037549184713~8472608980");
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		View gameView = initializeForView(new GameContext(), config);
//		View gameView = initializeForView(new TestApplication(), config);

		/*AdView adView = new AdView(this);
		adView.setAdSize(AdSize.BANNER);
		adView.setAdUnitId("ca-app-pub-9599037549184713/2426075388");

		int gameViewWidth = getScreenWidth();
		int gameViewHeight = gameViewWidth * Global.SCREEN_HEIGHT / Global.SCREEN_WIDTH;

		LinearLayout contentView = new LinearLayout(this);
		contentView.setOrientation(LinearLayout.VERTICAL);
		contentView.addView(gameView, new LinearLayout.LayoutParams(gameViewWidth, gameViewHeight));
		contentView.addView(adView, new LinearLayout.LayoutParams(-1, -1));

		setContentView(contentView);

		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);*/

		setContentView(gameView);
	}

	public int getScreenWidth() {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		return dm.widthPixels;
	}
}
