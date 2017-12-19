/**
 * 
 */
package com.applabs.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.applabs.dao.UrlShortDao;
import com.applabs.dao.UrlShortRepository;
import com.applabs.dao.documents.UrlShort;

/**
 * @author prabodh.hend
 *
 */
@Component
public class UrlShortRepositoryImpl implements UrlShortRepository {

	@Autowired
	UrlShortDao urlShortDao;

	@Override
	public UrlShort create(UrlShort urlShort) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UrlShort update(UrlShort urlShort) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UrlShort find(String hash) throws Exception {

		UrlShort urlShort = urlShortDao.findByHash(hash);

		return urlShort;
	}

	@Override
	public String delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
