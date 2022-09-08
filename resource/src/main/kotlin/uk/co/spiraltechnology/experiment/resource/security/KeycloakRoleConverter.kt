package uk.co.spiraltechnology.experiment.resource.security

import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt

class KeycloakRoleConverter : Converter<Jwt, Collection<GrantedAuthority>> {

    override fun convert(jwt: Jwt): Collection<GrantedAuthority>? {
        val realmAccess = jwt.getClaimAsMap("realm_access")

        if (realmAccess.isNullOrEmpty()) return emptyList()

        val roles = realmAccess.get("roles") as List<String>

        return roles.asSequence()
            .map { SimpleGrantedAuthority("ROLE_" + it) }
            .toList()
    }
}