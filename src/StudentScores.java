import exceptions.StudentNotFoundException;

import java.io.*;
import java.util.*;

public class StudentScores {
    private Map<String, Student> students = new TreeMap<>();

    private static final StudentScores INSTANCE = new StudentScores();

    private StudentScores() {
    }

    public static StudentScores getInstance() {
        return INSTANCE;
    }

    public Student addStudent(Student student) {
        students.putIfAbsent(student.getName(), student);
        return student;
    }

    public Student removeStudent(String studentName) {
        return students.remove(studentName);
    }

    public Student findStudent(String studentName) {
        return Optional.ofNullable(students.get(studentName))
                .orElseThrow(() -> new StudentNotFoundException(String.format("Student name: %s not found", studentName)));
    }

    public List<Score> getAllScoresByStudent(String studentName) {
        return findStudent(studentName).getScores();
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    @SuppressWarnings("unchecked")
    public void loadScores(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream oos = new ObjectInputStream(fis)) {
            students = (Map<String, Student>) oos.readObject();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Не удалось загрузить файл данных: " + e.getMessage());;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveScores(String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(students);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Не удалось сохранить файл данных: " + e.getMessage());;
        }
    }

}
