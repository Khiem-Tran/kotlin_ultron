package gin.kotlin.code

import gin.kotlin.code.slack.Client
import gin.kotlin.code.slack.ClientImpl
import gin.kotlin.code.handlers.MessageHandler
import gin.kotlin.code.slack.commands.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

class DataKoinModule(private val token : String) {

    private  val module = module {
        single { ClientImpl(token) as Client }
        single { MessageHandler() }
        single { CommandFactory() }
        single { ManagedServices() }
        single<Command>(named("hello")) {  HelloCommand() }
        single<Command>(named("forward")) {  ForwardCommand() }
        single<Command>(named("stop")) {  StopCommand() }
        single<Command>(named("anonymous")) {  AnonymousCommand() }
    }

    fun get() = module
}