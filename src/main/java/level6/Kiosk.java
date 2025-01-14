package level6;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final List<Menu> menuList;
    private final List<MenuItem> shoppingCartList = new ArrayList<>();
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
                MenuItem selectedMenuItem = menu.getMenuItems().get(selectMenuItemAns - 1);

                // 선택한 메뉴 출력
                System.out.println("\"" + selectedMenuItem.toString() + "\"");
                System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                System.out.println("1. 확인\t\t2. 취소");
                int shoppingCartAns = scanner.nextInt();
                if (shoppingCartAns == 1) {
                    System.out.println(selectedMenuItem.getMenuName() + "이 장바구니에 추가되었습니다.");
                    shoppingCartList.add(selectedMenuItem);
                } else if (shoppingCartAns == 2) {
                    System.out.println(selectedMenuItem.getMenuName() + "이 선택 취소되었습니다.");
                    continue;
                } else {
                    throw new IndexOutOfBoundsException("[오류] 선택사항을 입력해주세요");
                }

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

        if (!shoppingCartList.isEmpty() && "Main".equals(title)) {
            System.out.println("[ ORDER MENU ]");
            System.out.println("4. Orders\t\t| 장바구니를 확인 후 주문합니다.");
            System.out.println("5. Cancel\t\t| 진행중인 주문을 취소합니다.");

            int selectMenuAns = scanner.nextInt();
            if (selectMenuAns > list.size() + 2 || selectMenuAns < 0)
                throw new IndexOutOfBoundsException("[오류] 원하는 메뉴를 선택해주세요");
            if (selectMenuAns == 4) {
                orderShoppingCart();
            } else if (selectMenuAns == 5) {
                cancelShoppingCart();
            } else if (selectMenuAns == 0) {
                return selectMenuAns;
            }
            throw new RuntimeException("");
        }
        int selectMenuAns = scanner.nextInt();
        if (selectMenuAns > list.size() || selectMenuAns < 0)
            throw new IndexOutOfBoundsException("[오류] 원하는 메뉴를 선택해주세요");
        return selectMenuAns;
    }

    public void showShoppingCart() {
        System.out.println("[ Orders ]");
        for (MenuItem item : shoppingCartList) {
            System.out.println(item.toString());
        }

        System.out.println("[ Total ]");
        double shoppingCartPriceSum = shoppingCartList.stream().mapToDouble(MenuItem::getMenuPrice).sum();
        System.out.println("W " + shoppingCartPriceSum);
    }

    public void orderShoppingCart() {
        System.out.println("아래와 같이 주문 하시겠습니까?\n");
        showShoppingCart();

        System.out.println("1. 주문\t\t2. 메뉴판");
        int shoppingCartAns = scanner.nextInt();
        if (shoppingCartAns == 1) {
            double shoppingCartPriceSum = shoppingCartList.stream().mapToDouble(MenuItem::getMenuPrice).sum();
            System.out.println("주문이 완료되었습니다. 금액은 " + shoppingCartPriceSum + "입니다.");
            shoppingCartList.clear();
        } else if (shoppingCartAns == 2) {
            System.out.println("메뉴판으로 돌아갑니다.");
        } else {
            throw new IndexOutOfBoundsException("[오류] 선택사항을 입력해주세요");
        }
    }

    public void cancelShoppingCart() {
        System.out.println("어떤 상품을 취소하시겠습니까?\n");
        for (int i = 0; i < shoppingCartList.size(); i++){
            System.out.println((i+1) + ". " + shoppingCartList.get(i).toString());
        }
        System.out.println("0. 뒤로가기\t\t | 뒤로가기");
        int selectMenuAns = scanner.nextInt();
        if (selectMenuAns > shoppingCartList.size() || selectMenuAns < 0)
            throw new IndexOutOfBoundsException("[오류] 원하는 메뉴를 선택해주세요");
        if (selectMenuAns == 0) return;

        System.out.println("\"" + shoppingCartList.get(selectMenuAns - 1).toString() + "\"");
        System.out.println("위 상품을 장바구니에서 삭제하시겠습니까?");
        System.out.println("1. 삭제\t\t2. 메뉴판");
        int shoppingCartAns = scanner.nextInt();
        if (shoppingCartAns == 1) {
            MenuItem removeMenuItem = shoppingCartList.remove(selectMenuAns - 1);
            System.out.println(removeMenuItem.toString() + "가 장바구니에서 삭제되었습니다.");
        } else if (shoppingCartAns == 2) {
            System.out.println("메뉴판으로 돌아갑니다.");
        } else {
            throw new IndexOutOfBoundsException("[오류] 선택사항을 입력해주세요");
        }
    }
}
