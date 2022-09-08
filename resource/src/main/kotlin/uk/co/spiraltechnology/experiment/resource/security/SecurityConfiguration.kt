package uk.co.spiraltechnology.experiment.resource.security

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {

        // Wire in converter that takes jwt realm roles and converts into list of granted authority
        val authenticationConverter = JwtAuthenticationConverter()
        authenticationConverter.setJwtGrantedAuthoritiesConverter( KeycloakRoleConverter())

        http!!
            .cors().configurationSource(corsConfiguration())
            .and()
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(authenticationConverter)
    }

    @Bean
    fun corsConfiguration() : CorsConfigurationSource {
        // Enable cross origin configuration in the resource server
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.setAllowedOrigins(listOf("*")) // All origins
        corsConfiguration.setAllowedMethods(listOf("GET", "POST"))
        corsConfiguration.setAllowedHeaders(listOf("*"))

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfiguration) // any route and subpath
        return source
    }

}