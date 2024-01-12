package config

import com.fasterxml.jackson.annotation.JsonProperty

data class ApiConfig(
    @JsonProperty("api_token") val apiToken: ApiTokenConfig,
    @JsonProperty("session_path") val sessionPath: String,
    @JsonProperty("phone_number") val phoneNumber: String,
)
