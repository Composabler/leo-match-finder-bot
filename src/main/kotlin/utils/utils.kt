package utils

import it.tdlight.client.SimpleTelegramClient
import it.tdlight.jni.TdApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.concurrent.CompletableFuture


val Scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

fun coroutine(block: suspend CoroutineScope.() -> Unit) = Scope.launch { block() }

fun SimpleTelegramClient.sendTextMessage(chatId: Long, text: String, wait: Boolean): CompletableFuture<TdApi.Message> {
    val sendMessage = TdApi.SendMessage()

    sendMessage.chatId = chatId

    val inputMessage = TdApi.InputMessageText()

    inputMessage.text = TdApi.FormattedText(text, emptyArray())

    sendMessage.inputMessageContent = inputMessage

    return this.sendMessage(sendMessage, wait)
}