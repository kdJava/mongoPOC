/**
 * @createdOn 03-Jul-2015 10:49:52 am
 * @qualifiedName mongoPOC/org.mongo.test.poc.tryitOut.mongoPOC/MongoConfig.java
 * @author ketandikshit
 * @typeName MongoConfig
 * @year 2015
 */
package org.mongo.test.poc.tryitOut.mongoPOC.mongoconfig;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

/**
 * @author ketandikshit
 * @createdOn 03-Jul-2015 10:49:52 am
 * @qualifiedName mongoPOC/org.mongo.test.poc.tryitOut.mongoPOC/MongoConfig.java
 * @year 2015
 */
public class MongoConfig implements MongoConfigConstants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MongoConfig.class);

	public static final MongoDatabase MONGO_DB = connect();

	private static MongoDatabase connect() {
		LOGGER.info("Initializing Mongo DB Database.....");

		MongoClientURI uriAndOptions = new MongoClientURI("mongodb://"
				+ MONGODB_HOST_URL + "/", MongoClientOptions.builder()
				.cursorFinalizerEnabled(false));
		MongoClient client = new MongoClient(uriAndOptions);

		for (Document db : client.listDatabases()) {
			LOGGER.info("MongoDB has Database--->" + db.toJson());
		}

		return client.getDatabase(MONGODB_DATABASE);
	}
	public static void main(String[] args) {
		LOGGER.debug(MONGO_DB.getName());
	}

}
