package br.ifnmg.edu;

import java.util.List;
import java.util.Optional;
import javax.ejb.Local;

/**
 *
 * @author hudson Rikelme &lt;luis.guisso at ifnmg.edu.br&gt;
 */
@Local
public interface DataServiceBeanLocal {




    Optional<Credencial> getCredencial(String email);

//    public Credencial criaCredencial(Cliente cliente, String email, String senha);

    public List<Credencial> getAllCredenciais();

    public Cliente getCliente(Credencial credencial);

    Cliente criaCliente(Credencial credencial);

    void salvarCliente(Cliente cliente);

//    public Credencial criaCredencial(Cliente cliente, String email, String senha, String group);

//    public Credencial criaCredencial(Cliente cliente, String email, String senha, Credencial.TipoCliente tipoCliente);

    public Credencial criaCredencial(Cliente cliente, String email, String senha);
    
}
