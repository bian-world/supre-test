package com.supretest.commons.utils;

import java.lang.reflect.Method;

public class LicenseUtils {
    public static boolean valid() {
        try {
            Class<?> aClass = Class.forName("com.supretest.xpack.license.util.LicenseCache");
            Method get = aClass.getMethod("valid");
            return (boolean) get.invoke(null);
        } catch (Exception e) {
            LogUtil.error(e);
            return false;
        }
    }
}
