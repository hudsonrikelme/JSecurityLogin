package br.ifnmg.edu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author hudson Rikelme &lt;luis.guisso at ifnmg.edu.br&gt;
 */
@Stateless
public class DataServiceBean
        implements DataServiceBeanLocal {

    @PersistenceContext(unitName = "CantinaPU")
    EntityManager em;

    @Inject
    Pbkdf2PasswordHash passwordHasher;

    
    /**
     * Cria a credencial de um cliente de acordo com suas informações
     * @param cliente
     * @param email
     * @param senha
     * @return 
     */
    @Override
    public Credencial criaCredencial(Cliente cliente, String email, String senha) {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("Pbkdf2PasswordHash.Iterations", "3071");
        parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512");
        parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
        passwordHasher.initialize(parameters);

        Credencial novaCredencial =  new Credencial(
                email, 
                passwordHasher.generate(senha.toCharArray()), 
                cliente);
        cliente.setCredencial(novaCredencial);     

        em.persist(novaCredencial);
        
        return novaCredencial;
    }

    
    
    @Override
    public List<Credencial> getAllCredenciais() {
        return em.createNamedQuery("Credencial.all", Credencial.class).getResultList();
    }

    @Override
    public Optional<Credencial> getCredencial(String email) {
        return em.createNamedQuery("Credencial.email", Credencial.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst(); //Pode ser null (Optional)...
    }

    @Override
    public Cliente getCliente(Credencial credencial) {
        return em.createNamedQuery("Cliente.byCredencial", Cliente.class)
                .setParameter("cliente.id", credencial.getId())
                .getSingleResult();
    }

    @Override
    public Cliente criaCliente(Credencial credencial) {
        Cliente novoCliente = new Cliente(credencial);
        em.persist(novoCliente);
        return novoCliente;
    }

    @Override
    public void salvarCliente(Cliente cliente) {
        em.persist(cliente);
    }

    
    
    

}
