package green.model.request;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class RegisterMemberRequest {

	@Getter
	@SerializedName("login")
	private String login;

	@Getter
	@SerializedName("password")
	private String password;

	@Getter
	@SerializedName("name")
	private String name;
}
