package br.ifnmg.edu;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.SEND_CONTINUE;
import static javax.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import static javax.security.enterprise.AuthenticationStatus.SUCCESS;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author hudson Rikelme &lt;luis.guisso at ifnmg.edu.br&gt;
 */
@Named
@RequestScoped
public class ControladorLogin {

    @NotEmpty
    private String email;

    @NotEmpty
    private String senha;

    @Inject
    FacesContext facesContext;

    //SecurityContext é injetado
    @Inject
    SecurityContext securityContext;

    //<editor-fold defaultstate="collapsed" desc="G/S">
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

//</editor-fold>
    public void execute() throws IOException {
        switch (processAuthentication()) {
            //Ainda está no processo de Autenticação
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            //Falha na Autenticação
            case SEND_FAILURE:
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Credencial Inválida", null));
                break;
            //Sucesso na Autenticação
            case SUCCESS:
                getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/app/index.xhtml");
                break;
        }
    }

    //Autenticação - Pega o email e a senha e entrega para verificação
    private AuthenticationStatus processAuthentication() {
        ExternalContext ec = getExternalContext();
        return securityContext.authenticate(
                (HttpServletRequest) ec.getRequest(),
                (HttpServletResponse) ec.getResponse(),
                AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(email, senha)));
    }

    private ExternalContext getExternalContext() {
        return facesContext.getExternalContext();
    }

}
