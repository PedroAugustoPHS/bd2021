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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(
        name = "PrecoDataController",
        urlPatterns = {
                "/preco",
        }
)

public class PrecoDataController extends HttpServlet {

    private static PrecoData parseObj(JSONObject precoEl, PrecoData price, Date filedate, String lojaname) {

        Date data_reg = (Date) precoEl.get(filedate);
        price.setData_registro(data_reg);

        String spreco = (String) precoEl.get("preco");
        spreco = spreco.replace("R$ ", "").replace("R$ ", "").replace(",", ".");  // R$ 50,00 -> 50.00
        Float preco = Float.valueOf(spreco);
        price.setPreco(preco);

        String spromo = (String) precoEl.get("desconto");
        spromo = spromo.replace("-", "").replace("%", ""); // -50% -> 50
        Integer promo = Integer.valueOf(spromo);

        price.setPorcentagem_promo(promo);

        String jogoName = (String) precoEl.get("titulo");
        price.setJogo_id(getId(jogoName));

        switch (lojaname) {
            case "steam": {
                price.setLoja_id(1);
            }
            case "nuuvem": {
                price.setLoja_id(2);
            }
            case "epic": {
                price.setLoja_id(3);
            }
        }

        return price;

    }

    private static Integer getId(String jogoname) {

        if (jogoname.indexOf("Portia") != -1) {
            return (1);
        } else if (jogoname.indexOf("Builder") != -1) {
            return (2);
        } else if (jogoname.indexOf("A Lenda do Herói") != -1 || jogoname.indexOf("Songs for a Hero") != -1) {
            return (3);
        } else if (jogoname.indexOf("DEATH") != -1 || jogoname.indexOf("Death") != -1) {
            return (4);
        } else if (jogoname.indexOf("Farming") != -1) {
            return (5);
        } else if (jogoname.indexOf("Red Dead") != -1) {
            return (6);
        } else if (jogoname.indexOf("while True") != -1) {
            return (7);
        } else if (jogoname.indexOf("Mafia") != -1) {
            return (8);
        } else if (jogoname.indexOf("Overcooked") != -1 && jogoname.indexOf("2") == -1) {
            return (9);
        } else if (jogoname.indexOf("Never Alone") != -1) {
            return (10);
        } else if ((jogoname.indexOf("Batman") != -1 && jogoname.indexOf("Videogame") != -1) || jogoname.indexOf("LEGO Batman") != -1) {
            return (11);
        } else if (jogoname.indexOf("Eastward") != -1) {
            return (12);
        } else if (jogoname.indexOf("Overcooked") != -1 && jogoname.indexOf("2") != -1) {
            return (13);
        } else if (jogoname.indexOf("Vampyr") != -1) {
            return (14);
        } else if (jogoname.indexOf("Batman") != -1 && jogoname.indexOf("2") != -1) {
            return (15);
        } else if (jogoname.indexOf("Gungeon") != -1) {
            return (16);
        } else if (jogoname.indexOf("Among Us") != -1) {
            return (17);
        } else if (jogoname.indexOf("Minit") != -1) {
            return (18);
        } else if (jogoname.indexOf("PC Building") != -1) {
            return (19);
        } else if (jogoname.indexOf("Batman") != -1 && jogoname.indexOf("3") != -1) {
            return (20);
        } else if (jogoname.indexOf("A Plague Tale") != -1) {
            return (21);
        } else if (jogoname.indexOf("Darkest") != -1) {
            return (22);
        } else if (jogoname.indexOf("Chivalry") != -1) {
            return (23);
        } else if (jogoname.indexOf("SUPERHOT") != -1) {
            return (24);
        } else if (jogoname == "Far Cry" || jogoname == "Far Cry 1" || jogoname == "Far Cry®") {
            return (25);
        } else if ((jogoname.indexOf("Far Cry") != -1 || jogoname.indexOf("FAR") != -1) && jogoname.indexOf("3") != -1) {
            return (26);
        } else if (jogoname.indexOf("South Park") != -1) {
            return (27);
        } else if ((jogoname.indexOf("Far Cry") != -1 || jogoname.indexOf("FAR") != -1) && jogoname.indexOf("4") != -1) {
            return (28);
        } else if ((jogoname.indexOf("Far Cry") != -1 || jogoname.indexOf("FAR") != -1) && (jogoname.indexOf("5") != -1)) {
            return (29);
        } else if (jogoname.indexOf("Just Die") != -1) {
            return (30);
        }
        return 0;

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrecoDataDAO dao;
        PrecoData precoData;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/preco/create": {
                dispatcher = request.getRequestDispatcher("/view/preco/create.jsp");
                dispatcher.forward(request, response);
                break;

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        PrecoDataDAO dao;
        PrecoData precoData = new PrecoData();

        String servletPath = request.getServletPath();

        switch (request.getServletPath()) {

            case "/preco/create": {
                JSONParser jsonP = new JSONParser();
                Date filedata;
                String lojaname;
                String mes, mes2;

                try {
                    String jsonName = request.getParameter("fileName");
                    //FileReader reader = new FileReader("C:/Users/yoshi/Documents/drip_games/Scrapy/teste/spiders/" + jsonName, StandardCharsets.UTF_8);
                    FileReader reader = new FileReader("C:/Users/Guto/IdeaProjects/bd2021/Scrapy/teste/spiders/" + jsonName, StandardCharsets.UTF_8);
                    Object obj = jsonP.parse(reader);

                    mes2 = jsonName.split("-")[2];
                    mes = mes2.split("\\.j")[0];

                    lojaname = jsonName.split("-")[0];
                    filedata = Date.valueOf(("2022" + "-" + mes + "-" + (jsonName.split("-")[1])));
                    System.out.println("data:" + filedata);

                    JSONArray precoList = (JSONArray) obj;

                    try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                        dao = daoFactory.getPrecoDataDAO();
                        Date finalFiledata = filedata;

                        precoList.forEach(precoEl -> {
                            try {
                                dao.create(parseObj((JSONObject) precoEl, precoData, finalFiledata, lojaname));

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

                try {
                    response.sendRedirect(request.getContextPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
