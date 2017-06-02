package com.jmhz.devicemanage.mycenter;

import com.jmhz.devicemanage.R;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

public class MyDialog extends AlertDialog {
//private TextView mTvOk=null;
    Context context;
    public MyDialog(Context context) {
        super(context);
        this.context = context;
    }
    public MyDialog(Context context, int theme){
        super(context, theme);
        this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.toast_changepsd);
//        mTvOk=(TextView)findViewById(R.id.tv_changepsd_ok);
//        mTvOk.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				MyDialog.dismiss();  
//			}
//		});
    }

}