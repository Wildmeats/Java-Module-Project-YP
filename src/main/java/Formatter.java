public class Formatter {
    public static String formatRubles(double amount) {
        int rubles = (int) amount;
        int kopecks = (int) Math.round((amount - rubles) * 100);

        String rublesString;
        if (rubles % 10 == 1 && rubles % 100 != 11) {
            rublesString = "рубль";
        } else if (rubles % 10 >= 2 && rubles % 10 <= 4 && (rubles % 100 < 10 || rubles % 100 >= 20)) {
            rublesString = "рубля";
        } else {
            rublesString = "рублей";
        }

        return rubles + " " + rublesString + " " + String.format("%02d", kopecks) + " копеек";
    }
}