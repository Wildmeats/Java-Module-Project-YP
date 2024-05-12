import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    private ArrayList<Item> items;
    private int numberOfPeople;
    public Calculator() { items = new ArrayList<>();}
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        while (!validInput) {
            System.out.println("На скольких человек необходимо разделить счёт:");
            String inmput = scanner.nextLine().trim();
            if (inmput.matches("\\d+")) {
                numberOfPeople = Integer.parseInt(inmput);
                if (numberOfPeople <= 1) {
                    System.out.println("Ошибка: Введите корректное количество гостей, больше одного.");
                } else {
                    validInput = true;
                }
            }
        }
        while (true) {
            System.out.println("Введите название товара и его стоимость в формате 'название стоимость': например, пиво 58.99\nЛибо введите команду 'Завершить' для того, чтоб завершить процесс добавления товаров.");
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("завершить")) {
                break;
            }
            String[] parts = line.split(" ");
            if (parts.length != 2 || !isValidPrice(parts[1])) {
                System.out.println("Ошибка: Неккоректный формат ввода или некорректная сумма товара.");
                continue;
            }
            String name = parts[0];
            double price = Double.parseDouble(parts[1]);
            items.add(new Item(name, price));
            System.out.println("Товар успешно добавлен.");
            System.out.println("Хотите добавить еще один товар?");
        }
        System.out.println("Добавленные товары:");
        for(Item item : items) {
            System.out.println(item.getName() + " - " + item.getPrice());
        }
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        double perPerson = total / numberOfPeople;
        String rublesString = Formatter.formatRubles(perPerson);
        System.out.println("Каждый человек должен заплатить: " + rublesString);
    }
    private boolean isValidPrice(String priceStr) {
        String[] parts = priceStr.split("\\.");
        if (parts.length !=2) {
            return false;
        }
        if (!parts[0].matches("\\d+") || !parts[1].matches("\\d{2}")){
            return  false;
        }
        return true;
    }
}
