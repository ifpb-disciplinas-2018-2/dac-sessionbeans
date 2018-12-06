package br.edu.ifpb.web.converter;

import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.domain.ProdutoDAOIF;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author murillo
 */
@FacesConverter(value = "ProdutoConverter")
public class ProdutoConverter implements Converter{

    //@EJB
    //private ProdutoDAOIF dao;
    
    private ProdutoDAOIF dao;    
    public ProdutoConverter() throws NamingException {
        this.dao = (ProdutoDAOIF) new InitialContext()
                .lookup("java:global/dac-sessionbean-core/ProdutoDAOJDBC");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return dao.buscar(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Produto p = (Produto) value;
        return p.getDescricao();
    }
    
}
