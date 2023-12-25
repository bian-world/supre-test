package com.supretest.log.utils.diff.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.supretest.log.utils.diff.json.jsonwrap.gson.GsonWrapper;

public class GsonDiff extends JsonDiff {

	public GsonDiff() {
		super(new GsonWrapper());
	}

	public JsonObject diff(JsonElement from, JsonElement to) throws IllegalArgumentException {
		return (JsonObject) super.diff(from, to);
	}
}
