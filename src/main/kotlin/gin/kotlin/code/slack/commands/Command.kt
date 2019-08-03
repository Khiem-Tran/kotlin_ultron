package gin.kotlin.code.slack.commands

import com.github.seratch.jslack.api.model.Message
import gin.kotlin.code.slack.Client

interface Command {
    fun exec(info: CommandInfo, message : Message)
}