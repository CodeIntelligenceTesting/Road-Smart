package org.codeintelligence.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.h2.jdbcx.JdbcDataSource;
import org.codeintelligence.models.Road;

public class InformationDatabase {

    private Connection conn;

    public void connect()  {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:database.db");
        try {
            conn = ds.getConnection();

            // A dummy database is dynamically created
            conn.createStatement().execute("CREATE TABLE IF NOT EXISTS roads (id IDENTITY PRIMARY KEY, name VARCHAR(50), country VARCHAR(50))");
            conn.createStatement().execute("INSERT INTO roads (name, country) VALUES ('PCH', 'USA')");
            conn.createStatement().execute("INSERT INTO roads (name, country) VALUES ('I95', 'USA')");
            conn.createStatement().execute("INSERT INTO roads (name, country) VALUES ('401', 'Canada')");
            conn.createStatement().execute("INSERT INTO roads (name, country) VALUES ('Bundesautobhan 7', 'Germany')");


        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet insertRoadData(Road road) {
        try {
            String query = String.format("INSERT INTO roads (name, country) VALUES (%s, %s)", road.getName(), road.getCountry());
            return conn.createStatement().executeQuery(query);
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet readAllData() {
        String query = "SELECT * from roads";
        try {
            return conn.createStatement().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteRoadData(String name) {
        try {
            String query = String.format("DELETE FROM roads WHERE name = %s", name);
            conn.createStatement().executeQuery(query);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
