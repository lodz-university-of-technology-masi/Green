package green.model.request;

import com.google.gson.annotations.SerializedName;
import green.dto.VersionDto;
import green.entity.Version;

import java.util.List;


public class CreateTestRequest {

    @SerializedName("name")
    private String name;

    @SerializedName("candidate")
    private String candidate;

    @SerializedName("redactor")
    private String redactor;

    @SerializedName("position")
    private String position;

    @SerializedName("versions")
    private List<VersionDto> versions;

    public CreateTestRequest() {
    }

    public String getName() {
        return name;
    }

    public String getCandidate() {
        return candidate;
    }

    public String getRedactor() {
        return redactor;
    }

    public String getPosition() {
        return position;
    }

    public List<VersionDto> getVersions() {
        return versions;
    }
}
