package green.model.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginMemberRequest extends BaseRequest {
    @SerializedName("login")
    private String login;

    @SerializedName("password")
    private String password;
}
