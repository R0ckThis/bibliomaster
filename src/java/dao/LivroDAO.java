/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.Autor;
import entidade.Editora;
import entidade.Genero;
import entidade.Idioma;
import entidade.Livro;
import interfaces.IDAO;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Formatacao;

/**
 *
 * @author ramon
 */
public class LivroDAO implements IDAO {

    private final String Diretorio = "D:/Google Drive/Univates/2016 B/Programação para Internet/Projeto/bibliomaster/web/";

    @Override
    public String salvar(Object o, char op) {
        Livro livro = (Livro) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "INSERT INTO livro VALUES"
                    + "(DEFAULT, "
                    + "'" + livro.getTitulo() + "', '" + livro.getPublicacao() + "', " + livro.getPaginas() + ", '" + livro.getEdicao() + "', "
                    + "'" + livro.getLocalizacao() + "', '"
                    + livro.getCapa() + "', " + livro.getIdioma().getId() + ", " + livro.getEditora().getId() + ") RETURNING id";

            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            int idLivro = 0;
            if (resultado.next()) {
                idLivro = resultado.getInt("id");
                for (Autor autor : livro.getAutor()) {
                    sql = "INSERT INTO livro_has_autor VALUES (" + idLivro + ", " + autor.getId() + ")";
                    System.out.println(sql);
                    int resultadoAutor = st.executeUpdate(sql);
                }

                for (Genero genero : livro.getGenero()) {
                    sql = "INSERT INTO livro_has_genero VALUES (" + idLivro + ", " + genero.getId() + ")";
                    System.out.println(sql);
                    int resultadoGenero = st.executeUpdate(sql);
                }

            }
            if (!livro.getCapa().isEmpty()) {

                String extencao = livro.getCapa().substring(livro.getCapa().lastIndexOf('.') + 1);
                File capaAntigo = new File(Diretorio + livro.getCapa());    // Arquivo ou diretório com novo nome    
                File capaNovo = new File(Diretorio + "capa/" + idLivro + "." + extencao);    // Renomeando arquivo ou diretório    
                boolean success = capaAntigo.renameTo(capaNovo);
                if (success) {
                    sql = "UPDATE livro SET capa = 'capa/" + idLivro + "." + extencao + "' WHERE id = " + idLivro;
                    int resultadoCapa = st.executeUpdate(sql);
                }
            }

            return null;
        } catch (Exception e) {
            String erro = e.toString();
            System.out.println("Erro salvar livro = " + e);
            return erro;
        }
    }

    @Override
    public String atualizar(Object o) {
        Livro livro = (Livro) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE livro SET "
                    + "titulo = '" + livro.getTitulo() + "', publicacao = '" + livro.getPublicacao() + "', "
                    + "paginas = " + livro.getPaginas() + ", edicao = '" + livro.getEdicao() + "', "
                    + "localizacao = '" + livro.getLocalizacao() + "', capa = '" + livro.getCapa() + "', "
                    + "idioma = " + livro.getIdioma().getId() + ", editora = " + livro.getEditora().getId()
                    + " WHERE id = " + livro.getId();

            System.out.println("sql: " + sql);

            int retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

            sql = "DELETE FROM livro_has_autor WHERE livro = " + livro.getId();
            int resultadoExcluirAutor = st.executeUpdate(sql);

            for (Autor autor : livro.getAutor()) {

                sql = "INSERT INTO livro_has_autor VALUES (" + livro.getId() + ", " + autor.getId() + ")";
                System.out.println(sql);
                int resultadoAutor = st.executeUpdate(sql);
            }

            sql = "DELETE FROM livro_has_genero WHERE livro = " + livro.getId();
            int resultadoExcluirGenero = st.executeUpdate(sql);

            for (Genero genero : livro.getGenero()) {
                sql = "INSERT INTO livro_has_genero VALUES (" + livro.getId() + ", " + genero.getId() + ")";
                System.out.println(sql);
                int resultadoGenero = st.executeUpdate(sql);
            }
            return null;
        } catch (Exception e) {
            String erro = e.toString();
            System.out.println("Erro atualizar livro = " + e);
            return erro;
        }
    }

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> livros = new ArrayList<>();

        try {
            String sql = "select * from livro ORDER BY id";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            while (resultado.next()) {
                Livro l = new Livro();

                l.setId(resultado.getInt("id"));
                l.setTitulo(resultado.getString("titulo"));
                l.setPublicacao(resultado.getDate("publicacao"));
                l.setPaginas(resultado.getInt("paginas"));
                l.setEdicao(resultado.getString("edicao"));
                l.setLocalizacao(resultado.getString("localizacao"));
                l.setCapa(resultado.getString("capa"));
                l.setIdioma((Idioma) new IdiomaDAO().consultarId(resultado.getInt("idioma")));
                l.setEditora((Editora) new EditoraDAO().consultarId(resultado.getInt("editora")));
                sql = "select * from livro_has_autor where livro = " + l.getId() + " ORDER BY autor";
                ResultSet resultadoAutor = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
                ArrayList<Autor> autores = new ArrayList();
                while (resultadoAutor.next()) {
                    autores.add((Autor) new AutorDAO().consultarId(resultadoAutor.getInt("autor")));
                }
                sql = "select * from livro_has_genero where livro = " + l.getId() + " ORDER BY genero";
                ResultSet resultadoGenero = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
                ArrayList<Genero> generos = new ArrayList();
                while (resultadoGenero.next()) {
                    generos.add((Genero) new GeneroDAO().consultarId(resultadoGenero.getInt("genero")));
                }

                l.setAutor(autores);
                l.setGenero(generos);

                livros.add(l);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar livros: " + e);
        }
        return livros;
    }

    @Override
    public boolean registroUnico(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        ArrayList<Object> livros = new ArrayList<>();

        try {
            String sql = "select * from livro WHERE titulo ILIKE '%" + criterio + "%' ORDER BY id";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Livro l = new Livro();

                l.setId(resultado.getInt("id"));
                l.setTitulo(resultado.getString("titulo"));
                l.setPublicacao(resultado.getDate("publicacao"));
                l.setPaginas(resultado.getInt("paginas"));
                l.setEdicao(resultado.getString("edicao"));
                l.setLocalizacao(resultado.getString("localizacao"));
                l.setCapa(resultado.getString("capa"));
                l.setIdioma((Idioma) new IdiomaDAO().consultarId(resultado.getInt("idioma")));
                l.setEditora((Editora) new EditoraDAO().consultarId(resultado.getInt("editora")));
                sql = "select * from livro_has_autor where livro = " + l.getId() + " ORDER BY autor";
                ResultSet resultadoAutor = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
                ArrayList<Autor> autores = new ArrayList();
                while (resultadoAutor.next()) {
                    autores.add((Autor) new AutorDAO().consultarId(resultadoAutor.getInt("autor")));
                }
                sql = "select * from livro_has_genero where livro = " + l.getId() + " ORDER BY genero";
                ResultSet resultadoGenero = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
                ArrayList<Genero> generos = new ArrayList();
                while (resultadoGenero.next()) {
                    generos.add((Genero) new GeneroDAO().consultarId(resultadoGenero.getInt("genero")));
                }

                l.setAutor(autores);
                l.setGenero(generos);

                livros.add(l);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar livros: " + e);
        }

        return livros;
    }

    @Override
    public Object consultarId(int id) {
        try {
            String sql = "select * from livro where id = " + id + " ORDER BY id";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            Livro l = new Livro();
            if (resultado.next()) {

                l.setId(resultado.getInt("id"));
                l.setTitulo(resultado.getString("titulo"));
                l.setPublicacao(resultado.getDate("publicacao"));
                l.setPaginas(resultado.getInt("paginas"));
                l.setEdicao(resultado.getString("edicao"));
                l.setLocalizacao(resultado.getString("localizacao"));
                l.setCapa(resultado.getString("capa"));
                l.setIdioma((Idioma) new IdiomaDAO().consultarId(resultado.getInt("idioma")));
                l.setEditora((Editora) new EditoraDAO().consultarId(resultado.getInt("editora")));
                sql = "select * from livro_has_autor where livro = " + l.getId() + " ORDER BY autor";
                ResultSet resultadoAutor = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
                ArrayList<Autor> autores = new ArrayList();
                while (resultadoAutor.next()) {
                    autores.add((Autor) new AutorDAO().consultarId(resultadoAutor.getInt("autor")));
                }
                sql = "select * from livro_has_genero where livro = " + l.getId() + " ORDER BY genero";
                ResultSet resultadoGenero = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
                ArrayList<Genero> generos = new ArrayList();
                while (resultadoGenero.next()) {
                    generos.add((Genero) new GeneroDAO().consultarId(resultadoGenero.getInt("genero")));
                }

                l.setAutor(autores);
                l.setGenero(generos);

            }
            return l;
        } catch (Exception e) {
            System.out.println("Erro ao consultar livros: " + e);
            return e;
        }
    }

    @Override
    public boolean consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int estoqueTotal(Livro livro) {
        try {
            String sql = "select count(*) from copia_livro where livro = " + livro.getId();
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            int quantidade = 0;
            if (resultado.next()) {
                quantidade = resultado.getInt(1);
            }
            return quantidade;
        } catch (Exception e) {
            return 0;
        }
    }

    public int estoqueDisponivel(Livro livro) {
        try {
            String sql = "select count(*) from copia_livro where livro = " + livro.getId() + " and estado = 'Disponível'";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            int quantidade = 0;
            if (resultado.next()) {
                quantidade = resultado.getInt(1);
            }
            return quantidade;
        } catch (Exception e) {
            return 0;
        }
    }

}
