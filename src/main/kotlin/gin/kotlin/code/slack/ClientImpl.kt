package gin.kotlin.code.slack

import com.github.seratch.jslack.Slack
import com.github.seratch.jslack.api.rtm.RTMMessageHandler
import com.github.seratch.jslack.api.rtm.message.Message

class ClientImpl(val token : String) : Client {

    private val slack = Slack.getInstance()
    private val channelMap = slack.methods()
            .channelsList { req -> req.token(token) }
            .channels
            .asSequence()
            .map { it.name to it }
            .toMap()

    private val rtm = slack.rtm(token)

    override fun sendMessage(message : String, channelId : String?) {
        rtm.sendMessage(Message.builder()
                .id(System.currentTimeMillis())
                .channel(channelId ?: channelMap["general"]?.id)
                .text(message)
                .build().toJSONString())
    }

    override fun register(handler : RTMMessageHandler) {
        rtm.addMessageHandler(handler)
    }

    override fun start() {
        rtm.connect()
    }
}
