package green.model.request;

import com.google.gson.annotations.SerializedName;


public class LoginMemberRequest extends BaseRequest {
    @SerializedName("name")
    private String name;

    @SerializedName("password")
    private String password;

    public LoginMemberRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
