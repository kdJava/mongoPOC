/**
 * @createdOn 03-Jul-2015 6:37:55 pm
 * @qualifiedName mongoPOC/org.mongo.test.poc.tryitOut.mongoPOC.mongoDAO/PersonJsonDAO.java
 * @author ketandikshit
 * @typeName PersonJsonDAO
 * @year 2015
 */
package org.mongo.test.poc.tryitOut.mongoPOC.mongoDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.mongo.test.poc.tryitOut.mongoPOC.mongo_collections.Person;
import org.mongo.test.poc.tryitOut.mongoPOC.mongoconfig.MongoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;

/**
 * @author ketandikshit
 * @createdOn 03-Jul-2015 6:37:55 pm
 * @qualifiedName
 *                mongoPOC/org.mongo.test.poc.tryitOut.mongoPOC.mongoDAO/
 *                PersonJsonDAO
 *                .java
 * @year 2015
 */
public class PersonJsonDAO {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PersonJsonDAO.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();

	private static MongoCollection<Document> getPersonsCollections() {
		return MongoConfig.MONGO_DB.getCollection("personsJson");

	}

	public void clearPersonJsonCollection() {
		getPersonsCollections().drop();
	}

	public void createPersonJsonCollection() {
		MongoConfig.MONGO_DB.createCollection("personsJson");
	}

	public void savePersonJson(Person person) {
		Map<String, Object> personMap = MAPPER.convertValue(person, Map.class);
		Document document = new Document(personMap);
		getPersonsCollections().insertOne(document);
	}

	public List<Person> getAllPersonJson() {
		;
		List<Person> personList = new ArrayList<Person>();
		for (Document personDoc : getPersonsCollections().find()) {
			String json = personDoc.toJson();
			Person person = new Person();
			person.setName(personDoc.getString(Person.FIELDS.NAME.getKey()));
			person.setAge(personDoc.getInteger(Person.FIELDS.AGE.getKey()));
			person.setStartDate(personDoc.getString(Person.FIELDS.START_DATE
					.getKey()));
			person.setSalary(personDoc.getLong(Person.FIELDS.SALARY.getKey()));
			personList.add(person);
			LOGGER.info(json);
		}
		return personList;
	}
}
