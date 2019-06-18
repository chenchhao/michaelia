package com.michaelia.emma.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public enum GlobleEnum implements IEnum<Serializable> {
    SYS_SYSTEM_ERROR("sys", 10000, "未知异常"),
    SYS_LOGIN_SUCESS("sys", 10001, "登录成功"),
    SYS_LOGOUT_SUCESS("sys", 10002, "退出成功"),
    SYS_UNLOGIN("sys", 10003, "用户未登录"),
    SYS_USER_LOCKED("sys", 10004, "用户已冻结"),
    SYS_UNKNOWN_ACCOUNT("sys", 10005, "用户名不存在"),
    SYS_INCORRECT_CREDENTIALS("sys", 10006, "密码错误"),
    SYS_UNAUTHORIZED("sys", 10007, "您无权访问该资源，请联系管理员"),
    SYS_VALIDATE_ERROR("sys", 10010, "表单验证失败"),
    SYS_ROLE_USED("sys", 10020, "角色已关联用户，不能删除"),
    SYS_ROLENAME_ALREADY_EXISTS("sys", 10021, "该角色名称已经存在"),
    SYS_ROLECODE_ALREADY_EXISTS("sys", 10022, "该角色编码已经存在"),
    SYS_USER_OLDPASSWORLD_ERROR("sys", 10023, "原密码错误"),
    SYS_NO_FILE("sys", 10030, "未接收到文件，请重传"),
    SYS_IMPORT_ZERO_DATA("sys", 10031, "本次共导入0条数据，请检查Excel模板及数据是否正确填写"),
    SYS_EXCEL_ERROR("sys", 10032, "您选择的Excel模板错误"),
    SYS_ILLEGAL_ARGUMENT("sys", 10040, "参数错误"),
    SYS_ILLEGAL_FILE("sys", 10061, "文件类型不符"),
    SYS_OPE_FAILD("sys", 10050, "系统繁忙，请稍后再试"),
    SYS_NO_DATA("sys", 10060, "数据为空"),
    ////////////////////////////////////////////////////////////////
    SURGERY_ROOMCODE_ALREADY_EXISTS("surgery", 20000, "术间编码已存在"),
    SURGERY_ROOMNAME_ALREADY_EXISTS("surgery", 20001, "术间名称已存在"),
    SURGERY_ROOM_ALREADY_UESD("surgery", 20002, "手术间已关联库房，必须先删除库房，才能删除手术间"),
    /////////////////////////////////////////////////////////
    WAREHOUSE_WAREHOUSECODE_ALREADY_EXISTS("warehouse", 30001, "库房编码已存在"),
    WAREHOUSE_WAREHOUSENAME_ALREADY_EXISTS("warehouse", 30002, "库房名称已存在"),
    WAREHOUSE_WAREHOUSE_ALREADY_DISTRIBUTION("warehouse", 30003, "您所选的手术间已关联其他库房"),
    WAREHOUSE_SHELVESCODE_ALREADY_EXISTS("warehouse", 30004, "货架编码已存在"),
    WAREHOUSE_SHELVESAREA_ALREADY_EXISTS("warehouse", 30005, "该货架区域数据已存在"),
    WAREHOUSE_SETTYPE_ALREADY_EXISTS("warehouse", 30006, "该器械包期效预警配置已存在"),
    WAREHOUSE_SETNUMBER_ALREADY_EXISTS("warehouse", 30007, "该器械包数量预警配置已存在"),
    WAREHOUSE_SHELVES_ALREADY_UESD("warehouse", 30008, "库房已关联货架，必须先删除货架，才能删除库房"),
    WAREHOUSE_SHELVESAREA_ALREADY_UESD("warehouse", 30009, "货架已关联货架区域，必须先删除区域，才能删除货架"),
    WAREHOUSE_SHELVESAREA_ALREADY_BINDING("warehouse", 30010, "该货架区域已分配给器械包使用，必须先取消关联，才能删除"),
    /**********************************************************/
    OPERATION_OPERATIONNAME_ALREADY_EXISTS("operation", 40000, "手术名称已存在"),
    OPERATION_OPERATIONDATE_ALREADY_EXISTS("operation", 40001, "手术日期已存在"),
    OPERATION_OPERATIONRULES_ALREADY_EXISTS("operation", 40002, "术式规则已存在"),
    OPERATION_OPERATIONRULE_RELA_ZERO_SET("operation", 40003, "术式规则必须关联至少一个器械包"),
    OPERATION_OPERATIONSET_ALREADY_EXISTS("operation", 40004, "术式规则已存在该器械包"),
    OPERATION_OPERATIONINFO_ALREADY_USED("operation", 40005, "手术信息已关联术式规则，必须先删除术式规则，才能删除手术信息"),
    OPERATION_OPERATIONRULE_ALREADY_USED("operation", 40006, "术式规则已有手术排班正在使用中"),
    /*********************************************************************/
    INSTRUMENT_INSTRUMENT_ALREADY_EXISTS("instrument", 50000, "该器械已存在，请核对后重新录入"),
    INSTRUMENT_SETNAME_ALREADY_EXISTS("instrument", 50001, "器械包名称已存在"),
    INSTRUMENT_INSTRUMENTSET_ALREADY_EXISTS("instrument", 50002, "器械包不可重复配置同一器械"),
    INSTRUMENT_SHELVESSET_ALREADY_EXISTS("instrument", 50003, "该货架已关联其他器械包，不能再分配给当前器械包"),
    INSTRUMENT_EPC_ALREADY_EXISTS("instrument", 50004, "该EPC编码的器械包单品已存在，不能重复注册"),
    INSTRUMENT_INSTRUMENT_ALREADY_BINDING("instrument", 50005, "该器械已绑定器械包，必须先删除器械包，才能删除器械"),
    INSTRUMENT_SET_ALREADY_BINDING("instrument", 50006, "该器械包规格已绑定术式规则，不能删除"),
    INSTRUMENT_SET_ALREADY_USED("instrument", 50007, "该器械包规格已注册有器械包单品，不能删除"),
    INSTRUMENT_CODE_ALREADY_EXISTS("instrument", 50008, "器械包编码已经存在，请重新输入"),
    SINGLE_NOT_ALLOWED_DELETE("instrument", 50009, "该状态不允许删除"),
    SINGLE_IMPORT_FAIL("instrument", 50010, "器械包单品导入失败"),
    WAREHOUSE_AND_SHELVES_ERROR("instrument", 50011, "库房和货架没有绑定"),
    /*********************************************************************/
    DOCTOR_DOCTORNO_ALREADY_EXISTS("doctor", 60000, "医生编码已存在"),
    DOCTOR_ALREADY_USED("doctor", 60001, "医生信息已关联术式规则，必须先删除术式规则，才能删除医生信息"),

    /*********************************************************************/
    DICT_ALREADY_EXISTS("dict", 70000, "系统字典值已存在"),

    /*********************************************************************/
    ILLEGAL_STATEMENT_UPDATE("bh", 90000, "补货单已提交，不能编辑"),
    ILLEGAL_STATEMENT_DELETE("bh", 90001, "补货单已提交，不能删除"),
    ILLEGAL_STATEMENT_CLOSE("bh", 90002, "当前状态不能关闭");

    public static String getMessageByCode(Integer code) {
        for (GlobleEnum c : GlobleEnum.values()) {
            if (c.getCode() == code) {
                return c.message;
            }
        }
        return null;
    }

    private String model; //模块名称
    private Integer code; //编码
    private String message; //信息

    private GlobleEnum(String model, Integer code, String message) {
        this.model = model;
        this.code = code;
        this.message = message;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Serializable getValue() {
        return null;
    }

}
