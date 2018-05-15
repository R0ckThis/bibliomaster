/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.Autor;
import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ramon
 */
public class AutorDAO implements IDAO {

    @Override
    public String salvar(Object o, char op) {
        if (!consultar(o)) {
            Autor autor = (Autor) o;
            try {
                Statement st = ConexaoBD.getInstance().getConnection().createStatement();
                String sql = "INSERT INTO autor VALUES"
                        + "(DEFAULT, "
                        + "'" + autor.getNome() + "', '" + autor.getSobrenome() + "')";

                System.out.println("sql: " + sql);

                int resultado = st.executeUpdate(sql);

                return null;
            } catch (Exception e) {
                String erro = e.toString();
                System.out.println("Erro salvar autor = " + e);
                if (erro.contains("duplicate key")) {
                    erro = "Autor já cadastrado.";
                }
                return erro;
            }
        } else {
            return "Autor já cadastrado";
        }
    }

    @Override
    public String atualizar(Object o) {
        if (!consultar(o)) {
            Autor autor = (Autor) o;
            try {
                String sql = "update autor "
                        + "set nome = '" + autor.getNome() + "',"
                        + "sobrenome = '" + autor.getSobrenome() + "'"
                        + "where id = " + autor.getId();

                int retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);
                return null;
            } catch (Exception e) {
                String erro = e.toString();
                System.out.println("erro atualizar autor: " + e);
                if (erro.contains("duplicate key")) {
                    erro = "Autor já cadastrado.";
                }
                return erro;
            }
        } else {
            return "Autor já cadastrado";
        }
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM autor WHERE id = " + id;

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            if (e.toString().contains("violates foreign key constraint")) {
                return "Autor com dados utilizados em outro(s) cadastro(s).";
            } else {
                return e.toString();
            }
        }
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> autores = new ArrayList<>();

        try {
            String sql = "select * from autor ORDER BY id";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Autor a = new Autor();

                a.setId(resultado.getInt("id"));
                a.setNome(resultado.getString("nome"));
                a.setSobrenome(resultado.getString("sobrenome"));

                autores.add(a);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar autores: " + e);
        }

        return autores;
    }

    @Override
    public boolean registroUnico(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        ArrayList<Object> autores = new ArrayList<>();

        try {
            String sql = "select * from autor WHERE nome || ' ' || sobrenome ILIKE '%" + criterio + "%'  ORDER BY id";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Autor a = new Autor();

                a.setId(resultado.getInt("id"));
                a.setNome(resultado.getString("nome"));
                a.setSobrenome(resultado.getString("sobrenome"));

                autores.add(a);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar autores: " + e);
        }

        return autores;
    }

    @Override
    public Object consultarId(int id) {
        Autor autor = null;

        try {
            String sql = "select * from autor "
                    + "where id = " + id;

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            if (resultado.next()) {
                autor = new Autor();
                autor.setId(resultado.getInt("id"));
                autor.setNome(resultado.getString("nome"));
                autor.setSobrenome(resultado.getString("sobrenome"));
            }

        } catch (Exception e) {
            System.out.println("erro ao consultar autor: " + e);
            return e;
        }

        return autor;
    }

    @Override
    public boolean consultar(Object o) {
        Autor autor = (Autor) o;

        try {
            String sql = "select * from autor "
                    + "where id <> " + autor.getId() + " and nome ilike '" + autor.getNome() + "' and sobrenome ilike '" + autor.getSobrenome() + "'";

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            return resultado.next();

        } catch (Exception e) {
            System.out.println("erro ao consultar autor: " + e);
            return false;
        }
    }

}
