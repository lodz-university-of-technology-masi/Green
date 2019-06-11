package green.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createTest() throws Exception {
        green.entity.Test body = new green.entity.Test();
        body.setName("Test");
        body.setRedactor_id(1);
        RequestEntity<green.entity.Test> request = RequestEntity.post(new URI("http://localhost:" + port + "/api/position")).accept(MediaType.APPLICATION_JSON).body(body);
        ResponseEntity<green.entity.Test> response = restTemplate.exchange(request, green.entity.Test.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(body.getRedactor_id(), response.getBody().getRedactor_id());
        assertEquals(body.getName(), response.getBody().getName());
    }
}