package io.github.koryl.test.framework.utilities.suitegenerator.suite;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@JacksonXmlRootElement(localName = "suite")
public class Suite {

    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlProperty(localName = "test")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List< Test > tests;

    public Suite(String name) {
        this.name = name;
        this.tests = new ArrayList <Suite.Test> ();
    }

    public void addTest(String testname, List<String> params, String className) {
        Test test = new Test(testname);
        test.addParam(params);
        Pattern.compile(",").splitAsStream(className).forEach(test::addClass);
        this.tests.add(test);
    }

    public void addTest(String testname, String className) {
        Test test = new Test(testname);
        Pattern.compile(",").splitAsStream(className).forEach(test::addClass);
        this.tests.add(test);
    }

    class Test {

        @JacksonXmlProperty(isAttribute = true)
        private String name;

        @JacksonXmlProperty(localName = "parameter")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<Parameter> param;

        @JacksonXmlProperty(localName = "classes")
        private Classes klasses;

        public Test(String name) {
            this.name = name;
            param = new ArrayList<>();
            klasses = new Classes();
        }

        void addParam(List<String> parameters) {
            int iterator = 1;
            for(String string : parameters) {
                param.add(new Parameter("Data"+iterator, string));
                iterator++;
            }
        }

        void addClass(String name) {
            klasses.addClasses(name);
        }

    }

    class Parameter {
        @JacksonXmlProperty(isAttribute = true)
        private String name;

        @JacksonXmlProperty(isAttribute = true)
        private String value;

        Parameter(String name, String value) {
            this.name = name;
            this.value = value;
        }

    }

    class Classes {

        @JacksonXmlProperty(localName = "class")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List < Class > classes;

        Classes() {
            this.classes = new ArrayList< Class >();
        }

        void addClasses(String name) {
            this.classes.add(new Class(name));
        }
    }

    class Class {

        @JacksonXmlProperty(isAttribute = true)
        private String name;

        Class(String name) {
            this.name = name;
        }

    }

}
