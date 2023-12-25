package com.supretest.api.dto.ssl;

import com.supretest.api.dto.scenario.request.BodyFile;
import lombok.Data;

@Data
public class KeyStoreFile {
    private String id;
    private String name;
    private String type;
    private String updateTime;
    private String password;
    private BodyFile file;

}
