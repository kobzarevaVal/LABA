import entity.Serial;

import java.sql.*;
import java.util.logging.*;
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        String url = "jdbc:postgresql://localhost/laba4";
        String name = "postgres";
        String password = "123456789";
        Class.forName("org.postgresql.Driver");
        System.out.println("Драйвер подключен");
        connection = DriverManager.getConnection(url, name, password);
        System.out.println("Соединение установлено");
        String selectTableSQL = "SELECT * FROM public.serial";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(selectTableSQL);
        while (rs.next()) {
            Serial serial = new Serial(rs.getString("name"),rs.getInt("season_count"),rs.getFloat("rating"));
            System.out.println(serial);
        }
    }
}
