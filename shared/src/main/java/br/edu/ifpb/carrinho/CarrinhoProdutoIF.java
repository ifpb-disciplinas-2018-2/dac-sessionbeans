package br.edu.ifpb.carrinho;

import br.edu.ifpb.domain.Produto;
import java.util.List;

/**
 *
 * @author murillo
 */
public interface CarrinhoProdutoIF {
    
    void adicionar(Produto produto);

    List<Produto> produtos();

    void remover(Produto produto);
    
    void finalizar();
    
}
