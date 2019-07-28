package gin.kotlin.code.app

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import com.hubspot.dropwizard.guice.GuiceBundle
import gin.kotlin.code.app.gin.kotlin.code.app.services.SlackService

class UltronModule : AbstractModule() {

    var guiceBundle : GuiceBundle<UltronConfiguration>? = null

    override fun configure() {
    }

    @Provides
    @Singleton
    fun getSlackService() : SlackService {
        val service = SlackService()
        guiceBundle?.injector?.injectMembers(service)
        return service
    }
}