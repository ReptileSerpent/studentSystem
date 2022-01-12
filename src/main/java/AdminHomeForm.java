import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminHomeForm {
    public JPanel adminHomePanel;
    private JLabel titleField;
    private JTabbedPane tabbedPane;
    private JPanel usersPanel;
    private JPanel studentGroupsPanel;
    private JPanel coursesPanel;
    private JPanel tasksPanel;
    private JPanel testsPanel;
    private JPanel testResultsPanel;
    private JList usersList;
    private JButton updateUsersButton;
    private JButton addUserButton;
    private JButton removeUserButton;
    private JTextField userToDeleteByIdField;
    private JTextField userIdToAddField;
    private JTextField userEmailToAddField;
    private JTextField userPasswordToAddField;
    private JTextField userRoleToAddField;
    private JTextField userStudentsGroupToAddField;
    private JTextField userNameToAddField;
    private JList studentGroupsList;
    private JButton updateGroupsButton;
    private JButton addGroupButton;
    private JTextField groupIdToAddField;
    private JTextField groupOwnerToAddField;
    private JTextField groupTitleToAddField;
    private JButton deleteGroupButton;
    private JTextField groupToDeleteByIdField;
    private JPanel mappingPanel;
    private JList coursesList;
    private JButton updateCoursesButton;
    private JButton addCourseButton;
    private JTextField courseIdToAddField;
    private JTextField courseNameToAddField;
    private JTextField courseOwnerToAddField;
    private JButton deleteCourseButton;
    private JTextField courseToDeleteByIdField;
    private JList mappingsList;
    private JButton updateMappingsButton;
    private JButton deleteMappingButton;
    private JTextField mappingIdToAddField;
    private JTextField mappingCourseToAddField;
    private JButton addMappingButton;
    private JTextField mappingGroupToAddField;
    private JTextField mappingToDeleteByIdField;
    private JList tasksList;
    private JButton updateTasksButton;
    private JButton addTaskButton;
    private JTextField taskIdToAddField;
    private JTextField taskTitleToAddField;
    private JTextField taskDescriptionToAddField;
    private JButton deleteTaskButton;
    private JTextField taskToDeleteByIdField;
    private JTextField taskMaxResultToAddField;
    private JTextField taskCourseToAddField;
    private JList testsList;
    private JButton updateTestsButton;
    private JButton addTestButton;
    private JTextField testIdToAddField;
    private JTextField testTitleToAddField;
    private JTextField testTaskToAddField;
    private JTextField testTestFilePathField;
    private JButton deleteTestButton;
    private JTextField testToDeleteByIdField;
    private JPanel gradesPanel;
    private JList testResultsList;
    private JButton updateTestResultsButton;
    private JButton addTestResultButton;
    private JTextField testResultIdToAddField;
    private JTextField testResultStudentToAddField;
    private JTextField testResultTestToAddField;
    private JTextField testResultStateToAddField;
    private JButton deleteTestResultButton;
    private JTextField testResultToDeleteByIdField;
    private JList gradesList;
    private JButton updateGradesButton;
    private JButton addGradeButton;
    private JTextField gradeIdToAddField;
    private JTextField gradeStudentToAddField;
    private JTextField gradeTaskToAddField;
    private JTextField gradeGradeToAddField;
    private JTextField gradeFilePathToAddField;
    private JButton deleteGradeButton;
    private JTextField gradeToDeleteByIdField;

    public AdminHomeForm() {
        updateUsers();
        updateGroups();
        updateCourses();
        updateMappings();
        updateTasks();
        updateTests();
        updateTestResults();
        updateGrades();

        updateUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateUsers();
            }
        });

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                var connectionTuple = DatabaseManager.tryGetConnection();
                if (connectionTuple.item1) {
                    try (var connection = connectionTuple.item2) {
                        try (var statement = connection.createStatement()) {
                            var id = userIdToAddField.getText();
                            var email = userEmailToAddField.getText();
                            var password = userPasswordToAddField.getText();
                            var role = userRoleToAddField.getText();
                            var studentsgroup = userStudentsGroupToAddField.getText();
                            var name = userNameToAddField.getText();

                            statement.executeQuery("use test_database;");
                            statement.executeQuery("INSERT INTO User VALUES (" + id +
                                    ", '" + email + "'" +
                                    ", '" + password + "'" +
                                    ", " + role +
                                    ", " + studentsgroup +
                                    ", '" + name + "'" + ");");
                            System.out.println("User addition successful");
                            updateUsers();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (userToDeleteByIdField.getText().equals(""))
                    return;

                var connectionTuple = DatabaseManager.tryGetConnection();
                if (connectionTuple.item1) {
                    try (var connection = connectionTuple.item2) {
                        try (var statement = connection.createStatement()) {
                            statement.executeQuery("use test_database;");
                            statement.executeQuery("DELETE FROM User WHERE Id = " + userToDeleteByIdField.getText() + ";");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Deletion successful");
                updateUsers();
            }
        });

        updateGroupsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateGroups();
            }
        });

        addGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                var connectionTuple = DatabaseManager.tryGetConnection();
                if (connectionTuple.item1) {
                    try (var connection = connectionTuple.item2) {
                        try (var statement = connection.createStatement()) {
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
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        deleteGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (groupToDeleteByIdField.getText().equals(""))
                    return;

                var connectionTuple = DatabaseManager.tryGetConnection();
                if (connectionTuple.item1) {
                    try (var connection = connectionTuple.item2) {
                        try (var statement = connection.createStatement()) {
                            statement.executeQuery("use test_database;");
                            statement.executeQuery("DELETE FROM StudentsGroup WHERE Id = " + groupToDeleteByIdField.getText() + ";");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Deletion successful");
                updateGroups();
            }
        });

        updateCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateCourses();
            }
        });

        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                var connectionTuple = DatabaseManager.tryGetConnection();
                if (connectionTuple.item1) {
                    try (var connection = connectionTuple.item2) {
                        try (var statement = connection.createStatement()) {
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
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        deleteCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (courseToDeleteByIdField.getText().equals(""))
                    return;

                var connectionTuple = DatabaseManager.tryGetConnection();
                if (connectionTuple.item1) {
                    try (var connection = connectionTuple.item2) {
                        try (var statement = connection.createStatement()) {
                            statement.executeQuery("use test_database;");
                            statement.executeQuery("DELETE FROM Course WHERE Id = " + courseToDeleteByIdField.getText() + ";");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Deletion successful");
                updateCourses();
            }
        });

        updateMappingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateMappings();
            }
        });

        addMappingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                var connectionTuple = DatabaseManager.tryGetConnection();
                if (connectionTuple.item1) {
                    try (var connection = connectionTuple.item2) {
                        try (var statement = connection.createStatement()) {
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
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        deleteMappingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (mappingToDeleteByIdField.getText().equals(""))
                    return;

                var connectionTuple = DatabaseManager.tryGetConnection();
                if (connectionTuple.item1) {
                    try (var connection = connectionTuple.item2) {
                        try (var statement = connection.createStatement()) {
                            statement.executeQuery("use test_database;");
                            statement.executeQuery("DELETE FROM StudentsGroupCourse WHERE Id = " + mappingToDeleteByIdField.getText() + ";");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Deletion successful");
                updateMappings();
            }
        });

        updateTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateTasks();
            }
        });

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                var connectionTuple = DatabaseManager.tryGetConnection();
                if (connectionTuple.item1) {
                    try (var connection = connectionTuple.item2) {
                        try (var statement = connection.createStatement()) {
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
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        deleteTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (taskToDeleteByIdField.getText().equals(""))
                    return;

                var connectionTuple = DatabaseManager.tryGetConnection();
                if (connectionTuple.item1) {
                    try (var connection = connectionTuple.item2) {
                        try (var statement = connection.createStatement()) {
                            statement.executeQuery("use test_database;");
                            statement.executeQuery("DELETE FROM Task WHERE Id = " + taskToDeleteByIdField.getText() + ";");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Deletion successful");
                updateTasks();
            }
        });

        updateTestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateTests();
            }
        });

        addTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                var connectionTuple = DatabaseManager.tryGetConnection();
                if (connectionTuple.item1) {
                    try (var connection = connectionTuple.item2) {
                        try (var statement = connection.createStatement()) {
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
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        deleteTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (testToDeleteByIdField.getText().equals(""))
                    return;

                var connectionTuple = DatabaseManager.tryGetConnection();
                if (connectionTuple.item1) {
                    try (var connection = connectionTuple.item2) {
                        try (var statement = connection.createStatement()) {
                            statement.executeQuery("use test_database;");
                            statement.executeQuery("DELETE FROM Test WHERE Id = " + testToDeleteByIdField.getText() + ";");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Deletion successful");
                updateTests();
            }
        });

        updateTestResultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateTestResults();
            }
        });

        addTestResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                var connectionTuple = DatabaseManager.tryGetConnection();
                if (connectionTuple.item1) {
                    try (var connection = connectionTuple.item2) {
                        try (var statement = connection.createStatement()) {
                            var id = testResultIdToAddField.getText();
                            var student = testResultStudentToAddField.getText();
                            var test = testResultTestToAddField.getText();
                            var state = testResultStateToAddField.getText();

                            statement.executeQuery("use test_database;");
                            statement.executeQuery("INSERT INTO StudentTestResult VALUES (" + id +
                                    ", " + student +
                                    ", " + test +
                                    ", '" + state + "'" + ");");
                            System.out.println("Test result addition successful");
                            updateTestResults();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        deleteTestResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (testResultToDeleteByIdField.getText().equals(""))
                    return;

                var connectionTuple = DatabaseManager.tryGetConnection();
                if (connectionTuple.item1) {
                    try (var connection = connectionTuple.item2) {
                        try (var statement = connection.createStatement()) {
                            statement.executeQuery("use test_database;");
                            statement.executeQuery("DELETE FROM StudentTestResult WHERE Id = " + testResultToDeleteByIdField.getText() + ";");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Deletion successful");
                updateTestResults();
            }
        });

        updateGradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateGrades();
            }
        });

        addGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                var connectionTuple = DatabaseManager.tryGetConnection();
                if (connectionTuple.item1) {
                    try (var connection = connectionTuple.item2) {
                        try (var statement = connection.createStatement()) {
                            var id = gradeIdToAddField.getText();
                            var student = gradeStudentToAddField.getText();
                            var task = gradeTaskToAddField.getText();
                            var grade = gradeGradeToAddField.getText();
                            var filePath = gradeFilePathToAddField.getText();

                            statement.executeQuery("use test_database;");
                            statement.executeQuery("INSERT INTO StudentTaskTeacherResult VALUES (" + id +
                                    ", " + student +
                                    ", " + task +
                                    ", " + grade +
                                    ", '" + filePath + "'" + ");");
                            System.out.println("Test result addition successful");
                            updateGrades();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        deleteGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (gradeToDeleteByIdField.getText().equals(""))
                    return;

                var connectionTuple = DatabaseManager.tryGetConnection();
                if (connectionTuple.item1) {
                    try (var connection = connectionTuple.item2) {
                        try (var statement = connection.createStatement()) {
                            statement.executeQuery("use test_database;");
                            statement.executeQuery("DELETE FROM StudentTaskTeacherResult WHERE Id = " + gradeToDeleteByIdField.getText() + ";");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Deletion successful");
                updateGrades();
            }
        });

        /*
        usersList.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent)
            {
                currentItemSelected = usersList.getSelectedIndex();
                System.out.println("Selection changed: " + currentItemSelected);
            }
        });
        */
    }

    private void updateUsers() {
        System.out.println("hello?");
        var connectionTuple = DatabaseManager.tryGetConnection();
        if (connectionTuple.item1) {
            try (var connection = connectionTuple.item2) {
                try (var statement = connection.createStatement()) {
                    statement.executeQuery("use test_database;");
                    var resultSet = statement.executeQuery("SELECT * FROM User;");

                    var usersListModel = new DefaultListModel();
                    while (resultSet.next()) {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("Failed to get database connection");
    }

    private void updateGroups() {
        System.out.println("Updating groups");
        var connectionTuple = DatabaseManager.tryGetConnection();
        if (connectionTuple.item1) {
            try (var connection = connectionTuple.item2) {
                try (var statement = connection.createStatement()) {
                    statement.executeQuery("use test_database;");
                    var resultSet = statement.executeQuery("SELECT * FROM StudentsGroup;");

                    var studentGroupsListModel = new DefaultListModel();
                    while (resultSet.next()) {
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

                    studentGroupsList.setModel(studentGroupsListModel);
                    studentGroupsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    studentGroupsList.setSelectedIndex(0);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("Failed to get database connection");
    }

    private void updateCourses() {
        System.out.println("Updating courses");
        var connectionTuple = DatabaseManager.tryGetConnection();
        if (connectionTuple.item1) {
            try (var connection = connectionTuple.item2) {
                try (var statement = connection.createStatement()) {
                    statement.executeQuery("use test_database;");
                    var resultSet = statement.executeQuery("SELECT * FROM Course;");

                    var coursesListModel = new DefaultListModel();
                    while (resultSet.next()) {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("Failed to get database connection");
    }

    private void updateMappings() {
        System.out.println("Updating mappings");
        var connectionTuple = DatabaseManager.tryGetConnection();
        if (connectionTuple.item1) {
            try (var connection = connectionTuple.item2) {
                try (var statement = connection.createStatement()) {
                    statement.executeQuery("use test_database;");
                    var resultSet = statement.executeQuery("SELECT * FROM StudentsGroupCourse;");

                    var mappingsListModel = new DefaultListModel();
                    while (resultSet.next()) {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("Failed to get database connection");
    }

    private void updateTasks() {
        System.out.println("Updating tasks");
        var connectionTuple = DatabaseManager.tryGetConnection();
        if (connectionTuple.item1) {
            try (var connection = connectionTuple.item2) {
                try (var statement = connection.createStatement()) {
                    statement.executeQuery("use test_database;");
                    var resultSet = statement.executeQuery("SELECT * FROM Task;");

                    var tasksListModel = new DefaultListModel();
                    while (resultSet.next()) {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("Failed to get database connection");
    }

    private void updateTests() {
        System.out.println("Updating tests");
        var connectionTuple = DatabaseManager.tryGetConnection();
        if (connectionTuple.item1) {
            try (var connection = connectionTuple.item2) {
                try (var statement = connection.createStatement()) {
                    statement.executeQuery("use test_database;");
                    var resultSet = statement.executeQuery("SELECT * FROM Test;");

                    var testsListModel = new DefaultListModel();
                    while (resultSet.next()) {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("Failed to get database connection");
    }

    private void updateTestResults() {
        System.out.println("Updating test results");
        var connectionTuple = DatabaseManager.tryGetConnection();
        if (connectionTuple.item1) {
            try (var connection = connectionTuple.item2) {
                try (var statement = connection.createStatement()) {
                    statement.executeQuery("use test_database;");
                    var resultSet = statement.executeQuery("SELECT * FROM StudentTestResult;");

                    var testResultsListModel = new DefaultListModel();
                    while (resultSet.next()) {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("Failed to get database connection");
    }

    private void updateGrades() {
        System.out.println("Updating grades");
        var connectionTuple = DatabaseManager.tryGetConnection();
        if (connectionTuple.item1) {
            try (var connection = connectionTuple.item2) {
                try (var statement = connection.createStatement()) {
                    statement.executeQuery("use test_database;");
                    var resultSet = statement.executeQuery("SELECT * FROM StudentTaskTeacherResult;");

                    var gradesListModel = new DefaultListModel();
                    while (resultSet.next()) {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("Failed to get database connection");
    }

    public static void main(String[] args) {
    }

}
