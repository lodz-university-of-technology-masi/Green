package green.controller;

import green.manager.MemberManager;
import green.model.request.ChangeLanugageRequest;
import green.model.request.EditMemberRequest;
import green.model.request.LoginMemberRequest;
import green.model.request.RegisterMemberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class MemberController {

    @Autowired
    MemberManager memberManager;

    @PostMapping(value = "api/member/register")
    public String registerMember(@RequestBody RegisterMemberRequest request) {
        return memberManager.registerUser(request).toString();
    }

    @PostMapping(value = "api/member/login")
    public String registerMember(@RequestBody LoginMemberRequest request) {
        return memberManager.login(request).toString();
    }

    @PostMapping(value = "api/member/GetUserClaims")
    public String getUserClaims(@RequestBody LoginMemberRequest request) {
        return memberManager.getUserClaims(request).toString();
    }
    @PutMapping(value = "api/member/Language")
    public String changeLanguage(@RequestBody ChangeLanugageRequest request) {
        return memberManager.changeLanguage(request).toString();
    }
    @GetMapping(value = "api/member/getAll")
    public String getAllMembers() {
        return memberManager.getAllMembers().toString();
    }

    @PutMapping(value = "api/member/edit")
    public String editMember(@RequestBody EditMemberRequest request) {
        return memberManager.editMember(request).toString();
    }

//
//    @ApiOperation(value = "Login Member", consumes = "application/json")
//    @PostMapping(value = "api/member/login")
//    public ResponseEntity<LoginMemberResponse> loginMember(@RequestBody LoginMemberRequest request) {
//        return memberManager.login(request);
//    }
//
//    @ApiOperation(value = "Get All Members", consumes = "application/json")
//    @GetMapping(value = "api/member/{token}")
//    public ResponseEntity<ListResponse<Member>> getAllMembers(@PathVariable("token") String token) {
//        return memberManager.getAllMembers(token);
//    }
//
//    @ApiOperation(value = "Get Member", consumes = "application/json")
//    @GetMapping(value = "api/member/{token}/{id}")
//    public ResponseEntity<Member> getMember(@PathVariable("token") String token, @PathVariable("id") Integer id) {
//        return memberManager.getMember(token, id);
//    }
//
//    @ApiOperation(value = "Create Member", consumes = "application/json")
//    @PostMapping(value = "api/member/{token}")
//    public ResponseEntity<Member> createMember(@PathVariable("token") String token, @RequestBody CreateMemberRequest request) {
//        return memberManager.createMember(token, request);
//    }
//
//    @ApiOperation(value = "Update Member", consumes = "application/json")
//    @PutMapping(value = "api/member/{token}/{id}")
//    public ResponseEntity<Member> updateMember(@PathVariable("token") String token, @RequestBody EditMemberRequest request) {
//        return memberManager.updateMember(token, request);
//    }
//
//    @ApiOperation(value = "Delete Member", consumes = "application/json")
//    @DeleteMapping(value = "api/member/{token}/{id}")
//    public ResponseEntity<BaseResponse> deleteMember(@PathVariable("token") String token, @PathVariable("id") Integer id) {
//        return memberManager.deleteMember(token, id);
//    }
}
