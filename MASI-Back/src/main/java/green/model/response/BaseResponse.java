package green.model.response;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BaseResponse {
    @SerializedName("message")
    private String message;

    public String toString() {
        return new Gson().toJson(this);
    }
}
