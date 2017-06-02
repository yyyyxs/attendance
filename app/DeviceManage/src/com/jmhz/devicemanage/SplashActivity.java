package com.jmhz.devicemanage;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.TextView;

import com.jmhz.devicemanage.R;

public class SplashActivity extends Activity{
	public void onCreate(Bundle saveInstanceState) {  
        super.onCreate(saveInstanceState); 
        requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.splash);
	    PackageManager pm = getPackageManager();
	    try {
	        PackageInfo pi = pm.getPackageInfo("com.lyt.android", 0);
	        TextView versionNumber = (TextView) findViewById(R.id.versionNumber);
	        versionNumber.setText("Version " + pi.versionName);
	    } catch (NameNotFoundException e) {
	        e.printStackTrace();
	    }

	    new Handler().postDelayed(new Runnable(){

	    @Override
	    public void run() {
	        Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
	        startActivity(intent);
	        SplashActivity.this.finish();
	    }

	    }, 2500);
	
	
	}
	
}
	
