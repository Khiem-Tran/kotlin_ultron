package gin.kotlin.code.app.gin.kotlin.code.resources

import com.codahale.metrics.health.HealthCheck

class HealthCheckResource : HealthCheck() {
    override fun check(): Result {
        return Result.healthy()
    }

}