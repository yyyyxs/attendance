package com.jmhz.devicemanage.utils;

import java.util.ArrayList;

public class XMLPullPaserTool {

	public ArrayList<String> group = new ArrayList<String>();

	// public ArrayList<ArrayList<ProfessionItem>> child = new
	// ArrayList<ArrayList<ProfessionItem>>();

	// public ArrayList<String> groupSubscribe = new ArrayList<String>();
	// public ArrayList<ArrayList<SubscribeInfoItem>> childSubscribe = new
	// ArrayList<ArrayList<SubscribeInfoItem>>();
	//
	// public void getSubscribeInfo(InputStream is) {
	// try {
	// XmlPullParser parser = Xml.newPullParser();
	// parser.setInput(is, "utf-8");
	// int eventCode = parser.getEventType();
	// ArrayList<SubscribeInfoItem> data = null;
	// while (eventCode != XmlPullParser.END_DOCUMENT) {
	// switch (eventCode) {
	// case XmlPullParser.START_DOCUMENT:
	// break;
	// case XmlPullParser.START_TAG:
	// if (parser.getName().equals("subscribe")) {
	// group.add(parser.getAttributeValue(0));
	// data = new ArrayList<SubscribeInfoItem>();
	// } else if (parser.getName().equals("item")) {
	// String parserString = parser.nextText();
	// data.add(new ProfessionItem(parserString));
	// }
	// break;
	// case XmlPullParser.END_TAG:
	// if (parser.getName().equals("profession")) {
	// child.add(data);
	// }
	// break;
	// default:
	// break;
	// }
	// eventCode = parser.next();
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	// public void getProfessions(InputStream is) {
	// try {
	// XmlPullParser parser = Xml.newPullParser();
	// parser.setInput(is, "utf-8");
	// int eventCode = parser.getEventType();
	// // ArrayList<ProfessionItem> data = null;
	// while (eventCode != XmlPullParser.END_DOCUMENT) {
	// switch (eventCode) {
	// case XmlPullParser.START_DOCUMENT:
	// break;
	// case XmlPullParser.START_TAG:
	// if (parser.getName().equals("profession")) {
	// group.add(parser.getAttributeValue(0));
	// // data = new ArrayList<ProfessionItem>();
	// } else if (parser.getName().equals("item")) {
	// String parserString = parser.nextText();
	// // data.add(new ProfessionItem(parserString));
	// }
	// break;
	// case XmlPullParser.END_TAG:
	// if (parser.getName().equals("profession")) {
	// // child.add(data);
	// }
	// break;
	// default:
	// break;
	// }
	// eventCode = parser.next();
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

}
