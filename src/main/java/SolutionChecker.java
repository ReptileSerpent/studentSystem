import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class SolutionChecker
{
    public static void checkSolution(int studentId, int task, String filePath)
    {
        var testIdsAndPaths = new ArrayList<Tuple<Integer, String>>();

        //get tests
        var connectionTuple = DatabaseManager.tryGetConnection();
        if (connectionTuple.item1)
        {
            try (var connection = connectionTuple.item2)
            {
                try (var statement = connection.createStatement())
                {

                    statement.executeQuery("use test_database;");
                    var resultSet = statement.executeQuery("SELECT Id, TestFilePath FROM Test WHERE Task_id = " + task + ";");
                    while (resultSet.next())
                    {
                        testIdsAndPaths.add(new Tuple<>(resultSet.getInt("Id"), resultSet.getString("TestFilePath")));
                    }
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        var programWithTestIdsAndTexts = new ArrayList<Tuple<Integer, String>>();
        try
        {
            var fileText = Files.readString(Path.of(filePath));
            for (var testIdAndPath : testIdsAndPaths)
            {
                var testId = testIdAndPath.item1;
                var testText = Files.readString(Path.of(testIdAndPath.item2));
                programWithTestIdsAndTexts.add(new Tuple<>(testId, fileText + "\n\n" + testText));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        for (var programWithTestIdAndText : programWithTestIdsAndTexts)
        {
            var testId = programWithTestIdAndText.item1;
            var programWithTestText = programWithTestIdAndText.item2;
            Object result = null;

            try
            {
                ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
                ScriptEngine engine = scriptEngineManager.getEngineByName("JavaScript");

                result = engine.eval(programWithTestText);
                System.out.println("Test result: " + result);
            }
            catch (ScriptException e)
            {
                e.printStackTrace();
            }

            var secondConnectionTuple = DatabaseManager.tryGetConnection();
            if (secondConnectionTuple.item1)
            {
                try (var connection = secondConnectionTuple.item2)
                {
                    try (var statement = connection.createStatement())
                    {
                        statement.executeQuery("use test_database;");
                        var maxId = 0;
                        var resultSet = statement.executeQuery("SELECT MAX(Id) FROM StudentTestResult;");
                        while (resultSet.next())
                            maxId = resultSet.getInt("max(id)");

                        var id = maxId + 1;
                        statement.executeQuery("use test_database;");
                        statement.executeQuery("INSERT INTO StudentTestResult VALUES (" + id +
                                ", " + studentId +
                                ", " + testId +
                                ", '" + result + "'" + ");");
                        System.out.println("Test addition successful");
                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
