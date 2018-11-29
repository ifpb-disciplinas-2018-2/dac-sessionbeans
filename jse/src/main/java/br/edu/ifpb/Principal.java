package br.edu.ifpb;

import br.edu.ifpb.domain.Clientes;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 29/11/2018, 09:42:38
 */
public class Principal {

    public static void main(String[] args) {
        String recurso = "java:global/dac-sessionbean-core/ClientesEmJDBC";
        Clientes clientes = lookup(recurso);
        clientes
                .todosOsClientes()
                .forEach(
                        c -> System.out.println("<p>" + c.getNome() + "</p>")
                );

//        Informações:   Portable JNDI names for EJB ClientesEmJDBC: 
//                [java:global/dac-sessionbean-core/ClientesEmJDBC!br.edu.ifpb.domain.Clientes, 
//                java:global/dac-sessionbean-core/ClientesEmJDBC]
    }

    public static Clientes lookup(String nomeDoRecurso) {
        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
            props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
            Context context = new InitialContext(props);

            return (Clientes) context.lookup(nomeDoRecurso);
        } catch (NamingException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
