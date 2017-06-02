package com.jmhz.devicemanage.table;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jmhz.devicemanage.R;



public class UITableView extends LinearLayout {
	
	private int mIndexController = 0;
	private LayoutInflater mInflater;
	private LinearLayout mMainContainer;
	private LinearLayout mListContainer;
	private List<IListItem> mItemList;
	private ClickListener mClickListener;
	
	public UITableView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mItemList = new ArrayList<IListItem>();
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMainContainer = (LinearLayout)  mInflater.inflate(R.layout.list_container, null);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		addView(mMainContainer, params);				
		mListContainer = (LinearLayout) mMainContainer.findViewById(R.id.buttonsContainer);		
	}
	
	public void addBasicItem(String title) {
		mItemList.add(new BasicItem(title));
	}
	
	public void addBasicItem(String title, String summary) {
		mItemList.add(new BasicItem(title, summary));
	}
	
	public void addBasicItem(String title, String summary, int color) {
		mItemList.add(new BasicItem(title, summary, color));
	}

	public void addBasicItem(int drawable, String title, String summary) {
		mItemList.add(new BasicItem(drawable, title, summary));
	}

	public void addBasicItem(int drawable, String title, String summary, int color) {
		mItemList.add(new BasicItem(drawable, title, summary, color));
	}

	public void addBasicItem(BasicItem item) {
		mItemList.add(item);
	}

	public void addViewItem(ViewItem itemView) {
		mItemList.add(itemView);
	}
	
	public void addViewItem(int index, ViewItem itemView) {
		mItemList.add(index, itemView);
	}
	
	public void commit() {
		mIndexController = 0;
		
		if(mItemList.size() > 1) {
			//when the list has more than one item
			for(IListItem obj : mItemList) {
				View tempItemView;
				if(mIndexController == 0) {
					tempItemView = mInflater.inflate(R.layout.list_item_top, null);
				}
				else if(mIndexController == mItemList.size()-1) {
					tempItemView = mInflater.inflate(R.layout.list_item_bottom, null);
				}
				else {
					tempItemView = mInflater.inflate(R.layout.list_item_middle, null);
				}	
				setupItem(tempItemView, obj, mIndexController);
				tempItemView.setClickable(obj.isClickable());
				mListContainer.addView(tempItemView);
				mIndexController++;
			}
		} else if(mItemList.size() == 1) {
			//when the list has only one item
			View tempItemView = mInflater.inflate(R.layout.list_item_single, null);
			IListItem obj = mItemList.get(0);
			setupItem(tempItemView, obj, mIndexController);
			tempItemView.setClickable(obj.isClickable());
			mListContainer.addView(tempItemView);
		}
	}
	
	private void setupItem(View view, IListItem item, int index) {
		if(item instanceof BasicItem) {
			BasicItem tempItem = (BasicItem) item;
			setupBasicItem(view, tempItem, mIndexController);
		}
		else if(item instanceof ViewItem) {
			ViewItem tempItem = (ViewItem) item;
			setupViewItem(view, tempItem, mIndexController);
		}
	}
	
	private void setupBasicItem(View view, BasicItem item, int index) {
		if(item.getDrawable() > -1) {
			((ImageView) view.findViewById(R.id.image)).setBackgroundResource(item.getDrawable());
		}
		if(item.getSubtitle() != null) {
//			((TextView) view.findViewById(R.id.subtitle)).setText(item.getSubtitle());
		}
		else {
//			((TextView) view.findViewById(R.id.subtitle)).setVisibility(View.GONE);
		}		
		((TextView) view.findViewById(R.id.title)).setText(item.getTitle());
		if(item.getColor() > -1) {
			((TextView) view.findViewById(R.id.title)).setTextColor(item.getColor());
		}
		view.setTag(index);
		if(item.isClickable()) {
			view.setOnClickListener( new View.OnClickListener() {
	
				@Override
				public void onClick(View view) {
					if(mClickListener != null)
						mClickListener.onClick((Integer) view.getTag());
				}
				
			});	
		} else {
			((ImageView) view.findViewById(R.id.chevron)).setVisibility(View.GONE);
		}
	}
	
	private void setupViewItem(View view, ViewItem itemView, int index) {
		if(itemView.getView() != null) {
			LinearLayout itemContainer = (LinearLayout) view.findViewById(R.id.itemContainer);
			itemContainer.removeAllViews();
//			itemContainer.removeAllViewsInLayout();
			itemContainer.addView(itemView.getView());
			
			if(itemView.isClickable()) {
		        	itemContainer.setTag(index);
		               	itemContainer.setOnClickListener( new View.OnClickListener() {
		                	@Override
		                    	public void onClick(View view) {
		                        	if(mClickListener != null)
		                            		mClickListener.onClick((Integer) view.getTag());
					}
		                });
		           }
		}
	}
	
	public interface ClickListener {		
		void onClick(int index);		
	}

	public int getCount() {
		return mItemList.size();
	}
	
	public ViewItem getItemView(int index){
		return (ViewItem) mItemList.get(index);
	}
	
	public View getView(int index){
		return getItemView(index).getView();
	}
	
	public void setInputType(int index, int type){
		setInputType(index, R.id.value, type);
	}
	
	public void setInputType(int index, int id, int type){
		View v = getView(index);
		((EditText)v.findViewById(id)).setInputType(type);
	}
	
	public void setPassword(int index){
		setPassword(index, R.id.value);
	}
	
	public void setPassword(int index, int id){
		View v = getView(index);
		((EditText) v.findViewById(id))
		.setTransformationMethod(PasswordTransformationMethod
				.getInstance());
	}
	
	public String getText(int index){
		return getText(index, R.id.value);
	}
	
	public String getText(int index, int id){
		View v = getView(index);
		return ((EditText)v.findViewById(id)).getText().toString();
	}
	
	public void setText(int index, String text){
		setText(index, R.id.value, text);
	}
	
	public void setText(int index, int id, String text){
		View v = getView(index);
		((TextView)v.findViewById(id)).setText(text);
	}
	
	public void setText(int index, int resId){
		setText(index, R.id.value, resId);
	}
	
	public void setText(int index, int id, int resId){
		View v = getView(index);
		((EditText)v.findViewById(id)).setText(resId);
	}
	
	public void clear() {
		mItemList.clear();
		mListContainer.removeAllViews();
	}
	
	public void setClickListener(ClickListener listener) {
		this.mClickListener = listener;
	}
	
	public void removeClickListener() {
		this.mClickListener = null;
	}

	public void setHint(int index, int hint) {
		setHint(index, R.id.value, hint);
	}
	
	private void setHint(int index, int id, int hint) {
		View v = getView(index);
		((EditText)v.findViewById(id)).setHint(hint);
	}
	public void setHint(int index, String hint) {
		setHint(index, R.id.value, hint);
	}
	private void setHint(int index, int id, String hint) {
		View v = getView(index);
		((EditText)v.findViewById(id)).setHint(hint);
	}
}
