/**
 * 
 */
package com.applabs.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.NDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.applabs.utils.SequenceUtils;

/**
 * @author prabodh.hend
 *
 */
@RestController
@RequestMapping("/api/applabs")
public class HitCountController {

	private static Logger logger = LoggerFactory.getLogger(HitCountController.class);

	@Autowired
	SequenceUtils sequenceUtils;

	@RequestMapping(value = "/hits", method = RequestMethod.GET)
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@ResponseBody
	public Map<String, Object> hitCount(HttpServletRequest req) throws Exception {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		List<String> data = new ArrayList<String>();
		List<String> errorLines = new ArrayList<String>();

		String uid = UUID.randomUUID().toString();
		NDC.push(uid);

		String message = "Success";
		String status = "200";
		Timestamp timestamp = new Timestamp(new Date().getTime());
		Boolean hasMore = false;
		Integer created = 0;

		try {

			Long sequence = Long.parseLong(sequenceUtils.generateNextSequence("hits"));
			data.add(sequence.toString());

			created = data == null ? 0 : data.size();

			if ((created != 0) && (errorLines.size() != 0)) {
				status = HttpStatus.PARTIAL_CONTENT.toString();
				message = HttpStatus.PARTIAL_CONTENT.getReasonPhrase();
			}
			if ((created == 0) && (errorLines.size() != 0)) {
				status = HttpStatus.NOT_MODIFIED.toString();
				message = HttpStatus.NOT_MODIFIED.getReasonPhrase();
			}
			if ((created != 0) && (errorLines.size() == 0)) {
				status = HttpStatus.OK.toString();
				message = HttpStatus.OK.getReasonPhrase();
			}
			if ((created == 0) && (errorLines.size() == 0)) {
				status = HttpStatus.NOT_MODIFIED.toString();
				message = HttpStatus.NOT_MODIFIED.getReasonPhrase();
			}
		} catch (Exception e) {
			message = HttpStatus.NOT_FOUND.getReasonPhrase();
			status = HttpStatus.NOT_FOUND.toString();
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		responseMap.put("data", data);
		responseMap.put("errors", errorLines.size());
		responseMap.put("errorLines", errorLines);
		responseMap.put("request_id", uid);
		responseMap.put("timestamp", timestamp);
		responseMap.put("status", status);
		responseMap.put("message", message);
		responseMap.put("hasMore", hasMore);
		responseMap.put("nextUrl", "");

		return responseMap;
	}

}
