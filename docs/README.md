
Assuming keycloak has been started with

`docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:19.0.1 start-dev`

 - Set up a realm called `movies`
 - A client called `spring-web-app`
   - Set redirect_url to be http://localhost:8083/callback
   - Client authentication **on** as this is a trusted client
   - A user in the realm
 - A client call `js-web-app`
     - Set redirect_url to be http://localhost:8083/callback
     - Client authentication **off** as this is an untrusted PKCE client
     - A user in the realm

 