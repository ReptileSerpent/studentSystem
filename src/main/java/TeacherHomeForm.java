import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

public class TeacherHomeForm
{
    public JPanel teacherHomePanel;
    private JTabbedPane tabbedPane1;
    private JPanel usersPanel;
    private JPanel groupsPanel;
    private JPanel coursesPanel;
    private JPanel mappingsPanel;
    private JPanel tasksPanel;
    private JPanel testsPanel;
    private JPanel testResultsPanel;
    private JPanel gradesPanel;
    private JTextField usersList1;
    private JButton updateUsersButton;
    private JButton changeGroupByIdButton;
    private JTextField userIdToAddField;
    private JTextField userStudentsGroupToAddField;
    private JList groupsList;
    private JButton updateGroupsButton;
    private JButton addGroupButton;
    private JTextField groupIdToAddField;
    private JTextField groupTitleToAddField;
    private JTextField groupOwnerToAddField;
    private JTextField courseNameToAddField;
    private JTextField courseOwnerToAddField;
    private JButton updateCoursesButton;
    private JButton addCourseButton;
    private JTextField courseIdToAddField;
    private JList coursesList;
    private JTextField mappingCourseToAddField;
    private JTextField mappingGroupToAddField;
    private JList mappingsList;
    private JButton addMappingButton;
    private JTextField mappingIdToAddField;
    private JTextField taskDescriptionToAddField;
    private JTextField taskMaxResultToAddField;
    private JTextField taskCourseToAddField;
    private JList tasksList;
    private JButton updateTasksButton;
    private JButton addTaskButton;
    private JTextField taskIdToAddField;
    private JTextField taskTitleToAddField;
    private JButton updateMappingsButton;
    private JList testsList;
    private JTextField testTitleToAddField;
    private JTextField testTaskToAddField;
    private JTextField testTestFilePathField;
    private JButton updateTestsButton;
    private JButton addTestButton;
    private JTextField testIdToAddField;
    private JList testResultsList;
    private JButton updateTestResultsButton;
    private JList gradesList;
    private JButton updateGradesButton;
    private JButton addGradeButton;
    private JButton viewFileButton;
    private JTextField gradeFilePathToViewField;
    private JTextField gradeIdToAddField;
    private JTextField gradeGradeToAddField;
    private JTextArea fileViewTextArea;
    private JList usersList;
    private JScrollPane fileViewScrollPane;

