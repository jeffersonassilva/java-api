package Service;

import Model.DatabaseConnection;
import Model.UsuarioDAO;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Usuario extends Abstract {

    private Connection connection;

    private Gson gson = new Gson();

    /**
     * @throws SQLException           SQLException
     * @throws ClassNotFoundException ClassNotFoundException
     */
    public Usuario() throws SQLException, ClassNotFoundException {
        this.connection = DatabaseConnection.initializeDatabase();
        this.model = "usuario";
    }

    /**
     * @param response Response
     * @throws SQLException SQLException
     * @throws IOException  IOException
     */
    public void all(HttpServletResponse response) throws SQLException, IOException {
        ArrayList json = new ArrayList();

        try {
            String sql = "select * from " + this.model;
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                HashMap usuario = new HashMap();
                usuario.put("id", rs.getInt("id"));
                usuario.put("nome", rs.getString("nome"));
                json.add(usuario);
            }

            stmt.close();
            connection.close();

            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(this.gson.toJson(json));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param response Response
     * @param id       ID
     * @throws SQLException SQLException
     */
    public void find(HttpServletResponse response, Integer id) throws SQLException {
        try {
            String sql = "select * from usuario where id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, id.toString());

            ResultSet rs = stmt.executeQuery();

            HashMap usuario = new HashMap();
            while (rs.next()) {
                usuario.put("id", rs.getInt("id"));
                usuario.put("nome", rs.getString("nome"));
            }

            stmt.execute();
            stmt.close();

            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(this.gson.toJson(usuario));
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param usuarioDAO User Model
     * @throws SQLException SQLException
     */
    public void create(UsuarioDAO usuarioDAO) throws SQLException {
        String sql = "insert into usuario (nome) values(?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, usuarioDAO.getNome());
        stmt.execute();
        stmt.close();
    }

    /**
     * @param response Response
     * @param usuarioDAO  UsuarioDAO
     * @throws SQLException SQLException
     */
    public void update(HttpServletResponse response, UsuarioDAO usuarioDAO) throws SQLException {
        try {
            String sql = "update usuario set nome = ? where id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuarioDAO.getNome());
            stmt.setInt(2, usuarioDAO.getId());
            stmt.execute();
            stmt.close();

            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(this.gson.toJson(usuarioDAO));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
