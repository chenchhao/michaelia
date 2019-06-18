package com.michaelia.emma.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Response<T> {
//错误码
private Integer code;
//消息
private String message;
//数据
private T data;
	

}
