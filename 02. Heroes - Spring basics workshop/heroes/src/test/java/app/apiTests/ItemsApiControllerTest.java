package app.apiTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemsApiControllerTest {

	@Autowired
	TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	
	
	@Test
	public void getItemsApiTest() {
		Object result = restTemplate.getForObject("http://localhost:" + port + "/api/items", Object.class);
		System.out.println();
	}
}
