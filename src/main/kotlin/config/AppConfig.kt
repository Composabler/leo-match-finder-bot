package config

import com.fasterxml.jackson.annotation.JsonProperty

data class AppConfig(
    @JsonProperty("auth") val auth: AuthConfig,
    @JsonProperty("session") val session: SessionConfig,
    @JsonProperty("leo_match_bot_id") val leoMatchBotId: Long,
    @JsonProperty("match_words") val matchWords: Set<String>,
)
