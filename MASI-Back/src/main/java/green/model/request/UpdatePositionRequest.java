package green.model.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UpdatePositionRequest extends CreatePositionRequest {
    @SerializedName("id")
    private Integer id;
}
