package org.codeintelligence.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.codeintelligence.processing.RoadDataProcessor;
import org.h2.jdbcx.JdbcDataSource;
import org.codeintelligence.models.Road;

public class InformationDatabase {

    private Connection conn;
    private final String initialize = "CREATE TABLE IF NOT EXISTS roads (id IDENTITY PRIMARY KEY, name VARCHAR(50), country VARCHAR(50), length VARCHAR(10), speedLimit VARCHAR(10))";

    public void connect()  {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:database.db");
        try {
            conn = ds.getConnection();

            // A dummy database is dynamically created
            conn.createStatement().execute(initialize);
            conn.createStatement().execute("INSERT INTO roads (name, country, length, speedLimit) VALUES ('Pacific Coast Highway', 'USA', 1055.0, 100)");
            conn.createStatement().execute("INSERT INTO roads (name, country, length, speedLimit) VALUES ('I95', 'USA', 3000.0, 100)");
            conn.createStatement().execute("INSERT INTO roads (name, country, length, speedLimit) VALUES ('401', 'Canada', 828.0, 110)");
            conn.createStatement().execute("INSERT INTO roads (name, country, length, speedLimit) VALUES ('Bundesautobhan 7', 'Germany', 962.2, 150)");


        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void insertRoadData(Road road) {

        RoadDataProcessor processor = new RoadDataProcessor(road);
        String length = processor.computeLength();
        String speedLimit = processor.computeSpeedLimit().toString();

        try {
            String query = String.format("INSERT INTO roads (name, country, length, speedLimit) VALUES ('%s', '%s', '%s', '%s')", road.getName(), road.getCountry(), length, speedLimit);
            conn.createStatement().execute(query);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet readAllData() {
        String query = "SELECT * FROM roads";
        try {
            return conn.createStatement().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteRoadData(String name) {
        try {
            String query = String.format("DELETE FROM roads WHERE name = '%s'", name);
            conn.createStatement().execute(query);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
