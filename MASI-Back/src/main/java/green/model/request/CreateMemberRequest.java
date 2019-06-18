package green.model.request;

import com.google.gson.annotations.SerializedName;
import green.entity.Language;
import green.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CreateMemberRequest {
    @SerializedName("active")
    private boolean active;

    @SerializedName("login")
    private String login;

    @SerializedName("name")
    private String name;

    @SerializedName("password")
    private String password;

    @SerializedName("role")
    private Role role;

    @SerializedName("language")
    private Language language;
}
