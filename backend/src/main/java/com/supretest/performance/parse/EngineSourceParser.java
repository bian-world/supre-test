package com.supretest.performance.parse;

import com.supretest.performance.engine.EngineContext;

import java.io.InputStream;

public interface EngineSourceParser {
    byte[] parse(EngineContext context, InputStream source) throws Exception;
}
