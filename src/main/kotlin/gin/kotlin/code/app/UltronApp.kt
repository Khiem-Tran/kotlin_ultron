package gin.kotlin.code.app

import com.hubspot.dropwizard.guice.GuiceBundle
import gin.kotlin.code.app.gin.kotlin.code.filters.DiagnosticContextFilter
import gin.kotlin.code.app.gin.kotlin.code.resources.HealthCheckResource
import gin.kotlin.code.app.gin.kotlin.code.resources.RootResource
import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

class UltronApp : Application<UltronConfiguration>() {

    override fun run(configuration: UltronConfiguration, environment: Environment) {
        environment.jersey().register(RootResource("UltronApp"))
        environment.jersey().register(DiagnosticContextFilter())
        environment.healthChecks().register("default", HealthCheckResource())
    }

    override fun initialize(bootstrap: Bootstrap<UltronConfiguration>) {
        val module = UltronModule()
        var guiceBundle = GuiceBundle.newBuilder<UltronConfiguration>()
                .addModule(module)
                .setConfigClass(UltronConfiguration::class.java)
                .enableAutoConfig("gin.kotlin.code.app")
                .build()

        module.guiceBundle = guiceBundle
        bootstrap.addBundle(guiceBundle)
        super.initialize(bootstrap)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            UltronApp().run(*args)
        }
    }
}

