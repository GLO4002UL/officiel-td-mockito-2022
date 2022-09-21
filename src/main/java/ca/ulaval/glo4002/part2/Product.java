package ca.ulaval.glo4002.part2;

import java.util.Objects;

public record Product(String name, int quantity) {
    public boolean canBeSold() {
        return !name.contains("apple");
    }
}
