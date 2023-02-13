import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import com.code_intelligence.jazzer.junit.FuzzTest;
import org.codeintelligence.processing.SpeedAnalyzer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class SpeedAnalyzerTest {

    @Test
    public void checkSizes(){
        SpeedAnalyzer underTest = new SpeedAnalyzer();

        List expected = Arrays.asList(0,1,2);
        List actual = underTest.speedLimitConverter(new int[]{0,1,2});

        Assert.assertEquals(expected.size(), actual.size());
    }

    @FuzzTest
    void fuzzComplexProcessing(FuzzedDataProvider data) {
        SpeedAnalyzer underTest = new SpeedAnalyzer();

        underTest.speedLimitConverter(data.consumeInts(10));
    }

}
