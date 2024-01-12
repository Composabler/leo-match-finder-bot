package handler

import it.tdlight.client.SimpleTelegramClient
import it.tdlight.jni.TdApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import utils.coroutine
import utils.sendTextMessage

@OptIn(ObsoleteCoroutinesApi::class)
class MessageHandler(
    private val client: SimpleTelegramClient,
    private val leoMatchBotId: Long,
    private val matchWords: Set<String>,
) {

    private val messageChannel = ConflatedBroadcastChannel<TdApi.UpdateNewMessage>()

    init {
        coroutine {
            messageChannel.openSubscription().consumeEach { update ->
                processMessage(update)
                // avoid throttling
                delay(1)
            }
        }
    }

    fun onUpdateNewMessage(update: TdApi.UpdateNewMessage) {
        coroutine {
            messageChannel.send(update)
        }
    }

    private fun processMessage(update: TdApi.UpdateNewMessage) {
        val message = update.message
        val sender = message.senderId

        if (sender !is TdApi.MessageSenderUser || sender.userId != leoMatchBotId) return

        val text = when (val content = message.content) {
            is TdApi.MessagePhoto -> content.caption.text
            is TdApi.MessageVideo -> content.caption.text
            else -> return
        }

        val words = text
            .split(" ", ",", ".", "-", "(", ")", "!", "?", ":", ";", "\"", "«", "»", "–", "\n", "\r", "\t")
            .map(String::trim)
            .filter(String::isNotEmpty)

        if (words.intersect(matchWords).isEmpty()) {
            client.sendTextMessage(leoMatchBotId, "\uD83D\uDC4E", wait = false)
            println("skip")
            return
        }

        println("match")
        client.sendTextMessage(leoMatchBotId, "match", wait = false)
    }
}