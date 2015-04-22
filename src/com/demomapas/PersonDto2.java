package com.demomapas;

import com.virtualef.pgj.service.commandmentService.model.Key;

public class PersonDto2 {
	  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
	  private java.lang.Long age;
	  @com.google.api.client.util.Key
	  private java.lang.String firstName;
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
	  private java.lang.String lastName;
	  @com.google.api.client.util.Key
	  private java.lang.String name;
	  @com.google.api.client.util.Key
	  private java.lang.String sex;
	public java.lang.Long getAge() {
		return age;
	}
	public void setAge(java.lang.Long age) {
		this.age = age;
	}
	public java.lang.String getFirstName() {
		return firstName;
	}
	public void setFirstName(java.lang.String firstName) {
		this.firstName = firstName;
	}

	public java.lang.String getLastName() {
		return lastName;
	}
	public void setLastName(java.lang.String lastName) {
		this.lastName = lastName;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getSex() {
		return sex;
	}
	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}
	  
	  

}
