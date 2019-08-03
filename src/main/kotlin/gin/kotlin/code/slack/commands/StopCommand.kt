package gin.kotlin.code.slack.commands

import com.github.seratch.jslack.api.model.Message
import gin.kotlin.code.slack.Client
import org.koin.core.KoinComponent
import org.koin.core.inject

class StopCommand : Command, KoinComponent {

    val client by inject<Client>()
    val factory by inject<CommandFactory>()

    override fun exec(info: CommandInfo, message : Message) {
        factory.topics[info.text]?.minus(message.user)
        client.sendMessage("Forwarding stopped for ${info.text}", message.channel)
    }
}