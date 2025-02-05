package level2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 메뉴 데이터 적재
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                // 메뉴 리스트 출력
                System.out.println("\n[ SHAKESHACK MENU ]");
                for (MenuItem item : menuItems) {
                    System.out.println((menuItems.indexOf(item) + 1) + ". " + item.getMenuName() + "\t | W " + item.getMenuPrice() + " | " + item.getMenuDescription());
                }
                System.out.println("0. 종료\t\t\t | 종료");

                int mainAns = scanner.nextInt();
                if (mainAns == 0) break;

            } catch (InputMismatchException e) {
                System.out.println("[오류] 정수값를 입력해주세요");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("프로그램을 종료합니다");
    }
}
