package io.river.backend.global.webMvc

import io.river.backend.global.app.AppConfig
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.nio.file.Paths

@Configuration
class WebMvcConfig : WebMvcConfigurer {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        val location = Paths.get(AppConfig.getGenFileDirPath()).toUri().toString()

        registry.addResourceHandler("/gen/**")
            .addResourceLocations(location)
    }
}