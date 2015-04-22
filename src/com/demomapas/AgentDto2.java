package com.demomapas;

import com.virtualef.pgj.service.commandmentService.model.Key;

public class AgentDto2 {
	
	  @com.google.api.client.util.Key
	  private java.lang.String alias;
	  
	  @com.google.api.client.util.Key
	  private java.lang.String aliasP;
	  
//	  @com.google.api.client.util.Key
//	  private Key key;
	  public class Key{
		  @com.google.api.client.util.Key
		  private java.lang.String appId;

		  /**
		   * The value may be {@code null}.
		   */
		  @com.google.api.client.util.Key
		  private java.lang.Boolean complete;

		  /**
		   * The value may be {@code null}.
		   */
		  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
		  private java.lang.Long id;

		  /**
		   * The value may be {@code null}.
		   */
		  @com.google.api.client.util.Key
		  private java.lang.String kind;

		  /**
		   * The value may be {@code null}.
		   */
		  @com.google.api.client.util.Key
		  private java.lang.String name;

		  /**
		   * The value may be {@code null}.
		   */
		  @com.google.api.client.util.Key
		  private java.lang.String namespace;

		  /**
		   * The value may be {@code null}.
		   */
		  @com.google.api.client.util.Key
		  private Key parent;
		  
		  
	  }
	  
	  @com.google.api.client.util.Key
	  private java.lang.Float latitude;
	  
	  @com.google.api.client.util.Key
	  private java.lang.Float longitude;
	  
	 public class PersonDto{
		  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
		  private java.lang.Long age;
		  @com.google.api.client.util.Key
		  private java.lang.String firstName;
		  @com.google.api.client.util.Key
		  private Key key;
		  @com.google.api.client.util.Key
		  private java.lang.String lastName;
		  @com.google.api.client.util.Key
		  private java.lang.String name;
		  @com.google.api.client.util.Key
		  private java.lang.String sex;
		 
	 }
	  
	  @com.google.api.client.util.Key
	  private java.lang.String tuition;

	public java.lang.String getAlias() {
		return alias;
	}

	public void setAlias(java.lang.String alias) {
		this.alias = alias;
	}

	public java.lang.String getAliasP() {
		return aliasP;
	}

	public void setAliasP(java.lang.String aliasP) {
		this.aliasP = aliasP;
	}




	public java.lang.Float getLatitude() {
		return latitude;
	}

	public void setLatitude(java.lang.Float latitude) {
		this.latitude = latitude;
	}

	public java.lang.Float getLongitude() {
		return longitude;
	}

	public void setLongitude(java.lang.Float longitude) {
		this.longitude = longitude;
	}

	public java.lang.String getTuition() {
		return tuition;
	}

	public void setTuition(java.lang.String tuition) {
		this.tuition = tuition;
	}
	  
	  

}
