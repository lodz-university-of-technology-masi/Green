package green.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import green.model.request.LoginMemberRequest;
import green.model.request.RegisterMemberRequest;
import green.model.response.BaseResponse;
import green.model.response.LoginMemberResponse;
import org.aspectj.lang.annotation.Before;
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
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MemberControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<BaseResponse> testRegister(RegisterMemberRequest body) throws Exception {
        RequestEntity<RegisterMemberRequest> request = RequestEntity
                .post(new URI("http://localhost:" + port + "/api/member/register"))
                .accept(MediaType.APPLICATION_JSON)
                .body(body);
        return restTemplate.exchange(request, BaseResponse.class);
    }

    private ResponseEntity<LoginMemberResponse> testLogin(LoginMemberRequest body) throws Exception {
        RequestEntity<LoginMemberRequest> request = RequestEntity
                .post(new URI("http://localhost:" + port + "/api/member/login"))
                .accept(MediaType.APPLICATION_JSON)
                .body(body);
        return restTemplate.exchange(request, LoginMemberResponse.class);
    }

    @Test
    public void registerMemberEmptyFields() throws Exception {
        RegisterMemberRequest body = new RegisterMemberRequest("", "", "");
        ResponseEntity<BaseResponse> response = testRegister(body);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Some of the required fields are empty!", response.getBody().getMessage());
    }

    @Test
    public void registerMemberUsernameAlreadyUsed() throws Exception {
        RegisterMemberRequest body = new RegisterMemberRequest("login", "password", "name");
        ResponseEntity<BaseResponse> response = testRegister(body);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Created", response.getBody().getMessage());
    }

    @Test
    public void registerMemberSuccess() throws Exception {
        RegisterMemberRequest body = new RegisterMemberRequest("login", "password", "name");
        ResponseEntity<BaseResponse> response = testRegister(body);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("This login is already used!", response.getBody().getMessage());
    }

    @Test
    public void loginMemberEmptyFields() throws Exception {
        LoginMemberRequest body = new LoginMemberRequest("", "");
        ResponseEntity<LoginMemberResponse> response = testLogin(body);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Some of the required fields are empty!", response.getBody().getMessage());
        assertEquals(null, response.getBody().getSessionToken());
    }

    @Test
    public void loginMemberCredentialsIncorrect() throws Exception {
        LoginMemberRequest body = new LoginMemberRequest("foo", "foo");
        ResponseEntity<LoginMemberResponse> response = testLogin(body);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Credentials are incorrect!", response.getBody().getMessage());
        assertEquals(null, response.getBody().getSessionToken());
    }
}