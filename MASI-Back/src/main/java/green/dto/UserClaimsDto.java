package green.dto;

import com.google.gson.annotations.SerializedName;

public class UserClaimsDto {


	@SerializedName("name")
	private String name;

	@SerializedName("email")
	private String email;

	@SerializedName("role")
	private String role;

	@SerializedName("language")
	private String language;

	@SerializedName("id")
	private long id;

	public UserClaimsDto(String name, String email, String role, String language, long id) {
		this.name = name;
		this.email = email;
		this.role = role;
		this.language = language;
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

	public String getLanguage() {
		return language;
	}

	public long getId() {
		return id;
	}
}
