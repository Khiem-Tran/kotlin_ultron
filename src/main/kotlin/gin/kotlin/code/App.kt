package gin.kotlin.code

import com.sun.security.ntlm.Client
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject
import org.slf4j.LoggerFactory

class App : KoinComponent {

    companion object {
        const val TOKEN = "xoxb-708285948944-701950033329-KlcM7LMBNA01VrA9XyADZKyN"
    }

    private val logger = LoggerFactory.getLogger(javaClass)

    val services by inject<ManagedServices>()

    fun start() {
        logger.info("Start RTM!")
        services.start()
        while (true) {}
    }
}

fun main(args: Array<String>) {

    startKoin {
        modules(DataKoinModule(App.TOKEN).get())
    }

    App().start()
}