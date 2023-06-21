package net.revature.revpay.presentation;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.revature.revpay.controller.AccountController;
import net.revature.revpay.model.Account;
import net.revature.revpay.service.AccountService;
import net.revature.revpay.model.Requests;

@WebMvcTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControllerTests {
	
	@MockBean
	AccountService accountService;
	@Autowired
	MockMvc mock;
	@Autowired
	private ObjectMapper objectMapper;
	

	@Test
	@Order(1)
	public void verifyRequestMatching() throws Exception {

		mock.perform(get("/v1/account")
				.contentType("application/json"))
				.andExpect(status().isOk());
	}
	@Test
	@Order(2)
	public void RegisterAccountTest() throws Exception {
		Account account1 = new Account("sadrach", "password", 1000, "sadrach@gmail.com", "1");
		Account account2 = new Account("jesus", "password", 1000, "jesus@gmail.com", "2");
		mock.perform(post("/v1/account", 42L)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(account1)))
				.andExpect(status().isAccepted());
		mock.perform(post("/v1/account", 42L)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(account1)))
				.andExpect(status().isAccepted());

		ResultActions result = mock.perform(get("/v1/account")
				.contentType("application/json")
				.param("accountId", "1"))
				.andExpect(status().isOk());
		result.andDo(print());
	}
	@Test
	@Order(3)
	public void CreateRequestTest() throws Exception {
		Account account1 = new Account("sadrach", "password", 1000, "sadrach@gmail.com", "1");
		Account account2 = new Account("jesus", "password", 1000, "jesus@gmail.com", "2");
		Requests req = new Requests(999, account1, account2);
		mock.perform(post("/v1/request")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(req)))
				.andExpect(status().isAccepted());		
	}
	
	@Test
	@Order(4)
	public void CompleteRequestTest() throws Exception {

		mock.perform(patch("/v1/request")
				.contentType("application/json")
				.param("requestId", "1"))
				.andExpect(status().isAccepted());
		ResultActions result = mock.perform(get("/v1/account")
				.contentType("application/json")
				.param("accountId", "1"))
				.andExpect(status().isOk())
				.andDo(print());
//		result.andDo(print());
	}
}
