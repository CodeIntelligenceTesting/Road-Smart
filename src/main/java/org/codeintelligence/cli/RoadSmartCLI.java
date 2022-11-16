package org.codeintelligence.cli;

import org.codeintelligence.database.InformationDatabase;
import org.codeintelligence.models.Road;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RoadSmartCLI {

    InformationDatabase db;

    private Scanner scanner;

    private final String greeting = """
    Welcome to Road Smart, the intelligent tool for getting road information!        
    """;

    private final String optionsPrompt = """
        Please select from the following options:
    
        [C]reate new road entry
        [R]ead all road entries
        [D]elete road entry
        [P]rint road entries reports
        [Q]uit this application
    """;

    private final String promptString = ">> ";

    public RoadSmartCLI(InformationDatabase db){
        this.db = db;
        db.connect();
    }

    public void runCLI(){
        String input;
        scanner = new Scanner(System.in);
        System.out.print(greeting);

        while (true){
            System.out.print(optionsPrompt);
            System.out.print(promptString);

            input = scanner.next();
            processOption(input);
        }
    }

    private void processOption(String optionIn){
        switch (optionIn.toLowerCase()) {
            case "c": createOption(); break;
            case "r": readOption(); break;
            case "d": deleteOption(); break;
            case "p": printOption(); break;
            case "q": quitOption(); break;
        }
    }
    private void createOption() {
        System.out.println("Create new road entry");

        System.out.print("Enter the road's name: ");
        String name = scanner.next();

        System.out.print("Enter the road's origin country: ");
        String country = scanner.next();

        db.insertRoadData(new Road(name, country));

    }
    private void readOption() {
        System.out.println("Read all roads");

        try {
            ResultSet resultSet = db.readAllData();
            while (resultSet.next()) {
                System.out.printf("%s %s%n", resultSet.getString(1), resultSet.getString(2));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    private void deleteOption() {
        System.out.println("Delete road");

        System.out.print("Enter the road's name: ");
        String name = scanner.next();

        db.deleteRoadData(name);
    }

    private void printOption(){
        System.out.print("Print roads to report ");

        try {
            ResultSet resultSet = db.readAllData();
            while (resultSet.next()) {
                System.out.printf("%s %s%n", resultSet.getString(1), resultSet.getString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void quitOption(){

        System.out.println("Quitting...");
        System.exit(0);
    }
}
