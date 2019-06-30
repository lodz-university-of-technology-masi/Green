package green.model.request;

import com.google.gson.annotations.SerializedName;


public class EditTestRequest extends CreateTestRequest{


    @SerializedName("id")
    private Integer id;

    public EditTestRequest() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
