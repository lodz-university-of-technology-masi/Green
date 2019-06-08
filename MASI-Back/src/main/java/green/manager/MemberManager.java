package green.manager;

import green.entity.Member;
import green.entity.Role;
import green.entity.Session;
import green.model.request.LoginMemberRequest;
import green.model.request.RegisterMemberRequest;
import green.model.response.BaseObjectResponse;
import green.model.response.LoginMemberResponse;
import green.repository.MemberRepository;
import green.repository.RoleRepository;
import green.repository.SessionRepository;
import green.tools.Tools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class MemberManager {

	@Autowired
    MemberRepository memberRepository;
	
	@Autowired
	SessionRepository sessionRepository;

	@Autowired
	RoleRepository roleRepository;

	@SuppressWarnings("rawtypes")
	public BaseObjectResponse registerUser(RegisterMemberRequest request) {
		final BaseObjectResponse response = new BaseObjectResponse();
		if(StringUtils.isBlank(request.getLogin()) || StringUtils.isBlank(request.getPassword()) || StringUtils.isBlank(request.getName())) {
			response.setCode(400);
			response.setMessage("Some of the required fields are empty!");
			return response;
		}
		final Role role = roleRepository.findById(1);
		final Member check = memberRepository.findByLogin(request.getLogin());
		if(check != null) {
			response.setCode(400);
			response.setMessage("This login is already used!");
		} else {
			final Member member = new Member();
			member.setLogin(request.getLogin());
			member.setPassword(Tools.generateMD5(request.getPassword()));
			member.setName(request.getName());
			member.setActive(true);
			member.setRole(role);
			memberRepository.save(member);

			response.setCode(201);
			response.setMessage("Created");
		}
		return response;
	}
	
	public BaseObjectResponse<LoginMemberResponse> login(LoginMemberRequest request) {
		final BaseObjectResponse<LoginMemberResponse> response = new BaseObjectResponse();
		if(StringUtils.isBlank(request.getLogin()) || StringUtils.isBlank(request.getPassword())) {
			response.setCode(400);
			response.setMessage("Some of the required fields are empty!");
			return response;
		}
		final Member member = memberRepository.findByLoginAndPassword(request.getLogin(), Tools.generateMD5(request.getPassword()));
		if(member != null) {
			final Session session = new Session();
			session.setMember(member);
			final String token = Tools.generateSessionToken();
			session.setToken(token);
			session.setAddDate(new Timestamp(System.currentTimeMillis()));
			sessionRepository.save(session);
			
			final LoginMemberResponse loginMemberResponse = new LoginMemberResponse(token);
			response.setCode(200);
			response.setMessage("OK");
			response.setResponse(loginMemberResponse);
		} else {
			response.setCode(400);
			response.setMessage("Credentials are incorrect!");
		}
		
		return response;
	}
}
