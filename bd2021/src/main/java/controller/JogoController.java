/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAO;
import dao.DAOFactory;
import dao.JogoDAO;
import model.Jogo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
//import static sun.font.CreatedFontTracker.MAX_FILE_SIZE;

/**
 *
 * @author dskaster
 */
@WebServlet(
        name = "JogoController",
        urlPatterns = {
                "/jogo",
                "/jogo/create",
                "/jogo/read",
                "/jogo/update",
                "/jogo/delete",
                "/jogo/checkLogin",
        }
)
public class JogoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JogoDAO dao;
        Jogo jogo;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/jogo": {
                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getJogoDAO();

                    List<Jogo> jogoList = dao.showImportant();
                    request.setAttribute("jogoList", jogoList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/view/jogo/index.jsp");
                dispatcher.forward(request, response);
                break;
            }

            case "/jogo/create": {
                dispatcher = request.getRequestDispatcher("/view/jogo/create.jsp");
                dispatcher.forward(request, response);
                break;
            }

            case "/jogo/update": {
                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getJogoDAO();

                    jogo = dao.read(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("jogo", jogo);

                    dispatcher = request.getRequestDispatcher("/view/jogo/update.jsp");
                    dispatcher.forward(request, response);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/jogo");
                }
                break;

            }
            case "/jogo/delete": {
                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getJogoDAO();
                    dao.delete(Integer.parseInt(request.getParameter("id")));
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/jogo");
                break;
            }
            case "/jogo/read": {
                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getJogoDAO();

                    jogo = dao.read(Integer.parseInt(request.getParameter("id")));

                    request.setAttribute("jogo", jogo);

                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }
                dispatcher = request.getRequestDispatcher("/view/jogo/jogo.jsp");
                dispatcher.forward(request, response);
                break;
            }
        }

    }


    private static Jogo parseObj(JSONObject jogoEl, Jogo jogo) {

        String title = (String) jogoEl.get("titulo");
        jogo.setTitulo(title);
        String developer = (String) jogoEl.get("desenvolvedora");
        jogo.setDesenvolvedora(developer);
        String category = (String) jogoEl.get("categoria");
        jogo.setCategoria(category);
        String description = (String) jogoEl.get("descricao");
        jogo.setDescricao(description);
        String publisher = (String) jogoEl.get("publicadora");
        jogo.setPublicadora(publisher);
        String ano = (String) jogoEl.get("ano_publicacao");
        jogo.setAno_publicacao(ano);
        String cpu = (String) jogoEl.get("cpu");
        jogo.setCpu(cpu);
        String gpu = (String) jogoEl.get("gpu");
        jogo.setGpu(gpu);
        String ram = (String) jogoEl.get("memoria_ram");
        jogo.setMemoria_ram(ram);
        String so = (String) jogoEl.get("so");
        jogo.setSo(so);
        String armazenamento = (String) jogoEl.get("armazenamento");
        jogo.setArmazenamento(armazenamento);
        String image = (String) jogoEl.get("img_src");
        jogo.setImage(image);

        return jogo;

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        DAO<Jogo> dao;
        DAO<Jogo> dao2;
        Jogo jogo = new Jogo();
        request.setCharacterEncoding("UTF-8");


        switch (request.getServletPath()) {

            case "/jogo/create": {

                JSONParser jsonP = new JSONParser();

                try {
                    String jsonName = request.getParameter("fileName");
                    //FileReader reader = new FileReader("C:/Users/yoshi/Documents/drip_games/Scrapy/teste/spiders/" + jsonName, StandardCharsets.UTF_8);
                    FileReader reader = new FileReader("C:/Users/Guto/IdeaProjects/bd2021/Scrapy/teste/spiders/" + jsonName, StandardCharsets.UTF_8);
                    Object obj = jsonP.parse(reader);

                    JSONArray jogoList = (JSONArray) obj;

                    try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                        dao2 = daoFactory.getJogoDAO();
                        jogoList.forEach(jogoEl -> {
                            try {
                                dao2.create(parseObj((JSONObject) jogoEl, jogo));
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                response.sendRedirect(request.getContextPath());
                break;
            }

            case "/jogo/update": {

                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getJogoDAO();

                    jogo.setId(Integer.parseInt(request.getParameter("id")));
                    jogo.setTitulo(request.getParameter("title"));
                    jogo.setDesenvolvedora(request.getParameter("developer"));
                    jogo.setCategoria(request.getParameter("category"));
                    jogo.setDescricao(request.getParameter("description"));
                    jogo.setPublicadora(request.getParameter("publisher"));
                    jogo.setAno_publicacao(request.getParameter("date_publi"));
                    jogo.setImage(request.getParameter("image"));
                    jogo.setCpu(request.getParameter("cpu"));
                    jogo.setGpu(request.getParameter("gpu"));
                    jogo.setSo(request.getParameter("so"));
                    jogo.setMemoria_ram(request.getParameter("memoria_ram"));
                    jogo.setArmazenamento(request.getParameter("armazenamento"));

                    dao.update(jogo);
                    response.sendRedirect(request.getContextPath() + "/jogo");
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
