import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import com.code_intelligence.jazzer.junit.FuzzTest;
import org.codeintelligence.processing.Analyzer;


public class AnalyzerFuzzTest {

    @FuzzTest
    void fuzzComplexProcessing(FuzzedDataProvider data) {
        Analyzer underTest = new Analyzer();

        underTest.complexProcessing(data.consumeInts(10));
    }

}
