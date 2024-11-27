package nl.miwnn.se14.eatwell.unittests;

import nl.miwnn.se14.eatwell.model.Ingredient;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Bart Molenaars
 * Tests code functionality of Ingredient model
 */

public class IngredientTests {
    private Ingredient ingredient;


    @Test
    void testCapitalCounter(){

//    Arrange
    Ingredient testIngredient = new Ingredient();

//    Act
    int capitalLetters = testIngredient.countNumberOfCapitalLetters("AwhOleBunCHoFpinEapPles");

//    Assert
    assertEquals(8, capitalLetters, "The number of capital letters should match the string.");
    }
//

}
