package gin.kotlin.code.slack.commands

import com.github.seratch.jslack.api.model.Message
import gin.kotlin.code.slack.Client
import org.koin.core.KoinComponent
import org.koin.core.inject

class AnonymousCommand : Command , KoinComponent {

    val client by inject<Client>()

    override fun exec(info: CommandInfo, message : Message) {
        client.sendMessage("What u want", message.channel)
    }
}