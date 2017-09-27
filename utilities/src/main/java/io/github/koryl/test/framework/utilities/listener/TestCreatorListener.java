package io.github.koryl.test.framework.utilities.listener;

import io.github.koryl.test.framework.utilities.suitegenerator.TestSuiteGenerator;
import io.github.koryl.test.framework.utilities.propertieshandler.PathHandler;
import org.testng.IAlterSuiteListener;
import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.List;

public class TestCreatorListener implements IAlterSuiteListener {

    public void alter(List<XmlSuite> suites) {
        TestNG testNG = new TestNG();
        TestSuiteGenerator.generate();

        List<String> suiteList = new ArrayList<>();
        suiteList.add("src/test/resources/testng-generated.xml");

        ArrayList<Class<? extends ITestNGListener>> listenerList = new ArrayList<>();
        listenerList.add(TestStepsListener.class);
        listenerList.add(TestReporterListener.class);

        String outputDirectoryPath = PathHandler.getPath("output.directory");
        testNG.setListenerClasses(listenerList);
        testNG.setTestSuites(suiteList);
        testNG.setOutputDirectory(outputDirectoryPath);
        testNG.run();
    }
}