/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
import java.sql.SQLException;
import java.util.Iterator;
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

                    dispatcher = request.getRequestDispatcher("/view/user/update.jsp");
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

    private static void parseObj(JSONObject jogo) {
        //get emp firstname, lastname, website
        String fname = (String) jogo.get("titulo");
        String lname = (String) jogo.get("desenvolvedora");
        String website = (String) jogo.get("publicadora");
        System.out.println("First Name: " + fname);
        System.out.println("Last Name: " + lname);
        System.out.println("Website: " + website);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        JogoDAO dao;
        Jogo jogo = new Jogo();

        String servletPath = request.getServletPath();

        switch (request.getServletPath()) {

            case "/jogo/create": {
                String jsonname = request.getParameter("fileName");
                JSONParser jsonP = new JSONParser();
                try (FileReader reader = new FileReader("Users\\Guto\\IdeaProjects\\bd2021\\Scrapy\\teste\\spiders\\" + jsonname)) {
                    //Read JSON File            /Users/Shared/crunchify.json
                    Object obj = jsonP.parse(reader);
                    JSONArray jogoList = (JSONArray) obj;
                    System.out.println(jogoList);

                    Iterator<JSONObject> iterator = jogoList.iterator();
                    44
                    while (iterator.hasNext()) {
                        parseObj(iterator.next());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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
