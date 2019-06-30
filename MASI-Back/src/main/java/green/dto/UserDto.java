package green.dto;

import com.google.gson.annotations.SerializedName;

public class UserDto {


	@SerializedName("name")
	private String name;

	@SerializedName("email")
	private String email;

	@SerializedName("role")
	private String role;


	@SerializedName("id")
	private long id;

	public UserDto(String name, String email, String role, long id) {
		this.name = name;
		this.email = email;
		this.role = role;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getRole() {
		return role;
	}

	public long getId() {
		return id;
	}
}
