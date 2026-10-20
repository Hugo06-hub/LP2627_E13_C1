package pt.upt.perdidos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import pt.upt.perdidos.model.*;

public class Gerir {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private Session session = sessionFactory.openSession();

    private Cliente obterCliente(long idCliente) {
        Cliente cliente = session.get(Cliente.class, idCliente);
        if (cliente == null) {
            System.out.println("Erro: o cliente " + idCliente + " não existe");
        }
        return cliente;
    }

    private Funcionario obterFuncionario(long idFuncionario) {
        Funcionario funcionario = session.get(Funcionario.class, idFuncionario);
        if (funcionario == null) {
            System.out.println("Erro: o funcionário " + idFuncionario + " não existe");
        }
        return funcionario;
    }

    private Espaco obterEspaco(long idEspaco) {
        Espaco espaco = session.get(Espaco.class, idEspaco);
        if (espaco == null) {
            System.out.println("Erro: o espaço " + idEspaco + " não existe");
        }
        return espaco;
    }

    private Objeto obterObjeto(long idObjeto) {
        Objeto objeto = session.get(Objeto.class, idObjeto);
        if (objeto == null) {
            System.out.println("Erro: o objeto " + idObjeto + " não existe");
        }
        return objeto;
    }

    private Mensagem obterMensagem(long idMensagem) {
        Mensagem mensagem = session.get(Mensagem.class, idMensagem);
        if (mensagem == null) {
            System.out.println("Erro: a mensagem " + idMensagem + " não existe");
        }
        return mensagem;
    }

    private boolean gravar(Object objetoNovo) {
        Transaction transacao = session.beginTransaction();
        try {
            session.persist(objetoNovo);
            transacao.commit();
            return true;
        } catch (RuntimeException excecao) {
            if (transacao.isActive()) {
                transacao.rollback();
            }
            session.clear();
            System.out.println("Erro ao gravar na base de dados: " + excecao.getMessage());
            return false;
        }
    }

    private boolean atualizar() {
        Transaction transacao = session.beginTransaction();
        try {
            transacao.commit();
            return true;
        } catch (RuntimeException excecao) {
            if (transacao.isActive()) {
                transacao.rollback();
            }
            session.clear();
            System.out.println("Erro ao atualizar na base de dados: " + excecao.getMessage());
            return false;
        }
    }

    private boolean apagar(Object objetoParaApagar) {
        Transaction transacao = session.beginTransaction();
        try {
            session.remove(objetoParaApagar);
            transacao.commit();
            return true;
        } catch (RuntimeException excecao) {
            if (transacao.isActive()) {
                transacao.rollback();
            }
            session.clear();
            System.out.println("Erro ao apagar na base de dados: " + excecao.getMessage());
            return false;
        }
    }

    public Cliente registarCliente(String nome, int telefone, String email, String password) {
        if (nome == null || nome.isBlank()) {
            System.out.println("Erro: nome obrigatório");
            return null;
        }
        if (String.valueOf(telefone).length() != 9) {
            System.out.println("Erro: o telefone deve ter 9 dígitos");
            return null;
        }
        if (email == null || !email.contains("@")) {
            System.out.println("Erro: email inválido");
            return null;
        }
        if (password == null || password.length() < 4) {
            System.out.println("Erro: a password deve ter pelo menos 4 caracteres");
            return null;
        }

        Cliente cliente = new Cliente(nome, telefone, email, password);
        if (!gravar(cliente)) {
            return null;
        }
        return cliente;
    }

    public Funcionario registarFuncionario(String nome, String email, String password) {
        if (nome == null || nome.isBlank()) {
            System.out.println("Erro: nome obrigatório");
            return null;
        }
        if (email == null || !email.contains("@")) {
            System.out.println("Erro: email inválido");
            return null;
        }
        if (password == null || password.length() < 4) {
            System.out.println("Erro: a password deve ter pelo menos 4 caracteres");
            return null;
        }

        Funcionario funcionario = new Funcionario(nome, email, password);
        if (!gravar(funcionario)) {
            return null;
        }
        return funcionario;
    }

    public Espaco registarEspaco(String sala) {
        if (sala == null || sala.isBlank()) {
            System.out.println("Erro: nome da sala obrigatório");
            return null;
        }

        Espaco espaco = new Espaco(sala);
        if (!gravar(espaco)) {
            return null;
        }
        return espaco;
    }

