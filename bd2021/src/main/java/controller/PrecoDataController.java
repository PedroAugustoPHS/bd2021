package controller;

import dao.DAOFactory;
import dao.PrecoDataDAO;
import model.PrecoData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "PrecoDataController",
        urlPatterns = {
                "/preco",
        }
)

public class PrecoDataController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrecoDataDAO dao;
        PrecoData precoData;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/preco": {

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        PrecoDataDAO dao;
        PrecoData precoData = new PrecoData();

        String servletPath = request.getServletPath();

        switch (request.getServletPath()) {

            case "/preco": {
            }
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
