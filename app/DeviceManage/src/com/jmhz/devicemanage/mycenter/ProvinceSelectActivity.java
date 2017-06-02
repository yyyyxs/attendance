package com.jmhz.devicemanage.mycenter;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.model.Province;
import com.jmhz.devicemanage.table.UITableView;
import com.jmhz.devicemanage.table.UITableView.ClickListener;
import com.jmhz.devicemanage.table.ViewItem;
import com.jmhz.devicemanage.utils.AddressUtils;

public class ProvinceSelectActivity extends BaseActivity {

	ArrayList<Province> provinces = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_province_select);
		initializeMemberVariable(findViewById(R.id.mainHead));
		
		provinces = AddressUtils.getProvinces(provinceDatabaseFile);

		showTable();
	}

	private void showTable() {

		uitableView = (UITableView) findViewById(R.id.provinceSelectTable);
		for (int i = 0; i < provinces.size(); i++) {
			View v = LayoutInflater.from(this).inflate(R.layout.province_item,
					null);
			((TextView) v.findViewById(R.id.province)).setText(provinces.get(i)
					.getC_PROVINCE_NAME());
			ViewItem viewItem = new ViewItem(v);
			uitableView.addViewItem(viewItem);
		}
		uitableView.commit();
		uitableView.setClickListener(listener);
		
	}
	private ClickListener listener = new ClickListener(){

		@Override
		public void onClick(int index) {
			// TODO Auto-generated method stub
			toastShort(provinces.get(index)
					.getC_PROVINCE_NAME());
		}
		
	};
	@Override
	protected void initializeActivityHead() {
		// TODO Auto-generated method stub
		rightButton.setVisibility(View.GONE);
		centreText.setText(R.string.province);
		leftButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		 if (keyCode == KeyEvent.KEYCODE_BACK) {
			 finish(); 
		 }
		return super.onKeyDown(keyCode, event);
	}
}
