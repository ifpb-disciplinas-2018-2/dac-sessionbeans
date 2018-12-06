package br.edu.ifpb.chat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 06/12/2018, 07:39:31
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Startup
public class ChatOnline{

//    private List<String> mensagens = new ArrayList<>();
    private List<String> mensagens = new CopyOnWriteArrayList<>();

    private Object lock = new Object();

//    public synchronized void novaMensagem(String msg) {
    public void novaMensagem(String msg) {
        synchronized (mensagens) { // 
            this.mensagens.add(msg);
        }
    }

    public void removerMensagem(String msg) {
//        synchronized (this) { //pior!!! Não usem!!!
//            this.mensagens.remove(msg);
//        }
        synchronized (lock) { // 
            this.mensagens.remove(msg);
        }
    }

    public List<String> historico() {
        return Collections.unmodifiableList(mensagens);
    }
}
