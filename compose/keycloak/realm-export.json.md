# HowTo
It is not possible to replace the master realm. Only new realms can be imported

1. export the realm in keycloak
2. add some users
3. solve [KEYCLOAK-16682](https://issues.redhat.com/browse/KEYCLOAK-16682) by filling the arrays
   in .clients[].defaultClientScopes and .clients[].optionalClientScopes

TODO: script this with cli-export and jq for filling scopes and users
