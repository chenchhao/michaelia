package com.michaelia.emma.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private Integer code;
	
	public BusinessException(Integer code,String message) {
		super(message);
        this.code = code;
	}
}
