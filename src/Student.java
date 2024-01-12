import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private final Map<LocalDate, Byte> scores = new TreeMap<>();

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Score> getScores() {
        return scores.entrySet()
                .stream().map(entry -> new Score(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public Score getScoreForDate(LocalDate date) {
        return scores.containsKey(date) ? new Score(date, scores.get(date)) : null;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", scores=" + scores +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Byte addScore(LocalDate date, Byte score) {
        return scores.putIfAbsent(date, score);
    }

    public Byte replaceScore(LocalDate date, Byte newScore) {
        return scores.computeIfPresent(date, (date1, score) -> newScore);
    }
}
