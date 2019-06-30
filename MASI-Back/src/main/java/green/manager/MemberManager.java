package green.manager;

//
//import green.repository.MemberRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;

import green.dto.UserClaimsDto;
import green.dto.UserDto;
import green.entity.Member;
import green.model.request.ChangeLanugageRequest;
import green.model.request.EditMemberRequest;
import green.model.request.LoginMemberRequest;
import green.model.request.RegisterMemberRequest;
import green.model.response.BaseArrayResponse;
import green.model.response.BaseObjectResponse;
import green.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MemberManager {

    @Autowired
    MemberRepository memberRepository;


    @SuppressWarnings("rawtypes")
    public BaseObjectResponse registerUser(RegisterMemberRequest request) {
        final Member member = new Member();
        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setLanguage(request.getLanguage());
        member.setPassword(request.getPassword());
        member.setRole(request.getRole());
        memberRepository.save(member);
        final BaseObjectResponse response = new BaseObjectResponse();
        response.setCode(1);
        response.setMessage("OK");
        return response;
    }

    @SuppressWarnings("rawtypes")
    public BaseObjectResponse login(LoginMemberRequest request) {
        final Member user = memberRepository.findByLoginAndPassword(request.getName(),
                request.getPassword());
        final BaseObjectResponse response = new BaseObjectResponse();
        if(user != null) {
            response.setCode(1);
            response.setMessage("OK");
        }
        else{
            response.setCode(2);
            response.setMessage("Credentials are incorrect");
        }
        return response;
    }

    @SuppressWarnings("rawtypes")
    public BaseObjectResponse getUserClaims(LoginMemberRequest request) {
        final Member user = memberRepository.findByLogin(request.getName());
        final BaseObjectResponse response = new BaseObjectResponse();
        UserClaimsDto dto = new UserClaimsDto(user.getName(),user.getEmail(),user.getRole(),
                user.getLanguage(),user.getId());

        response.setResponse(dto);
        response.setCode(1);
        response.setMessage("OK");

        return response;
    }

    @SuppressWarnings("rawtypes")
    public BaseObjectResponse changeLanguage(ChangeLanugageRequest request) {
        final Member user = memberRepository.findByLogin(request.getName());
        final BaseObjectResponse response = new BaseObjectResponse();
        user.setLanguage(request.getLanguage());
        memberRepository.save(user);

        response.setCode(1);
        response.setMessage("OK");

        return response;
    }

    public BaseArrayResponse<UserDto> getAllMembers() {
        final BaseArrayResponse<UserDto> response = new BaseArrayResponse<UserDto>();
        final List<Member> memberList = memberRepository.findAll();
        final List<UserDto> dto = new ArrayList<>();
        for(Member u : memberList) {
            dto.add(new UserDto(u.getName(),u.getEmail(),u.getRole(),u.getId()));
        }

        response.setResponse(dto);
        response.setCode(1);
        response.setMessage("OK");
        return response;

    }

    @SuppressWarnings("rawtypes")
    public BaseObjectResponse editMember(EditMemberRequest request) {
        final Member user = memberRepository.findOne(request.getId());
        final BaseObjectResponse response = new BaseObjectResponse();
        user.setRole(request.getRole());
        user.setEmail(request.getEmail());
        user.setName(request.getName());

        memberRepository.save(user);

        response.setCode(1);
        response.setMessage("OK");

        return response;
    }

//    public ResponseEntity<Member> getMember(String token, Integer id) {
//        Session session = sessionRepository.findByToken(token);
//        ResponseEntity<Member> response = new ResponseEntity(null, HttpStatus.BAD_REQUEST);
//        if (session != null) {
//            Member member = session.getMember();
//            if (member.getId() == id) {
//                response = new ResponseEntity(member, HttpStatus.OK);
//            }
//            else if (member.getRole() == Role.Moderator) {
//                Member body = memberRepository.getOne(id);
//                if (body != null) {
//                    response = new ResponseEntity(body, HttpStatus.OK);
//                }
//            }
//        }
//        return response;
//    }
//
//    public ResponseEntity<Member> createMember(String token, CreateMemberRequest request) {
//        Session session = sessionRepository.findByToken(token);
//        ResponseEntity<Member> response = new ResponseEntity(null, HttpStatus.BAD_REQUEST);
//        if (session != null && Tools.isSessionValid(session)) {
//            Member member = session.getMember();
//            if (member.getRole() == Role.Moderator) {
//                Member newMember = new Member();
//                newMember.setId(null);
//                newMember.setActive(request.isActive());
//                newMember.setName(request.getName());
//                newMember.setPassword(request.getPassword());
//                newMember.setLanguage(request.getLanguage());
//                newMember.setRole(request.getRole());
//                newMember.setLogin(request.getLogin());
//                newMember = memberRepository.save(newMember);
//                response = new ResponseEntity(newMember, HttpStatus.OK);
//            }
//        }
//        return response;
//    }
//
//    public ResponseEntity<Member> updateMember(String token, EditMemberRequest request) {
//        Session session = sessionRepository.findByToken(token);
//        ResponseEntity<Member> response = new ResponseEntity(null, HttpStatus.BAD_REQUEST);
//        if (session != null && Tools.isSessionValid(session)) {
//            Member member = session.getMember();
//            if (member.getRole() == Role.Moderator || member.getId() == request.getId()) {
//                Member updatedMember = member;
//                updatedMember.setId(request.getId());
//                updatedMember.setActive(request.isActive());
//                updatedMember.setName(request.getName());
//                updatedMember.setPassword(request.getPassword());
//                updatedMember.setLanguage(request.getLanguage());
//                updatedMember.setRole(request.getRole());
//                updatedMember.setLogin(request.getLogin());
//                updatedMember = memberRepository.save(updatedMember);
//                response = new ResponseEntity(updatedMember, HttpStatus.OK);
//            }
//        }
//        return response;
//    }
//
//    public ResponseEntity<BaseResponse> deleteMember(String token, Integer id)
//    {
//        Session session = sessionRepository.findByToken(token);
//        ResponseEntity<BaseResponse> response = new ResponseEntity(new BaseResponse("Bad request!"), HttpStatus.BAD_REQUEST);
//        if (session != null && Tools.isSessionValid(session)) {
//            Member member = session.getMember();
//            if (member.getRole() == Role.Moderator || member.getId() == id) {
//                memberRepository.delete(memberRepository.getOne(id));
//                response = new ResponseEntity("Deleted.", HttpStatus.OK);
//            }
//        }
//        return response;
//    }
}
