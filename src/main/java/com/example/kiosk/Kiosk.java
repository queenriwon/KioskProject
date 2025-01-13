package com.example.kiosk;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private List<Menu> menuList;
    Scanner scanner = new Scanner(System.in);

    public Kiosk(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public void start() {
        while (true) {
            try {
                int selectCategoryAns = selectCategory();
                if (selectCategoryAns == 0) break;
                Menu menu = menuList.get(selectCategoryAns - 1);

                int selectMenuItemAns = selectMenuItem(menu);
                if (selectMenuItemAns == 0) continue;

                System.out.println("구매창: " + menu.getMenuItems().get(selectMenuItemAns).toString());

            } catch (InputMismatchException e){
                System.out.println("[오류] 숫자값을 입력해주세요.");
                scanner.nextLine();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    public int selectCategory() {
        System.out.println("[ MAIN MENU ]");
        for (Menu menu : menuList) {
            System.out.println((menuList.indexOf(menu)+1) + ". " + menu.getMenuCategory());
        }
        System.out.println("0. 종료");
        int selectCategoryAns = scanner.nextInt();
        if (selectCategoryAns > menuList.size())
            throw new IndexOutOfBoundsException("[오류] 해당되는 번호를 입력해주세요.");
        return selectCategoryAns;
    }

    public int selectMenuItem(Menu menu) {
        System.out.println("[ "+ menu.getMenuCategory().toUpperCase() +" MENU ]");
        List<MenuItem> menuItems = menu.getMenuItems();
        for (MenuItem item : menuItems) {
            System.out.println((menuItems.indexOf(item)+1) + ". " + item.getMenuName() + "\t | W " + item.getMenuPrice() + " | " + item.getMenuDescription());
        }
        System.out.println("0. 뒤로가기");
        int selectMenuItemAns = scanner.nextInt();
        if (selectMenuItemAns > menuItems.size())
            throw new IndexOutOfBoundsException("[오류] 해당되는 번호를 입력해주세요.");
        return selectMenuItemAns;
    }
}
