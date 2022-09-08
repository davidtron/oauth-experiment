package uk.co.spiraltechnology.experiment.resource.controller

import mu.KLogging
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import uk.co.spiraltechnology.experiment.resource.dto.MovieDto

@RestController
@RequestMapping("/movies")
class MovieController {

    companion object : KLogging()

    @GetMapping("/all")
    fun getAllMovies() : List<MovieDto> {
        return listOf(
            MovieDto("Back to the future", "Great Scott Marty"),
            MovieDto("Star Wars", "Use the force"),
            MovieDto("Austin Powers", "Who throws a shoe"),
            MovieDto("Belfast", "A semi biographical film from Ken"),
        )
    }

    @PreAuthorize("hasAuthority('ROLE_administrator') or #id == #jwt.subject")
    @GetMapping("/user/{id}")
    fun myMovies(@PathVariable id : String, @AuthenticationPrincipal jwt : Jwt): List<MovieDto> {
        logger.warn("Retrieved movies for user $id with subject ${jwt.subject}")
        return listOf(
            MovieDto("Back to the future", "Great Scott Marty"),
            MovieDto("Star Wars", "Use the force")
        )
    }
}