    public TeacherHomeForm()
    {
        updateUsers();
        updateGroups();
        updateCourses();
        updateMappings();
        updateTasks();
        updateTests();
        updateTestResults();
        updateGrades();

        updateUsersButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                updateUsers();
            }
        });

        changeGroupByIdButton.addActionListener(new ActionListener()
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
                            var id = userIdToAddField.getText();
                            var studentsgroup = userStudentsGroupToAddField.getText();

                            statement.executeQuery("use test_database;");
                            statement.executeQuery("UPDATE User SET StudentsGroup_id = " + studentsgroup + " WHERE id=" + id + ";");
                            System.out.println("User group change successful");
                            updateUsers();
                        }
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

        updateGroupsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                updateGroups();
            }
        });

        addGroupButton.addActionListener(new ActionListener()
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
                            var id = groupIdToAddField.getText();
                            var title = groupTitleToAddField.getText();
                            var owner = groupOwnerToAddField.getText();

                            statement.executeQuery("use test_database;");
                            statement.executeQuery("INSERT INTO StudentsGroup VALUES (" + id +
                                    ", '" + title + "'" +
                                    ", " + owner + ");");
                            System.out.println("Group addition successful");
                            updateGroups();
                        }
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

        updateCoursesButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                updateCourses();
            }
        });

        addCourseButton.addActionListener(new ActionListener()
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
                            var id = courseIdToAddField.getText();
                            var name = courseNameToAddField.getText();
                            var owner = courseOwnerToAddField.getText();

                            statement.executeQuery("use test_database;");
                            statement.executeQuery("INSERT INTO Course VALUES (" + id +
                                    ", '" + name + "'" +
                                    ", " + owner + ");");
                            System.out.println("Course addition successful");
                            updateCourses();
                        }
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

        updateMappingsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                updateMappings();
            }
        });

        addMappingButton.addActionListener(new ActionListener()
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
                            var id = mappingIdToAddField.getText();
                            var course = mappingCourseToAddField.getText();
                            var group = mappingGroupToAddField.getText();

                            statement.executeQuery("use test_database;");
                            statement.executeQuery("INSERT INTO StudentsGroupCourse VALUES (" + id +
                                    ", '" + course + "'" +
                                    ", " + group + ");");
                            System.out.println("Mapping addition successful");
                            updateMappings();
                        }
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
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

        addTaskButton.addActionListener(new ActionListener()
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
                            var id = taskIdToAddField.getText();
                            var title = taskTitleToAddField.getText();
                            var description = taskDescriptionToAddField.getText();
                            var maxResult = taskMaxResultToAddField.getText();
                            var course = taskCourseToAddField.getText();

                            statement.executeQuery("use test_database;");
                            statement.executeQuery("INSERT INTO Task VALUES (" + id +
                                    ", '" + title + "'" +
                                    ", '" + description + "'" +
                                    ", " + maxResult +
                                    ", " + course + ");");
                            System.out.println("Task addition successful");
                            updateTasks();
                        }
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

        updateTestsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                updateTests();
            }
        });

        addTestButton.addActionListener(new ActionListener()
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
                            var id = testIdToAddField.getText();
                            var title = testTitleToAddField.getText();
                            var task = testTaskToAddField.getText();
                            var testFilePath = testTestFilePathField.getText();

                            statement.executeQuery("use test_database;");
                            statement.executeQuery("INSERT INTO Test VALUES (" + id +
                                    ", '" + title + "'" +
                                    ", " + task +
                                    ", '" + testFilePath + "'" + ");");
                            System.out.println("Test addition successful");
                            updateTests();
                        }
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
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
                            var grade = gradeGradeToAddField.getText();

                            statement.executeQuery("use test_database;");
                            statement.executeQuery("UPDATE StudentTaskTeacherResult SET TeacherResult = " + grade + " WHERE id=" + id + ";");
                            System.out.println("Grade result change successful");
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

        viewFileButton.addActionListener(new ActionListener()
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

                            statement.executeQuery("use test_database;");
                            var resultSet = statement.executeQuery("SELECT StudentFilePath FROM StudentTaskTeacherResult WHERE id = " + id + ";");
                            while (resultSet.next())
                            {
                                var resultFilePath = resultSet.getString("StudentFilePath");
                                var fileText = Files.readString(Path.of(resultFilePath));
                                fileViewTextArea.setText(fileText);
                            }
                            System.out.println("File view request successful");
                        }
                    }
                    catch (SQLException | IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void updateUsers()
    {
        var connectionTuple = DatabaseManager.tryGetConnection();
        if (connectionTuple.item1)
        {
            try (var connection = connectionTuple.item2)
            {
                try (var statement = connection.createStatement())
                {
                    statement.executeQuery("use test_database;");
                    var resultSet = statement.executeQuery("SELECT * FROM User WHERE Role_id = 3;");

                    var usersListModel = new DefaultListModel();
                    while (resultSet.next())
                    {
                        var resultId = resultSet.getInt("Id");
                        var resultEmail = resultSet.getString("Email");
                        var resultRole = resultSet.getInt("Role_id");
                        var resultStudentsGroup = resultSet.getInt("StudentsGroup_id");
                        var resultName = resultSet.getString("Name");
                        var resultStringBuilder = new StringBuilder().append(resultId).append("; ")
                                .append(resultEmail).append("; ")
                                .append(resultStudentsGroup).append("; ")
                                .append(resultRole).append("; ")
                                .append(resultName);
                        var resultString = resultStringBuilder.toString();
                        System.out.println(resultString);
                        usersListModel.addElement(resultString);
                    }

                    usersList.setModel(usersListModel);
                    usersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    usersList.setSelectedIndex(0);
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

    private void updateGroups()
    {
        System.out.println("Updating groups");
        var connectionTuple = DatabaseManager.tryGetConnection();
        if (connectionTuple.item1)
        {
            try (var connection = connectionTuple.item2)
            {
                try (var statement = connection.createStatement())
                {
                    statement.executeQuery("use test_database;");
                    var resultSet = statement.executeQuery("SELECT * FROM StudentsGroup;");

                    var studentGroupsListModel = new DefaultListModel();
                    while (resultSet.next())
                    {
                        var resultId = resultSet.getInt("Id");
                        var resultTitle = resultSet.getString("Title");
                        var resultOwner = resultSet.getInt("Owner_id");
                        var resultStringBuilder = new StringBuilder().append(resultId).append("; ")
                                .append(resultTitle).append("; ")
                                .append(resultOwner).append("; ");
                        var resultString = resultStringBuilder.toString();
                        System.out.println(resultString);
                        studentGroupsListModel.addElement(resultString);
                    }

                    groupsList.setModel(studentGroupsListModel);
                    groupsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    groupsList.setSelectedIndex(0);
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

    private void updateMappings()
    {
        System.out.println("Updating mappings");
        var connectionTuple = DatabaseManager.tryGetConnection();
        if (connectionTuple.item1)
        {
            try (var connection = connectionTuple.item2)
            {
                try (var statement = connection.createStatement())
                {
                    statement.executeQuery("use test_database;");
                    var resultSet = statement.executeQuery("SELECT * FROM StudentsGroupCourse;");

                    var mappingsListModel = new DefaultListModel();
                    while (resultSet.next())
                    {
                        var resultId = resultSet.getInt("Id");
                        var resultCourse = resultSet.getInt("Course_id");
                        var resultGroup = resultSet.getInt("StudentsGroup_id");
                        var resultStringBuilder = new StringBuilder().append(resultId).append("; ")
                                .append(resultCourse).append("; ")
                                .append(resultGroup).append("; ");
                        var resultString = resultStringBuilder.toString();
                        System.out.println(resultString);
                        mappingsListModel.addElement(resultString);
                    }

                    mappingsList.setModel(mappingsListModel);
                    mappingsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    mappingsList.setSelectedIndex(0);
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

    private void updateTests()
    {
        System.out.println("Updating tests");
        var connectionTuple = DatabaseManager.tryGetConnection();
        if (connectionTuple.item1)
        {
            try (var connection = connectionTuple.item2)
            {
                try (var statement = connection.createStatement())
                {
                    statement.executeQuery("use test_database;");
                    var resultSet = statement.executeQuery("SELECT * FROM Test;");

                    var testsListModel = new DefaultListModel();
                    while (resultSet.next())
                    {
                        var resultId = resultSet.getInt("Id");
                        var resultTitle = resultSet.getString("Title");
                        var resultTask_id = resultSet.getString("Task_id");
                        var resultTestFilePath = resultSet.getString("TestFilePath");
                        var resultStringBuilder = new StringBuilder().append(resultId).append("; ")
                                .append(resultTitle).append("; ")
                                .append(resultTask_id).append("; ")
                                .append(resultTestFilePath).append("; ");
                        var resultString = resultStringBuilder.toString();
                        System.out.println(resultString);
                        testsListModel.addElement(resultString);
                    }

                    testsList.setModel(testsListModel);
                    testsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    testsList.setSelectedIndex(0);
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
                    var resultSet = statement.executeQuery("SELECT * FROM StudentTestResult;");

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
                    var resultSet = statement.executeQuery("SELECT * FROM StudentTaskTeacherResult;");

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