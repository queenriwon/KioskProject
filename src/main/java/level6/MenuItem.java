package level6;

public class MenuItem<T extends Number> {
    private final String menuName;
    private final T menuPrice;
    private final String menuDescription;

    public MenuItem(String menuName, T menuPrice, String menuDescription) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuDescription = menuDescription;
    }

    public String getMenuName() {
        return menuName;
    }

    public T getMenuPrice() {
        return menuPrice;
    }

    @Override
    public String toString() {
        return menuName + "\t | W " + menuPrice + " | " + menuDescription;
    }
}
