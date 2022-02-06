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

    private static void parseObj(JSONObject jogoEl) {
        JSONParser jsonP = new JSONParser();

        String fname = (String) jogoEl.get("titulo");
        String lname = (String) jogoEl.get("desenvolvedora");
        String website = (String) jogoEl.get("publicadora");
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

                JSONParser jsonP = new JSONParser();

//                try {
//                    File myObj = new File("C:\\Users\\Guto\\IdeaProjects\\bd2021\\bd2021\\src\\main\\java\\controller\\geral-17-01.json");
//                    // C:\Users\yoshi\Documents\drip_games\bd2021\src\main\java\controller\geral-17-01.json
//                    Scanner myReader = new Scanner(myObj,"utf-8");
//                    String str = new String();
//                    while (myReader.hasNextLine()) {
//
//                        //JSONArray array = new JSONArray(myReader.nextLine());
//                        //str += myReader.nextLine();
//                    }
//                    System.out.println("An error occurred.");
//                    myReader.close();
//
//                    JSONObject obj = new JSONObject(str);
//
//                } catch (FileNotFoundException e) {
//                    System.out.println("An error occurred.");
//                    e.printStackTrace();
//                }

//                String jsonName = request.getParameter("fileName");
////                String test = "bd2021/src/main/java/controller/" + jsonName;
//                JSONParser jsonP = new JSONParser();
////                Path path2 = Paths.get(test);
////                System.out.println("\n[Path] : " + path2);
//
                try {
                    FileReader reader = new FileReader("C:\\Users\\Guto\\IdeaProjects\\bd2021\\bd2021\\src\\main\\java\\controller\\geral-17-01.json", StandardCharsets.UTF_8);
                    Object obj = jsonP.parse(reader);
                    JSONArray jogoList = (JSONArray) obj;
                    System.out.println(jogoList);
                    jogoList.forEach(jogoEl -> parseObj((JSONObject) jogoEl));

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
