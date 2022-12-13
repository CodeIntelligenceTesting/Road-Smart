import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import com.code_intelligence.jazzer.junit.FuzzTest;
import org.codeintelligence.database.InformationDatabase;
import org.codeintelligence.models.Road;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InformationDatabaseFuzzTest {

    private InformationDatabase db;

    private InformationDatabase initializeDatabase(){
        InformationDatabase db = new InformationDatabase();
        db.connect();
        return db;
    }

    private String fuzzedString(FuzzedDataProvider data){
        return data.consumeString(10);
    }

    @Test // a unit test to verify the functionality of inserting data
    public void insertDataUnitTest(){ 
        db = initializeDatabase();
        Road testRoad = new Road("Germany"); // use static data for object creation
        Boolean result = db.insertRoadData(testRoad); // Execute vulnerable function without errors
        Assert.assertTrue(result); // assert that the provided test case returns true
    }

    @FuzzTest // the fuzzer generates inputs in data based on a unit's run-time behaviour
    public void insertDataFuzzTest(FuzzedDataProvider data) {
        db = initializeDatabase(); 
        Road testRoad = new Road(data.consumeRemainingAsString()); // use generated data for object creation
        db.insertRoadData(testInput)); // Execute vulnerable function and detect an SQL injection + RCE
        Assert.assertNotNull(db); // fuzz testing will find inputs violating this assertion
    }
}
