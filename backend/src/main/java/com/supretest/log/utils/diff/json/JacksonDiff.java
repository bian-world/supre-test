package com.supretest.log.utils.diff.json;

import com.supretest.log.utils.diff.json.jsonwrap.jackson.JacksonWrapper;

public class JacksonDiff extends JsonDiff {

	public JacksonDiff() {
		super(new JacksonWrapper());
	}

}
