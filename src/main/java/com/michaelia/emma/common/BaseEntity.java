package com.michaelia.emma.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.ID_WORKER)
	@ApiModelProperty(value = "主键")
	@JsonSerialize(using= ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "创建时间", hidden = true)
	@TableField(fill=FieldFill.INSERT)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;

	@ApiModelProperty(value = "创建人", hidden = true)
	@TableField(fill=FieldFill.INSERT)
	@JsonSerialize(using= ToStringSerializer.class)
	private Long createBy;

	@ApiModelProperty(value = "更新时间", hidden=true)
	@TableField(fill=FieldFill.UPDATE)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date updateTime;

	@ApiModelProperty(value = "更新人", hidden = true)
	@TableField(fill=FieldFill.UPDATE)
	@JsonSerialize(using= ToStringSerializer.class)
	private Long updateBy;
	
	@ApiModelProperty(value = "页码")
	@TableField(exist=false)
	private int currentPage=1;
	
	@ApiModelProperty(value = "每页条数")
	@TableField(exist=false)
	private int pageSize=10;
	
}
