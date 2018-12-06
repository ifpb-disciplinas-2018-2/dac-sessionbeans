package br.edu.ifpb.web.jsf;

import br.edu.ifpb.chat.Chat;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 06/12/2018, 07:45:50
 */
@Named
@RequestScoped
public class ControladorDeChat  {

    @EJB
    private Chat chat;
    
    private String mensagem;
    
    public String enviar(){
        this.chat.novaMensagem(mensagem);
        this.mensagem = "";
        return null;
    }
    
    public List<String> todasAsMensagens(){
        return this.chat.historico();
                
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
}
