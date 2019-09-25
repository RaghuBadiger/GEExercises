package com.ge.exercise1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {

    Parser parser;

    @Before
    public void setUp() {
        try {
            Class parserClass = Class.forName("com.ge.exercise1.ApplicationParser");
            parser = (Parser) parserClass.newInstance();
        } catch (Exception e) {
            Assume.assumeNoException(e);
        }
    }

    @Test
    public void parseApplicationDataSimpleTest() {
        String simpleTestData = "Application(id: 0,name: MyApp,users:[User(id: 1,name: Beth Jones,salry:rrer),User(id: 2,name: Beth Jones2,age:20),User(id: 3,name: Beth Jones3,age:20)],groups:[Group(id: 1,name: SimpleGroup1,users:[User(id: 1,name: Beth Jones),User(id: 2,name: Beth Jones2),User(id: 3,name: Beth Jones3)])],Group(id: 2,name: SimpleGroup2,users:[User(id: 1,name: Beth Jones),User(id: 2,name: Beth Jones2)])])";
        Application app = parser.parseApplicationData(simpleTestData);
        assertEquals("MyApp", app.getName());
        assertEquals("2", app.getUser("2").getId());
        assertEquals("SimpleGroup1", app.getGroup("1").getName());
        assertEquals(2, app.getGroupSize());
        assertEquals(3, app.getUserSize());
        assertEquals("Beth Jones", app.getUser("1").getName());
        assertNotNull(app.getGroup("1").getUsers());
        assertNotEquals(0, app.getGroup("2").getUsers().size());
        assertEquals(3, app.getGroup("1").getUsers().size());
        assertEquals("SimpleGroup2", app.getGroup("2").getName());
        assertEquals(2, app.getGroup("2").getUsers().size());
    }
}