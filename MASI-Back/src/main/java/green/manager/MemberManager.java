package green.manager;

import green.entity.Member;
import green.entity.Role;
import green.entity.Session;
import green.model.request.CreateMemberRequest;
import green.model.request.LoginMemberRequest;
import green.model.request.RegisterMemberRequest;
import green.model.request.UpdateMemberRequest;
import green.model.response.BaseResponse;
import green.model.response.ListResponse;
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

@Service
public class MemberManager {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    SessionRepository sessionRepository;

    @SuppressWarnings("rawtypes")
    public ResponseEntity<BaseResponse> registerUser(RegisterMemberRequest request) {
        if (StringUtils.isBlank(request.getLogin()) || StringUtils.isBlank(request.getPassword()) || StringUtils.isBlank(request.getName())) {
            return new ResponseEntity(new BaseResponse("Some of the required fields are empty!"), HttpStatus.BAD_REQUEST);
        }
        final Member check = memberRepository.findByLogin(request.getLogin());
        if (check != null) {
            return new ResponseEntity(new BaseResponse("This login is already used!"), HttpStatus.BAD_REQUEST);
        } else {
            final Member member = new Member();
            member.setLogin(request.getLogin());
            member.setPassword(Tools.generateMD5(request.getPassword()));
            member.setName(request.getName());
            member.setActive(true);
            member.setRole(Role.Candidate);
            member.setLanguage(request.getLanguage());
            memberRepository.save(member);
            return new ResponseEntity(new BaseResponse("Created"), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<LoginMemberResponse> login(LoginMemberRequest request) {
        LoginMemberResponse body = new LoginMemberResponse();
        HttpStatus status;
        if (StringUtils.isBlank(request.getLogin()) || StringUtils.isBlank(request.getPassword())) {
            status = HttpStatus.BAD_REQUEST;
            body.setMessage("Some of the required fields are empty!");
            body.setSessionToken(null);
            return new ResponseEntity(body, status);
        }
        final Member member = memberRepository.findByLoginAndPassword(request.getLogin(), Tools.generateMD5(request.getPassword()));
        if (member != null) {
            final Session session = new Session();
            session.setMember(member);
            final String token = Tools.generateSessionToken();
            session.setToken(token);
            session.setAddDate(new Timestamp(System.currentTimeMillis()));
            sessionRepository.save(session);
            status = HttpStatus.OK;
            body.setMessage("OK");
            body.setSessionToken(token);
            body.setUser_id(member.getId());
        } else {
            status = HttpStatus.BAD_REQUEST;
            body.setMessage("Credentials are incorrect!");
            body.setSessionToken(null);
            body.setUser_id(null);
        }
        return new ResponseEntity(body, status);
    }

    public ResponseEntity<ListResponse<Member>> getAllMembers(String token) {
        ListResponse body = new ListResponse();
        body.setMessage("OK");
        body.setList(memberRepository.findAll());
        return new ResponseEntity(body, HttpStatus.OK);
    }

    public ResponseEntity<Member> getMember(String token, Integer id) {
        Session session = sessionRepository.findByToken(token);
        ResponseEntity<Member> response = new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        if (session != null) {
            Member member = session.getMember();
            if (member.getId() == id) {
                response = new ResponseEntity(member, HttpStatus.OK);
            }
            else if (member.getRole() == Role.Moderator) {
                Member body = memberRepository.getOne(id);
                if (body != null) {
                    response = new ResponseEntity(body, HttpStatus.OK);
                }
            }
        }
        return response;
    }

    public ResponseEntity<Member> createMember(String token, CreateMemberRequest request) {
        Session session = sessionRepository.findByToken(token);
        ResponseEntity<Member> response = new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        if (session != null && Tools.isSessionValid(session)) {
            Member member = session.getMember();
            if (member.getRole() == Role.Moderator) {
                Member newMember = new Member();
                newMember.setId(null);
                newMember.setActive(request.isActive());
                newMember.setName(request.getName());
                newMember.setPassword(request.getPassword());
                newMember.setLanguage(request.getLanguage());
                newMember.setRole(request.getRole());
                newMember.setLogin(request.getLogin());
                newMember = memberRepository.save(newMember);
                response = new ResponseEntity(newMember, HttpStatus.OK);
            }
        }
        return response;
    }

    public ResponseEntity<Member> updateMember(String token, UpdateMemberRequest request) {
        Session session = sessionRepository.findByToken(token);
        ResponseEntity<Member> response = new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        if (session != null && Tools.isSessionValid(session)) {
            Member member = session.getMember();
            if (member.getRole() == Role.Moderator || member.getId() == request.getId()) {
                Member updatedMember = member;
                updatedMember.setId(request.getId());
                updatedMember.setActive(request.isActive());
                updatedMember.setName(request.getName());
                updatedMember.setPassword(request.getPassword());
                updatedMember.setLanguage(request.getLanguage());
                updatedMember.setRole(request.getRole());
                updatedMember.setLogin(request.getLogin());
                updatedMember = memberRepository.save(updatedMember);
                response = new ResponseEntity(updatedMember, HttpStatus.OK);
            }
        }
        return response;
    }

    public ResponseEntity<BaseResponse> deleteMember(String token, Integer id)
    {
        Session session = sessionRepository.findByToken(token);
        ResponseEntity<BaseResponse> response = new ResponseEntity(new BaseResponse("Bad request!"), HttpStatus.BAD_REQUEST);
        if (session != null && Tools.isSessionValid(session)) {
            Member member = session.getMember();
            if (member.getRole() == Role.Moderator || member.getId() == id) {
                memberRepository.delete(memberRepository.getOne(id));
                response = new ResponseEntity("Deleted.", HttpStatus.OK);
            }
        }
        return response;
    }
}
