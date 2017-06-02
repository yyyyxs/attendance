package com.jmhz.devicemanage.mydevices;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.fragment.FailureReportingAuditFragment;
import com.jmhz.devicemanage.fragment.FailureReportingFinishFragment;
import com.jmhz.devicemanage.fragment.FailureReportingNotAuditFragment;
import com.jmhz.devicemanage.fragment.FailureReportingRejectFragment;

public class FailureReportingProcessActivity extends BaseActivity implements OnClickListener{


	//private ListView failureReportListview;
	//private LinearLayout failureReportList;
	private TextView failureNotAudit;
	private TextView failureAudit;
	private TextView failureFinish;
	private TextView failureReject;
	private FragmentManager manager;
	private FragmentTransaction transaction;
	private FailureReportingAuditFragment  failureAuditFragment;
	private FailureReportingNotAuditFragment  failureNotAuditFragment;
	private FailureReportingRejectFragment  failureRejectFragment;
	private FailureReportingFinishFragment  failureFinishFragment;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_mydevice_failure);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initWidget();

	}
	private void initWidget() {
		failureAudit=(TextView) findViewById(R.id.to_fragment_failure_audit);
		failureNotAudit=(TextView) findViewById(R.id.to_fragment_failure_notaudit);
		failureReject=(TextView) findViewById(R.id.to_fragment_failure_Reject);
		failureFinish=(TextView) findViewById(R.id.to_fragment_failure_finish);
		failureNotAudit.setBackgroundResource(R.drawable.headtab_pressed);
		failureAudit.setBackgroundResource(R.drawable.white_normal);
		failureReject.setBackgroundResource(R.drawable.white_normal);
		failureFinish.setBackgroundResource(R.drawable.white_normal);
		//failureReportList=(LinearLayout) findViewById(R.id.liner_failure_list);
		failureAudit.setOnClickListener(this);
		failureNotAudit.setOnClickListener(this);
		failureReject.setOnClickListener(this);
		failureFinish.setOnClickListener(this);
		manager=getFragmentManager();
		transaction=manager.beginTransaction();
		failureNotAuditFragment=new FailureReportingNotAuditFragment();
		transaction.add(R.id .liner_failure_list, failureNotAuditFragment);
		//transaction.replace(R.id.liner_failure_list, failureAuditFragment, "failureAuditFragment");
		//transaction.addToBackStack("failureAuditFragment");
		transaction.commit();
		//manager=getFragmentManager();
		//failureReportListview=(ListView) findViewById(R.id.failure_report_list);
		
	}
	protected void initializeActivityHead() {
		centreText.setText("π ’œ…Í±®«Èøˆ");
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				FailureReportingProcessActivity.this.finish();
			}
		});
	}
	@Override
	public void onClick(View v) {
		transaction=manager.beginTransaction();
		switch(v.getId())
		{
		case R.id.to_fragment_failure_audit:
			failureNotAudit.setBackgroundResource(R.drawable.white_normal);
			failureAudit.setBackgroundResource(R.drawable.headtab_pressed);
			failureReject.setBackgroundResource(R.drawable.white_normal);
			failureFinish.setBackgroundResource(R.drawable.white_normal);
			failureAuditFragment=new FailureReportingAuditFragment();
			transaction.replace(R.id.liner_failure_list, failureAuditFragment, "failureAuditFragment");
			transaction.addToBackStack("failureAuditFragment");
			break;
		case R.id.to_fragment_failure_notaudit:
			failureNotAudit.setBackgroundResource(R.drawable.headtab_pressed);
			failureAudit.setBackgroundResource(R.drawable.white_normal);
			failureReject.setBackgroundResource(R.drawable.white_normal);
			failureFinish.setBackgroundResource(R.drawable.white_normal);
			failureNotAuditFragment=new FailureReportingNotAuditFragment();
			transaction.replace(R.id.liner_failure_list,failureNotAuditFragment,"failureNotAuditFragment");
			transaction.addToBackStack("failureNotAuditFragment");
			break;
		case R.id.to_fragment_failure_Reject:
			failureNotAudit.setBackgroundResource(R.drawable.white_normal);
			failureAudit.setBackgroundResource(R.drawable.white_normal);
			failureReject.setBackgroundResource(R.drawable.headtab_pressed);
			failureFinish.setBackgroundResource(R.drawable.white_normal);
			failureRejectFragment=new FailureReportingRejectFragment();
			transaction.replace(R.id.liner_failure_list,failureRejectFragment,"failureRejectFragment");
			transaction.addToBackStack("failureRejectFragment");
			break;
		case R.id.to_fragment_failure_finish:
			failureNotAudit.setBackgroundResource(R.drawable.white_normal);
			failureAudit.setBackgroundResource(R.drawable.white_normal);
			failureReject.setBackgroundResource(R.drawable.white_normal);
			failureFinish.setBackgroundResource(R.drawable.headtab_pressed);
			failureFinishFragment=new FailureReportingFinishFragment();
			transaction.replace(R.id.liner_failure_list,failureFinishFragment,"failureFinishFragment");
			transaction.addToBackStack("failureFinishFragment");
			break;
		}
		transaction.commit();
		
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		 if (keyCode == KeyEvent.KEYCODE_BACK) {
			 FailureReportingProcessActivity.this.finish(); 
		 }
		return super.onKeyDown(keyCode, event);
	}

}
