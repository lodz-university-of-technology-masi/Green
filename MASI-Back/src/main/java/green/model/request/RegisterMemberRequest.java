package green.model.request;

import com.google.gson.annotations.SerializedName;



public class RegisterMemberRequest extends BaseRequest {
    @SerializedName("name")
    private String name;

    @SerializedName("password")
    private String password;

    @SerializedName("name")
    private String email;

    @SerializedName("language")
    private String language;

    @SerializedName("role")
    private String role;

    public RegisterMemberRequest() {
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
