package com.demomapas;

import com.virtualef.pgj.service.commandmentService.model.AgentDto;
import com.virtualef.pgj.service.commandmentService.model.Key;
import com.virtualef.pgj.service.commandmentService.model.PersonDto;
import com.virtualef.pgj.service.commandmentService.model.RequireDto;

public class commandmentdto2 {
	 
	 
	  @com.google.api.client.util.Key
	  private java.lang.String address;
	  
	  @com.google.api.client.util.Key
	  private AgentDto agent;
	  
	 public class AgentDto{
		 @com.google.api.client.util.Key
		  private java.lang.String alias;

		  /**
		   * The value may be {@code null}.
		   */
		  @com.google.api.client.util.Key
		  private java.lang.String aliasP;

		  /**
		   * The value may be {@code null}.
		   */
		  @com.google.api.client.util.Key
		  private Key key;

		  /**
		   * The value may be {@code null}.
		   */
		  @com.google.api.client.util.Key
		  private java.lang.Float latitude;

		  /**
		   * The value may be {@code null}.
		   */
		  @com.google.api.client.util.Key
		  private java.lang.Float longitude;

		  /**
		   * The value may be {@code null}.
		   */
		  @com.google.api.client.util.Key
		  private PersonDto person;
		  
		public class PersonDto{
			 @com.google.api.client.util.Key @com.google.api.client.json.JsonString
			  private java.lang.Long age;

			  /**
			   * The value may be {@code null}.
			   */
			  @com.google.api.client.util.Key
			  private java.lang.String firstName;

			  /**
			   * The value may be {@code null}.
			   */
			  @com.google.api.client.util.Key
			  private Key key;

			  /**
			   * The value may be {@code null}.
			   */
			  @com.google.api.client.util.Key
			  private java.lang.String lastName;

			  /**
			   * The value may be {@code null}.
			   */
			  @com.google.api.client.util.Key
			  private java.lang.String name;

			  /**
			   * The value may be {@code null}.
			   */
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

			public Key getKey() {
				return key;
			}

