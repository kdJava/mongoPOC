/**
 * @createdOn 03-Jul-2015 3:38:23 pm
 * @qualifiedName mongoPOC/org.mongo.test.poc.tryitOut.mongoPOC.mongoDAO/PersonDAO.java
 * @author ketandikshit
 * @typeName PersonDAO
 * @year 2015
 */
package org.mongo.test.poc.tryitOut.mongoPOC.mongoDAO;

import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.mongo.test.poc.tryitOut.mongoPOC.codecs.PersonCodec;
import org.mongo.test.poc.tryitOut.mongoPOC.mongo_collections.Person;
import org.mongo.test.poc.tryitOut.mongoPOC.mongoconfig.MongoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

/**
 * @author ketandikshit
 * @createdOn 03-Jul-2015 3:38:23 pm
 * @qualifiedName
 *                mongoPOC/org.mongo.test.poc.tryitOut.mongoPOC.mongoDAO/
 *                PersonDAO
 *                .java
 * @year 2015
 */
public class PersonDAO {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PersonDAO.class);
	private static final CodecRegistry codecRegistry = CodecRegistries
			.fromRegistries(CodecRegistries.fromCodecs(new PersonCodec()),
					MongoClient.getDefaultCodecRegistry());

	private static MongoCollection<Person> getPersonsCollections() {
		return MongoConfig.MONGO_DB.getCollection("persons", Person.class)
				.withCodecRegistry(codecRegistry);
	}

	public void clearPersonCollection() {
		getPersonsCollections().drop();
	}

	public void createPersonCollection() {
		MongoConfig.MONGO_DB.createCollection("persons");
	}

	public void savePerson(Person person) {
		MongoCollection<Person> personsCollection = getPersonsCollections();
		/*
		 * DBObject personBSON = BasicDBObjectBuilder
		 * .start(Person.FIELDS.NAME.getKey(), person.getName())
		 * .add(Person.FIELDS.AGE.getKey(), person.getAge())
		 * .add(Person.FIELDS.START_DATE.getKey(), person.getStartDate())
		 * .add(Person.FIELDS.SALARY.getKey(), person.getSalary()).get();
		 */
		personsCollection.insertOne(person);

	}
	public List<Person> getAllPerson() {
		MongoCollection<Person> personsCollection = getPersonsCollections();
		List<Person> personList = new ArrayList<Person>();
		for (Person person : personsCollection.find(Person.class)) {
			personList.add(person);
			LOGGER.info(person.JSON());
		}
		return personList;
	}
}
