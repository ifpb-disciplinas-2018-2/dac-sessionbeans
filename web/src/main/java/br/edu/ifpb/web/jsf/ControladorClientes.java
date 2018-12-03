package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.Clientes;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 29/11/2018, 07:24:53
 */
@Named
@RequestScoped
public class ControladorClientes {

    @EJB
    private Clientes clientes;
//    private Clientes clientes = new ServiceLocator()
//            .lookup(
//                    "java:global/dac-sessionbean-core/ClientesEmJDBC!br.edu.ifpb.domain.Clientes",
//                    Clientes.class
//            );

    public List<Cliente> todos() {
        return this.clientes.todosOsClientes();
    }

}
