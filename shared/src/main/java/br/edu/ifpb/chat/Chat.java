package br.edu.ifpb.chat;

import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 06/12/2018, 07:44:29
 */
public interface Chat {

    public void novaMensagem(String msg);
    public void removerMensagem(String msg);
    public List<String> historico();

}
