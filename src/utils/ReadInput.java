package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ReadInput {
    public static final DateTimeFormatter DATE_IME_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                Scanner scanner = new Scanner(System.in);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода! Введите целое число");
            }
        }
    }

    public static byte readByte(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            try {
                Scanner scanner = new Scanner(System.in);
                byte number = scanner.nextByte();
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.printf("Ошибка ввода! Введите целое число от %d до %d\n", min, max);
                }
            } catch (InputMismatchException e) {
                System.out.printf("Ошибка ввода! Введите целое число от %d до %d\n", min, max);
            }
        }
    }

    public static char readChar(String message) {
        while (true) {
            System.out.print(message);
            try {
                Scanner scanner = new Scanner(System.in);
                return scanner.next("[a-f,z]").charAt(0);
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода! Введите символ из указанных выше");
            }
        }
    }

    public static LocalDate readDate(String message) {
        while (true) {
            System.out.print(message);
            try {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.next();
                return LocalDate.parse(input, DATE_IME_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка ввода! Введите дату в формате ДД.ММ.ГГГГ");
            }
        }
    }

    public static String readString(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
