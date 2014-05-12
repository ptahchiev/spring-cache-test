package com.test.cache;

import java.io.Serializable;

public class SitePojo implements Serializable
{

	private String uid;
	
	private String url;

	/* getters/setters */
	
	public String getUid()
	{
		return uid;
	}

	public void setUid(String uid)
	{
		this.uid = uid;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}
	
	
}
