/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.Telefone;
import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ramon
 */
public class TelefoneDAO implements IDAO {

    @Override
    public String salvar(Object o, char op) {
        Telefone telefone = (Telefone) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "INSERT INTO telefone VALUES"
                    + "(DEFAULT, "
                    + telefone.getPessoa() + ", '" + telefone.getNumero() + "', '" + telefone.getDdd() + "')";

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro salvar telefone = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM telefone WHERE pessoa = " + id;

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            return e.toString();
        }
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

    @Override
    public Object consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Telefone> consultarIdPessoa(int id) {
        ArrayList<Telefone> telefones = new ArrayList<>();

        try {
            String sql = "select * from telefone where pessoa = " + id + " ORDER BY id";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Telefone t = new Telefone();

                t.setId(resultado.getInt("id"));
                t.setPessoa(resultado.getInt("pessoa"));
                t.setNumero(resultado.getString("numero"));
                t.setDdd(resultado.getString("ddd"));

                telefones.add(t);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar telefones: " + e);
        }

        return telefones;
    }

}
