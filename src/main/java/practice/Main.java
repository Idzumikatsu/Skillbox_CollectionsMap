package practice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner sc = new Scanner(System.in);
        String waitStr = "Введите номер, имя или команду:";
        String phone = null;
        String name = null;

        while (true) {
            if (phone == null && name == null) {
                System.out.println(waitStr);
            }

            String input = sc.nextLine().strip();

            if (input.equalsIgnoreCase("end")) {
                System.out.println("Работа программы остановлена пользователем");
                break;
            }

            if (!phoneBook.isName(input) && !phoneBook.isPhoneNumber(input) && !input.equals("LIST")) {
                System.out.println("Неверный формат ввода");
            }

            if (input.equals("LIST") && !phoneBook.getAllContacts().isEmpty()) {
                phoneBook.getAllContacts().forEach(System.out::println);
            } else if (input.equals("LIST") && phoneBook.getAllContacts().isEmpty()) {
                System.out.println("Телефонная книга пуста, сначала добавьте в нее данные для вывода");
            }

            if (phoneBook.isName(input)) {
                name = input;
                if (!phoneBook.getNames().contains(name) && phone == null) {
                    System.out.println("Такого имени в телефонной книге нет.\n"
                            .concat("Введите номер телефона для абонента ")
                            .concat(String.format("\"%s\"", name)));
                }
            } else if (phoneBook.isPhoneNumber(input)) {
                phone = input;
                if (!phoneBook.getPhones().contains(phone) && name == null) {
                    System.out.println("Такого номера в телефонной книге нет.\n"
                            .concat("Введите имя абонента для номера ")
                            .concat(String.format("\"%s\"", phone)));
                }
            }

            if (name != null && phone != null) {
                phoneBook.addContact(phone, name);
                System.out.println("Контакт сохранен!");
                phone = null;
                name = null;
            }
        }
    }
}
