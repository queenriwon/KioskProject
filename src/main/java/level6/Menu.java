package level6;

import java.util.ArrayList;
import java.util.List;

public class Menu<T extends Number> {
    private final String menuCategory;
    private final List<MenuItem<T>> menuItems = new ArrayList<>();

    public Menu(String menuCategory) {
        this.menuCategory = menuCategory;
    }

    public String getMenuCategory() {
        return menuCategory;
    }

    public List<MenuItem<T>> getMenuItems() {
        return menuItems;
    }

    @Override
    public String toString() {
        return menuCategory;
    }
}
