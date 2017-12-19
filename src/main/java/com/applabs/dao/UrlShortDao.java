package com.applabs.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.applabs.dao.documents.UrlShort;

/**
 * @author prabodh.hend
 *
 */
public interface UrlShortDao extends MongoRepository<UrlShort, Long> {

	@Query(value = "{ 'hash' : ?0 }")
	@Transactional
	UrlShort findByHash(String hash);

}
