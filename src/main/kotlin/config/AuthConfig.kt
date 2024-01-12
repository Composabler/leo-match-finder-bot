package config

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthConfig(
    @JsonProperty("api_id") val apiId: Int,
    @JsonProperty("api_hash") val apiHash: String,
    @JsonProperty("phone_number") val phoneNumber: Long,
)