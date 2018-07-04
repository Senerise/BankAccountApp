package com.fsene.kata.bankaccount.controllers.test;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.fsene.kata.bankaccount.entities.Account;
import com.fsene.kata.bankaccount.entities.Client;
import com.fsene.kata.bankaccount.services.AccountService;
import com.fsene.kata.bankaccount.services.OperationService;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@DataJpaTest
public class BankAccountControllerTest {

	@Autowired
	private TestEntityManager testEntityManager;
	@MockBean
	private AccountService accountService;
	@MockBean
	private OperationService operationService;

	private Account account;
	private Client client;

	@Before
	public void init() {

		client = new Client("Jean", "Dujardin");
		account = new Account("1002", 32000L);

		account.setClient(client);
		client.setAccount(account);

		testEntityManager.persist(client);
		testEntityManager.persist(account);

	}

	@Test
	public void getAccountTest() {
		given().when().get("http://localhost:8080/account/1001/").then().statusCode(200).body("number",
				equalTo("1001"));
	}

	@Test
	public void updateAccountTest() {
		Response oldAccountResponse = given().when().get("http://localhost:8080/account/1001");
		JsonPath oldJpAccount = new JsonPath(oldAccountResponse.asString());
		String operationsSize = oldJpAccount.get("operations.size()").toString();
		String oldBalance = oldJpAccount.get("balance").toString();

		// On teste qu'une nouvelle opération à été crée et que liste a été incrémentée
		// de 1
		given().when().post("http://localhost:8080/account/1001/operations/DEPOSIT/110").then()
				.body("operations.size()", is(Integer.valueOf(operationsSize) + 1));

		Response newAccountResponse = given().when().get("http://localhost:8080/account/1001/");
		JsonPath newJpAccount = new JsonPath(newAccountResponse.asString());
		String newBalance = newJpAccount.get("balance").toString();

		// On vérifie aussi que le solde du compte a changé du montant associé
		assertThat(oldBalance).isNotEqualTo(newBalance);
	}

	@Test
	public void getAccountHistory() throws ClientProtocolException, IOException {
		Response response = given().when().get("http://localhost:8080/account/1001");
		JsonPath jp = new JsonPath(response.asString());
		String operationsSize = jp.get("operations.size()").toString();

		// On vérifie que toutes les opérations du compte ont été récupéré
		given().when().get("http://localhost:8080/account/1001/operations").then().statusCode(200)
				.body("operations.size()", is(Integer.valueOf(operationsSize)));
	}
}
