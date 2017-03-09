package com.merchantshengdacar.mvp.order;

import com.merchantshengdacar.common.BaseBean;

import java.io.Serializable;
import java.util.List;

public class OrderBean extends BaseBean {

	public List<DataBean>	orders;//	Array
	public String size;//	309
	
	public static class DataBean implements Serializable{
		
		public String activityName;//	洗车（小车）
		public String appointmentData;//	
		public String date;//	2017-03-02 15:10:40
		public String endTime;//	
		public String id;//	5769087
		public String imgicon;//	
		public String orderId;//	1000CESHI000201612300001758631
		public byte orderStatus;//	5
		public String tel;//	1
		public String userName;//	test161215
	}
}
