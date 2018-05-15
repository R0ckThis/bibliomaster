/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.Copia_livro;
import entidade.Livro;
import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ramon
 */
public class Copia_livroDAO implements IDAO {

    @Override
    public String salvar(Object o, char op) {
        Copia_livro cl = (Copia_livro) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "INSERT INTO copia_livro VALUES"
                    + "(" + cl.getLivro().getId() + ", DEFAULT, '"
                    + "" + cl.getEstado() + "', '" + cl.getObservacao() + "')";

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            String erro = e.toString();
            System.out.println("Erro salvar copiaLivro = " + e);
            return erro;
        }
    }

    @Override
    public String atualizar(Object o) {
        Copia_livro cl = (Copia_livro) o;
        try {
            String sql = "update copia_livro "
                    + "set estado = '" + cl.getEstado() + "', "
                    + "observacao = '" + cl.getObservacao() + "'"
                    + "where livro = " + cl.getLivro().getId() + " and identificador = " + cl.getIdentificador();

            int retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            String erro = e.toString();
            System.out.println("Erro ao atualizar copia_livro = " + e);
            return erro;
        }

        return null;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Object> consultarLivro(String id) {
        if (id.isEmpty()) {
            id = "0";
        }
        ArrayList<Object> cls = new ArrayList<>();

        try {
            String sql = "select * from copia_livro "
                    + "where livro = " + id + " ORDER BY identificador";

            LivroDAO lDAO = new LivroDAO();

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Copia_livro cl = new Copia_livro();
                cl.setLivro((Livro) lDAO.consultarId(resultado.getInt("livro")));
                cl.setIdentificador(resultado.getInt("identificador"));
                cl.setEstado(resultado.getString("estado"));
                cl.setObservacao(resultado.getString("observacao"));
                cls.add(cl);
            }

        } catch (Exception e) {
            System.out.println("erro ao consultar copia_livro: " + e);
        }

        return cls;
    }

    @Override
    public Object consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object consultarUnico(int livro, int identificador) {
        Copia_livro cl = null;

        try {
            String sql = "select * from copia_livro "
                    + "where livro = " + livro + " and identificador = " + identificador;

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            
            LivroDAO lDAO = new LivroDAO();
            
            if (resultado.next()) {
                cl = new Copia_livro();
                cl.setLivro((Livro) lDAO.consultarId(resultado.getInt("livro")));
                cl.setIdentificador(resultado.getInt("identificador"));
                cl.setEstado(resultado.getString("estado"));
                cl.setObservacao(resultado.getString("observacao"));
            }

        } catch (Exception e) {
            System.out.println("erro ao consultar copia livro: " + e);
            return e;
        }

        return cl;
    }
    
    public Copia_livro pegarCopiaDisponivel(Livro livro) {
        Copia_livro cl = new Copia_livro();

        try {
            String sql = "select * from copia_livro "
                    + "where livro = " + livro.getId() + " and estado = 'Disponível' ORDER BY identificador";

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            if (resultado.next()) {
                cl.setLivro(livro);
                cl.setIdentificador(resultado.getInt("identificador"));
                cl.setEstado(resultado.getString("estado"));
                cl.setObservacao(resultado.getString("observacao"));
            }

        } catch (Exception e) {
            System.out.println("erro ao consultar copia_livro: " + e);
        }

        return cl;
    }

}
