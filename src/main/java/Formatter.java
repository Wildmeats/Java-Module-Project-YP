public class Formatter {
    public static String formatRubles(double amount) {
        int rubles = (int) amount;
        String rublesString;
        if (rubles % 10 == 1 && rubles % 100 != 11){
            rublesString = "Рубль";
        } else if (rubles % 10 >= 2 && rubles % 10 <= 4 &&(rubles % 100 < 10 || rubles % 100 >= 20)) {
            rublesString = "рубля";
        } else {
            rublesString = "рублей";
        }
        return rubles + " " + rublesString;
    }
}
