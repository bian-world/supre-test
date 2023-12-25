package com.supretest.api.parse.old;

import com.supretest.api.dto.ApiTestImportRequest;
import com.supretest.api.dto.definition.parse.ApiDefinitionImport;
import com.supretest.api.dto.parse.ApiImport;

import java.io.InputStream;

public interface ApiImportParser {
    ApiImport parse(InputStream source, ApiTestImportRequest request);
    ApiDefinitionImport parseApi(InputStream source, ApiTestImportRequest request);

}
