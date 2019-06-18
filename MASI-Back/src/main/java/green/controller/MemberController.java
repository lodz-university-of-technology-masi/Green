package green.controller;

import green.entity.Member;
import green.manager.MemberManager;
import green.model.request.CreateMemberRequest;
import green.model.request.LoginMemberRequest;
import green.model.request.RegisterMemberRequest;
import green.model.request.UpdateMemberRequest;
import green.model.response.BaseResponse;
import green.model.response.ListResponse;
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
    @GetMapping(value = "api/member/{token}")
    public ResponseEntity<ListResponse<Member>> getAllMembers(@PathVariable("token") String token) {
        return memberManager.getAllMembers(token);
    }

    @ApiOperation(value = "Get Member", consumes = "application/json")
    @GetMapping(value = "api/member/{token}/{id}")
    public ResponseEntity<Member> getMember(@PathVariable("token") String token, @PathVariable("id") Integer id) {
        return memberManager.getMember(token, id);
    }

    @ApiOperation(value = "Create Member", consumes = "application/json")
    @PostMapping(value = "api/member/{token}")
    public ResponseEntity<Member> createMember(@PathVariable("token") String token, @RequestBody CreateMemberRequest request) {
        return memberManager.createMember(token, request);
    }

    @ApiOperation(value = "Update Member", consumes = "application/json")
    @PutMapping(value = "api/member/{token}/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable("token") String token, @RequestBody UpdateMemberRequest request) {
        return memberManager.updateMember(token, request);
    }

    @ApiOperation(value = "Delete Member", consumes = "application/json")
    @DeleteMapping(value = "api/member/{token}/{id}")
    public ResponseEntity<BaseResponse> deleteMember(@PathVariable("token") String token, @PathVariable("id") Integer id) {
        return memberManager.deleteMember(token, id);
    }
}
