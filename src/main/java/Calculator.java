import java.util.ArrayList;
import java.util.Scanner;


public class Calculator {
    private ArrayList<Item> items;
    private int numberOfPeople;

    public Calculator() {
        items = new ArrayList<>();
    }

    public void start() {
        inputNumberOfPeople();
        inputItems();
        printItems();
        printAmountPerPerson();
    }

    private void inputNumberOfPeople() {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        while (!validInput) {
            System.out.println("На сколько человек необходимо разделить счёт:");
            String input = scanner.nextLine().trim();
            if (input.matches("\\d+")) {
                numberOfPeople = Integer.parseInt(input);
                if (numberOfPeople <= 1) {
                    System.out.println("Ошибка: Введите корректное количество гостей, больше 1.");
                } else {
                    validInput = true;
                }
            } else {
                System.out.println("Ошибка: Введите целое число.");
            }
        }
    }

    private void inputItems() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите название товара и его стоимость в формате 'название стоимость': например, пиво 58.99\nЛибо введите команду 'Завершить' для того, чтобы завершить процесс добавления товаров.");
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("завершить")) {
                break;
            }

            String[] parts = line.split(" ");
            if (parts.length != 2 || !isValidPrice(parts[1])) {
                System.out.println("Ошибка: Некорректный формат ввода или некорректная сумма товара.");
                continue;
            }

            String name = parts[0];
            double price = Double.parseDouble(parts[1].replace(',', '.'));

            items.add(new Item(name, price));
            System.out.println("Товар успешно добавлен.");
            System.out.println("Хотите добавить еще один товар?");
        }
    }

    private void printItems() {
        System.out.println("Добавленные товары:");
        for (Item item : items) {
            System.out.println(item.getName() + " - " + item.getPrice());
        }
    }

    private void printAmountPerPerson() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        double perPerson = total / numberOfPeople;

        String rublesString = Formatter.formatRubles(perPerson);

        System.out.println("Каждый человек должен заплатить: " + rublesString);
    }

    private boolean isValidPrice(String priceStr) {
        try {
            String normalizedPrice = priceStr.replace(',', '.');
            double price = Double.parseDouble(normalizedPrice);
            return price >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
