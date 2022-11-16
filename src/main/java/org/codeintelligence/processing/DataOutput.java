package org.codeintelligence.processing;

import org.codeintelligence.database.InformationDatabase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
                System.out.printf("%s %s %s %s %n", resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void toXML(){
        final String filePath = "roads.xml";

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("roads");
            document.appendChild(root);

            ResultSet resultSet = db.readAllData();
            Element roadEntry, name, country, length, speedLimit;
            while (resultSet.next()) {
                // road elements
                roadEntry = document.createElement("road");
                root.appendChild(roadEntry);

                name = document.createElement("name");
                name.setTextContent(resultSet.getString(2));
                roadEntry.appendChild(name);

                country = document.createElement("country");
                country.setTextContent(resultSet.getString(3));
                roadEntry.appendChild(country);

                length = document.createElement("length");
                length.setTextContent(resultSet.getString(4));
                roadEntry.appendChild(length);

                speedLimit = document.createElement("speedLimit");
                speedLimit.setTextContent(resultSet.getString(5));
                roadEntry.appendChild(speedLimit);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(filePath);

            transformer.transform(source, result);
        } catch (ParserConfigurationException | SQLException | TransformerException e) {
            e.printStackTrace();
        }
    }

}
