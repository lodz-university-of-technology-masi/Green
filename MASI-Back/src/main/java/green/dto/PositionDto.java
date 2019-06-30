package green.dto;

import com.google.gson.annotations.SerializedName;

public class PositionDto {


	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("active")
	private boolean active;

	@SerializedName("id")
	private long id;

	public PositionDto(String name, String description, boolean active, long id) {
		this.name = name;
		this.description = description;
		this.active = active;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isActive() {
		return active;
	}

	public long getId() {
		return id;
	}
}
