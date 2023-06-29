package br.ifnmg.edu;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Hudson Rikelme <hudson.rikelme at ifnmg.edu.br>
 */
@Named
@RequestScoped
public class ControladorNovaCredencial {
    @Inject
    DataServiceBeanLocal dataService;
    
    private Cliente cliente;
    
    private Credencial credencial;

    public ControladorNovaCredencial() {
        cliente = new Cliente();
        credencial = new Credencial();
        credencial.setTipoCliente(Credencial.TipoCliente.CLIENTE);
        credencial.setCliente(cliente);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Credencial getCredencial() {
        return credencial;
    }

    public void setCredencial(Credencial credencial) {
        this.credencial = credencial;
    }

    
    
    
    public String save() {
        credencial = dataService.criaCredencial(
                cliente,
                credencial.getEmail(), 
                credencial.getSenha());
        
        
        return "/app/credenciais?faces-redirect=true";
    }
}
