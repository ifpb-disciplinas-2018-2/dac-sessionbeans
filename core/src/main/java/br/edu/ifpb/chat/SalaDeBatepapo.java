package br.edu.ifpb.chat;

import java.util.Collections;
import java.util.List;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Remote;
import javax.ejb.Singleton;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 06/12/2018, 08:15:31
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.WRITE)
@Remote(Chat.class)
//@DependsOn("ChatOnline")
public class SalaDeBatepapo implements Chat {

//    private List<String> mensagens = new CopyOnWriteArrayList<>();
    @EJB
    private ChatOnline chat;

    // Lock - WRITE
//    @Lock(LockType.WRITE)
    public void novaMensagem(String msg) {
        this.chat.novaMensagem(msg);
    }

    // Lock - WRITE 
//    @Lock(LockType.WRITE)
    public void removerMensagem(String msg) {
        this.chat.removerMensagem(msg);
    }

    @Lock(LockType.READ)
    public List<String> historico() {
        return Collections.unmodifiableList(
                chat.historico()
        );
    }
}
