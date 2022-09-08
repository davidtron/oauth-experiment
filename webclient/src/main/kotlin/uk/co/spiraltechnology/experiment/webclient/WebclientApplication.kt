package uk.co.spiraltechnology.experiment.webclient

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction
import org.springframework.security.oauth2.client.web.server.AuthenticatedPrincipalServerOAuth2AuthorizedClientRepository
import org.springframework.web.reactive.function.client.WebClient

@SpringBootApplication
class WebclientApplication {


	@Bean
	fun webClient(clientRegistrationRepository: ClientRegistrationRepository, oAuth2AuthorizedClientRepository: OAuth2AuthorizedClientRepository) : WebClient {

		val oauth2 = ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository,oAuth2AuthorizedClientRepository)
		oauth2.setDefaultOAuth2AuthorizedClient(true)

		return WebClient
			.builder().apply(oauth2.oauth2Configuration())
			.build()
	}
}

fun main(args: Array<String>) {
	runApplication<WebclientApplication>(*args)
}


