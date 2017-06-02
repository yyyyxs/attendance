package com.jmhz.devicemanage.utils;

import java.io.File;
import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jmhz.devicemanage.model.Area;
import com.jmhz.devicemanage.model.City;
import com.jmhz.devicemanage.model.Province;

public class AddressUtils {

	public static final String DB_NAME = "province_city_area.db";
	public static final String PROVINCE_TABLE_NAME = "t_province";
	public static final String CITY_TABLE_NAME = "t_city";
	public static final String AREA_TABLE_NAME = "t_area";
	public static final String TB_AREA_TABLE_NAME = "tb_area";

	public static ArrayList<Province> getProvinces(File file) {
		ArrayList<Province> provinces = new ArrayList<Province>();
		String sql = "select * from " + PROVINCE_TABLE_NAME;
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(file, null);
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			Province p = new Province();
			p.setC_PROVINCE_ID(cursor.getString(0));
			p.setC_PROVINCE_NAME(cursor.getString(1));
			p.setC_REGION_ID(cursor.getInt(2));
			provinces.add(p);
		}
		close(cursor, db);
		return provinces;
	}

	public static String getCityCode(File file, String city) {
		String sql = "select C_CITY_ID from " + CITY_TABLE_NAME
				+ " where C_CITY_NAME Like '%" + city + "%'";
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(file, null);
		Cursor cursor = db.rawQuery(sql, null);
		String cityCode = null;
		if (cursor.moveToNext()) {
			cityCode = cursor.getString(0);
		}

		close(cursor, db);
		return cityCode;
	}

	public static ArrayList<City> getCities(File file, String provinceId) {
		ArrayList<City> cities = new ArrayList<City>();
		String sql = "select * from " + CITY_TABLE_NAME
				+ " where C_PROVINCE_ID='" + provinceId + "'";
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(file, null);
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			City c = new City();
			c.setC_CITY_ID(cursor.getString(0));
			c.setC_CITY_NAME(cursor.getString(1));
			c.setC_PROVINCE_ID(cursor.getString(2));
			cities.add(c);
		}
		close(cursor, db);
		return cities;
	}

	public static ArrayList<Area> getAreaes(File file, String cityId) {
		ArrayList<Area> areaes = new ArrayList<Area>();
		String sql = "select * from " + AREA_TABLE_NAME + " where C_CITY_ID='"
				+ cityId + "'";
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(file, null);
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			Area a = new Area();
			a.setC_AREA_ID(cursor.getString(0));
			a.setC_AREA_NAME(cursor.getString(1));
			a.setC_CITY_ID(cursor.getString(2));
			areaes.add(a);
		}
		close(cursor, db);
		return areaes;
	}

	private static void close(Cursor cursor, SQLiteDatabase database) {
		if (cursor != null) {
			cursor.close();
		}
		if (database != null) {
			database.close();
		}
	}

}
