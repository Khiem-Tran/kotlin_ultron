package gin.kotlin.code.app

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import org.hibernate.validator.constraints.NotEmpty

class UltronConfiguration : Configuration() {
    @JsonProperty
    @NotEmpty
    val appName: String = "appName";
}