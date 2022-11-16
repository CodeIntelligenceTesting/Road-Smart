package org.codeintelligence;

import org.codeintelligence.cli.RoadSmartCLI;
import org.codeintelligence.database.InformationDatabase;

public class Main {

    public static void main(String[] args) {
        RoadSmartCLI cli = new RoadSmartCLI();
        cli.runCLI();
    }
}