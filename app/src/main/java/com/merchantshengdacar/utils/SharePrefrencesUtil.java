package com.merchantshengdacar.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.merchantshengdacar.mvp.BaseApplication;

import java.util.Set;

/**
 * 工程名: hejuhui2.0 文件名: SharePrefrencesUtil.java 时间 ： 2016年6月12日上午11:05:54 描述 :
 * TODO
 * 
 * @Author mi
 */

public class SharePrefrencesUtil {

	static SharePrefrencesUtil mInstance;
	static final String SP_NAME = "shengdashanghu";
	private SharedPreferences sp;

	private SharePrefrencesUtil() {
		sp = BaseApplication.getInstance().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
	}

	public static SharePrefrencesUtil getInstance() {
		if (mInstance == null) {
			mInstance = new SharePrefrencesUtil();
		}
		return mInstance;
	}

	public void putString(String key, String value) {
		Editor edit = sp.edit();
		edit.putString(key, value);
		edit.apply();
	}

	public void putInt(String key, int value) {
		Editor edit = sp.edit();
		edit.putInt(key, value);
		edit.apply();
	}
	
	public void putBoolean(String key, boolean value) {
		Editor edit = sp.edit();
		edit.putBoolean(key, value);
		edit.apply();
	}
	
	public void removeObject(String key){
		Editor edit = sp.edit();
		edit.remove(key);
		edit.apply();
	}

	public void putLong(String key, long value) {
		Editor edit = sp.edit();
		edit.putLong(key, value);
		edit.apply();
	}

	public void putFloat(String key, float value) {
		Editor edit = sp.edit();
		edit.putFloat(key, value);
		edit.apply();
	}

	public void putSet(String key, Set<String> value) {
		Editor edit = sp.edit();
		edit.putStringSet(key, value);
		edit.apply();
	}

	public String getString(String key) {
		return sp.getString(key, "");
	}

	public int getInt(String key) {
		return sp.getInt(key, -1);
	}

	public long getLong(String key) {
		return sp.getLong(key, -1);
	}

	public float getFloat(String key) {
		return sp.getFloat(key, -1);
	}

	public boolean getBoolean(String key) {
		return sp.getBoolean(key, false);
	}
	
	public Set<String> getSet(String key) {
		return sp.getStringSet(key, null);
	}
	
	public void celars(){
		Editor edit = sp.edit();
		edit.clear();
		edit.apply();
	}

}