			public void setKey(Key key) {
				this.key = key;
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

		  /**
		   * The value may be {@code null}.
		   */
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

		public Key getKey() {
			return key;
		}

		public void setKey(Key key) {
			this.key = key;
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

		public PersonDto getPerson() {
			return person;
		}

		public void setPerson(PersonDto person) {
			this.person = person;
		}
		  
		  
		 
	 }      

	  
	  @com.google.api.client.util.Key
	  private java.lang.String area;

	 
	  @com.google.api.client.util.Key
	  private java.util.List<java.lang.String> audio;

	  
	  @com.google.api.client.util.Key
	  private java.lang.String commandmentType;

	 
	  @com.google.api.client.util.Key
	  private java.lang.String court;

	 
	  @com.google.api.client.util.Key
	  private java.lang.String crime;

	  
	  @com.google.api.client.util.Key
	  private java.lang.String date;

	  
	  @com.google.api.client.util.Key
	  private java.lang.String department;

	 
	  @com.google.api.client.util.Key
	  private java.util.List<java.lang.String> evidence;

	 
	  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
	  private java.lang.Long id;

	
	  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
	  private java.lang.Long idAgent;

	  
	  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
	  private java.lang.Long idRequire;

	
	  @com.google.api.client.util.Key
	  private java.lang.String inquest;

	  
	  @com.google.api.client.util.Key
	  private java.lang.String judge;

	  
	  @com.google.api.client.util.Key
	  private java.lang.Float latitude;

	 
	  @com.google.api.client.util.Key
	  private java.lang.Float longitude;

	  
	  @com.google.api.client.util.Key
	  private java.lang.String observations;

	 
	  @com.google.api.client.util.Key
	  private java.lang.String order;

	  
	  @com.google.api.client.util.Key
	  private java.lang.String record;

	  @com.google.api.client.util.Key
	  private RequireDto require;
	 public class RequireDto{
		  @com.google.api.client.util.Key
		  private java.lang.String alias;

		  /**
		   * The value may be {@code null}.
		   */
		  @com.google.api.client.util.Key
		  private Key key;

		  /**
		   * The value may be {@code null}.
		   */
		  @com.google.api.client.util.Key
		  private PersonDto person;
		 public class PersonDto{
			 @com.google.api.client.util.Key @com.google.api.client.json.JsonString
			  private java.lang.Long age;

			  /**
			   * The value may be {@code null}.
			   */
			  @com.google.api.client.util.Key
			  private java.lang.String firstName;

			  /**
			   * The value may be {@code null}.
			   */
			  @com.google.api.client.util.Key
			  private Key key;

			  /**
			   * The value may be {@code null}.
			   */
			  @com.google.api.client.util.Key
			  private java.lang.String lastName;

			  /**
			   * The value may be {@code null}.
			   */
			  @com.google.api.client.util.Key
			  private java.lang.String name;

			  /**
			   * The value may be {@code null}.
			   */
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

			public Key getKey() {
				return key;
			}

			public void setKey(Key key) {
				this.key = key;
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

		public java.lang.String getAlias() {
			return alias;
		}

		public void setAlias(java.lang.String alias) {
			this.alias = alias;
		}

		public Key getKey() {
			return key;
		}

		public void setKey(Key key) {
			this.key = key;
		}
		 
		 
	 }

	  
	  @com.google.api.client.util.Key
	  private java.lang.Boolean status;


	public java.lang.String getAddress() {
		return address;
	}


	public void setAddress(java.lang.String address) {
		this.address = address;
	}


	public java.lang.String getArea() {
		return area;
	}


	public void setArea(java.lang.String area) {
		this.area = area;
	}


	public java.util.List<java.lang.String> getAudio() {
		return audio;
	}


	public void setAudio(java.util.List<java.lang.String> audio) {
		this.audio = audio;
	}


	public java.lang.String getCommandmentType() {
		return commandmentType;
	}


	public void setCommandmentType(java.lang.String commandmentType) {
		this.commandmentType = commandmentType;
	}


	public java.lang.String getCourt() {
		return court;
	}


	public void setCourt(java.lang.String court) {
		this.court = court;
	}


	public java.lang.String getCrime() {
		return crime;
	}


	public void setCrime(java.lang.String crime) {
		this.crime = crime;
	}


	public java.lang.String getDate() {
		return date;
	}


	public void setDate(java.lang.String date) {
		this.date = date;
	}


	public java.lang.String getDepartment() {
		return department;
	}


	public void setDepartment(java.lang.String department) {
		this.department = department;
	}


	public java.util.List<java.lang.String> getEvidence() {
		return evidence;
	}


	public void setEvidence(java.util.List<java.lang.String> evidence) {
		this.evidence = evidence;
	}


	public java.lang.Long getId() {
		return id;
	}


	public void setId(java.lang.Long id) {
		this.id = id;
	}


	public java.lang.Long getIdAgent() {
		return idAgent;
	}


	public void setIdAgent(java.lang.Long idAgent) {
		this.idAgent = idAgent;
	}


	public java.lang.Long getIdRequire() {
		return idRequire;
	}


	public void setIdRequire(java.lang.Long idRequire) {
		this.idRequire = idRequire;
	}


	public java.lang.String getInquest() {
		return inquest;
	}


	public void setInquest(java.lang.String inquest) {
		this.inquest = inquest;
	}


	public java.lang.String getJudge() {
		return judge;
	}


	public void setJudge(java.lang.String judge) {
		this.judge = judge;
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


	public java.lang.String getObservations() {
		return observations;
	}


	public void setObservations(java.lang.String observations) {
		this.observations = observations;
	}


	public java.lang.String getOrder() {
		return order;
	}


	public void setOrder(java.lang.String order) {
		this.order = order;
	}


	public java.lang.String getRecord() {
		return record;
	}


	public void setRecord(java.lang.String record) {
		this.record = record;
	}


	public java.lang.Boolean getStatus() {
		return status;
	}


	public void setStatus(java.lang.Boolean status) {
		this.status = status;
	}


	public AgentDto getAgent() {
		return agent;
	}


	public void setAgent(AgentDto agent) {
		this.agent = agent;
	}
	  
	  
	
}
