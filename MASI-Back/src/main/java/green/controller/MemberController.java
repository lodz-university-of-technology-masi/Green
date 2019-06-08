package green.controller;

import green.manager.MemberManager;
import green.model.request.LoginMemberRequest;
import green.model.request.RegisterMemberRequest;
import green.model.response.LoginMemberResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

	@Autowired
    MemberManager memberManager;

    @ApiOperation(value="Register Member", consumes = "application/json")
    @PostMapping(value = "api/member/register")
    public String registerUser(@RequestBody RegisterMemberRequest request) {
        return memberManager.registerUser(request).toString();
    }

	@ApiOperation(value="Login Member", response= LoginMemberResponse.class, consumes = "application/json")
	@PostMapping(value = "api/member/login")
	public String registerUser(@RequestBody LoginMemberRequest request) {
		return memberManager.login(request).toString();
	}
}
