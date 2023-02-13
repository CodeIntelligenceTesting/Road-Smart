import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import com.code_intelligence.jazzer.junit.FuzzTest;
import org.codeintelligence.database.InformationDatabase;
import org.codeintelligence.models.Road;
import org.junit.Assert;
import org.junit.Test;

public class InformationDatabaseFuzzTest {

    private InformationDatabase db;

    private InformationDatabase initializeDatabase(){
        InformationDatabase db = new InformationDatabase();
        db.connect();
        return db;
    }

    @Test // a unit test to verify the functionality of inserting data
    public void insertDataUnitTest(){ 
        db = initializeDatabase();
        Road testRoad = new Road("Canada"); // use static data for object creation
        Boolean result = db.insertRoadData(testRoad); // execute vulnerable function without errors
        Assert.assertTrue(result); // assert that the provided test case returns true
    }

    @FuzzTest // the fuzzer generates inputs in data based on a unit's run-time behaviour
    public void insertDataFuzzTest(FuzzedDataProvider data) {
        db = initializeDatabase(); 
        Road testRoad = new Road(data.consumeRemainingAsString()); // use generated data for object creation
        Boolean result = db.insertRoadData(testRoad); // execute vulnerable function and detect an SQL injection + RCE
        Assert.assertTrue(result); // fuzz testing will find inputs violating this assertion
    }
}
