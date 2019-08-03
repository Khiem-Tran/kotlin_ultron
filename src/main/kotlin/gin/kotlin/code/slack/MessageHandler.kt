package gin.kotlin.code.handlers

import com.github.seratch.jslack.api.model.Message
import com.github.seratch.jslack.api.rtm.RTMMessageHandler
import com.google.gson.Gson
import gin.kotlin.code.slack.Client
import gin.kotlin.code.slack.commands.CommandFactory
import org.koin.core.KoinComponent
import org.koin.core.inject

class MessageHandler : RTMMessageHandler, KoinComponent {

    private val gson = Gson()

    private val factory by inject<CommandFactory>()

    private val client by inject<Client>()

    private val supportedTypes = setOf("hello", "message")

    fun start() {
        client.register(this)
    }

    override fun handle(msg: String) {
        val message = gson.fromJson(msg, Message::class.java)
        if (message.type in supportedTypes) factory.exec(message)
    }
}