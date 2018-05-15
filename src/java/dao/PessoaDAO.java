/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.Pessoa;
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
public class PessoaDAO implements IDAO {

    @Override
    public String salvar(Object o, char op) {
        Pessoa pessoa = (Pessoa) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO pessoa VALUES (DEFAULT, '" + pessoa.getCpf() + "', '" + pessoa.getNome() + "', '"
                    + pessoa.getSobrenome() + "', '" + pessoa.getRg() + "', '" + pessoa.getEmail() + "') RETURNING id";

            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            if (resultado.next()) {
                for (Telefone telefone : pessoa.getTelefone()) {
                    telefone.setPessoa(resultado.getInt("id"));
                    new TelefoneDAO().salvar(telefone, 'i');
                }
            }

            return null;
        } catch (Exception e) {
            System.out.println("Erro salvar pessoa = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Object o) {
        Pessoa pessoa = (Pessoa) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE pessoa SET cpf = '" + pessoa.getCpf() + "', nome = '" + pessoa.getNome() + "', "
                    + "sobrenome = '" + pessoa.getSobrenome() + "', rg = '" + pessoa.getRg() + "', "
                    + "email = '" + pessoa.getEmail() + "' "
                    + "WHERE id = " + pessoa.getId();

            System.out.println("sql: " + sql);

            int retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

            TelefoneDAO tDAO = new TelefoneDAO();

            tDAO.excluir(pessoa.getId());

            for (Telefone telefone : pessoa.getTelefone()) {
                tDAO.salvar(telefone, 'i');
            }

        } catch (Exception e) {
            System.out.println("erro atualizar pessoa: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM pessoa WHERE id = " + id;

            System.out.println("sql: " + sql);
            new TelefoneDAO().excluir(id);
            int resultado = st.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            if (e.toString().contains("violates foreign key constraint")) {
                return "Pessoa com dados utilizados em outro(s) cadastro(s).";
            } else {
                return e.toString();
            }
        }
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> pessoas = new ArrayList<>();

        try {
            String sql = "select * from pessoa ORDER BY id";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            TelefoneDAO tDAO = new TelefoneDAO();
            while (resultado.next()) {
                Pessoa p = new Pessoa();

                p.setId(resultado.getInt("id"));
                p.setNome(resultado.getString("nome"));
                p.setSobrenome(resultado.getString("sobrenome"));
                p.setCpf(resultado.getString("cpf"));
                p.setRg(resultado.getString("rg"));
                p.setEmail(resultado.getString("email"));
                p.setTelefone(tDAO.consultarIdPessoa(resultado.getInt("id")));

                pessoas.add(p);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar pessoas: " + e);
        }

        return pessoas;
    }

    @Override
    public boolean registroUnico(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        ArrayList<Object> pessoas = new ArrayList<>();

        try {
            String sql = "select * from pessoa WHERE nome || ' ' || sobrenome ILIKE '%" + criterio + "%'  ORDER BY id";
            
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            TelefoneDAO tDAO = new TelefoneDAO();
            
            while (resultado.next()) {
                Pessoa p = new Pessoa();

                p.setId(resultado.getInt("id"));
                p.setNome(resultado.getString("nome"));
                p.setSobrenome(resultado.getString("sobrenome"));
                p.setCpf(resultado.getString("cpf"));
                p.setRg(resultado.getString("rg"));
                p.setEmail(resultado.getString("email"));
                p.setTelefone(tDAO.consultarIdPessoa(resultado.getInt("id")));

                pessoas.add(p);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar pessoas: " + e);
        }

        return pessoas;
    }

    @Override
    public Object consultarId(int id) {
        Pessoa pessoa = new Pessoa();

        try {
            String sql = "select * from pessoa "
                    + "where id = " + id;

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            TelefoneDAO tDAO = new TelefoneDAO();
            if (resultado.next()) {
                pessoa.setId(resultado.getInt("id"));
                pessoa.setNome(resultado.getString("nome"));
                pessoa.setSobrenome(resultado.getString("sobrenome"));
                pessoa.setCpf(resultado.getString("cpf"));
                pessoa.setRg(resultado.getString("rg"));
                pessoa.setEmail(resultado.getString("email"));
                pessoa.setTelefone(tDAO.consultarIdPessoa(resultado.getInt("id")));
            }

        } catch (Exception e) {
            System.out.println("erro ao consultar pessoa: " + e);
            return e;
        }

        return pessoa;
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String verificaCpfExistente(String cpf) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "Select count(*) from pessoa where cpf = '" + cpf + "'";

            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                if (resultado.getInt(1) == 1) {
                    return "true";
                } else {
                    return "false";
                }
            } else {
                return "false";
            }
        } catch (Exception e) {
            System.out.println("Erro consultar cpf = " + e);
            return e.toString();
        }
    }

    public Object consultarCpf(String cpf) {
        Pessoa pessoa = null;

        try {
            String sql = "select * from pessoa "
                    + "where cpf = '" + cpf + "'";

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            TelefoneDAO tDAO = new TelefoneDAO();
            if (resultado.next()) {
                pessoa = new Pessoa();
                pessoa.setId(resultado.getInt("id"));
                pessoa.setNome(resultado.getString("nome"));
                pessoa.setSobrenome(resultado.getString("sobrenome"));
                pessoa.setCpf(resultado.getString("cpf"));
                pessoa.setRg(resultado.getString("rg"));
                pessoa.setEmail(resultado.getString("email"));
                pessoa.setTelefone(tDAO.consultarIdPessoa(resultado.getInt("id")));
            }

        } catch (Exception e) {
            System.out.println("erro ao consultar pessoa: " + e);
            return e;
        }

        return pessoa;
    }
    
}
