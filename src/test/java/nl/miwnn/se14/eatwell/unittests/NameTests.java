package nl.miwnn.se14.eatwell.unittests;

import nl.miwnn.se14.eatwell.model.Recipe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class NameTests {

    @Test
    void TestLowerLCastCount(){

        // Arrange
        Recipe recipe = new Recipe();

        //Act
        int lowerCase = recipe.countNumberOfLowerCase("Furkan");


        //Assert
        assertEquals(5, lowerCase, "Should be 5");
    }
}
