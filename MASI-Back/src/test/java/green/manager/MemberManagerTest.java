package green.manager;

import green.model.request.LoginMemberRequest;
import green.model.response.LoginMemberResponse;
import green.repository.MemberRepository;
import green.repository.SessionRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MemberManagerTest {

    @Mock
    MemberRepository memberRepository;

    @Mock
    SessionRepository sessionRepository;

    @InjectMocks
    MemberManager memberManager = new MemberManager();

    @Before(value = "test")
    public void setMockOutput() {
        when(memberRepository.findById(null)).thenReturn(null);
        when(memberRepository.findAll()).thenReturn(null);
        when(memberRepository.findByLogin(null)).thenReturn(null);
        when(memberRepository.findByLoginAndPassword(null, null)).thenReturn(null);

        when(sessionRepository.findById(null)).thenReturn(null);
        when(sessionRepository.findByToken(null)).thenReturn(null);
    }

    @Test
    public void test() throws Exception {
        LoginMemberRequest request = new LoginMemberRequest("", "");
        ResponseEntity<LoginMemberResponse> response = memberManager.login(request);
        assertEquals(new ResponseEntity(new LoginMemberResponse(null, null), HttpStatus.BAD_REQUEST), response);
    }

}
