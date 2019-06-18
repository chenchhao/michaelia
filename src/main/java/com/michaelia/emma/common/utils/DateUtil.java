package com.michaelia.emma.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
* <p>标题: DateUtil.java</p>
* <p>业务描述:医疗物资综合管理系统 </p>
* <p>公司:北京瑞华康源科技有限公司</p>
* <p>版权:rivamed-2018</p>
* @author 李飞
* @date 2018年9月1日
* @version V1.0 
*/
public class DateUtil {
	
	private DateUtil() {
		
	}
	
	private enum DateEnum {
		INSTANCE;
		private DateUtil dateUtil;
		
		DateEnum() {
			dateUtil = new DateUtil();
		}
		
		public DateUtil getInstance() {
			return dateUtil;
		}
	}
	
	public static DateUtil getInstance() {
		return DateEnum.INSTANCE.getInstance();
	}
	
	/** 
	* 方法名:          getStartTimeByDate
	* 方法功能描述:    获取指定日期的终止时刻
	* @param:         
	* @return:        
	* @Author:        李飞
	* @Create Date:   2018年9月1日 下午5:07:10
	*/
	public Date getStartTimeByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);

		return cal.getTime();
	}
	
	/** 
	* 方法名:          getEndTimeByDate
	* 方法功能描述:    获取指定日期的终止时刻
	* @param:         
	* @return:        
	* @Author:        李飞
	* @Create Date:   2018年9月1日 下午5:07:10
	*/
	public Date getEndTimeByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);

		return cal.getTime();
	}

}
