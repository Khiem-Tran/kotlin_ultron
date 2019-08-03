package gin.kotlin.code

import com.google.inject.AbstractModule
import com.google.inject.Injector
import com.google.inject.Provides
import com.google.inject.Singleton
import gin.kotlin.code.slack.Client
import gin.kotlin.code.slack.ClientImpl
import gin.kotlin.code.slack.commands.Command
import gin.kotlin.code.slack.commands.CommandFactory
import gin.kotlin.code.slack.commands.ForwardCommand
import gin.kotlin.code.slack.commands.HelloCommand
import gin.kotlin.code.handlers.MessageHandler
import javax.inject.Named

class DataModule(private val token : String) : AbstractModule() {

    lateinit var injector : Injector

    @Provides
    @Singleton
    fun getSlackClient() : Client {
        val client = ClientImpl(token)
        injector.injectMembers(client)
        return client
    }

    @Provides
    @Singleton
    fun getMessageHandler() : MessageHandler {
        val handler = MessageHandler()
        handler.client = injector.getInstance(Client::class.java)
        handler.factory = injector.getInstance(CommandFactory::class.java)
        return handler
    }

    @Provides
    @Singleton
    fun getCommandFactory() : CommandFactory {
        val factory = CommandFactory()
        factory.client = injector.getInstance(Client::class.java)
        return factory
    }

    @Provides
    @Singleton
    fun getManagedService() : ManagedServices {
        val service = ManagedServices()
        service.client = injector.getInstance(Client::class.java)
        service.handler = injector.getInstance(MessageHandler::class.java)
        return service
    }
}