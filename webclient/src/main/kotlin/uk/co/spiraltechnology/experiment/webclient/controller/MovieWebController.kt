package uk.co.spiraltechnology.experiment.webclient.controller

import org.springframework.core.ParameterizedTypeReference
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.reactive.function.client.WebClient
import uk.co.spiraltechnology.experiment.webclient.dto.MovieDto

@Controller
class MovieWebController(val webClient: WebClient) {


    @GetMapping("/movies")
    fun getMovies(model : Model, @AuthenticationPrincipal principal : OidcUser) : String {

        val movies = webClient
            .get().uri("http://localhost:8081/movies/all").retrieve()
            .bodyToMono(object : ParameterizedTypeReference<List<MovieDto>>() {})
            .block() // since we are not reactive its ok to block

        model.addAttribute("movies", movies)

        return "movies"
    }
}