package level6;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Kiosk {
    private final List<Menu<Double>> menuList;
    private final Scanner scanner = new Scanner(System.in);
    private final ShoppingCart shoppingCart = new ShoppingCart(scanner);

    public Kiosk(List<Menu<Double>> menuList) {
        this.menuList = menuList;
    }

    public void start() {
        while (true) {
            try {
                // 1. 상위 카테고리 Menu 를 인덱스를 이용해 선택
                // (+ 장바구니 옵션(주문,취소))
                int selectCategoryAns = selectMenu("Main", menuList);
                if (selectCategoryAns == 0) {
                    break;
                }
                Menu<Double> menu = menuList.get(selectCategoryAns - 1);

                // 2. 하위 카테고리 MenuItem 를 인덱스를 이용해 선택
                int selectMenuItemAns = selectMenu(menu.getMenuCategory(), menu.getMenuItems());
                if (selectMenuItemAns == 0) {
                    continue;
                }
                MenuItem<Double> selectedMenuItem = menu.getMenuItems().get(selectMenuItemAns - 1);

                // 3. 선택한 MenuItem 를 장바구니에 담을지 선택
                // (+ 할인)
                shoppingCart.insertShoppingCart(selectedMenuItem);

            } catch (InputMismatchException e) {
                System.out.println("[오류] 정수값을 입력해주세요.");
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
        System.out.println("\n[ " + title.toUpperCase() + " MENU ]");
        AtomicInteger atomicInteger = new AtomicInteger(1);
        list.forEach(item -> System.out.println(atomicInteger.getAndIncrement() + ". " + item.toString()));
        System.out.println((("Main".equals(title)) ? "0. 종료\t\t\t | 종료" : "0. 뒤로가기\t\t | 뒤로가기"));

        // 2-1. 장비구니에 상품이 있을 경우
        if (!shoppingCart.getShoppingCartList().isEmpty() && "Main".equals(title)) {
            System.out.println("\n[ ORDER MENU ]");
            System.out.println("4. Orders\t\t| 장바구니를 확인 후 주문합니다.");
            System.out.println("5. Cancel\t\t| 진행중인 주문을 취소합니다.");

            while (true) {
                try {
                    // 2-2. 선택지 입력
                    int selectMenuAns = scanner.nextInt();
                    if (selectMenuAns > list.size() + 2 || selectMenuAns < 0)
                        throw new IndexOutOfBoundsException("[오류] 원하는 메뉴를 선택해주세요.");
                    return switch (selectMenuAns) {
                        case 4 -> {
                            // 2-3. 장바구니 주문
                            shoppingCart.orderShoppingCart();
                            throw new KioskContinueException("메뉴판으로 돌아갑니다.");
                        }
                        case 5 -> {
                            // 2-4. 장바구니 삭제
                            shoppingCart.cancelShoppingCart();
                            throw new KioskContinueException("메뉴판으로 돌아갑니다.");
                        }
                        default -> selectMenuAns;
                    };
                } catch (InputMismatchException e) {
                    System.out.println("[오류] 정수값을 입력해주세요.");
                    scanner.nextLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        while (true) {
            try {
                // 3. 메뉴 입력
                int selectMenuAns = scanner.nextInt();
                if (selectMenuAns > list.size() || selectMenuAns < 0)
                    throw new IndexOutOfBoundsException("[오류] 원하는 메뉴를 선택해주세요.");
                return selectMenuAns;
            } catch (InputMismatchException e) {
                System.out.println("[오류] 정수값을 입력해주세요.");
                scanner.nextLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
