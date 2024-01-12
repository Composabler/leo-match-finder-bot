package config

import com.fasterxml.jackson.annotation.JsonProperty

data class SessionConfig(
    @JsonProperty("session_path") val path: String = "tdlib-session",
    @JsonProperty("data_directory") val dataDirectory: String = "data",
    @JsonProperty("downloads_directory") val downloadsDirectory: String = "downloads",
)
