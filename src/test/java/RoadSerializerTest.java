import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import com.code_intelligence.jazzer.junit.FuzzTest;
import org.codeintelligence.models.Road;

import java.io.ByteArrayInputStream;
import java.io.IOException;

class RoadSerializerTest {
    @FuzzTest
    void myFuzzTest(FuzzedDataProvider data) throws IOException {
        Road testRoad = new Road();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(data.consumeBytes(8));
        testRoad.deserialize(inputStream);
    }
}
