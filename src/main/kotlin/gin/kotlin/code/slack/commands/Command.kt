package gin.kotlin.code.slack.commands

import com.github.seratch.jslack.api.model.Message
import gin.kotlin.code.slack.Client

interface Command {
    fun exec(client: Client, commandFactory: CommandFactory, info: CommandInfo, message : Message)
}