import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Score implements Serializable  {
    private static final long serialVersionUID = 1L;

    private final LocalDate date;
    private Byte value;

    public Score(LocalDate date, Byte value) {
        this.date = date;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public Byte getValue() {
        return value;
    }

    public void setValue(Byte value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return Objects.equals(this.date, score1.date) && Objects.equals(this.value, score1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, value);
    }

    @Override
    public String toString() {
        return "{ Дата: " + date + ", Оценка: " + value + " }";
    }
}
