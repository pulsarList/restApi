package groovy.modelsP;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class UserData {

    //private User data;

    @JsonProperty("data") //response field
    private User user;

}
