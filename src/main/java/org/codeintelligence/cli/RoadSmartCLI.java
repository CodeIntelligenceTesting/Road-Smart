package org.codeintelligence.cli;

import org.codeintelligence.database.InformationDatabase;
import org.codeintelligence.models.Road;
import org.codeintelligence.processing.DataOutput;

import java.util.Scanner;

public class RoadSmartCLI {

    InformationDatabase db;
    DataOutput output;

    private Scanner scanner;

    private final String greeting = "Welcome to Road Smart, the intelligent tool for getting road information!\n";

    private final String optionsPrompt = "Please select from the following options:\n" +
        "[C]reate new road entry\n" +
        "[R]ead all road entries\n" +
        "[D]elete road entry\n" +
        "[P]rint road entries reports\n" +
        "[Q]uit this application\n\n";

    private final String promptString = ">> ";

    public RoadSmartCLI(InformationDatabase db){
        this.db = db;
        db.connect();
        output = new DataOutput(db);
    }

    public RoadSmartCLI(){
        this(new InformationDatabase());
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
            default: invalidOption(); break;
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

        output.toConsole();
    }

    private void deleteOption() {
        System.out.println("Delete road");

        System.out.print("Enter the road's name: ");
        String name = scanner.next();

        db.deleteRoadData(name);
    }

    private void printOption(){
        System.out.println("Print roads to report");

        output.toXML();
    }

    private void quitOption(){
        db.close();
        System.out.println("Quitting...");
        System.exit(0);
    }

    private void invalidOption(){
        System.out.println("Invalid option, try again.");
    }
}
