package level4;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String menuCategory;
    private List<MenuItem> menuItems = new ArrayList<>();

    public Menu(String menuCategory) {
        this.menuCategory = menuCategory;
    }

    public String getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(String menuCategory) {
        this.menuCategory = menuCategory;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public String toString() {
        return menuCategory;
    }
}
