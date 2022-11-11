package org.codeintelligence.cli;

import java.util.Scanner;

public class RoadSmartCLI {

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

    private void processOption(String optionIn){
        switch (optionIn) {
            case "c": createOption(); break;
            case "r": readOption(); break;
            case "d": deleteOption(); break;
            case "p": printOption(); break;
            case "q": quitOption(); break;
        }
    }
    private static void createOption() {

    }
    private static void readOption() {
    }

    private static void deleteOption() {

    }

    private void printOption(){

    }

    private void quitOption(){
        System.out.println("Quitting...");
        System.exit(0);
    }

    public void runCLI(){
        String input;
        scanner = new Scanner(System.in);
        System.out.print(greeting);

        while (true){
            System.out.print(optionsPrompt);
            System.out.println(promptString);

            input = scanner.next();
            processOption(input);
        }
    }
}
