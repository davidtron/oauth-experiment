package uk.co.spiraltechnology.experiment.keycloakspi

import org.keycloak.component.ComponentModel
import org.keycloak.models.KeycloakSession
import org.keycloak.storage.UserStorageProvider
import org.keycloak.storage.UserStorageProviderFactory

class RemoteUserStorageProviderFactory : UserStorageProviderFactory<UserStorageProvider> {
    override fun create(session: KeycloakSession?, model: ComponentModel?): UserStorageProvider {
        return RemoteUserStorageProvider(session, model)
    }

    override fun getId(): String {
        return "remote-user-storage"
    }
}