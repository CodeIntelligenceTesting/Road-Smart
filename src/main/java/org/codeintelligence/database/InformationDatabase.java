package org.codeintelligence.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codeintelligence.models.Road;
import org.codeintelligence.processing.RoadDataProcessor;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InformationDatabase {

    private Connection conn;
    private final String initialize = "CREATE TABLE IF NOT EXISTS roads (id IDENTITY PRIMARY KEY, name VARCHAR(50), country VARCHAR(50), length VARCHAR(10), speedLimit VARCHAR(10))";

    private Logger log = LogManager.getLogger(InformationDatabase.class);

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

    public Boolean insertRoadData(Road road) {

        RoadDataProcessor processor = new RoadDataProcessor(road);
        String length = processor.computeLength();
        String speedLimit = processor.computeSpeedLimit().toString();

        try {
            log.info(String.format("Inserting Road '%s' into database", road.getName()));
            String query = String.format("INSERT INTO roads (name, country, length, speedLimit) VALUES ('%s', '%s', '%s', '%s')", road.getName(), road.getCountry(), length, speedLimit);
            return !conn.createStatement().execute(query);
        } catch (SQLException e){
            log.error(String.format("Error in inputting '%s' into database", road.getName()));
            e.printStackTrace();
            return false;
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
