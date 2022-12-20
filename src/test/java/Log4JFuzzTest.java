import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import com.code_intelligence.jazzer.junit.FuzzTest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.DefaultConfigurationBuilder;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.status.StatusLogger;
import org.junit.Before;

public class Log4JFuzzTest {
    /**
     * Reproduces the work found in jazzer/examples to find the Log4J JMDI lookup.
     * Finding is located in .cifuzz-findings/zen-baboon/
     */

    private static Logger log = LogManager.getLogger(Log4JFuzzTest.class);

    @FuzzTest
    public void test(FuzzedDataProvider data){
        log.error(data.consumeRemainingAsString());
    }

    @Before
    public void setUp(){
        // Install a logger that constructs the log message, but never prints it.
        // This noticeably increases the fuzzing performance
        DefaultConfigurationBuilder configBuilder = new DefaultConfigurationBuilder();
        configBuilder.setPackages(FuzzingAppender.class.getPackage().getName());
        AppenderComponentBuilder fuzzingAppender =
                configBuilder.newAppender("nullAppender", "FuzzingAppender");
        configBuilder.add(fuzzingAppender);
        RootLoggerComponentBuilder rootLogger = configBuilder.newRootLogger();
        rootLogger.add(configBuilder.newAppenderRef("nullAppender"));
        configBuilder.add(rootLogger);
        Configurator.reconfigure(configBuilder.build());

        // Disable logging of exceptions caught in log4j itself.
        StatusLogger.getLogger().reset();
        StatusLogger.getLogger().setLevel(Level.OFF);
    }

    @Plugin(
            name = "FuzzingAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE)
    public static class FuzzingAppender extends AbstractAppender {
        protected FuzzingAppender(String name) {
            super(name, null, PatternLayout.createDefaultLayout(), true);
        }

        @PluginFactory
        public static FuzzingAppender createAppender(@PluginAttribute("name") String name) {
            return new FuzzingAppender(name);
        }

        @Override
        public void append(LogEvent event) {
            try {
                getLayout().toByteArray(event);
            } catch (Exception ignored) {
                // Prevent exceptions from being logged to stderr.
            }
        }
    }
}
