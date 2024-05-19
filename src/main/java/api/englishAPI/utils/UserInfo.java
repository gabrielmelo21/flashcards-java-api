package api.englishAPI.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {
    @JsonProperty("email")
    private String email;

    @JsonProperty("given_name")
    private String given_name;

    @JsonProperty("family_name")
    private String family_name;

    @JsonProperty("picture")
    private String picture;
}
