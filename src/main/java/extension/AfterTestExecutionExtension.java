package extension;

import core.db.TestsHistoryRepository;
import core.reporting.AllureReportManager;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.time.LocalDateTime;

import static core.configs.ConfigurationManager.config;

public class AfterTestExecutionExtension implements AfterTestExecutionCallback {
    private final TestsHistoryRepository testsHistoryRepository = new TestsHistoryRepository();

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {
            AllureReportManager.screenshot();
        }
        String status = context.getExecutionException().isPresent() ? "FAILED" : "SUCCESS";

        if (config().dbJdbcUrl() != null) {
            testsHistoryRepository.addTestExecutionData(context.getTestMethod().get().getName(),
                    LocalDateTime.now(),
                    config().browser(),
                    status);
        }
    }
}
