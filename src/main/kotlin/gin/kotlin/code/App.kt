package gin.kotlin.code

import com.google.inject.Guice
import com.google.inject.Inject
import com.google.inject.Injector
import gin.kotlin.code.slack.Client
import gin.kotlin.code.slack.ClientImpl
import gin.kotlin.code.handlers.MessageHandler
import org.slf4j.LoggerFactory

class App {

    companion object {
        const val TOKEN = "xoxb-708285948944-701950033329-KlcM7LMBNA01VrA9XyADZKyN"
    }

    private val logger = LoggerFactory.getLogger(javaClass)

    fun start() {
        val dataModule = DataModule(App.TOKEN)
        val injector = Guice.createInjector(dataModule)
        dataModule.injector = injector
        injector.getInstance(ManagedServices::class.java).start()
        logger.info("Start RTM!")
        while (true) {}
    }
}

fun main(args: Array<String>) {
    App().start()
}