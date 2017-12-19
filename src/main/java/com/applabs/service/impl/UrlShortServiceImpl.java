/**
 * 
 */
package com.applabs.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.applabs.dao.UrlShortRepository;
import com.applabs.dao.documents.UrlShort;
import com.applabs.service.UrlShortService;
import com.applabs.utils.SequenceUtils;
import com.applabs.utils.UrlShortnerUtility;

/**
 * @author prabodh.hend
 *
 */
@Component
public class UrlShortServiceImpl implements UrlShortService {

	@Autowired
	UrlShortRepository urlShortRepository;

	@Autowired
	UrlShortnerUtility urlShortnerUtility;

	@Autowired
	SequenceUtils sequenceUtils;

	@Override
	public String createShortUrl(String url) throws Exception {
		String hash = "";
		try {

			hash = urlShortnerUtility.shortenUrl(url);
			UrlShort urlShort = urlShortRepository.find(hash);
			if (null != urlShort) {
				urlShort.setHits(urlShort.getHits() + 1);
				urlShortRepository.update(urlShort);

			} else {
				Long id = Long.parseLong(sequenceUtils.generateNextSequence(UrlShort.class.getSimpleName().toString()));

				urlShort = new UrlShort();
				urlShort.setHash(hash);
				urlShort.setId(id);
				urlShort.setShortUrl(hash);
				urlShort.setDestination(url);
				urlShort.setHits(1);
				urlShortRepository.create(urlShort);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return hash;
	}

	@Override
	public String getUrl(String hash) throws Exception {
		String url = "";
		try {
			UrlShort urlShort = urlShortRepository.find(hash);
			if (null != urlShort) {
				url = urlShort.getDestination();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	@Override
	public Boolean validate(String url) throws Exception {
		Boolean flag = Boolean.FALSE;
		HttpURLConnection connection = null;
		try {
			URL myurl = new URL(url);
			connection = (HttpURLConnection) myurl.openConnection();
			connection.setRequestMethod("HEAD");
			int statusCode = connection.getResponseCode();

			if (statusCode == HttpStatus.OK.value()) {
				flag = Boolean.TRUE;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

}
