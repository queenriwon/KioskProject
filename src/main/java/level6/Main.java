package level6;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // 메뉴 데이터 적재
            MenuData menuData = new MenuData();
            List<Menu<Double>> menuList = menuData.loadMenuData();

            // 만들어진 리스트를 Kiosk 에 보내면서 kiosk.start()실행
            Kiosk kiosk = new Kiosk(menuList);
            kiosk.start();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
