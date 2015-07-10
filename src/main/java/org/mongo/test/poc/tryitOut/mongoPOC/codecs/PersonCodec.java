/**
 * @createdOn 03-Jul-2015 5:14:47 pm
 * @qualifiedName mongoPOC/org.mongo.test.poc.tryitOut.mongoPOC.codecs/PersonCodec.java
 * @author ketandikshit
 * @typeName PersonCodec
 * @year 2015
 */
package org.mongo.test.poc.tryitOut.mongoPOC.codecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.mongo.test.poc.tryitOut.mongoPOC.mongo_collections.Person;

/**
 * @author ketandikshit
 * @createdOn 03-Jul-2015 5:14:47 pm
 * @qualifiedName
 *                mongoPOC/org.mongo.test.poc.tryitOut.mongoPOC.codecs/
 *                PersonCodec
 *                .java
 * @year 2015
 */
public class PersonCodec implements Codec<Person> {

	/**
	 * @createdOn 03-Jul-2015 5:14:58 pm
	 * @author ketandikshit
	 * @see org.bson.codecs.Encoder#encode(org.bson.BsonWriter,
	 *      java.lang.Object, org.bson.codecs.EncoderContext)
	 */
	public void encode(BsonWriter writer, Person value,
			EncoderContext encoderContext) {
		writer.writeStartDocument();
		writer.writeString(Person.FIELDS.NAME.getKey(), value.getName());
		writer.writeInt32(Person.FIELDS.AGE.getKey(), value.getAge());
		writer.writeString(Person.FIELDS.START_DATE.getKey(),
				value.getStartDate());
		writer.writeInt64(Person.FIELDS.SALARY.getKey(), value.getSalary());
		writer.writeEndDocument();
	}
	/**
	 * @createdOn 03-Jul-2015 5:14:58 pm
	 * @author ketandikshit
	 * @see org.bson.codecs.Encoder#getEncoderClass()
	 */
	public Class<Person> getEncoderClass() {
		return Person.class;
	}

	/**
	 * @createdOn 03-Jul-2015 5:14:58 pm
	 * @author ketandikshit
	 * @see org.bson.codecs.Decoder#decode(org.bson.BsonReader,
	 *      org.bson.codecs.DecoderContext)
	 */
	public Person decode(BsonReader reader, DecoderContext decoderContext) {
		Person person = new Person();
		reader.readStartDocument();
		person.setId(reader.readObjectId(Person.FIELDS.ID.getKey()).toString());
		person.setName(reader.readString(Person.FIELDS.NAME.getKey()));
		person.setAge(reader.readInt32(Person.FIELDS.AGE.getKey()));
		person.setStartDate(reader.readString(Person.FIELDS.START_DATE.getKey()));
		person.setSalary(reader.readInt64(Person.FIELDS.SALARY.getKey()));
		reader.readEndDocument();
		return person;
	}

}
