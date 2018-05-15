/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.Editora;
import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ramon
 */
public class EditoraDAO implements IDAO {

    @Override
    public String salvar(Object o, char op) {
        if (!consultar(o)) {
            Editora editora = (Editora) o;
            try {
                Statement st = ConexaoBD.getInstance().getConnection().createStatement();
                String sql = "INSERT INTO editora VALUES"
                        + "(DEFAULT, "
                        + "'" + editora.getNome() + "')";

                System.out.println("sql: " + sql);

                int resultado = st.executeUpdate(sql);

                return null;
            } catch (Exception e) {
                String erro = e.toString();
                System.out.println("Erro salvar editora = " + e);
                if (erro.contains("duplicate key")) {
                    erro = "Editora já cadastrada.";
                }
                return erro;
            }
        } else {
            return "Editora já cadastrada.";
        }
    }

    @Override
    public String atualizar(Object o) {
        if (!consultar(o)) {
            Editora editora = (Editora) o;
            try {
                String sql = "update editora "
                        + "set nome = '" + editora.getNome() + "' "
                        + "where id = " + editora.getId();

                int retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

            } catch (Exception e) {
                String erro = e.toString();
                System.out.println("Erro ao atualizar editora = " + e);
                if (erro.contains("duplicate key")) {
                    erro = "Editora já cadastrada.";
                }
                return erro;
            }
            return null;
        } else {
            return "Editora já cadastrada.";
        }
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM editora WHERE id = " + id;

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            if (e.toString().contains("violates foreign key constraint")) {
                return "Editora com dados utilizados em outro(s) cadastro(s).";
            } else {
                return e.toString();
            }
        }
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> editoras = new ArrayList<>();

        try {
            String sql = "select * from editora ORDER BY id";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Editora e = new Editora();

                e.setId(resultado.getInt("id"));
                e.setNome(resultado.getString("nome"));

                editoras.add(e);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar editoras: " + e);
        }

        return editoras;
    }

    @Override
    public boolean registroUnico(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        ArrayList<Object> editoras = new ArrayList<>();

        try {
            String sql = "select * from editora WHERE nome ILIKE '%" + criterio + "%' ORDER BY id";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Editora e = new Editora();

                e.setId(resultado.getInt("id"));
                e.setNome(resultado.getString("nome"));

                editoras.add(e);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar editoras: " + e);
        }

        return editoras;
    }

    @Override
    public Object consultarId(int id) {
        Editora editora = null;

        try {
            String sql = "select * from editora "
                    + "where id = " + id;

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            if (resultado.next()) {
                editora = new Editora();
                editora.setId(resultado.getInt("id"));
                editora.setNome(resultado.getString("nome"));
            }

        } catch (Exception e) {
            System.out.println("erro ao consultar editora: " + e);
            return e;
        }

        return editora;
    }

    @Override
    public boolean consultar(Object o) {
        Editora editora = (Editora) o;

        try {
            String sql = "select * from editora "
                    + "where id <> " + editora.getId() + " and nome ilike '" + editora.getNome() + "'";

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            return resultado.next();

        } catch (Exception e) {
            System.out.println("erro ao consultar editora: " + e);
            return false;
        }
    }

}
