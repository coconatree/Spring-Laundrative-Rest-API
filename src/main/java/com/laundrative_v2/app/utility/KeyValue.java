package com.laundrative_v2.app.utility;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KeyValue {
	private String key;
	private String value;

	public KeyValue(String key, Object value) {
		this.key = key;
		this.value = value.toString();
	}
}
