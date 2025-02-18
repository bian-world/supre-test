package com.supretest.log.aspect;

import com.alibaba.fastjson.JSON;
import com.supretest.base.domain.OperatingLogWithBLOBs;
import com.supretest.commons.utils.LogUtil;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.log.annotation.MsAuditLog;
import com.supretest.log.service.OperatingLogService;
import com.supretest.log.utils.ReflexObjectUtil;
import com.supretest.log.vo.DetailColumn;
import com.supretest.log.vo.OperatingLogDetails;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

/**
 * 系统日志：切面处理类
 */
@Aspect
@Component
public class MsLogAspect {
    /**
     * 解析spel表达式
     */
    ExpressionParser parser = new SpelExpressionParser();
    /**
     * 将方法参数纳入Spring管理
     */
    LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    @Autowired
    private ApplicationContext applicationContext;

    @Resource
    private OperatingLogService operatingLogService;

    private ThreadLocal<String> beforeValue = new ThreadLocal<>();
    private ThreadLocal<String> operUser = new ThreadLocal<>();

    /**
     * 定义切点 @Pointcut 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.supretest.log.annotation.MsAuditLog)")
    public void logPoinCut() {
    }

    @Before("logPoinCut()")
    public void before(JoinPoint joinPoint) {
        try {
            //从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = signature.getMethod();
            //获取参数对象数组
            Object[] args = joinPoint.getArgs();
            MsAuditLog msLog = method.getAnnotation(MsAuditLog.class);
            if (msLog != null && StringUtils.isNotEmpty(msLog.beforeEvent())) {
                //获取方法参数名
                String[] params = discoverer.getParameterNames(method);
                //将参数纳入Spring管理
                EvaluationContext context = new StandardEvaluationContext();
                for (int len = 0; len < params.length; len++) {
                    context.setVariable(params[len], args[len]);
                }
                boolean isNext = false;
                for (Class clazz : msLog.msClass()) {
                    if (clazz.getName().equals("com.supretest.commons.utils.SessionUtils")) {
                        operUser.set(SessionUtils.getUserId());
                        continue;
                    }
                    context.setVariable("msClass", applicationContext.getBean(clazz));
                    isNext = true;
                }
                if (isNext) {
                    Expression expression = parser.parseExpression(msLog.beforeEvent());
                    String beforeContent = expression.getValue(context, String.class);
                    beforeValue.set(beforeContent);
                }
            }
        } catch (Exception e) {
            LogUtil.error("操作日志写入异常：" + joinPoint.getSignature());
        }
    }

    /**
     * 切面 配置通知
     */
    @AfterReturning("logPoinCut()")
    public void saveLog(JoinPoint joinPoint) {
        try {
            //从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = signature.getMethod();
            //获取参数对象数组
            Object[] args = joinPoint.getArgs();

            //获取操作
            MsAuditLog msLog = method.getAnnotation(MsAuditLog.class);
            if (msLog != null) {
                //保存日志
                OperatingLogWithBLOBs msOperLog = new OperatingLogWithBLOBs();

                //保存获取的操作
                msOperLog.setId(UUID.randomUUID().toString());
                // 操作类型
                msOperLog.setOperType(msLog.type().name());
                // 项目ID
                msOperLog.setProjectId(msLog.project());

                String module = msLog.module();
                msOperLog.setOperModule(StringUtils.isNotEmpty(module) ? module : msLog.module());
                //获取方法参数名
                String[] params = discoverer.getParameterNames(method);
                //将参数纳入Spring管理
                EvaluationContext context = new StandardEvaluationContext();
                for (int len = 0; len < params.length; len++) {
                    context.setVariable(params[len], args[len]);
                }

                for (Class clazz : msLog.msClass()) {
                    if (clazz.getName().equals("com.supretest.commons.utils.SessionUtils")) {
                        continue;
                    }
                    context.setVariable("msClass", applicationContext.getBean(clazz));
                }
                // 项目ID 表达式
                try {
                    Expression titleExp = parser.parseExpression(msLog.project());
                    String project = titleExp.getValue(context, String.class);
                    msOperLog.setProjectId(project);
                } catch (Exception e) {
                    msOperLog.setProjectId(msLog.project());
                }
                // 标题
                if (StringUtils.isNotEmpty(msLog.title())) {
                    String title = msLog.title();
                    try {
                        Expression titleExp = parser.parseExpression(title);
                        title = titleExp.getValue(context, String.class);
                        msOperLog.setOperTitle(title);
                    } catch (Exception e) {
                        msOperLog.setOperTitle(title);
                    }
                }
                // 资源ID
                if (StringUtils.isNotEmpty(msLog.sourceId())) {
                    try {
                        String sourceId = msLog.sourceId();
                        Expression titleExp = parser.parseExpression(sourceId);
                        sourceId = titleExp.getValue(context, String.class);
                        msOperLog.setSourceId(sourceId);
                    } catch (Exception e) {
                    }
                }

                // 操作内容
                String beforeValue = this.beforeValue.get();
                if (StringUtils.isNotEmpty(msLog.content())) {
                    Expression expression = parser.parseExpression(msLog.content());
                    String content = expression.getValue(context, String.class);
                    try {
                        if (StringUtils.isNotEmpty(content)) {
                            OperatingLogDetails details = JSON.parseObject(content, OperatingLogDetails.class);
                            if (StringUtils.isNotEmpty(details.getProjectId())) {
                                msOperLog.setProjectId(details.getProjectId());
                            }
                            if (StringUtils.isEmpty(msLog.title())) {
                                msOperLog.setOperTitle(details.getTitle());
                            }
                            msOperLog.setSourceId(details.getSourceId());
                            msOperLog.setCreateUser(details.getCreateUser());
                        }
                        if (StringUtils.isNotEmpty(content) && StringUtils.isNotEmpty(beforeValue)) {
                            OperatingLogDetails details = JSON.parseObject(content, OperatingLogDetails.class);
                            List<DetailColumn> columns = ReflexObjectUtil.compared(JSON.parseObject(beforeValue, OperatingLogDetails.class), details, msLog.module());
                            details.setColumns(columns);
                            msOperLog.setOperContent(JSON.toJSONString(details));
                            msOperLog.setSourceId(details.getSourceId());
                            msOperLog.setCreateUser(details.getCreateUser());
                        } else {
                            msOperLog.setOperContent(content);
                        }
                    } catch (Exception e) {
                        msOperLog.setOperContent(content);
                    }
                }
                // 只有前置操作的处理/如 删除操作
                if (StringUtils.isNotEmpty(msLog.beforeEvent()) && StringUtils.isNotEmpty(beforeValue) && StringUtils.isEmpty(msLog.content())) {
                    msOperLog.setOperContent(beforeValue);
                    OperatingLogDetails details = JSON.parseObject(beforeValue, OperatingLogDetails.class);
                    if (StringUtils.isEmpty(msLog.title())) {
                        msOperLog.setOperTitle(details.getTitle());
                    }
                    if (StringUtils.isNotEmpty(details.getProjectId())) {
                        msOperLog.setProjectId(details.getProjectId());
                    }
                    msOperLog.setSourceId(details.getSourceId());
                    msOperLog.setCreateUser(details.getCreateUser());
                }

                //获取请求的类名
                String className = joinPoint.getTarget().getClass().getName();
                //获取请求的方法名
                String methodName = method.getName();
                msOperLog.setOperMethod(className + "." + methodName);

                msOperLog.setOperTime(System.currentTimeMillis());
                //获取用户名
                if (StringUtils.isNotEmpty(operUser.get())) {
                    msOperLog.setOperUser(operUser.get());
                } else {
                    msOperLog.setOperUser(SessionUtils.getUserId());
                }
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                // 特殊情况处理
                if (StringUtils.isEmpty(msOperLog.getOperTitle())) {
                    Object title = request.getAttribute("ms-req-title");
                    if (title != null) {
                        msOperLog.setOperTitle(title.toString());
                    }
                }
                if (StringUtils.isEmpty(msOperLog.getSourceId())) {
                    Object sourceId = request.getAttribute("ms-req-source-id");
                    if (sourceId != null) {
                        msOperLog.setSourceId(sourceId.toString());
                    }
                }

                String path = request.getServletPath();
                if (StringUtils.isNotEmpty(msOperLog.getOperTitle()) && msOperLog.getOperTitle().length() > 6000) {
                    msOperLog.setOperTitle(msOperLog.getOperTitle().substring(0, 5999));
                }
                msOperLog.setOperPath(path);
                operatingLogService.create(msOperLog, msOperLog.getSourceId());
            }
        } catch (Exception e) {
            LogUtil.error("操作日志写入异常：" + joinPoint.getSignature());
        } finally {
            operUser.remove();
            beforeValue.remove();
        }
    }
}
