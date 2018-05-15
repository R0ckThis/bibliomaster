/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.Idioma;
import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ramon
 */
public class IdiomaDAO implements IDAO {

    @Override
    public String salvar(Object o, char op) {
        if (!consultar(o)) {
            Idioma idioma = (Idioma) o;
            try {
                Statement st = ConexaoBD.getInstance().getConnection().createStatement();
                String sql = "INSERT INTO idioma VALUES"
                        + "(DEFAULT, "
                        + "'" + idioma.getNome() + "', '" + idioma.getSigla() + "')";

                System.out.println("sql: " + sql);

                int resultado = st.executeUpdate(sql);

                return null;
            } catch (Exception e) {
                String erro = e.toString();
                System.out.println("Erro salvar idioma = " + e);
                if (erro.contains("duplicate key")) {
                    erro = "Idioma já cadastrado.";
                }
                return erro;
            }
        } else {
            return "Idioma já cadastrado";
        }
    }

    @Override
    public String atualizar(Object o) {
        if (!consultar(o)) {
            Idioma idioma = (Idioma) o;
            try {
                String sql = "update idioma "
                        + "set nome = '" + idioma.getNome() + "', "
                        + "sigla = '" + idioma.getSigla() + "' "
                        + "where id = " + idioma.getId();

                int retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

            } catch (Exception e) {
                String erro = e.toString();
                System.out.println("erro atualizar idioma: " + e);
                if (erro.contains("duplicate key")) {
                    erro = "Idioma já cadastrado.";
                }
                return erro;
            }
            return null;
        } else {
            return "Idioma já cadastrado";
        }
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM idioma WHERE id = " + id;

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            if (e.toString().contains("violates foreign key constraint")) {
                return "Idioma com dados utilizados em outro(s) cadastro(s).";
            } else {
                return e.toString();
            }
        }
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> idiomas = new ArrayList<>();

        try {
            String sql = "select * from idioma ORDER BY id";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Idioma i = new Idioma();

                i.setId(resultado.getInt("id"));
                i.setNome(resultado.getString("nome"));
                i.setSigla(resultado.getString("sigla"));

                idiomas.add(i);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar idiomas: " + e);
        }

        return idiomas;
    }

    @Override
    public boolean registroUnico(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        ArrayList<Object> idiomas = new ArrayList<>();

        try {
            String sql = "select * from idioma WHERE nome ILIKE '%" + criterio + "%' ORDER BY id";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Idioma i = new Idioma();

                i.setId(resultado.getInt("id"));
                i.setNome(resultado.getString("nome"));
                i.setSigla(resultado.getString("sigla"));

                idiomas.add(i);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar generos: " + e);
        }

        return idiomas;
    }

    @Override
    public Object consultarId(int id) {
        Idioma idioma = null;

        try {
            String sql = "select * from idioma "
                    + "where id = " + id;

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            if (resultado.next()) {
                idioma = new Idioma();
                idioma.setId(resultado.getInt("id"));
                idioma.setNome(resultado.getString("nome"));
                idioma.setSigla(resultado.getString("sigla"));
            }

        } catch (Exception e) {
            System.out.println("erro ao consultar idioma: " + e);
            return e;
        }

        return idioma;
    }

    @Override
    public boolean consultar(Object o) {
        Idioma idioma = (Idioma) o;

        try {
            String sql = "select * from idioma "
                    + "where id <> " + idioma.getId() + " and (nome ilike '" + idioma.getNome() + "' or sigla ilike '" + idioma.getSigla() + "')";

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            return resultado.next();

        } catch (Exception e) {
            System.out.println("erro ao consultar idioma: " + e);
            return false;
        }
    }
}
