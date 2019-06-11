package green.controller;

import green.entity.Position;
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
public class PositionControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createPosition() throws Exception {
        Position body = new Position();
        body.setActive(true);
        body.setName("Position");
        RequestEntity<Position> request = RequestEntity.post(new URI("http://localhost:" + port + "/api/position")).accept(MediaType.APPLICATION_JSON).body(body);
        ResponseEntity<Position> response = restTemplate.exchange(request, Position.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(body.isActive(), response.getBody().isActive());
        assertEquals(body.getName(), response.getBody().getName());
    }
}