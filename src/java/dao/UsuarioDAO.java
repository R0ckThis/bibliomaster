/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.Pessoa;
import entidade.Usuario;
import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ramon
 */
public class UsuarioDAO implements IDAO {

    @Override
    public String salvar(Object o, char op) {
        Usuario usuario = (Usuario) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO usuario VALUES (" + usuario.getPessoa().getId() + ", '" + usuario.getSenha() + "', '"
                    + usuario.getTipo() + "', " + usuario.isAtivo() + ")";

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro salvar usuario = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Object o) {
        Usuario usuario = (Usuario) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE usuario SET senha = '" + usuario.getSenha() + "', tipo = '" + usuario.getTipo() + "', "
                    + "ativo = " + usuario.isAtivo()
                    + " WHERE pessoa = " + usuario.getPessoa().getId();

            System.out.println("sql: " + sql);

            int retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("erro atualizar usuario: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> usuarios = new ArrayList<>();

        try {
            String sql = "select * from usuario ORDER BY pessoa";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            PessoaDAO pDAO = new PessoaDAO();
            while (resultado.next()) {
                Usuario usuario = new Usuario();
                usuario.setPessoa((Pessoa) pDAO.consultarId(resultado.getInt("pessoa")));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setTipo(resultado.getString("tipo"));
                usuario.setAtivo(resultado.getBoolean("ativo"));

                usuarios.add(usuario);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar usuarios: " + e);
        }

        return usuarios;
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
        Usuario usuario = null;

        try {
            String sql = "select * from usuario "
                    + "where pessoa = " + id;

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            if (resultado.next()) {
                usuario = new Usuario();
                usuario.setPessoa((Pessoa) new PessoaDAO().consultarId(resultado.getInt("pessoa")));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setTipo(resultado.getString("tipo"));
                usuario.setAtivo(resultado.getBoolean("ativo"));
            }

        } catch (Exception e) {
            System.out.println("erro ao consultar usuario: " + e);
            return e;
        }

        return usuario;
    }

    @Override
    public boolean consultar(Object o) {
        Usuario u = (Usuario) o;

        try {
            String sql = "select p.cpf, u.senha "
                    + "from usuario u, pessoa p "
                    + "where u.pessoa = p.id and p.cpf = '" + u.getPessoa().getCpf() + "' and "
                    + "u.senha = '" + u.getSenha() + "' and u.ativo = true";

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            if (resultado.next()) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("erro ao validar login: " + e);
        }
        return false;
    }

    public boolean pessoaCadastrada(Usuario u) {
        try {
            String sql = "select p.cpf, u.senha "
                    + "from usuario u, pessoa p "
                    + "where u.pessoa = p.id and p.cpf = '" + u.getPessoa().getCpf() + "'";

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            if (resultado.next()) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("erro ao verificar pessoa: " + e);
        }
        return false;
    }
}
