package io.github.koryl.test.framework.utilities.suitegenerator;

import io.github.koryl.test.framework.utilities.suitegenerator.xlsreader.XlsConverter;
import io.github.koryl.test.framework.utilities.propertieshandler.PathHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestSuiteGenerator {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestSuiteGenerator.class);

    private static List<String> queryList;
    private final static String queryPath = PathHandler.getPath("query.file");
    private static final String xlsFilePath = PathHandler.getPath("xls.file");

    public static void generate() {

        XlsConverter suite = new XlsConverter(xlsFilePath);
        try {
            queryList = Files.readAllLines(Paths.get(queryPath));
        } catch (IOException e) {
            LOGGER.error("Exception: " + e.getMessage());
        }
        String query;
        if(queryList.size() == 0){
            query = "SELECT * FROM Sheet1";
        } else {
            query = String.join(" ", queryList);
        }
        suite.getTests(query);
        LOGGER.info("suite was generated properly.");
    }
}
