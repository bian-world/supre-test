package com.supretest.log.annotation;

import com.supretest.commons.constants.OperLogConstants;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * MsAuditLog class
 * @author mr.zhao
 * @date 2021/05/06
 */
public @interface MsAuditLog {
    /**
     * 功能模块
     *
     * @return
     */
    String module();

    /**
     * 项目
     *
     * @return
     */
    String project() default "";

    /**
     * 操作类型
     *
     * @return
     */
    OperLogConstants type() default OperLogConstants.OTHER;

    /**
     * 标题
     */
    String title() default "";

    /**
     * 资源ID
     */
    String sourceId() default "";


    /**
     * 操作内容
     *
     * @return
     */
    String content() default "";

    /**
     * 操作前触发内容
     *
     * @return
     */
    String beforeEvent() default "";

    /**
     * 传入执行类
     *
     * @return
     */
    Class[] msClass() default {};

}
