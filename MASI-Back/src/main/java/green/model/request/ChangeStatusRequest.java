package green.model.request;

import com.google.gson.annotations.SerializedName;



public class ChangeStatusRequest {
    @SerializedName("id")
    private Integer id;

    @SerializedName("active")
    private boolean active;

    public ChangeStatusRequest() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
