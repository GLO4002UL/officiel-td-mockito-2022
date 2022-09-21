package ca.ulaval.glo4002.part4;

/**
 * Le choix d'utiliser un singleton est discutable, c'est seulement pour l'exercice.
 */
public class Configuration {
    private static Configuration instance = null;

    public static synchronized Configuration get() {
        if (instance == null) {
            instance = new Configuration(
                Double.parseDouble(System.getenv("MY_APP_HOLIDAY_DISCOUNT")),
                Integer.parseInt(System.getenv("MY_APP_MAXIMUM_ITEMS"))
            );
        }
        return instance;
    }

    static void setForTests(Configuration configuration) {
        instance = configuration;
    }

    private final double holidayDiscount;

    private final int maximumItems;

    Configuration(double holidayDiscount, int maximumItems) {
        this.holidayDiscount = holidayDiscount;
        this.maximumItems = maximumItems;
    }

    public double getHolidayDiscount() {
        return holidayDiscount;
    }

    public int getMaximumItems() {
        return maximumItems;
    }
}
