package com.wan.upms.client.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by w1992wishes on 2017/8/24.
 */
public class RequestParameterUtil {
    /**
     * 移除url中的token参数
     * @param request
     * @return
     */
    public static String getParameterWithOutToken(HttpServletRequest request) {
        StringBuffer backUrl = request.getRequestURL();
        String params = "";
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            if (!entry.getKey().equals("token")) {
                if (params.equals("")) {
                    params = entry.getKey() + "=" + entry.getValue()[0];
                } else {
                    params += "&" + entry.getKey() + "=" + entry.getValue()[0];
                }
            }
        }
        if (!StringUtils.isEmpty(params)) {
            backUrl = backUrl.append("?").append(params);
        }
        return backUrl.toString();
    }

}
