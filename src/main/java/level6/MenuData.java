package level6;

import java.util.ArrayList;
import java.util.List;

public class MenuData {
    public List<Menu<Double>> loadMenuData() {
        // MenuItem 을 추가
        Menu<Double> burgersMenu = new Menu<>("Burgers");
        burgersMenu.getMenuItems().add(new MenuItem<>("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        burgersMenu.getMenuItems().add(new MenuItem<>("SmokeShack",8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        burgersMenu.getMenuItems().add(new MenuItem<>("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        burgersMenu.getMenuItems().add(new MenuItem<>("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        Menu<Double> drinksMenu = new Menu<>("Drinks");
        drinksMenu.getMenuItems().add(new MenuItem<>("Cola", 2.0, "시원한 콜라 음료"));
        drinksMenu.getMenuItems().add(new MenuItem<>("Lemonade", 2.5, "상큼한 레모네이드"));
        drinksMenu.getMenuItems().add(new MenuItem<>("Iced Tea", 2.2, "청량한 아이스티"));
        drinksMenu.getMenuItems().add(new MenuItem<>("Milkshake", 4.5, "부드럽고 달콤한 밀크셰이크"));

        Menu<Double> dessertsMenu = new Menu<>("Desserts");
        dessertsMenu.getMenuItems().add(new MenuItem<>("Chocolate Shake", 5.0, "진한 초콜릿이 들어간 쉐이크"));
        dessertsMenu.getMenuItems().add(new MenuItem<>("Vanilla Custard", 3.5, "바닐라맛 부드러운 커스터드"));
        dessertsMenu.getMenuItems().add(new MenuItem<>("Brownie", 4.0, "촉촉한 초코 브라우니"));
        dessertsMenu.getMenuItems().add(new MenuItem<>("Ice Cream Cone", 3.0, "바삭한 콘에 담긴 아이스크림"));

        // 추가된 Menu 를 ArrayList 에 .add()
        List<Menu<Double>> menuList = new ArrayList<>();
        menuList.add(burgersMenu);
        menuList.add(drinksMenu);
        menuList.add(dessertsMenu);

        return menuList;
    }
}
