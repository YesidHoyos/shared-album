package com.woloxgram.sharedalbum.util.enums;

public enum AccessEnum {
	WRITE("write"),
	READ("read");
	
	public final String value;

    private AccessEnum(String value) {
        this.value = value;
    }
}
