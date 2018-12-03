package br.edu.ifpb;

import br.edu.ifpb.carrinho.Carrinho;
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
public class ClienteDeCarrinho {

    public static void main(String[] args) {
        String recurso = "java:global/dac-sessionbean-core/CarrinhoDeCompras";
        Carrinho carrinho = lookup(recurso);
        
        
        carrinho.adicionar("Lápis de Cera");
        
        carrinho
                .produtos()
                .forEach(
                        c -> System.out.println("<p>" + c + "</p>")
                );
        
//        Informações:   Portable JNDI names for EJB CarrinhoDeCompras: 
//                [java:global/dac-sessionbean-core/CarrinhoDeCompras, 
//                java:global/dac-sessionbean-core/CarrinhoDeCompras!br.edu.ifpb.carrinho.Carrinho]

//        Informações:   Portable JNDI names for EJB ClientesEmJDBC: 
//                [java:global/dac-sessionbean-core/ClientesEmJDBC!br.edu.ifpb.domain.Clientes, 
//                java:global/dac-sessionbean-core/ClientesEmJDBC]
    }

    public static Carrinho lookup(String nomeDoRecurso) {
        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
            props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
            Context context = new InitialContext(props);

            return (Carrinho) context.lookup(nomeDoRecurso);
        } catch (NamingException ex) {
            Logger.getLogger(ClienteDeCarrinho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
