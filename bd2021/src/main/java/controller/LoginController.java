/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOFactory;
import dao.UserDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author dskaster
 */
@WebServlet(
        name = "LoginController", 
        urlPatterns = {
            "",
            "/logout",
            "/login"
        })
public class LoginController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "": {
                session = request.getSession(false);

                if (session != null && session.getAttribute("usuario") != null) {
                    dispatcher = request.getRequestDispatcher("/welcome.jsp");
                } else {
                    dispatcher = request.getRequestDispatcher("/index.jsp");
                }

                dispatcher.forward(request, response);

                break;
            }
            
            case "/logout": {
                session = request.getSession(false);

                if (session != null) {
                    session.invalidate();
                }

                response.sendRedirect(request.getContextPath() + "/");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws  IOException {

        UserDAO dao;
        User user = new User();
        HttpSession session = request.getSession();

        switch (request.getServletPath()) {
            case "/login":
                user.setLogin(request.getParameter("login"));
                user.setSenha(request.getParameter("senha"));

                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getUserDAO();

                    dao.authenticate(user);

                    session.setAttribute("usuario", user);
                } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
                    session.setAttribute("error", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/");
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
