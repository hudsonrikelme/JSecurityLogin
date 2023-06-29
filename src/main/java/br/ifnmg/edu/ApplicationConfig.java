package br.ifnmg.edu;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author Hudson Rikelme <hudson.rikelme at ifnmg.edu.br>
 */
@Named
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:/CantinaDS",
        callerQuery = "select senha from credencial "
        + "where email = ? ",
        groupsQuery = "select tipocliente from credencial "
        + "where email = ? ",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        hashAlgorithmParameters = {
            "Pbkdf2PasswordHash.Iterations=3071",
            "${applicationConfig.dyna}"
        }
)
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                errorPage = "",
                useForwardToLogin = false
        )
)
/*Indicação de que estamos trabalhando com Jakarta Server Faces*/
@FacesConfig
@ApplicationScoped
public class ApplicationConfig {
    
    /**
     * Devolve um arranjo de Strings com duas configurações, qual o algoritmo que vai ser utilizado e qual o tamanho da chave de força
     * @return 
     */
    public String[] getDyna() {
        return new String[]{
            "Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512", 
            "Pbkdf2PasswordHash.SaltSizeBytes=64"};
    }
}
