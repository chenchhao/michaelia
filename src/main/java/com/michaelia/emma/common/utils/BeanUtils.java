package com.michaelia.emma.common.utils;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import java.lang.reflect.Field;

/**
* <p>标题: BeanUtils.java</p>
* <p>业务描述:提供一些反射方面缺失功能的封装 </p>
* <p>公司:北京瑞华康源科技有限公司</p>
* <p>版权:rivamed-2018</p>
* @author 李飞
* @date 2018年12月19日
* @version V1.0 
*/
@SuppressWarnings("rawtypes")
public class BeanUtils {

	private static Log log = LogFactory.getLog(BeanUtils.class);
	
	private BeanUtils() {
		
	}
	
	private enum BeanEnum {
		INSTANCE;
		private BeanUtils beanUtils;
		
		BeanEnum() {
			beanUtils = new BeanUtils();
		}
		
		public BeanUtils getInstance() {
			return beanUtils;
		}
	}
	
	public static BeanUtils getInstance() {
		return BeanEnum.INSTANCE.getInstance();
	}
	
	/** 
	* 方法名:          getDeclaredField
	* 方法功能描述:    循环向上转型,获取对象的DeclaredField
	* @param:         
	* @return:        
	* @Author:        李飞
	* @Create Date:   2018年12月19日 下午2:14:58
	*/
	public Field getDeclaredField(Object object, String attrName)
			throws NoSuchFieldException {
		Assert.notNull(object, "The class must not be null");
		Assert.hasText(attrName, "'"+attrName+"' must not be empty");
		return getDeclaredField(object.getClass(), attrName);
	}
	
	/** 
	* 方法名:          getDeclaredField
	* 方法功能描述:    循环向上转型,获取对象的DeclaredField
	* @param:         
	* @return:        
	* @Author:        李飞
	* @Create Date:   2018年12月19日 下午2:15:32
	*/
	public Field getDeclaredField(Class clazz, String attrName)
			throws NoSuchFieldException {
		Assert.notNull(clazz, "The class must not be null");
		Assert.hasText(attrName, "'"+attrName+"' must not be empty");
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(attrName);
			} catch (NoSuchFieldException e) {
				log.error(e.getMessage(),e);
			}
		}
		throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + attrName);
	}
	
	/** 
	* 方法名:          getProperty
	* 方法功能描述:    获取对象变量值,忽略private,protected修饰符的限制
	* @param:         
	* @return:        
	* @Author:        李飞
	* @Create Date:   2018年12月19日 下午2:17:23
	*/
	public Object getProperty(Object object, String attrName)
			throws NoSuchFieldException {
		Assert.notNull(object, "The class must not be null");
		Assert.hasText(attrName, "'"+attrName+"' must not be empty");
		Object result = null;
		try {
			result = BeanUtilsBean.getInstance().getPropertyUtils().getProperty(object, attrName);
			return result;
		} catch (Exception e) {
			log.error(e);
		}
		
		Field field = getDeclaredField(object, attrName);
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			log.info(e.getMessage());
		}
		field.setAccessible(accessible);
		return result;
	}
	
	public void setProperty(Object object, String attrName,
			Object newValue) throws NoSuchFieldException {
		Assert.notNull(object, "The class must not be null");
		Assert.hasText(attrName, "'"+attrName+"' must not be empty");

		try {
			BeanUtilsBean.getInstance().getPropertyUtils().setProperty(object,
					attrName, newValue);
			return;
		} catch (Exception e) {
			//log.error(e.getMessage(),e);
		}

		Field field = getDeclaredField(object, attrName);
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		try {
			field.set(object, newValue);
		} catch (IllegalAccessException e) {
			log.info("Error won't happen");
		}
		field.setAccessible(accessible);
	}
}
