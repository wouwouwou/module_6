package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Wouter on 14-12-2015.
 *
 * JUnit class for testing the Dish in the Model.
 */
public class DishTest {

    @Before
    public void setUp() throws Exception {

        Dish dish1 = new Dish("Dame Blanche", "Vanille ijs met chocoladesaus");

    }

    @Test
    public void testName() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

}
