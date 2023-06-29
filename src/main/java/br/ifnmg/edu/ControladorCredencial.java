package br.ifnmg.edu;

import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.servlet.ServletException;

/**
 *
 * @author Hudson Rikelme <hudson.rikelme at ifnmg.edu.br>
 */
@Named
@RequestScoped
public class ControladorCredencial {

    @Inject
    DataServiceBeanLocal dataService;

    @Inject
    SecurityContext securityContext;

    @Inject
    FacesContext facesContext;

    private Optional<Credencial> currentCredencial;
    private Cliente currentCliente;

    /**
     * Contexto de Segurança fornece o Cliente Busca o nome pelo Principal
     * DataService pega o nome no banco de Dados Se recebo a credencial busca o
     * cliente da credencial e armazena em currentCliente
     */
    @PostConstruct
    public void initialize() {
        String email = securityContext.getCallerPrincipal().getName();
        this.currentCredencial = dataService.getCredencial(email);
        this.currentCredencial.ifPresent(credencial -> {
            this.currentCliente = dataService.getCliente(credencial);
        });
    }

    public Credencial getCurrentCredencial() {
        return currentCredencial.orElse(null);
    }

    public boolean isAuthenticated() {
        return securityContext.getCallerPrincipal() != null;
    }

    /**
     * Qual o papel que o cliente tem - ADMIN ou não
     *
     * @return
     */
    public boolean isAllowedToSeeClientes() {
        return securityContext.isCallerInRole("GERENTE");
    }

    public String logout() throws ServletException {
        facesContext.getExternalContext()
                .invalidateSession();
        return "/login?faces-redirect=true";
    }
}
