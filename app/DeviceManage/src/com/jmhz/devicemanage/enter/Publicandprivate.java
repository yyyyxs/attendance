package com.jmhz.devicemanage.enter;

import com.jmhz.devicemanage.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Publicandprivate extends Activity {
private RadioGroup radiogroup;
private RadioButton radiobutton;

protected void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	setContentView(R.layout.publicandprivate);
	radiogroup=(RadioGroup) findViewById(R.id.radioGroup1);
	
}
}
