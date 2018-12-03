
package br.edu.ifpb.carrinho;

import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 03/12/2018, 10:03:31
 */
public interface Carrinho {

    void adicionar(String produto);

    List<String> produtos();

    void remover(String produto);
    
    void finalizar();
    
}
