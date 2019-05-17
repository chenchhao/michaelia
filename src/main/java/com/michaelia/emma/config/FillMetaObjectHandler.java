package com.michaelia.emma.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.michaelia.emma.entity.sys.SysUser;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FillMetaObjectHandler implements MetaObjectHandler {

    //新增填充
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createBy = metaObject.getValue("createBy");
        //定时任务时，是不会有用户，在使用定时任务，save的时候只需要creteBy赋值即可
        if(createBy==null){
            //获取当前登录用户
            SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
            if (user != null) {
                metaObject.setValue("createBy", user.getId());
            }
        }
        metaObject.setValue("createTime", new Date());
    }

    //更新填充
    @Override
    public void updateFill(MetaObject metaObject) {
        //获取当前登录用户
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (user != null) {
            this.setFieldValByName("updateBy", user.getId(), metaObject);
        }
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
