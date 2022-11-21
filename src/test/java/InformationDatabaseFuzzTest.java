import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import com.code_intelligence.jazzer.junit.FuzzTest;
import org.codeintelligence.database.InformationDatabase;
import org.codeintelligence.models.Road;

class InformationDatabaseFuzzTest {

    @FuzzTest
    void myFuzzTest(FuzzedDataProvider data) {
        Road testRoad;
        testRoad = new Road(data.consumeAsciiString(10), data.consumeString(10));

        InformationDatabase db = new InformationDatabase();

        db.insertRoadData(testRoad);
    }
}
