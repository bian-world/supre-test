package com.supretest.log.utils.diff.json.jsonwrap.gson;

import com.google.gson.JsonPrimitive;

import com.supretest.log.utils.diff.json.jsonwrap.JzonPrimitive;


public class GsonJsonPrimitive extends GsonJsonElement implements JzonPrimitive {

    public GsonJsonPrimitive(JsonPrimitive wrapped) {
        super(wrapped);
    }

}
