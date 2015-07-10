/**
 * @createdOn 03-Jul-2015 1:40:40 pm
 * @qualifiedName mongoPOC/org.mongo.test.poc.tryitOut.mongoPOC.mongo_collections/Person.java
 * @author ketandikshit
 * @typeName Person
 * @year 2015
 */
package org.mongo.test.poc.tryitOut.mongoPOC.mongo_collections;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ketandikshit
 * @createdOn 03-Jul-2015 1:40:40 pm
 * @qualifiedName
 *                mongoPOC/org.mongo.test.poc.tryitOut.mongoPOC.
 *                mongo_collections
 *                /Person.java
 * @year 2015
 */
public class Person {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final Logger LOGGER = LoggerFactory.getLogger(Person.class);
	public static enum FIELDS {
		ID("_id"), NAME("name"), AGE("age"), START_DATE("start_date"), SALARY(
				"salary");

		private final String value;

		FIELDS(String value) {
			this.value = value;
		}

		public String getKey() {
			return this.value;
		}
	}

	private String id;
	private String name;
	private int age;
	private String startDate;
	private long salary;

	/**
	 * @createdOn 03-Jul-2015 1:44:58 pm
	 * @author ketandikshit
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", startDate="
				+ startDate + ", salary=" + salary + "]";
	}

	/**
	 * @createdOn 03-Jul-2015 3:53:38 pm
	 * @author ketandikshit
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @createdOn 03-Jul-2015 3:53:38 pm
	 * @author ketandikshit
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @createdOn 03-Jul-2015 3:53:38 pm
	 * @author ketandikshit
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @createdOn 03-Jul-2015 3:53:38 pm
	 * @author ketandikshit
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @createdOn 03-Jul-2015 3:53:38 pm
	 * @author ketandikshit
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @createdOn 03-Jul-2015 3:53:38 pm
	 * @author ketandikshit
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @createdOn 03-Jul-2015 3:53:38 pm
	 * @author ketandikshit
	 * @return the salary
	 */
	public long getSalary() {
		return salary;
	}

	/**
	 * @createdOn 03-Jul-2015 3:53:38 pm
	 * @author ketandikshit
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(long salary) {
		this.salary = salary;
	}

	/**
	 * @createdOn 03-Jul-2015 6:08:57 pm
	 * @author ketandikshit
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @createdOn 03-Jul-2015 6:08:57 pm
	 * @author ketandikshit
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String JSON() {
		try {
			return MAPPER.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Person personGet(String json) {
		LOGGER.debug(json);
		try {
			return MAPPER.readValue(json, Person.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
