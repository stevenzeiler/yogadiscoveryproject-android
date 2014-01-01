package com.yogadiscovery;

import java.util.UUID;

public class Studio {
    private String mName;
    private UUID mId;
    
	Studio (String mName) {
		this.mName = mName;
	}

    public UUID getId() {
    	return mId;
    }
    public String getName() {
   	    return this.mName;
    }

    public void setName(String mName) {
        this.mName= mName;
    }
}
