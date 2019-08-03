package gin.kotlin.code.handlers

import com.github.seratch.jslack.api.model.Message
import com.github.seratch.jslack.api.rtm.RTMMessageHandler
import com.google.gson.Gson
import com.google.inject.Inject
import gin.kotlin.code.slack.Client
import gin.kotlin.code.slack.commands.CommandFactory

class MessageHandler : RTMMessageHandler {

    private val gson = Gson()

    @Inject
    lateinit var factory : CommandFactory

    @Inject
    lateinit var client : Client



    fun start() {
        client.register(this)
    }

    override fun handle(msg: String) {
        val message = gson.fromJson(msg, Message::class.java)
        if (message.type == "user_typing") return
        factory.exec(message)
    }
}