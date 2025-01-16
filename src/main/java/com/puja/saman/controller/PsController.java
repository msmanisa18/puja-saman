package com.puja.saman.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PsController {

	@GetMapping("/welcome")
	public String welcome() {
		log.info("Inside welcome method");
		return "<h1>Welcome</h1>";
	}

	@PostMapping("/login")
	public ResponseEntity<ObjectNode> login(@RequestBody ObjectNode body) {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("status", "fail");

		if (null != body && !body.isEmpty() && body.has("uname") && body.has("upass")) {
			log.info("Inside login method : " + body.toString());
			String uname = body.get("uname").asText();
			String upass = body.get("upass").asText();

			if ("shreya".equals(uname) && "12345".equals(upass)) {
				objectNode.put("status", "success");
			}
		} else {
			log.info("Inside login method : body is coming as null");
		}
		log.info("response : " + objectNode);
		return ResponseEntity.ok(objectNode);
	}
}