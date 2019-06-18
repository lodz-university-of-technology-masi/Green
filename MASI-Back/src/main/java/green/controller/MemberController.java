package green.controller;

import green.entity.Member;
import green.manager.MemberManager;
import green.model.request.LoginMemberRequest;
import green.model.request.RegisterMemberRequest;
import green.model.response.BaseResponse;
import green.model.response.GetAllResponse;
import green.model.response.LoginMemberResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    @Autowired
    MemberManager memberManager;

    @ApiOperation(value = "Register Member", consumes = "application/json")
    @PostMapping(value = "api/member/register")
    public ResponseEntity<BaseResponse> registerMember(@RequestBody RegisterMemberRequest request) {
        return memberManager.registerUser(request);
    }

    @ApiOperation(value = "Login Member", consumes = "application/json")
    @PostMapping(value = "api/member/login")
    public ResponseEntity<LoginMemberResponse> loginMember(@RequestBody LoginMemberRequest request) {
        return memberManager.login(request);
    }

    @ApiOperation(value = "Get All Members", consumes = "application/json")
    @GetMapping(value = "api/member")
    public ResponseEntity<GetAllResponse<Member>> getAllMembers() {
        return memberManager.getAllMembers();
    }

    @ApiOperation(value = "Get Member", consumes = "application/json")
    @GetMapping(value = "api/member/{token}")
    public ResponseEntity<Member> getMember(@PathVariable("token") String token) {
        return memberManager.getMember(token);
    }
}
