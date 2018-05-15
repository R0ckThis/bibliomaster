<%-- 
    Document   : menu
    Created on : 25/08/2016, 15:40:46
    Author     : ramon
--%>

<%@page import="entidade.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link href="css/sweetalert.css" rel="stylesheet" type="text/css"/>
        <script src="jquery/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="jquery/jquery.maskedinput.js" type="text/javascript"></script>
        <script src="js/sweetalert.min.js" type="text/javascript"></script>
        <title>Início - BiblioMaster</title>
    </head>
    <%
        HttpSession sessaoMenu = ((HttpServletRequest) request).getSession();
        Usuario usuarioMenu = (Usuario) sessaoMenu.getAttribute("usuarioLogado");
    %>
    <body>
        <%if (usuarioMenu.getTipo().equals("Administrador")) {%>
        <div class="container">
            <!-- Static navbar -->
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="menu.jsp">BiblioMaster</a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Movimentação <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="cadReserva.jsp">Reserva</a></li>
                                    <li><a href="cadRetirada.jsp">Retirada</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="listaMovimentacao.jsp">Continuar Movimentação</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Cadastros de Apoio <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="cadAutor.jsp">Autor</a></li>
                                    <li><a href="cadCopia_livro.jsp">Cópia de Livros</a></li>
                                    <li><a href="cadEditora.jsp">Editora</a></li>
                                    <li><a href="cadGenero.jsp">Gênero</a></li>
                                    <li><a href="cadIdioma.jsp">Idioma</a></li>
                                    <li><a href="cadLivro.jsp">Livro</a></li>
                                    <li><a href="cadPessoa.jsp">Pessoa</a></li>
                                    <li><a href="cadUsuarioAdmin.jsp">Usuário</a></li>
                                    <li><a href="cadValor.jsp">Valores</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Listagens <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="listaAutor.jsp">Autores</a></li>
                                    <li><a href="listaCopiaLivro.jsp">Cópia de Livros</a></li>
                                    <li><a href="listaEditora.jsp">Editoras</a></li>
                                    <li><a href="listaGenero.jsp">Gêneros</a></li>
                                    <li><a href="listaIdioma.jsp">Idiomas</a></li>
                                    <li><a href="listaLivro.jsp">Livros</a></li>
                                    <li><a href="listaPessoa.jsp">Pessoas</a></li>
                                    <li><a href="listaUsuario.jsp">Usuários</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Relatórios <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="/bibliomaster/acao?parametro=relAtraso">Atrasos</a></li>
                                    <li><a href="/bibliomaster/relLivros.jsp">Livros</a></li>
                                    <li><a href="/bibliomaster/histLivro.jsp">Histórico de Livro</a></li>
                                    <li><a href="/bibliomaster/histPessoa.jsp">Histórico de Pessoa</a></li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%= usuarioMenu.getPessoa().getNome() + " " + usuarioMenu.getPessoa().getSobrenome()%> <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="/bibliomaster/acao?parametro=logout">Logout</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div><!--/.container-fluid -->
            </nav>
        </div> <!-- /container -->
        <%} else {%>
        <div class="container">
            <!-- Static navbar -->
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="menu.jsp">BiblioMaster</a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Movimentação <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="cadReserva.jsp">Reserva</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Listagens <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="listaLivro.jsp">Livros</a></li>
                                    <li><a href="listaMovimentacao.jsp">Movimentações</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Relatórios <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="/bibliomaster/histPessoa.jsp">Histórico</a></li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%= usuarioMenu.getPessoa().getNome() + " " + usuarioMenu.getPessoa().getSobrenome()%> <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="/bibliomaster/acao?parametro=logout">Logout</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div><!--/.container-fluid -->
            </nav>
        </div> <!-- /container -->
        <%}%>
    </body>
</html>
