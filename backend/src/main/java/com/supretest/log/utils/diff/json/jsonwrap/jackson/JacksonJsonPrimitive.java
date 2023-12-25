package com.supretest.log.utils.diff.json.jsonwrap.jackson;

import org.codehaus.jackson.node.ValueNode;

import com.supretest.log.utils.diff.json.jsonwrap.JzonPrimitive;


public class JacksonJsonPrimitive extends JacksonJsonElement implements JzonPrimitive {

    public JacksonJsonPrimitive(ValueNode wrapped) {
        super(wrapped);
    }

}
