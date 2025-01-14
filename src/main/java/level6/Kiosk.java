package level6;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final List<Menu> menuList;
    Scanner scanner = new Scanner(System.in);

    public Kiosk(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public void start() {
        while (true) {
            try {
                int selectCategoryAns = selectMenu("Main", menuList);
                if (selectCategoryAns == 0) break;
                Menu menu = menuList.get(selectCategoryAns - 1);

                int selectMenuItemAns = selectMenu(menu.getMenuCategory(), menu.getMenuItems());
                if (selectMenuItemAns == 0) continue;

                System.out.println("구매창: " + menu.getMenuItems().get(selectMenuItemAns).toString());

            } catch (InputMismatchException e){
                System.out.println("[오류] 정수값을 입력해주세요");
                scanner.nextLine();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    public <T> int selectMenu(String title, List<T> list) {
        System.out.println("[ "+ title.toUpperCase() +" MENU ]");
        for (int i = 0; i < list.size(); i++){
            System.out.println((i+1) + ". " + list.get(i).toString());
        }
        if ("Main".equals(title)) System.out.println("0. 종료\t\t\t | 종료");
        else System.out.println("0. 뒤로가기\t\t | 뒤로가기");

        int selectMenuAns = scanner.nextInt();
        if (selectMenuAns > list.size() || selectMenuAns < 0)
            throw new IndexOutOfBoundsException("[오류] 원하는 메뉴를 선택해주세요");
        return selectMenuAns;
    }
}
