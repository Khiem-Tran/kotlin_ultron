package gin.kotlin.code.slack

import com.github.seratch.jslack.api.rtm.RTMMessageHandler

interface Client {
    fun sendMessage(message : String, channelId : String? = null)
    fun register(handler: RTMMessageHandler)
    fun start()
}