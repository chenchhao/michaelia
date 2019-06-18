package com.michaelia.emma.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
* <p>标题: DictUtil.java</p>
* <p>业务描述:字典转换工具 </p>
* <p>公司:北京瑞华康源科技有限公司</p>
* <p>版权:rivamed-2018</p>
* @author 李飞
* @date 2018年12月19日
* @version V1.0 
*/
public class DictUtil {
	
	private static Log log = LogFactory.getLog(DictUtil.class);
	
	// 私有构造函数
	private DictUtil() {
		
	}
	
	private enum DictEnum {
		INSTANCE;
		
		private DictUtil dictUtil;
		// JVM保证此方法绝对只调用一次
		DictEnum() {
			dictUtil = new DictUtil();
		}
		
		public DictUtil getInstance() {
			return dictUtil;
		}
	}
	
	public static DictUtil getInstance() {
		return DictEnum.INSTANCE.getInstance();
	}
	
	public void dictConvert(List<?> list, Map<String, Map<Integer, String>> dictMap, String suffix) {
		if (null == list || list.isEmpty()) {
			return;
		}
		if (null == dictMap || dictMap.isEmpty()) {
			return;
		}
		
		for (Object obj : list) {
			this.dictConvert(obj, dictMap, suffix);
		}
	}
	
	private void dictConvert(Object obj, Map<String, Map<Integer, String>> dictMap, String suffix) {
		if (null == obj) {
			return;
		}
		if (null == dictMap || dictMap.isEmpty()) {
			return;
		}
		
		try {
			for (Entry<String, Map<Integer, String>> entry : dictMap.entrySet()) {
				Object attrValue = BeanUtils.getInstance().getProperty(obj, entry.getKey());
				if (null == attrValue) {
					continue;
				}
				BeanUtils.getInstance().setProperty(obj, entry.getKey() + suffix, entry.getValue().get((Integer)attrValue));
				attrValue = null;
			}
		} catch (NoSuchFieldException e) {
			log.error(e.getMessage(), e);
		}
	}
	
}
