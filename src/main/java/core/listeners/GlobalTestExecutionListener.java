package core.listeners;
import core.db.DataBasePoolFactory;
import core.db.TestsHistoryRepository;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;

public class GlobalTestExecutionListener implements TestExecutionListener {

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        DataBasePoolFactory.closeDataSource();
    }
}
