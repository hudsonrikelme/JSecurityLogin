package br.ifnmg.edu;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 *
 * @author Hudson Rikelme <hudson.rikelme at ifnmg.edu.br>
 */
@ApplicationScoped
public class DadosIniciais {

    @Inject
    DataServiceBeanLocal dataService;

    public void execute(@Observes @Initialized(ApplicationScoped.class) Object event) {
        if (dataService.getAllCredenciais().isEmpty()) {
            Cliente c = new Cliente();
            c.setNome("Hudson Rikelme Soares Aquino");
            c.setCpf("111.222.333-44");
            dataService.criaCredencial(c,"hudson@", "1234");
            
            
            
            

//            dataService.salvarCliente(c);
//            dataService.criaCliente(hudson);
        }
    }
}
