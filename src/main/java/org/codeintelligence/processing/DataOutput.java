package org.codeintelligence.processing;

import org.codeintelligence.database.InformationDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataOutput {

    private InformationDatabase db;

    public DataOutput(InformationDatabase db) {
        this.db = db;
    }

    public void toConsole() {
        try {
            ResultSet resultSet = db.readAllData();
            while (resultSet.next()) {
                System.out.printf("%s %s%n", resultSet.getString(1), resultSet.getString(2));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void toXML(){

    }

}
