package gin.kotlin.code.slack.commands

import com.github.seratch.jslack.api.model.Message
import gin.kotlin.code.slack.Client

class AnonymousCommand : Command {
    override fun exec(client: Client, commandFactory: CommandFactory,info: CommandInfo, message : Message) {
        client.sendMessage("What u want", message.channel)
    }
}