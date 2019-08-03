package gin.kotlin.code.slack.commands

import com.github.seratch.jslack.api.model.Message
import gin.kotlin.code.slack.Client
import org.koin.core.KoinComponent
import org.koin.core.inject

class ForwardCommand : Command, KoinComponent {

    private val client by inject<Client>()
    private val factory by inject<CommandFactory>()

    override fun exec(info: CommandInfo, message : Message) {
        factory.topics.putIfAbsent(info.text, mutableSetOf())
        factory.topics[info.text]?.plus(message.user)
        client.sendMessage("Forwarding set for ${info.text}", message.channel)
    }
}