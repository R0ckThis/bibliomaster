/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.Movimentacao;
import entidade.Movimentacao_has_livro;
import entidade.Pessoa;
import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ramon
 */
public class MovimentacaoDAO implements IDAO {

    @Override
    public String salvar(Object o, char op) {
        Movimentacao movimentacao = (Movimentacao) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String data_reserva = null;

            if (movimentacao.getData_reserva() != null) {
                data_reserva = "'" + movimentacao.getData_reserva() + "'";
            }

            String data_retirada = null;
            if (movimentacao.getData_retirada() != null) {
                data_retirada = "'" + movimentacao.getData_retirada() + "'";
            }

            String data_devolver = null;
            if (movimentacao.getData_devolver() != null) {
                data_devolver = "'" + movimentacao.getData_devolver() + "'";
            }
            String sql = "INSERT INTO movimentacao VALUES"
                    + "(DEFAULT, "
                    + movimentacao.getPessoa().getId() + ", '"
                    + movimentacao.getStatus() + "', "
                    + data_reserva + ", "
                    + data_retirada + ", "
                    + data_devolver + ", '"
                    + movimentacao.getObservacao() + "', "
                    + movimentacao.getValor() + ") RETURNING id";

            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            Movimentacao_has_livroDAO mhlDAO = new Movimentacao_has_livroDAO();
            Copia_livroDAO clDAO = new Copia_livroDAO();
            if (resultado.next()) {
                for (Movimentacao_has_livro mov : movimentacao.getLivros()) {
                    mov.setMovimentacao(resultado.getInt("id"));
                    mhlDAO.salvar(mov, 'i');
                    clDAO.atualizar(mov.getCopia_livro());
                }
            }
            return resultado.getString("id");
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String atualizar(Object o) {
        Movimentacao movimentacao = (Movimentacao) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String data_reserva = null;

            if (movimentacao.getData_reserva() != null) {
                data_reserva = "'" + movimentacao.getData_reserva() + "'";
            }

            String data_retirada = null;
            if (movimentacao.getData_retirada() != null) {
                data_retirada = "'" + movimentacao.getData_retirada() + "'";
            }

            String data_devolver = null;
            if (movimentacao.getData_devolver() != null) {
                data_devolver = "'" + movimentacao.getData_devolver() + "'";
            }
            String sql = "UPDATE movimentacao SET "
                    + "pessoa = " + movimentacao.getPessoa().getId() + ", status = '"
                    + movimentacao.getStatus() + "', data_reserva = "
                    + data_reserva + ", data_retirada = "
                    + data_retirada + ", data_devolver = "
                    + data_devolver + ", observacao = '"
                    + movimentacao.getObservacao() + "', valor = "
                    + movimentacao.getValor() + " WHERE id = " + movimentacao.getId();

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            Movimentacao_has_livroDAO mhlDAO = new Movimentacao_has_livroDAO();
            Copia_livroDAO clDAO = new Copia_livroDAO();

            for (Movimentacao_has_livro mov : movimentacao.getLivros()) {
                mhlDAO.atualizar(mov);
                clDAO.atualizar(mov.getCopia_livro());
            }

            return null;
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean registroUnico(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        ArrayList<Object> movimentacoes = new ArrayList<>();

        try {
            String sql = "select * from movimentacao " + criterio + " ORDER BY id";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            PessoaDAO pDAO = new PessoaDAO();
            Movimentacao_has_livroDAO mhlDAO = new Movimentacao_has_livroDAO();

            while (resultado.next()) {
                Movimentacao mov = new Movimentacao();

                mov.setId(resultado.getInt("id"));
                mov.setPessoa((Pessoa) pDAO.consultarId(resultado.getInt("pessoa")));
                mov.setStatus(resultado.getString("status"));
                mov.setData_reserva(resultado.getDate("data_reserva"));
                mov.setData_retirada(resultado.getDate("data_retirada"));
                mov.setData_devolver(resultado.getDate("data_devolver"));
                mov.setObservacao(resultado.getString("observacao"));
                mov.setValor(resultado.getDouble("valor"));
                ArrayList<Object> mhl = mhlDAO.consultar("WHERE movimentacao = " + mov.getId());
                ArrayList<Movimentacao_has_livro> mhlCerto = new ArrayList<>();
                for (int i = 0; i < mhl.size(); i++) {
                    mhlCerto.add((Movimentacao_has_livro) mhl.get(i));
                }
                mov.setLivros(mhlCerto);

                movimentacoes.add(mov);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar movimentacoes: " + e);
        }

        return movimentacoes;
    }

    @Override
    public Object consultarId(int id) {
        Movimentacao mov = new Movimentacao();
        try {
            String sql = "SELECT * FROM movimentacao WHERE id = " + id;
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            PessoaDAO pDAO = new PessoaDAO();
            Movimentacao_has_livroDAO mhlDAO = new Movimentacao_has_livroDAO();

            if (resultado.next()) {
                mov.setId(resultado.getInt("id"));
                mov.setPessoa((Pessoa) pDAO.consultarId(resultado.getInt("pessoa")));
                mov.setStatus(resultado.getString("status"));
                mov.setData_reserva(resultado.getDate("data_reserva"));
                mov.setData_retirada(resultado.getDate("data_retirada"));
                mov.setData_devolver(resultado.getDate("data_devolver"));
                mov.setObservacao(resultado.getString("observacao"));
                mov.setValor(resultado.getDouble("valor"));
                ArrayList<Object> mhl = mhlDAO.consultar("WHERE movimentacao = " + mov.getId());
                ArrayList<Movimentacao_has_livro> mhlCerto = new ArrayList<>();
                for (int i = 0; i < mhl.size(); i++) {
                    mhlCerto.add((Movimentacao_has_livro) mhl.get(i));
                }
                mov.setLivros(mhlCerto);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar movimentacoes: " + e);
        }

        return mov;
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean temPendencias(int pessoa) {
        boolean temPendencias = false;
        try {
            String sql = "SELECT * FROM movimentacao WHERE (status = 'Retirado' OR status = 'Reserva') AND pessoa = " + pessoa;
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            if (resultado.next()) {
                temPendencias = true;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar movimentacoes: " + e);
        }

        return temPendencias;
    }

    public String verificarReservas() {
        String erro = "";
        try {
            String sql = "SELECT * FROM movimentacao WHERE status = 'Reserva' AND data_reserva < CURRENT_DATE";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            PessoaDAO pDAO = new PessoaDAO();
            Movimentacao_has_livroDAO mhlDAO = new Movimentacao_has_livroDAO();

            while (resultado.next()) {
                Movimentacao mov = new Movimentacao();

                mov.setId(resultado.getInt("id"));
                mov.setPessoa((Pessoa) pDAO.consultarId(resultado.getInt("pessoa")));
                mov.setStatus("Cancelado");
                mov.setData_reserva(resultado.getDate("data_reserva"));
                mov.setData_retirada(resultado.getDate("data_retirada"));
                mov.setData_devolver(resultado.getDate("data_devolver"));
                mov.setObservacao(resultado.getString("observacao"));
                mov.setValor(resultado.getDouble("valor"));
                ArrayList<Object> mhl = mhlDAO.consultar("WHERE movimentacao = " + mov.getId());
                ArrayList<Movimentacao_has_livro> mhlCerto = new ArrayList<>();
                for (int i = 0; i < mhl.size(); i++) {
                    Movimentacao_has_livro mhlAdd = (Movimentacao_has_livro) mhl.get(i);
                    mhlAdd.setStatus("Cancelado");
                    mhlAdd.getCopia_livro().setEstado("Disponível");
                    mhlCerto.add(mhlAdd);
                }
                mov.setLivros(mhlCerto);

                this.atualizar(mov);
            }

        } catch (Exception e) {
            erro = "Erro ao consultar movimentacoes: " + e;
        }
        return erro;
    }

}
