package handler

import it.tdlight.jni.TdApi

class AuthorizationHandler {

    fun onUpdateAuthorizationState(update: TdApi.UpdateAuthorizationState) {
        val authorizationState = update.authorizationState

        val message = when (authorizationState) {
            is TdApi.AuthorizationStateReady -> "Logged in"
            is TdApi.AuthorizationStateClosing -> "Closing..."
            is TdApi.AuthorizationStateClosed -> "Closed"
            is TdApi.AuthorizationStateLoggingOut -> "Logging out..."
            else -> "Authorizing..."
        }

        println(message)
    }
}