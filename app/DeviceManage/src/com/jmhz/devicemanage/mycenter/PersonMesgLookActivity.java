package com.jmhz.devicemanage.mycenter;

import java.util.ArrayList;

import android.R.string;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.OrderDetailAdapter;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.TransportNodeApi;
import com.jmhz.devicemanage.http.api.UpdateVersionApi;
import com.jmhz.devicemanage.http.download.NotifyDownload;
import com.jmhz.devicemanage.model.OrderDetailItem;
import com.jmhz.devicemanage.param.VersionsParam;
import com.uid.trace.common.schema.TransportNode;
import com.uid.trace.module.stock.schema.TransportNodeMessage;

public class PersonMesgLookActivity extends BaseActivity  {
		
	    TransportNode tn = null;
	    public TextView tv_et_tea_id= null;  
		public TextView tv_et_tea_name=null;
		public TextView tv_et_tea_tel= null;  
		public String blank = "                            ";
		public String lon = "";
		public String lat = "";
		private SharedPreferences sharedPreferences;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pcl_tea);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initFileArea();
		tv_et_tea_id=(TextView) findViewById(R.id.tv_et_tea_id);
		tv_et_tea_name=(TextView) findViewById(R.id.tv_et_tea_name);
		tv_et_tea_tel=(TextView) findViewById(R.id.tv_et_tea_tel);
		
		sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
		String id = sharedPreferences.getString("roleIds", "");
		String name=sharedPreferences.getString("username", "");
		String tel = sharedPreferences.getString("tel", "");
		
		tv_et_tea_id.setText(id);
		tv_et_tea_name.setText(name);
		tv_et_tea_tel.setText(tel);
			
	}

		@Override
		protected void initializeActivityHead() {
			// TODO Auto-generated method stub
					centreText.setText(R.string.person_mesg_look);	
					rightButton.setVisibility(View.GONE);
					leftButton.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View arg0) {
							PersonMesgLookActivity.this.finish();
						}
					});
				}
		
		
		
		}
	
