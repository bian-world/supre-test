package com.supretest.track.issue;

import com.supretest.track.issue.client.ZentaoClient;
import com.supretest.track.issue.client.ZentaoGetClient;
import com.supretest.track.issue.client.ZentaoPathInfoClient;
import org.apache.commons.lang3.StringUtils;

public class ZentaoFactory {

    public static ZentaoClient getInstance(String url, String type) {
        if (StringUtils.equals(type, "PATH_INFO")) {
            return new ZentaoPathInfoClient(url);
        } else if (StringUtils.equals(type, "GET")) {
            return new ZentaoGetClient(url);
        }
        return new ZentaoPathInfoClient(url);
    }
}
