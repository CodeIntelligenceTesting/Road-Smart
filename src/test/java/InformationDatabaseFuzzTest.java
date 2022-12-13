import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import com.code_intelligence.jazzer.junit.FuzzTest;
import org.codeintelligence.database.InformationDatabase;
import org.codeintelligence.models.Road;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InformationDatabaseFuzzTest {

    private InformationDatabase db;
    private Road testRoad;

    private InformationDatabase initializeDatabase(){
        InformationDatabase db = new InformationDatabase();
        db.connect();
        return db;
    }

    private String fuzzedString(FuzzedDataProvider data){
        return data.consumeString(10);
    }

    @Test
    public void insertDataUnitTest(){ 
        // a unit test to verify the functionality of inserting data
        db = initializeDatabase();
        testRoad = new Road("Germany");

        Boolean result = db.insertRoadData(testRoad);

        Assert.assertTrue(result);
    }

    @FuzzTest
    public void insertDataFuzzTest(FuzzedDataProvider data) {
        // here we use the FuzzedDataProvider to generate fuzzed values for testing
        db = initializeDatabase();
        String testInput = fuzzedString(data);

        db.insertRoadData(new Road(testInput)); // here is a SQL injection in the source
        
        Assert.assertNotNull(db);
    }
}
