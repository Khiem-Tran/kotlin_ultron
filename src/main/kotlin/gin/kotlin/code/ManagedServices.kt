package gin.kotlin.code

import com.google.inject.Inject
import gin.kotlin.code.slack.Client
import gin.kotlin.code.handlers.MessageHandler

class ManagedServices : Managed {

    @Inject
    lateinit var client : Client

    @Inject
    lateinit var handler: MessageHandler

    override fun start() {
        handler.start()
        client.start()
    }

    override fun stop() { }
}