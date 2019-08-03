package gin.kotlin.code.slack.commands

import com.github.seratch.jslack.api.model.Message
import gin.kotlin.code.slack.Client
import org.koin.core.KoinComponent
import org.koin.core.inject

class HelloCommand : Command, KoinComponent {

    val client by inject<Client>()

    private val greek = "I am a bot!"

    override fun exec(info: CommandInfo, message : Message) {
        client.sendMessage(greek)
    }
}