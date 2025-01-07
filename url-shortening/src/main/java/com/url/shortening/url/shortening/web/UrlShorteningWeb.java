package com.url.shortening.url.shortening.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.url.shortening.base.factory.IProcessor;
import com.url.shortening.url.shortening.common.QueryUrlShorteningRequest;
import com.url.shortening.url.shortening.common.QueryUrlShorteningResponse;
import com.url.shortening.url.shortening.common.UpdateUrlShorteningRequest;
import com.url.shortening.url.shortening.common.UpdateUrlShorteningResponse;
import com.url.shortening.url.shortening.services.ProcessorFactory;
import com.url.shortening.url.shortening.services.ValidateFactory;

@RestController
public class UrlShorteningWeb {

	@Autowired
	ProcessorFactory processorFactory;

	@Autowired
	ValidateFactory validatorfactory;

	@GetMapping("/component/url/shortening/query")
	public ResponseEntity<QueryUrlShorteningResponse> query(@RequestBody QueryUrlShorteningRequest request) {
		try {

			IProcessor processor = processorFactory.getProcessor(request);
			QueryUrlShorteningResponse response = (QueryUrlShorteningResponse) processor.process(request);
			return ResponseEntity.ok(response);
		} catch (IllegalArgumentException e) {
			QueryUrlShorteningResponse response = new QueryUrlShorteningResponse();
			response.setStatus("500");
			return ResponseEntity.badRequest().body(response); // Or return an error response object
		} catch (Exception e) {
			QueryUrlShorteningResponse response = new QueryUrlShorteningResponse();
			response.setStatus("500");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // Handle unexpected errors
		}
	}

	@PostMapping("/component/url/shortening/update")
	public ResponseEntity<UpdateUrlShorteningResponse> update(@RequestBody UpdateUrlShorteningRequest request) {
		try {
			UpdateUrlShorteningResponse validateResponse = (UpdateUrlShorteningResponse) validatorfactory
					.getValidator(request).validate(request);
			if (!validateResponse.getErrors().isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(validateResponse); // Handle
			} else {
				UpdateUrlShorteningResponse response = (UpdateUrlShorteningResponse) processorFactory
						.getProcessor(request).process(request);
				return ResponseEntity.ok(response);
			}
		} catch (IllegalArgumentException e) {
			UpdateUrlShorteningResponse response = new UpdateUrlShorteningResponse();
			response.setStatus("500");
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			UpdateUrlShorteningResponse response = new UpdateUrlShorteningResponse();
			response.setStatus("500");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // Handle unexpected errors
		}
	}

}
