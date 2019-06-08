package green.model.response;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class BaseArrayResponse<T> {

	@SerializedName("code")
	private int code;

	@SerializedName("message")
	private String message;

	@SerializedName("response")
	private List<T> response;

	public String toString() {
		return new Gson().toJson(this);
	}
}

