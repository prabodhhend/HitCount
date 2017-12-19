/**
 * 
 */
package com.applabs.dao;

import com.applabs.dao.documents.UrlShort;

/**
 * @author prabodh.hend
 *
 */
public interface UrlShortRepository {

	public UrlShort create(UrlShort urlShort) throws Exception;

	public UrlShort update(UrlShort urlShort) throws Exception;

	public UrlShort find(String hash) throws Exception;

	public String delete(Long id) throws Exception;

}
