import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

public class StudentHomeForm {
    public JPanel studentHomePanel;
    public int studentId;
    private JTabbedPane tabbedPane1;
    private JPanel coursesPanel;
    private JPanel tasksPanel;
    private JPanel gradesPanel;
    private JButton updateCoursesButton;
    private JList coursesList;
    private JList tasksList;
    private JButton updateTasksButton;
    private JTextField gradeTaskToAddField;
    private JTextField gradeFilePathToAddField;
    private JButton addGradeButton;
    private JTextField gradeIdToAddField;
    private JList gradesList;
    private JButton updateGradesButton;
    private JPanel testResultsPanel;
    private JList testResultsList;
    private JButton updateTestResultsButton;

    public StudentHomeForm(int studentId)
    {
        this.studentId = studentId;

        updateCourses();
        updateTasks();
        updateTestResults();
        updateGrades();

        updateCoursesButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                updateCourses();
            }
        });

        updateTasksButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                updateTasks();
            }
        });

        updateTestResultsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                updateTestResults();
            }
        });

        updateGradesButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                updateGrades();
            }
        });

        addGradeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                var connectionTuple = DatabaseManager.tryGetConnection();
                if (connectionTuple.item1)
                {
                    try (var connection = connectionTuple.item2)
                    {
                        try (var statement = connection.createStatement())
                        {
                            var id = gradeIdToAddField.getText();
                            var student = studentId;
                            var task = gradeTaskToAddField.getText();
                            var filePath = gradeFilePathToAddField.getText();

                            statement.executeQuery("use test_database;");
                            statement.executeQuery("INSERT INTO StudentTaskTeacherResult VALUES (" + id +
                                    ", " + student +
                                    ", " + task +
                                    ", 0 " +
                                    ", '" + filePath + "'" + ");");
                            System.out.println("Grade information addition successful");

                            SolutionChecker.checkSolution(studentId, Integer.parseInt(task), filePath);
                            updateTestResults();
                            updateGrades();
                        }
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void updateCourses()
    {
        System.out.println("Updating courses");
        var connectionTuple = DatabaseManager.tryGetConnection();
        if (connectionTuple.item1)
        {
            try (var connection = connectionTuple.item2)
            {
                try (var statement = connection.createStatement())
                {
                    statement.executeQuery("use test_database;");
                    var resultSet = statement.executeQuery("SELECT * FROM Course;");

                    var coursesListModel = new DefaultListModel();
                    while (resultSet.next())
                    {
                        var resultId = resultSet.getInt("Id");
                        var resultName = resultSet.getString("Name");
                        var resultOwner = resultSet.getInt("Owner_id");
                        var resultStringBuilder = new StringBuilder().append(resultId).append("; ")
                                .append(resultName).append("; ")
                                .append(resultOwner).append("; ");
                        var resultString = resultStringBuilder.toString();
                        System.out.println(resultString);
                        coursesListModel.addElement(resultString);
                    }

                    coursesList.setModel(coursesListModel);
                    coursesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    coursesList.setSelectedIndex(0);
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
            System.out.println("Failed to get database connection");
    }

    private void updateTasks()
    {
        System.out.println("Updating tasks");
        var connectionTuple = DatabaseManager.tryGetConnection();
        if (connectionTuple.item1)
        {
            try (var connection = connectionTuple.item2)
            {
                try (var statement = connection.createStatement())
                {
                    statement.executeQuery("use test_database;");
                    var resultSet = statement.executeQuery("SELECT * FROM Task;");

                    var tasksListModel = new DefaultListModel();
                    while (resultSet.next())
                    {
                        var resultId = resultSet.getInt("Id");
                        var resultTitle = resultSet.getString("Title");
                        var resultDescription = resultSet.getString("Description");
                        var resultMaxResult = resultSet.getInt("MaxResult");
                        var resultCourse = resultSet.getInt("Course_id");
                        var resultStringBuilder = new StringBuilder().append(resultId).append("; ")
                                .append(resultTitle).append("; ")
                                .append(resultDescription).append("; ")
                                .append(resultMaxResult).append("; ")
                                .append(resultCourse).append("; ");
                        var resultString = resultStringBuilder.toString();
                        System.out.println(resultString);
                        tasksListModel.addElement(resultString);
                    }

                    tasksList.setModel(tasksListModel);
                    tasksList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    tasksList.setSelectedIndex(0);
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
            System.out.println("Failed to get database connection");
    }

    private void updateTestResults()
    {
        System.out.println("Updating test results");
        var connectionTuple = DatabaseManager.tryGetConnection();
        if (connectionTuple.item1)
        {
            try (var connection = connectionTuple.item2)
            {
                try (var statement = connection.createStatement())
                {
                    statement.executeQuery("use test_database;");
                    var resultSet = statement.executeQuery("SELECT * FROM StudentTestResult WHERE Student_id = " + studentId + ";");

                    var testResultsListModel = new DefaultListModel();
                    while (resultSet.next())
                    {
                        var resultId = resultSet.getInt("Id");
                        var resultStudent = resultSet.getInt("Student_id");
                        var resultTest = resultSet.getInt("Test_id");
                        var resultTestState = resultSet.getString("TestState");
                        var resultStringBuilder = new StringBuilder().append(resultId).append("; ")
                                .append(resultStudent).append("; ")
                                .append(resultTest).append("; ")
                                .append(resultTestState).append("; ");
                        var resultString = resultStringBuilder.toString();
                        System.out.println(resultString);
                        testResultsListModel.addElement(resultString);
                    }

                    testResultsList.setModel(testResultsListModel);
                    testResultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    testResultsList.setSelectedIndex(0);
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
            System.out.println("Failed to get database connection");
    }

    private void updateGrades()
    {
        System.out.println("Updating grades");
        var connectionTuple = DatabaseManager.tryGetConnection();
        if (connectionTuple.item1)
        {
            try (var connection = connectionTuple.item2)
            {
                try (var statement = connection.createStatement())
                {
                    statement.executeQuery("use test_database;");
                    var resultSet = statement.executeQuery("SELECT * FROM StudentTaskTeacherResult WHERE Student_id = " + studentId + ";");

                    var gradesListModel = new DefaultListModel();
                    while (resultSet.next())
                    {
                        var resultId = resultSet.getInt("Id");
                        var resultStudent = resultSet.getInt("Student_id");
                        var resultTask = resultSet.getInt("Task_id");
                        var resultTeacherResult = resultSet.getInt("TeacherResult");
                        var resultFilePath = resultSet.getString("StudentFilePath");
                        var resultStringBuilder = new StringBuilder().append(resultId).append("; ")
                                .append(resultStudent).append("; ")
                                .append(resultTask).append("; ")
                                .append(resultTeacherResult).append("; ")
                                .append(resultFilePath).append("; ");
                        var resultString = resultStringBuilder.toString();
                        System.out.println(resultString);
                        gradesListModel.addElement(resultString);
                    }

                    gradesList.setModel(gradesListModel);
                    gradesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    gradesList.setSelectedIndex(0);
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
            System.out.println("Failed to get database connection");
    }


}
