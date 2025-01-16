package level4;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final List<Menu> menuList;
    private final Scanner scanner = new Scanner(System.in);

    public Kiosk(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public void start() {
        while (true) {
            try {
                // 1. 상위 카테고리 Menu 를 인덱스를 이용해 선택
                int selectCategoryAns = selectMenu("Main", menuList);
                if (selectCategoryAns == 0) break;
                Menu menu = menuList.get(selectCategoryAns - 1);

                // 2. 하위 카테고리 MenuItem 를 인덱스를 이용해 선택
                int selectMenuItemAns = selectMenu(menu.getMenuCategory(), menu.getMenuItems());
                if (selectMenuItemAns == 0) continue;

                // 3. 메뉴 선택
                System.out.println("선택한 메뉴: " + menu.getMenuItems().get(selectMenuItemAns - 1).toString());

            } catch (InputMismatchException e) {
                System.out.println("[오류] 정수값을 입력해주세요");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    // 메뉴 선택
    public <T> int selectMenu(String title, List<T> list) {
        // 1. 메뉴 출력
        System.out.println("[ " + title.toUpperCase() + " MENU ]");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).toString());
        }
        if ("Main".equals(title)) System.out.println("0. 종료\t\t\t | 종료");
        else System.out.println("0. 뒤로가기\t\t | 뒤로가기");

        // 2. 선택할 메뉴 입력
        int selectMenuAns = scanner.nextInt();
        if (selectMenuAns > list.size() || selectMenuAns < 0)
            throw new IndexOutOfBoundsException("[오류] 원하는 메뉴를 선택해주세요");
        return selectMenuAns;
    }
}
