package Service;

import Model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Abstract {
    private Connection connection;

    protected String model;

    /**
     * @throws SQLException           SQLException
     * @throws ClassNotFoundException ClassNotFoundException
     */
    public Abstract() throws SQLException, ClassNotFoundException {
        this.connection = DatabaseConnection.initializeDatabase();
    }

    /**
     * @param id ID
     * @throws SQLException SQLException
     */
    public void delete(Integer id) throws SQLException {
        String sql = "delete from " + this.model + " where id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
    }
}
