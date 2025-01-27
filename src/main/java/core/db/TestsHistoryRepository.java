package core.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class TestsHistoryRepository {
    Logger LOGGER = LoggerFactory.getLogger(TestsHistoryRepository.class);

    public void addTestExecutionData(String testName, LocalDateTime executionDate, String browser, String status) {
        String insertQuery = "INSERT INTO tests_history (test_name, execution_date, browser, status) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection = DataBasePoolFactory.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, testName);
            preparedStatement.setTimestamp(2, java.sql.Timestamp.valueOf(executionDate));
            preparedStatement.setString(3, browser);
            preparedStatement.setString(4, status);

            LOGGER.info("Attempt to insert the row: {}", preparedStatement);
            LOGGER.info("done");
        } catch (Exception e) {
            LOGGER.error("Error inserting test execution data", e);
        }
    }

    public void createTableIfNotExists() {
        String createTableQuery = """
                create TABLE tests_history(
                                              id INT AUTO_INCREMENT PRIMARY KEY,
                                              test_name VARCHAR(100) NOT NULL,
                                              execution_date DATETIME NOT NULL,
                                              browser VARCHAR(10) NOT NULL,
                                              status VARCHAR(7));""";
        try (Connection connection = DataBasePoolFactory.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createTableQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
