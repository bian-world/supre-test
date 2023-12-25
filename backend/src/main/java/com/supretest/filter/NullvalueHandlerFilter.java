package com.supretest.filter;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

//@WebFilter(filterName = "NullvalueHandlerFilter",urlPatterns = "/*")
public class NullvalueHandlerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        //获得所有字段
        String urlParameters = ((HttpServletRequest) request).getPathInfo();
        String[] list = urlParameters.split("/");
        if(StringUtils.equals("null",list[list.length-1])){
            list[list.length-1] = null;
        }
//        Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
//        Iterator<Entry<String, String[]>> iterator = entrySet.iterator();
//        while (iterator.hasNext()) {
//            Entry<String, String[]> entry = iterator.next();
//            if (entry.getValue()!=null) {
//                for(int i=0;i<entry.getValue().length;i++) {
//                    if ("null".equals(entry.getValue()[i])) {
//                        iterator.remove();
//                    }
//                }

        //parameterMap就会将空字符串转成null.
        //注意：request对象是无法修改表单数据的！！ServletRequest对象是容Tomcat产生的，我们有没有办法new创建.

        NullValueHandlerRequestWrapper wrapper =new NullValueHandlerRequestWrapper((HttpServletRequest)request);
//        wrapper.setParameterMap(parameterMap);
        //获得重写后的请求
        chain.doFilter(wrapper, response);
    }

    @Override
    public void destroy() {

    }
}
