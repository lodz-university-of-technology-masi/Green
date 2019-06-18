package green.model.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterMemberRequest extends BaseRequest {
    @SerializedName("login")
    private String login;

    @SerializedName("password")
    private String password;

    @SerializedName("name")
    private String name;
}
