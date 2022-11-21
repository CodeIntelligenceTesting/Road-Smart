import org.codeintelligence.processing.RoadCalculator;
import org.junit.Assert;
import org.junit.Test;

public class RoadCalculatorTest {

    @Test
    public void baseCaseKilometers(){
        double actual = RoadCalculator.kilometersToMiles(1.0);
        double expected = 0.6214;

        Assert.assertEquals(actual, expected, 0.001);
    }

    @Test
    public void baseCaseMiles(){
        double actual = RoadCalculator.milesToKilometers(1.0);
        double expected = 1.609;

        Assert.assertEquals(actual, expected, 0.001);
    }

    @Test
    public void zeroCaseKilometers(){
        double actual = RoadCalculator.kilometersToMiles(0.0);
        double expected = 0.0;

        Assert.assertEquals(actual, expected, 0.001);
    }

    @Test
    public void zeroCaseMiles(){
        double actual = RoadCalculator.milesToKilometers(0.0);
        double expected = 0.0;

        Assert.assertEquals(actual, expected, 0.001);
    }
}
