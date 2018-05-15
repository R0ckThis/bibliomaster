/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.Copia_livro;
import entidade.Movimentacao_has_livro;
import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ramon
 */
public class Movimentacao_has_livroDAO implements IDAO {

    @Override
    public String salvar(Object o, char op) {
        Movimentacao_has_livro mov = (Movimentacao_has_livro) o;
        try {
            String data_devolucao = null;

            if (mov.getData_devolucao() != null) {
                data_devolucao = "'" + mov.getData_devolucao() + "'";
            }
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "INSERT INTO movimentacao_has_livro VALUES"
                    + "(" + mov.getMovimentacao() + ", "
                    + mov.getCopia_livro().getLivro().getId() + ", "
                    + mov.getCopia_livro().getIdentificador() + ", '"
                    + mov.getStatus() + "', "
                    + data_devolucao + ", '"
                    + mov.getObservacao() + "', "
                    + mov.getMulta() + ")";

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String atualizar(Object o) {
        Movimentacao_has_livro mov = (Movimentacao_has_livro) o;
        try {
            String data_devolucao = null;

            if (mov.getData_devolucao() != null) {
                data_devolucao = "'" + mov.getData_devolucao() + "'";
            }
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE movimentacao_has_livro SET "
                    + "status = '" + mov.getStatus() + "', data_devolucao = "
                    + data_devolucao + ", observacao = '"
                    + mov.getObservacao() + "', multa = "
                    + mov.getMulta() + " WHERE movimentacao = " + mov.getMovimentacao() + " AND livro = "
                    + mov.getCopia_livro().getLivro().getId() + " AND identificador = "
                    + mov.getCopia_livro().getIdentificador();

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

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
        ArrayList<Object> mhls = new ArrayList<>();

        try {
            String sql = "select * from movimentacao_has_livro " + criterio + " ORDER BY movimentacao";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            Copia_livroDAO clDAO = new Copia_livroDAO();

            while (resultado.next()) {
                Movimentacao_has_livro mhl = new Movimentacao_has_livro();

                mhl.setMovimentacao(resultado.getInt("movimentacao"));
                mhl.setCopia_livro((Copia_livro) clDAO.consultarUnico(resultado.getInt("livro"), resultado.getInt("identificador")));
                mhl.setStatus(resultado.getString("status"));
                mhl.setData_devolucao(resultado.getDate("data_devolucao"));
                mhl.setObservacao(resultado.getString("observacao"));
                mhl.setMulta(resultado.getDouble("multa"));

                mhls.add(mhl);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar movimentacao_has_livro: " + e);
        }

        return mhls;
    }

    @Override
    public Object consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
