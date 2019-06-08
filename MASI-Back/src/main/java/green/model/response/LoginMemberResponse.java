package green.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class LoginMemberResponse extends BaseObjectResponse {
	@Setter
	@SerializedName("session_token")
	private String sessionToken;
}
