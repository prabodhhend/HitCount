/**
 * 
 */
package com.applabs.config;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.DB;
import com.mongodb.MongoClientURI;

/**
 * @author prabodh.hend
 *
 */
public class MultiTenantMongoDbFactory extends SimpleMongoDbFactory {

	private static final Logger logger = LoggerFactory.getLogger(MultiTenantMongoDbFactory.class);

	private final String DEFAULT_DB = "db_0";

	private static final String DEFAULT_PREFIX = "db_";

	public MultiTenantMongoDbFactory(MongoClientURI uri) throws UnknownHostException {
		super(uri);
	}

	@Override
	public DB getDb() throws DataAccessException {
//		String currentDb = DbContext.getCurrentDb();
		String currentDb = "short_url";
		String db = DEFAULT_PREFIX;

		if (currentDb != null && !currentDb.equals("")) {
			db = DEFAULT_PREFIX + currentDb;
			logger.debug("Acquiring Database :" + db);
			return getDb(db);

		}

		logger.debug("Acquiring Database :" + db);
		return super.getDb(DEFAULT_DB);
	}

}