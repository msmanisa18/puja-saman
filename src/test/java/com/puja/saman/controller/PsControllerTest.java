package com.puja.saman.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class PsControllerTest {

	@Mock
	PsController psController = new PsController();

	@DisplayName("Test welcome method")
	@Test
	void welcome() {
		Assertions.assertEquals("<h1>Welcome</h1>", psController.welcome());
	}

	@DisplayName("login method - error scenario test")
	@Test
	void loginError() {
		ResponseEntity<ObjectNode> entity = psController.login(null);
		Assertions.assertEquals("{\"status\":\"fail\"}", entity.getBody().toString());
	}

	@DisplayName("login method - success scenario test")
	@Test
	void loginSuccess() {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode body = mapper.createObjectNode();
		body.set("uname", mapper.convertValue("shreya", JsonNode.class));
		body.set("upass", mapper.convertValue("12345", JsonNode.class));
		ResponseEntity<ObjectNode> entity = psController.login(body);
		Assertions.assertEquals("{\"status\":\"success\"}", entity.getBody().toString());
	}
}