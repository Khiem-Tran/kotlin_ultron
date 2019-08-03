package gin.kotlin.code

import gin.kotlin.code.slack.Client
import gin.kotlin.code.handlers.MessageHandler
import org.koin.core.KoinComponent
import org.koin.core.inject

class ManagedServices : Managed, KoinComponent {

    val client by inject<Client> ()

    val handler by inject<MessageHandler>()

    override fun start() {
        handler.start()
        client.start()
    }

    override fun stop() { }
}