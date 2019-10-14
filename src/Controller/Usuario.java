package Controller;

import Model.UsuarioDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "usuarioServlet", urlPatterns = {"/api/usuario/*"})
public class Usuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Service.Usuario service = new Service.Usuario();
            if (!(request.getPathInfo() == null)) {
                int userId = retrieveUserid(request);
                service.find(response, userId);
            } else {
                service.all(response);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.setNome(name);
            Service.Usuario service = new Service.Usuario();
            service.create(usuarioDAO);
            service.all(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        try {
            int userId = retrieveUserid(request);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.setId(userId);
            usuarioDAO.setNome(name);
            Service.Usuario service = new Service.Usuario();
            service.update(response, usuarioDAO);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = retrieveUserid(request);
            Service.Usuario service = new Service.Usuario();
            service.delete(userId);
            service.all(response);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * @param request request
     * @return Integer
     */
    private static int retrieveUserid(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo.startsWith("/")) {
            pathInfo = pathInfo.substring(1);
        }
        return Integer.parseInt(pathInfo);
    }
}