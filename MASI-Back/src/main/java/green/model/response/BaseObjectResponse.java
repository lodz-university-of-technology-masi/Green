package green.model.response;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class BaseObjectResponse<T> {

	@SerializedName("code")
	private int code;

	@SerializedName("message")
	private String message;

	@SerializedName("response")
	private T response;
	
	public String toString() {
		return new Gson().toJson(this);
	}
}
