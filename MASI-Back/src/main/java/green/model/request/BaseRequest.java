package green.model.request;

import com.google.gson.Gson;

public class BaseRequest {
    public String toString() {
        return new Gson().toJson(this);
    }
}
