package level4;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final String menuCategory;
    private final List<MenuItem> menuItems = new ArrayList<>();

    public Menu(String menuCategory) {
        this.menuCategory = menuCategory;
    }

    public String getMenuCategory() {
        return menuCategory;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    @Override
    public String toString() {
        return menuCategory;
    }
}
