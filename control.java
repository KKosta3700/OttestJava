import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;


public class control {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные: (Фамилия Имя Отчество; Дата_Рождения; Номер_Телефона(без +); Пол ():");

        String input = scanner.nextLine();

        String[] data = input.split(" ");


        if (data.length != 6) {
            System.err.println("Ошибка: Вы ввели неверное количество данных.");
            return;
        }

        try {
            String surname = data[0];
            String name = data[1];
            String patronymic = data[2];
            String birthDate = data[3];
            String phoneNumber = data[4];
            char gender = data[5].charAt(0);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.mm.yyyy");
            String dateNow = dtf.format(LocalDateTime.now());

            if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                throw new IllegalArgumentException("Ошибка: Неверный формат даты рождения!");
            }
            String[] dateParts = birthDate.split("\\.");
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);
            String[] dateNowParts = dateNow.split("\\.");
//            int dayNow = Integer.parseInt(dateNowParts[0]);
//            int monthNow = Integer.parseInt(dateNowParts[1]);
            int yearNow = Integer.parseInt(dateNowParts[2]);

            if (year > yearNow) {
                throw new IllegalArgumentException("Ошибка: Такой год ещё не наступил!");
            }

            if (month > 12) {
                throw new IllegalArgumentException("Ошибка: Такой месяц ещё не наступил!");
            }

            if (day > 31) {
                throw new IllegalArgumentException("Ошибка: Такой день ещё не наступил!");
            }

            if (phoneNumber.length() > 11) {
                throw new IllegalArgumentException("Ошибка: Номер телефона должен иметь не более 11 чисел!");
            }

            if (gender != 'f' && gender != 'm') {
                throw new IllegalArgumentException("Ошибка: Неверный формат пола!");
            }

            String fileName = surname + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write("ФИО: " + surname + " " + name + " " + patronymic + "; " + "дата_рождения: " + birthDate + "; " + "номер_телефона: +" + phoneNumber + "; " + "пол: " + gender + "\n");
            writer.close();
            System.out.println("Данные успешно записаны в файл " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлом: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: Неверный формат номера телефона!");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}