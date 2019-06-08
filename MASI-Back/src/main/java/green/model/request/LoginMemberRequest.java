package green.model.request;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class LoginMemberRequest {

	@SerializedName("login")
	private String login;

	@SerializedName("password")
	private String password;
}
