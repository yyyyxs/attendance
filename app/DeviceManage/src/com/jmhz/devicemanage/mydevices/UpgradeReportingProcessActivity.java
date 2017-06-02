package com.jmhz.devicemanage.mydevices;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.fragment.UpgradeReportingAuditFragment;
import com.jmhz.devicemanage.fragment.UpgradeReportingFinishFragment;
import com.jmhz.devicemanage.fragment.UpgradeReportingNotAuditFragment;
import com.jmhz.devicemanage.fragment.UpgradeReportingRejectFragment;

public class UpgradeReportingProcessActivity extends BaseActivity implements OnClickListener{

	private TextView upgradeNotAudit;
	private TextView upgradeAudit;
	private TextView upgradeFinish;
	private TextView upgradeReject;
	private FragmentManager manager;
	private FragmentTransaction transaction;
	private UpgradeReportingAuditFragment  upgradeAuditFragment;
	private UpgradeReportingNotAuditFragment  upgradeNotAuditFragment;
	private UpgradeReportingFinishFragment  upgradeFinishFragment;
	private UpgradeReportingRejectFragment  upgradeRejectFragment;
	//private ListView upgradeReportListview;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_mydevice_upgrade);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initWidget();
	}
	private void initWidget() {
		//upgradeReportListview=(ListView) findViewById(R.id.failure_report_list);
		upgradeAudit=(TextView) findViewById(R.id.to_fragment_upgrade_audit);
		upgradeNotAudit=(TextView) findViewById(R.id.to_fragment_upgrade_notaudit);
		upgradeFinish=(TextView) findViewById(R.id.to_fragment_upgrade_finish);
		upgradeReject=(TextView) findViewById(R.id.to_fragment_upgrade_Reject);
		upgradeNotAudit.setBackgroundResource(R.drawable.headtab_pressed);
		upgradeAudit.setBackgroundResource(R.drawable.white_normal);
		upgradeReject.setBackgroundResource(R.drawable.white_normal);
		upgradeFinish.setBackgroundResource(R.drawable.white_normal);
		upgradeAudit.setOnClickListener(this);
		upgradeNotAudit.setOnClickListener(this);
		upgradeFinish.setOnClickListener(this);
		upgradeReject.setOnClickListener(this);
		manager=getFragmentManager();
		transaction=manager.beginTransaction();
		upgradeNotAuditFragment=new UpgradeReportingNotAuditFragment();
		transaction.add(R.id.liner_upgrade_list, upgradeNotAuditFragment);
		transaction.commit();
		
	
	}
	protected void initializeActivityHead() {
		centreText.setText("升级申报情况");
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				UpgradeReportingProcessActivity.this.finish();
			}
		});
	}
	@Override
	public void onClick(View v) {
		transaction=manager.beginTransaction();
		switch(v.getId())
		{
		case R.id.to_fragment_upgrade_notaudit:
			upgradeNotAudit.setBackgroundResource(R.drawable.headtab_pressed);
			upgradeAudit.setBackgroundResource(R.drawable.white_normal);
			upgradeReject.setBackgroundResource(R.drawable.white_normal);
			upgradeFinish.setBackgroundResource(R.drawable.white_normal);
			upgradeNotAuditFragment=new UpgradeReportingNotAuditFragment();
			transaction.replace(R.id.liner_upgrade_list, upgradeNotAuditFragment, "upgradeNotAuditFragment");
			transaction.addToBackStack("upgradeNotAuditFragment");
		
			//Toast.makeText(UpgradeReportingProcessActivity.this,"升级未审核", Toast.LENGTH_SHORT).show();
			break;
		case R.id.to_fragment_upgrade_audit:
			upgradeNotAudit.setBackgroundResource(R.drawable.white_normal);
			upgradeAudit.setBackgroundResource(R.drawable.headtab_pressed);
			upgradeReject.setBackgroundResource(R.drawable.white_normal);
			upgradeFinish.setBackgroundResource(R.drawable.white_normal);
			upgradeAuditFragment=new UpgradeReportingAuditFragment();
			transaction.replace(R.id.liner_upgrade_list, upgradeAuditFragment, "upgradeAuditFragment");
			transaction.addToBackStack("upgradeAuditFragment");
			//Toast.makeText(UpgradeReportingProcessActivity.this,"升级已审核", Toast.LENGTH_SHORT).show();
			break;

		case R.id.to_fragment_upgrade_Reject:
			upgradeNotAudit.setBackgroundResource(R.drawable.white_normal);
			upgradeAudit.setBackgroundResource(R.drawable.white_normal);
			upgradeReject.setBackgroundResource(R.drawable.headtab_pressed);
			upgradeFinish.setBackgroundResource(R.drawable.white_normal);
			upgradeRejectFragment=new UpgradeReportingRejectFragment();
			transaction.replace(R.id.liner_upgrade_list, upgradeRejectFragment, "upgradeRejectFragment");
			transaction.addToBackStack("upgradeRejectFragment");
			break;
		case R.id.to_fragment_upgrade_finish:
			upgradeNotAudit.setBackgroundResource(R.drawable.white_normal);
			upgradeAudit.setBackgroundResource(R.drawable.white_normal);
			upgradeReject.setBackgroundResource(R.drawable.white_normal);
			upgradeFinish.setBackgroundResource(R.drawable.headtab_pressed);
			upgradeFinishFragment=new UpgradeReportingFinishFragment();
			transaction.replace(R.id.liner_upgrade_list, upgradeFinishFragment, "upgradeFinishFragment");
			transaction.addToBackStack("upgradeFinishFragment");
			//Toast.makeText(UpgradeReportingProcessActivity.this,"升级已完成", Toast.LENGTH_SHORT).show();
			break;
		}
		transaction.commit();
		
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		 if (keyCode == KeyEvent.KEYCODE_BACK) {
			 UpgradeReportingProcessActivity.this.finish(); 
		 }
		return super.onKeyDown(keyCode, event);
	}

}