    public Registo registarAcesso(long idCliente, long idEspaco, String tipo) {
        if (tipo == null || (!tipo.equals("ENTRADA") && !tipo.equals("SAIDA"))) {
            System.out.println("Erro: o tipo deve ser ENTRADA ou SAIDA");
            return null;
        }
        Cliente cliente = obterCliente(idCliente);
        Espaco espaco = obterEspaco(idEspaco);
        if (cliente == null || espaco == null) {
            return null;
        }

        Registo registo = new Registo(cliente, espaco, tipo);
        if (!gravar(registo)) {
            return null;
        }
        return registo;
    }

    public Objeto registarObjeto(long idEspaco, String nome, String tipo) {
        if (nome == null || nome.isBlank()) {
            System.out.println("Erro: nome do objeto obrigatório");
            return null;
        }
        Espaco espaco = obterEspaco(idEspaco);
        if (espaco == null) {
            return null;
        }

        Objeto objeto = new Objeto(nome, tipo, "ACHADO", null, espaco);
        if (!gravar(objeto)) {
            return null;
        }
        return objeto;
    }

    public Objeto reportarObjetoPerdido(long idCliente, long idEspaco, String nome, String tipo) {
        if (nome == null || nome.isBlank()) {
            System.out.println("Erro: nome do objeto obrigatório");
            return null;
        }
        Cliente cliente = obterCliente(idCliente);
        Espaco espaco = obterEspaco(idEspaco);
        if (cliente == null || espaco == null) {
            return null;
        }

        Objeto objeto = new Objeto(nome, tipo, "PERDIDO", cliente, espaco);
        if (!gravar(objeto)) {
            return null;
        }
        return objeto;
    }

    public Mensagem contactarPerdidosEAchados(long idCliente, long idObjeto, String canal, String texto) {
        if (texto == null || texto.isBlank()) {
            System.out.println("Erro: a mensagem não pode estar vazia");
            return null;
        }
        if (canal == null) {
            System.out.println("Erro: canal inválido (usar FORUM, TELEFONE ou EMAIL)");
            return null;
        }
        String canalMaiusculas = canal.trim().toUpperCase();
        if (!canalMaiusculas.equals("FORUM") && !canalMaiusculas.equals("TELEFONE") && !canalMaiusculas.equals("EMAIL")) {
            System.out.println("Erro: canal inválido (usar FORUM, TELEFONE ou EMAIL)");
            return null;
        }
        Cliente cliente = obterCliente(idCliente);
        Objeto objeto = obterObjeto(idObjeto);
        if (cliente == null || objeto == null) {
            return null;
        }

        Mensagem mensagem = new Mensagem(cliente, objeto, texto.trim(), canalMaiusculas);
        if (!gravar(mensagem)) {
            return null;
        }
        return mensagem;
    }

    public List<Mensagem> verMensagensDoObjeto(long idObjeto) {
        if (obterObjeto(idObjeto) == null) {
            return List.of();
        }
        return session.createQuery(
                "from Mensagem mensagem where mensagem.objeto.id = :idObjeto order by mensagem.id", Mensagem.class)
                .setParameter("idObjeto", idObjeto)
                .getResultList();
    }

    public void alterarEstado(long idObjeto, String estado) {
        Objeto objeto = obterObjeto(idObjeto);
        if (objeto == null) {
            return;
        }
        if (estado == null) {
            System.out.println("Erro: estado inválido (usar PERDIDO, ACHADO ou ENTREGUE)");
            return;
        }
        String novoEstado = estado.trim().toUpperCase();
        if (!novoEstado.equals("PERDIDO") && !novoEstado.equals("ACHADO") && !novoEstado.equals("ENTREGUE")) {
            System.out.println("Erro: estado inválido (usar PERDIDO, ACHADO ou ENTREGUE)");
            return;
        }

        objeto.mudarEstado(novoEstado);
        if (atualizar()) {
            System.out.println("Estado alterado.");
        }
    }

    public void responderCliente(long idMensagem, long idFuncionario, String resposta) {
        if (resposta == null || resposta.isBlank()) {
            System.out.println("Erro: a resposta não pode estar vazia");
            return;
        }
        Mensagem mensagem = obterMensagem(idMensagem);
        Funcionario funcionario = obterFuncionario(idFuncionario);
        if (mensagem == null || funcionario == null) {
            return;
        }

        mensagem.responder(funcionario, resposta);
        if (atualizar()) {
            System.out.println("Resposta guardada.");
        }
    }

