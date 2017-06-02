package com.jmhz.devicemanage.table;

public class BasicItem implements IListItem {
	
	private boolean mClickable = true;
	private int mDrawable = -1;
	private String mTitle;
	private String mSubtitle;
	private int mColor = -1;
	

	public BasicItem(String title) {
		this.mTitle = title;
	}
	
	public BasicItem(String title, String subtitle) {
		this.mTitle = title;
		this.mSubtitle = subtitle;
	}
	
	public BasicItem(String title, String subtitle, int color) {
		this.mTitle = title;
		this.mSubtitle = subtitle;
		this.mColor = color;
	}
	
	public BasicItem(String title, String subtitle, boolean clickable) {
		this.mTitle = title;
		this.mSubtitle = subtitle;
		this.mClickable = clickable;
	}	
	
	public BasicItem(int drawable, String title, String subtitle) {
		this.mDrawable = drawable;
		this.mTitle = title;
		this.mSubtitle = subtitle;
	}
	
	public BasicItem(int drawable, String title, String subtitle, int color) {
		this.mDrawable = drawable;
		this.mTitle = title;
		this.mSubtitle = subtitle;
		this.mColor = color;
	}

	public int getDrawable() {
		return mDrawable;
	}

	public void setDrawable(int drawable) {
		this.mDrawable = drawable;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}

	public String getSubtitle() {
		return mSubtitle;
	}

	public void setSubtitle(String summary) {
		this.mSubtitle = summary;
	}

	public int getColor() {
		return mColor;
	}

	public void setColor(int mColor) {
		this.mColor = mColor;
	}

	@Override
	public boolean isClickable() {
		return mClickable;
	}

	@Override
	public void setClickable(boolean clickable) {
		mClickable = clickable;			
	}
	
}
