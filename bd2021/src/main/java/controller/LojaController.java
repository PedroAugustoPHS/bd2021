
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DAO;
import dao.DAOFactory;
import dao.LojaDAO;
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

import model.Loja;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import static sun.font.CreatedFontTracker.MAX_FILE_SIZE;

@WebServlet(
        name = "LojaController",
        urlPatterns = {
                "/loja",
                "/loja/create",
                "/loja/read",
                "/loja/update",
                "/loja/delete",
                "/loja/checkLogin"
        }
)

public class LojaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DAO<Loja> dao;
        Loja loja;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/loja": {
                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getLojaDAO();

                    List<Loja> lojaList = dao.all();
                    request.setAttribute("lojaList", lojaList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/view/loja/index.jsp");
                dispatcher.forward(request, response);
                break;
            }

            case "/loja/create": {
                dispatcher = request.getRequestDispatcher("/view/user/create.jsp");
                dispatcher.forward(request, response);
                break;
            }

            case "/loja/update": {
                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getLojaDAO();

                    loja = dao.read(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("loja", loja);

                    dispatcher = request.getRequestDispatcher("/view/user/update.jsp");
                    dispatcher.forward(request, response);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/loja");
                }
                break;
            }
            case "/loja/delete": {
                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getLojaDAO();
                    dao.delete(Integer.parseInt(request.getParameter("id")));
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/loja");
                break;
            }
            case "/loja/read": {
                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getLojaDAO();

                    loja = dao.read(Integer.parseInt(request.getParameter("id")));

                    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
                    String json = gson.toJson(loja);

                    response.getOutputStream().print(json);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/loja");
                }
                break;
            }
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
     *//*

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
    }

    */
/**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     *//*

    @Override
    public String getServletInfo() // </editor-fold>

}*/
