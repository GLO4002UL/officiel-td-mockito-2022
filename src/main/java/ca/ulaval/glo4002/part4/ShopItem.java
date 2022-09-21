package ca.ulaval.glo4002.part4;

public class ShopItem {
    private final double unitPrice;

    public ShopItem(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice(int quantity) {
        if (quantity > Configuration.get().getMaximumItems()) {
            throw new TooManyItemsExceptions();
        }

        return (this.unitPrice * quantity) * Configuration.get().getHolidayDiscount();
    }
}
