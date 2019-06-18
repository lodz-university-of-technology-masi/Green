package green.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LoginMemberResponse extends BaseResponse {
    @SerializedName("session_token")
    private String sessionToken;
}
