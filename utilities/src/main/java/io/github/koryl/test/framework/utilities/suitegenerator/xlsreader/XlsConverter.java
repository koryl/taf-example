package io.github.koryl.test.framework.utilities.suitegenerator.xlsreader;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.github.koryl.test.framework.utilities.logger.Log;
import io.github.koryl.test.framework.utilities.suitegenerator.suite.Suite;

import java.io.File;
import java.util.List;

import static com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator.Feature.*;
import static java.util.Arrays.*;

public class XlsConverter {

    private final Fillo fillo;
    private final String filePath;

    private Connection connection;

    public XlsConverter(String filePath) {
        fillo = new Fillo();
        this.filePath = filePath;
    }

    public void getTests(String query) {
        try {
            connection = fillo.getConnection(this.filePath);
            Recordset recordset = connection.executeQuery(query);
            this.createSuite(recordset);
        } catch (FilloException e) {
            Log.error("Exception: " + e.getMessage());
        } finally {
            connection.close();
        }
    }

    private void createSuite(Recordset recordset) {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(WRITE_XML_DECLARATION, true);
        Suite suite = new Suite("Example Test Framework");
        try {
            while (recordset.next()) {
                String testName = recordset.getField("TestCaseDescription");
                String className = recordset.getField("ClassName");
                if(recordset.getField("Data").isEmpty()) {
                    suite.addTest(testName, className);
                } else {
                    List<String> params = asList(recordset.getField("Data").split(","));
                    suite.addTest(testName, params, className);
                }

            }
            xmlMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File("src/test/resources/testng-generated.xml"), suite);
        } catch (Exception e) {
            Log.error("Exception: " + e.getMessage());
        } finally {
            recordset.close();
        }
    }

}