    public void reclamarObjeto(long idCliente, long idObjeto) {
        Cliente cliente = obterCliente(idCliente);
        Objeto objeto = obterObjeto(idObjeto);
        if (cliente == null || objeto == null) {
            return;
        }

        Long passagensNoEspaco = session.createQuery(
                "select count(registo) from Registo registo "
                        + "where registo.cliente.idC = :idCliente and registo.espaco.id = :idEspaco", Long.class)
                .setParameter("idCliente", idCliente)
                .setParameter("idEspaco", objeto.getEspaco().getId())
                .getSingleResult();

        if (passagensNoEspaco == 0) {
            System.out.println("Erro: o cliente " + cliente.getNome() + " não passou o cartão no espaço "
                    + objeto.getEspaco().getSala() + ", por isso não pode reclamar este objeto");
            return;
        }
        if (cliente.getReclamados().contains(objeto)) {
            System.out.println("Erro: o cliente já reclamou este objeto");
            return;
        }

        cliente.getReclamados().add(objeto);
        if (atualizar()) {
            System.out.println("Reclamação registada.");
        }
    }

    public void registarLevantamento(long idCliente, long idObjeto) {
        Cliente cliente = obterCliente(idCliente);
        Objeto objeto = obterObjeto(idObjeto);
        if (cliente == null || objeto == null) {
            return;
        }

        if (!cliente.getReclamados().contains(objeto)) {
            System.out.println("Erro: o cliente tem de reclamar o objeto antes de o levantar");
            return;
        }
        if (objeto.getEstado().equals("ENTREGUE")) {
            System.out.println("Erro: o objeto já foi entregue");
            return;
        }

        objeto.mudarEstado("ENTREGUE");
        if (gravar(new Levantamento(cliente, objeto))) {
            System.out.println("Levantamento registado.");
        }
    }

    public void atualizarEmail(long idCliente, String novoEmail) {
        if (novoEmail == null || !novoEmail.contains("@")) {
            System.out.println("Erro: email inválido");
            return;
        }
        Cliente cliente = obterCliente(idCliente);
        if (cliente == null) {
            return;
        }

        cliente.setEmail(novoEmail);
        if (atualizar()) {
            System.out.println("Email atualizado.");
        }
    }

    public void eliminarObjeto(long idObjeto) {
        Objeto objeto = obterObjeto(idObjeto);
        if (objeto == null) {
            return;
        }

        Long totalLevantamentos = session.createQuery(
                "select count(levantamento) from Levantamento levantamento where levantamento.obj.id = :idObjeto", Long.class)
                .setParameter("idObjeto", idObjeto)
                .getSingleResult();
        Long totalMensagens = session.createQuery(
                "select count(mensagem) from Mensagem mensagem where mensagem.objeto.id = :idObjeto", Long.class)
                .setParameter("idObjeto", idObjeto)
                .getSingleResult();
        Long totalReclamacoes = session.createQuery(
                "select count(cliente) from Cliente cliente join cliente.reclamados reclamado "
                        + "where reclamado.id = :idObjeto", Long.class)
                .setParameter("idObjeto", idObjeto)
                .getSingleResult();

        if (totalLevantamentos > 0 || totalMensagens > 0 || totalReclamacoes > 0) {
            System.out.println("Erro: não é possível eliminar, o objeto tem levantamentos, mensagens ou reclamações");
            return;
        }

        if (apagar(objeto)) {
            System.out.println("Objeto eliminado.");
        }
    }

    public List<Cliente> listarClientes() {
        return session.createQuery("from Cliente", Cliente.class).getResultList();
    }

    public List<Funcionario> listarFuncionarios() {
        return session.createQuery("from Funcionario", Funcionario.class).getResultList();
    }

    public List<Espaco> listarEspacos() {
        return session.createQuery("from Espaco", Espaco.class).getResultList();
    }

    public List<Objeto> listarObjetos() {
        return session.createQuery("from Objeto", Objeto.class).getResultList();
    }

    public List<Mensagem> listarMensagens() {
        return session.createQuery("from Mensagem", Mensagem.class).getResultList();
    }

    public void historicoDoObjeto(long idObjeto) {
        Objeto objeto = obterObjeto(idObjeto);
        if (objeto == null) {
            return;
        }
        for (HistoricoEstado historico : objeto.getHistorico()) {
            System.out.println(historico.getEstado() + " em " + historico.getData());
        }
    }

    public void listarRegistosDoCliente(long idCliente) {
        Cliente cliente = obterCliente(idCliente);
        if (cliente == null) {
            return;
        }
        for (Registo registo : cliente.getRegistos()) {
            System.out.println(registo.getTipo() + " no espaço " + registo.getEspaco().getSala()
                    + " em " + registo.getDataHora());
        }
    }

    public void fechar() {
        session.close();
        sessionFactory.close();
    }
}
