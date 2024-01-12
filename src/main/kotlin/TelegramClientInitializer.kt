import config.AuthConfig
import config.SessionConfig
import handler.AuthorizationHandler
import handler.MessageHandler
import it.tdlight.Init
import it.tdlight.Log
import it.tdlight.Slf4JLogMessageHandler
import it.tdlight.client.*
import it.tdlight.jni.TdApi
import java.nio.file.Paths

object TelegramClientInitializer {

    fun initClient(authConfig: AuthConfig, sessionConfig: SessionConfig): SimpleTelegramClient {
        Init.init()
        Log.setLogMessageHandler(1, Slf4JLogMessageHandler())

        val clientFactory = SimpleTelegramClientFactory()

        val apiToken = APIToken(authConfig.apiId, authConfig.apiHash)
        val settings = TDLibSettings.create(apiToken)
        val sessionPath = Paths.get(sessionConfig.path)

        with(settings) {
            databaseDirectoryPath = sessionPath.resolve(sessionConfig.dataDirectory)
            downloadedFilesDirectoryPath = sessionPath.resolve(sessionConfig.downloadsDirectory)
        }

        val clientBuilder = clientFactory.builder(settings)
        val authenticationData = AuthenticationSupplier.user(authConfig.phoneNumber)

        return clientBuilder.build(authenticationData)
    }


    fun addUpdateHandlers(client: SimpleTelegramClient, authorizationHandler: AuthorizationHandler, messageHandler: MessageHandler) {
        with(client) {
            addUpdateHandler(TdApi.UpdateAuthorizationState::class.java, authorizationHandler::onUpdateAuthorizationState)
            addUpdateHandler(TdApi.UpdateNewMessage::class.java, messageHandler::onUpdateNewMessage)
        }
    }
}
