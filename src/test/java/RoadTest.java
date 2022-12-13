import org.codeintelligence.models.Road;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoadTest { // abstract details into super class, such as

    private Road initializedRoad;
    private Road emptyRoad;

    @Before
    public void setUp(){
        initializedRoad = new Road("401", "Canada");
        emptyRoad = new Road();
    }

    @Test
    public void validateInitializedRoad(){
        Double expectedLength = initializedRoad.getLength();
        Integer expectedSpeedLimit = initializedRoad.getSpeedLimit();

        Double actualLength = 0.0;
        Integer actualSpeedLimit = 0;

        Assert.assertEquals(actualSpeedLimit, expectedSpeedLimit);
        Assert.assertEquals(actualLength, expectedLength, 0.01);
    }

    @Test
    public void validateEmptyRoad(){
        // fixed inputs - defined by user
        Double expectedLength = emptyRoad.getLength();
        Integer expectedSpeedLimit = emptyRoad.getSpeedLimit();

        Double actualLength = 0.0;
        Integer actualSpeedLimit = 0;

        Assert.assertEquals(actualSpeedLimit, expectedSpeedLimit);
        Assert.assertEquals(actualLength, expectedLength, 0.01);
    }
}
