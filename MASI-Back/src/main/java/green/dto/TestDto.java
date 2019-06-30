package green.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestDto {


	@SerializedName("name")
	private String name;

	@SerializedName("candidate")
	private String candidate;

	@SerializedName("redactor")
	private String redactor;

	@SerializedName("position")
	private String position;

	@SerializedName("versions")
	private List<VersionDto> vesions;

	@SerializedName("id")
	private long id;

	public TestDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCandidate() {
		return candidate;
	}

	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}

	public String getRedactor() {
		return redactor;
	}

	public void setRedactor(String redactor) {
		this.redactor = redactor;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<VersionDto> getVesions() {
		return vesions;
	}

	public void setVesions(List<VersionDto> vesions) {
		this.vesions = vesions;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
