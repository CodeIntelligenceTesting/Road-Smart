package org.codeintelligence;

import org.codeintelligence.cli.RoadSmartCLI;
import org.codeintelligence.database.InformationDatabase;

public class Main {

    public static void main(String[] args) {
        InformationDatabase db = new InformationDatabase();

        RoadSmartCLI cli = new RoadSmartCLI(db);
        cli.runCLI();
    }
}