package green.manager;

import green.entity.Member;
import green.entity.Role;
import green.entity.Session;
import green.model.request.LoginMemberRequest;
import green.model.request.RegisterMemberRequest;
import green.model.response.BaseResponse;
import green.model.response.GetAllResponse;
import green.model.response.LoginMemberResponse;
import green.repository.MemberRepository;
import green.repository.SessionRepository;
import green.tools.Tools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MemberManager {

	@Autowired
    MemberRepository memberRepository;
	
	@Autowired
	SessionRepository sessionRepository;

	@SuppressWarnings("rawtypes")
	public ResponseEntity<BaseResponse> registerUser(RegisterMemberRequest request) {
		if(StringUtils.isBlank(request.getLogin()) || StringUtils.isBlank(request.getPassword()) || StringUtils.isBlank(request.getName())){
			return new ResponseEntity(new BaseResponse("Some of the required fields are empty!"), HttpStatus.BAD_REQUEST);
		}
		final Member check = memberRepository.findByLogin(request.getLogin());
		if(check != null) {
			return new ResponseEntity(new BaseResponse("This login is already used!"), HttpStatus.BAD_REQUEST);
		} else {
			final Member member = new Member();
			member.setLogin(request.getLogin());
			member.setPassword(Tools.generateMD5(request.getPassword()));
			member.setName(request.getName());
			member.setActive(true);
			member.setRole(Role.Candidate);
			memberRepository.save(member);
			return new ResponseEntity(new BaseResponse("Created"), HttpStatus.CREATED);
		}
	}
	
	public ResponseEntity<LoginMemberResponse> login(LoginMemberRequest request) {
	    LoginMemberResponse body = new LoginMemberResponse();
	    HttpStatus status;
		if(StringUtils.isBlank(request.getLogin()) || StringUtils.isBlank(request.getPassword())) {
		    status = HttpStatus.BAD_REQUEST;
		    body.setMessage("Some of the required fields are empty!");
		    body.setSessionToken(null);
		    return new ResponseEntity(body, status);
		}
		final Member member = memberRepository.findByLoginAndPassword(request.getLogin(), Tools.generateMD5(request.getPassword()));
		if(member != null) {
			final Session session = new Session();
			session.setMember(member);
			final String token = Tools.generateSessionToken();
			session.setToken(token);
			session.setAddDate(new Timestamp(System.currentTimeMillis()));
			sessionRepository.save(session);
			status = HttpStatus.OK;
            body.setMessage("OK");
			body.setSessionToken(token);
		} else {
            status = HttpStatus.BAD_REQUEST;
			body.setMessage("Credentials are incorrect!");
			body.setSessionToken(null);
		}
		return new ResponseEntity(body, status);
	}

	public ResponseEntity<GetAllResponse<Member>> getAllMembers()
	{
		GetAllResponse body = new GetAllResponse();
		body.setMessage("OK");
		body.setList(memberRepository.findAll());
		return new ResponseEntity(body, HttpStatus.OK);
	}
}
