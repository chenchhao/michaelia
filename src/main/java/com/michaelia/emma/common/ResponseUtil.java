package com.michaelia.emma.common;


public class ResponseUtil {

	public static <T> Response<T> success(T object) {
		Response<T> result = new Response<T>();
		result.setCode(0);
		result.setMessage("请求成功");
		result.setData(object);
		return result;
	}

	public static Response<?> success() {
		return success(null);
	}

	public static <T> Response<T> fail(Integer code, String msg) {
		Response<T> result = new Response<T>();
		result.setCode(code);
		result.setMessage(msg);
		return result;
	}
}
