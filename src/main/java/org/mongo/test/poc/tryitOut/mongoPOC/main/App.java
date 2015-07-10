package org.mongo.test.poc.tryitOut.mongoPOC.main;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import org.mongo.test.poc.tryitOut.mongoPOC.mongoDAO.PersonDAO;
import org.mongo.test.poc.tryitOut.mongoPOC.mongoDAO.PersonJsonDAO;
import org.mongo.test.poc.tryitOut.mongoPOC.mongo_collections.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static Person getPerson() {
		Person person = new Person();
		person.setName("Ketan");
		person.setAge(30);
		person.setSalary(2000000);
		LocalDateTime dateTime = LocalDateTime.of(2014, Month.JANUARY, 28, 9,
				0, 30);
		person.setStartDate(dateTime.format(DateTimeFormatter.ISO_DATE_TIME));
		return person;
	}

	public static void main(String[] args) {
		PersonDAO personDAO = new PersonDAO();
		// personDAO.clearPersonCollection();
		// personDAO.createPersonCollection();
		personDAO.savePerson(getPerson());

		personDAO.getAllPerson();

		PersonJsonDAO personJsonDAO = new PersonJsonDAO();
		// personJsonDAO.createPersonJsonCollection();

		personJsonDAO.savePersonJson(getPerson());
		personJsonDAO.getAllPersonJson();
	}
}
