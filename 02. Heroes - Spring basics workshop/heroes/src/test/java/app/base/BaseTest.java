package app.base;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseTest {

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
}
