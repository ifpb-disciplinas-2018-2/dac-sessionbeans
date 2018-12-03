package br.edu.ifpb.web.jsf;

import br.edu.ifpb.carrinho.Carrinho;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 03/12/2018, 09:59:59
 */
@Named
@SessionScoped
//@RequestScoped
public class ControladorDeCarrinho implements Serializable {

    @EJB
    private Carrinho carrinho;
    private String produto;

    public String finalizar(){
        this.carrinho.finalizar();
        finalizarSessao();
        return "index.xhtml?faces-redirect=true";
    }
    
    public String remover(String produto){
        this.carrinho.remover(produto);
        return null;
    }
    
    public String add() {
        this.carrinho.adicionar(
                this.produto
        );
        this.produto = "";
        return null;
    }

    public List<String> todosOsProdutos() {
        return this.carrinho.produtos();
    }

    private void finalizarSessao() {
    
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        session.invalidate();
        
    }
    
    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    

}
