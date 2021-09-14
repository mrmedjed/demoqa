import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        monochrome = true,
        tags = "@bug"
)
/**
 * Currently, the framework is set to run only features/scenarios annotated with @bug.
 * If you want to run all scenarios, just remove the tags property from @CucumberOptions block above.
 * If you want to run specific features/scenarios, make sure you add the desired tag right above feature/scenario in the feature file.
 * To run tests, just run this class.
 */
public class TestRunner {

    }
