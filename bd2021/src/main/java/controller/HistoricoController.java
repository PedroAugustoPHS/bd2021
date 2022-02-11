package controller;

import dao.HistoricoDAO;
import model.Historico;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "HistoricoController",
        urlPatterns = {
                "/load-hist",
        }
)

public class HistoricoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HistoricoDAO dao;
        Historico historico;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/load-hist": {

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        HistoricoDAO dao;
        Historico historico = new Historico();

        String servletPath = request.getServletPath();

        switch (request.getServletPath()) {

            case "/load-hist": {
            }
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
