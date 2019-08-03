package gin.kotlin.code.slack.commands

import com.github.seratch.jslack.api.model.Message
import gin.kotlin.code.slack.Client

class StopCommand : Command {

    override fun exec(client: Client, commandFactory: CommandFactory,info: CommandInfo, message : Message) {
        commandFactory.topics[info.text]?.minus(message.user)
        client.sendMessage("Forwarding stopped for ${info.text}", message.channel)
    }
}