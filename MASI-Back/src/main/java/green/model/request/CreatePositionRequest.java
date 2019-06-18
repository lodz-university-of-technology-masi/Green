package green.model.request;

import com.google.gson.annotations.SerializedName;
import green.entity.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CreatePositionRequest {

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("language")
    private Language language;

    @SerializedName("active")
    private boolean active;
}
