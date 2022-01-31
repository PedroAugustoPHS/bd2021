/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DAO;
import dao.DAOFactory;
import dao.JogoDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jogo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
                "/jogo/checkLogin"
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
                dispatcher = request.getRequestDispatcher("/view/user/create.jsp");
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
                    response.sendRedirect(request.getContextPath() + "/jogo");
                }
                dispatcher = request.getRequestDispatcher("/view/jogo/jogo.jsp");
                dispatcher.forward(request, response);
                break;
            }
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
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
