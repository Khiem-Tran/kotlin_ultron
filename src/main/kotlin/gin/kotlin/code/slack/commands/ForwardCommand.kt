package gin.kotlin.code.slack.commands

import com.github.seratch.jslack.api.model.Message
import gin.kotlin.code.slack.Client

class ForwardCommand : Command {

    override fun exec(client: Client, commandFactory: CommandFactory, info: CommandInfo, message : Message) {
        commandFactory.topics[info.text] ?: commandFactory.topics.plus(Pair(info.text, setOf()))
        commandFactory.topics[info.text]!!.plus(message.user)
        client.sendMessage("Forwarding set for ${info.text}", message.channel)
    }
}