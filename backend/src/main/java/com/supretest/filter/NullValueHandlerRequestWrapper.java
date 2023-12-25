package com.supretest.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

public class NullValueHandlerRequestWrapper extends HttpServletRequestWrapper {

    public NullValueHandlerRequestWrapper(HttpServletRequest request) {
        super(request);

    }

    private Map<String, String[]> parameterMap;

    public void setParameterMap(Map<String, String[]> parameterMap) {
        this.parameterMap = parameterMap;
    }

    @Override
    public String getParameter(String name) {
        String[] results = parameterMap.get(name);
        return results[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {

        return this.parameterMap;
    }

    @Override
    public Enumeration<String> getParameterNames() {

        Vector<String> vector = new Vector<String>(parameterMap.keySet());
        return vector.elements();
    }

    @Override
    public String[] getParameterValues(String name) {

        return parameterMap.get(name);
    }

}
