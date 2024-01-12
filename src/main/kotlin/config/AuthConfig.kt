package config

import com.fasterxml.jackson.annotation.JsonProperty

data class ApiTokenConfig(
    @JsonProperty("api_id") val apiId: Int,
    @JsonProperty("api_hash") val apiHash: String
)