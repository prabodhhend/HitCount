/**
 * 
 */
package com.applabs.service;

/**
 * @author prabodh.hend
 *
 */
public interface UrlShortService {

	public String createShortUrl(String url) throws Exception;

	public String getUrl(String hash) throws Exception;
	
	public Boolean validate(String url) throws Exception;

}
