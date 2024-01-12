import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import config.AppConfig
import handler.AuthorizationHandler
import handler.MessageHandler
import java.io.File

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Usage: kotlin LeoMatchFinderBot.kt <path_to_config>")
        return
    }

    val configFilePath = args[0]
    val config = loadConfig(configFilePath)

    val client = TelegramClientInitializer.initClient(config.auth, config.session)
    val authorizationHandler = AuthorizationHandler()
    val messageHandler = MessageHandler(client, config.leoMatchBotId, config.matchWords)

    TelegramClientInitializer.addUpdateHandlers(client, authorizationHandler, messageHandler)
}

private fun loadConfig(filePath: String): AppConfig {
    val objectMapper = ObjectMapper()
    return objectMapper.readValue(File(filePath))
}