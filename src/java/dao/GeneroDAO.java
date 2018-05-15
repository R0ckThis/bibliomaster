/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.Genero;
import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ramon
 */
public class GeneroDAO implements IDAO {

    @Override
    public String salvar(Object o, char op) {
        if (!consultar(o)) {
            Genero genero = (Genero) o;
            try {
                Statement st = ConexaoBD.getInstance().getConnection().createStatement();
                String sql = "INSERT INTO genero VALUES"
                        + "(DEFAULT, "
                        + "'" + genero.getNome() + "')";

                System.out.println("sql: " + sql);

                int resultado = st.executeUpdate(sql);

                return null;
            } catch (Exception e) {
                String erro = e.toString();
                System.out.println("Erro salvar genero = " + e);
                if (erro.contains("duplicate key")) {
                    erro = "Gênero já cadastrado.";
                }
                return erro;
            }
        } else {
            return "Gênero já cadastrado.";
        }
    }

    @Override
    public String atualizar(Object o) {
        if (!consultar(o)) {
            Genero genero = (Genero) o;
            try {
                String sql = "update genero "
                        + "set nome = '" + genero.getNome() + "' "
                        + "where id = " + genero.getId();

                int retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

            } catch (Exception e) {
                String erro = e.toString();
                System.out.println("Erro ao atualizar gênero = " + e);
                if (erro.contains("duplicate key")) {
                    erro = "Gênero já cadastrado.";
                }
                return erro;
            }
            return null;
        } else {
            return "Gênero já cadastrado.";
        }
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM genero WHERE id = " + id;

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            if (e.toString().contains("violates foreign key constraint")) {
                return "Gênero com dados utilizados em outro(s) cadastro(s).";
            } else {
                return e.toString();
            }
        }
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> generos = new ArrayList<>();

        try {
            String sql = "select * from genero ORDER BY id";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Genero g = new Genero();

                g.setId(resultado.getInt("id"));
                g.setNome(resultado.getString("nome"));

                generos.add(g);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar generos: " + e);
        }

        return generos;
    }

    @Override
    public boolean registroUnico(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        ArrayList<Object> generos = new ArrayList<>();

        try {
            String sql = "select * from genero WHERE nome ILIKE '%" + criterio + "%' ORDER BY id";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Genero g = new Genero();

                g.setId(resultado.getInt("id"));
                g.setNome(resultado.getString("nome"));

                generos.add(g);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar generos: " + e);
        }

        return generos;
    }

    @Override
    public Object consultarId(int id) {
        Genero genero = null;

        try {
            String sql = "select * from genero "
                    + "where id = " + id;

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            if (resultado.next()) {
                genero = new Genero();
                genero.setId(resultado.getInt("id"));
                genero.setNome(resultado.getString("nome"));
            }

        } catch (Exception e) {
            System.out.println("erro ao consultar genero: " + e);
            return e;
        }

        return genero;
    }

    @Override
    public boolean consultar(Object o) {
        Genero genero = (Genero) o;

        try {
            String sql = "select * from genero "
                    + "where id <> " + genero.getId() + " and nome ilike '" + genero.getNome() + "'";

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            return resultado.next();

        } catch (Exception e) {
            System.out.println("erro ao consultar genero: " + e);
            return false;
        }
    }

}
