package green.model.request;

import com.google.gson.annotations.SerializedName;


public class ChangeLanugageRequest extends BaseRequest {
    @SerializedName("name")
    private String name;

    @SerializedName("language")
    private String language;

    public ChangeLanugageRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
