import exceptions.StudentNotFoundException;

import java.time.LocalDate;
import java.util.List;

import static utils.ReadInput.*;

public class Main {
    private static final String DATA_FILE_NAME = "scores.dat";
    private static final String START_MENU_CHOICE = "" +
            "Выберите действие:\n" +
            "\t1. Загрузить данные из файла\n" +
            "\t2. Создать новый файл\n" +
            "\t3. Выход\n" +
            "Ваш выбор: ";
    private static final String MAIN_MENU_CHOICE = "" +
            "a. Добавьте нового ученика\n" +
            "b. Удалите ученика\n" +
            "c. Добавить оценку ученика\n" +
            "d. Обновить оценку ученика\n" +
            "e. Просмотр оценок конкретного учащегося\n" +
            "f. Просмотр оценок всех учащихся\n" +
            "z. Выход\n" +
            "Ваш выбор: ";

    public static void main(String[] args) {
        System.out.println("***** Добро пожаловать в систему управления оценками *****\n");

        StudentScores studentScores = StudentScores.getInstance();
        int choice = readInt(START_MENU_CHOICE);
        switch (choice) {
            case 1:
                studentScores.loadScores(DATA_FILE_NAME);
                mainMenuDialog(studentScores);
                break;
            case 2:
                mainMenuDialog(studentScores);
                break;
            case 3:
                break;
        }
    }

    private static void mainMenuDialog(StudentScores studentScores) {
        char choice;
        String studentName;
        do {
            System.out.println();
            choice = readChar(MAIN_MENU_CHOICE);
            System.out.println();
            switch (choice) {
                case 'a':
                    System.out.println("Добавление нового ученика");
                    studentName = readString("   Укажите имя нового ученика: ");
                    Student newStudent = studentScores.addStudent(new Student(studentName));
                    if (newStudent != null) {
                        System.out.println("Ученик добавлен: " + newStudent);
                    }
                    break;
                case 'b':
                    System.out.println("Удаление ученика");
                    studentName = readString("   Укажите имя ученика: ");
                    Student removedStudent = studentScores.removeStudent(studentName);
                    if (removedStudent == null) {
                        System.out.println("Студент не найден: " + studentName);
                    } else {
                        System.out.println("Студент удален: " + removedStudent);

                    }
                    break;
                case 'c':
                    System.out.println("Добавление оценки ученика");
                    studentName = readString("   Укажите имя ученика: ");
                    try {
                        Student student = studentScores.findStudent(studentName);
                        byte value = readByte("Введите оценку ученика: ", 1, 5);
                        LocalDate date = readDate("Укажите дату оценки: ");
                        Score score = new Score(date, value);
                        if (student.addScore(date, value) == null) {
                            System.out.println("Оценка добавлена: " + score);
                            System.out.println(student);
                        }
                    } catch (StudentNotFoundException e) {
                        System.out.println("Студент не найден: " + studentName);
                    }
                    break;
                case 'd':
                    System.out.println("Обновление оценки ученика");
                    studentName = readString("\tУкажите имя ученика: ");
                    try {
                        Student student = studentScores.findStudent(studentName);
                        LocalDate date = readDate("Укажите дату для оценки, которую необходимо обновить: ");
                        byte value = readByte("\tВведите новую оценку ученика: ", 1, 5);
                        Byte newValue = student.replaceScore(date, value);
                        if (newValue != null) {
                            System.out.println("Оценка обновлена, новая оценка: " + student.getScoreForDate(date));
                            System.out.println(student);
                        } else {
                            System.out.println("Для указанной даты оценка не найдена!");
                        }
                    } catch (StudentNotFoundException e) {
                        System.out.println("Студент не найден: " + studentName);
                    }
                    break;
                case 'e':
                    System.out.println("Просмотр оценок конкретного учащегося");
                    studentName = readString("   Укажите имя ученика: ");
                    try {
                        Student student = studentScores.findStudent(studentName);
                        System.out.println(student);
                    } catch (StudentNotFoundException e) {
                        System.out.println("Студент не найден: " + studentName);
                    }
                    break;
                case 'f':
                    System.out.println("Просмотр оценок всех учащихся");
                    List<Student> allStudents = studentScores.getAllStudents();
                    if (allStudents.isEmpty()) {
                        System.out.println("Список учеников пуст!");
                    } else {
                        allStudents.forEach(System.out::println);
                    }
                    break;
                case 'z':
                    // сохраняем файл данных перед выходом
                    System.out.println("Выход из системы и сохраняем данные ...");
                    studentScores.saveScores(DATA_FILE_NAME);
            }
        } while (choice != 'z');
    }

}