import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import com.code_intelligence.jazzer.junit.FuzzTest;
import org.codeintelligence.processing.Analyzer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class AnalyzerTest {

    @Test
    public void checkSizes(){
        Analyzer underTest = new Analyzer();

        List expected = Arrays.asList(0,1,2);
        List actual = underTest.complexProcessing(new int[]{0,1,2});

        Assert.assertEquals(expected.size(), actual.size());
    }

    @FuzzTest
    void fuzzComplexProcessing(FuzzedDataProvider data) {
        Analyzer underTest = new Analyzer();

        underTest.complexProcessing(data.consumeInts(10));
    }

}
