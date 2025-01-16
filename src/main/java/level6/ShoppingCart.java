package level6;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ShoppingCart {
    private final Scanner scanner;
    private final List<MenuItem<Double>> shoppingCartList = new ArrayList<>();

    // Scanner 를 하나의 인스턴스만 사용하게 하기 위해 생성자의 매개변수로 전달
    public ShoppingCart(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<MenuItem<Double>> getShoppingCartList() {
        return shoppingCartList;
    }

    // 장바구니 추가
    public void insertShoppingCart(MenuItem<Double> menuItem) {
        // 1. 선택된 메뉴 출력
        System.out.println("\n\"" + menuItem.toString() + "\"");
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인\t\t2. 취소");

        while (true) {
            try {
                int shoppingCartAns = scanner.nextInt();
                switch (shoppingCartAns) {
                    case 1 -> {
                        // 2. 선택된 메뉴 장바구니에 추가
                        System.out.println(menuItem.getMenuName() + "이 장바구니에 추가되었습니다.\n");
                        shoppingCartList.add(menuItem);
                        return;
                    }
                    case 2 -> {
                        System.out.println(menuItem.getMenuName() + "이 선택 취소되었습니다.");
                        return;
                    }
                    default -> throw new IndexOutOfBoundsException("[오류] 선택사항을 입력해주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[오류] 정수값을 입력해주세요.");
                scanner.nextLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // 장바구니 조회
    public double showShoppingCart() {
        // 1. 장바구니 조회
        System.out.println("\n[ Orders ]");
        for (int i = 0; i < shoppingCartList.size(); i++) {
            System.out.println((i + 1) + ". " + shoppingCartList.get(i).toString());
        }

        // 2. 총 가격 조회
        System.out.println("\n[ Total ]");
        double shoppingCartPriceSum = shoppingCartList.stream().mapToDouble(MenuItem::getMenuPrice).sum();
        System.out.println("W " + shoppingCartPriceSum + "\n");
        return shoppingCartPriceSum;
    }

    // 장바구니 주문
    public void orderShoppingCart() {
        // 1. 장바구니 조회
        System.out.println("아래와 같이 주문 하시겠습니까?\n");
        double shoppingCartPriceSum = showShoppingCart();
        System.out.println("1. 주문\t\t2. 메뉴판");

        while (true) {
            try {
                int shoppingCartAns = scanner.nextInt();
                switch (shoppingCartAns) {
                    case 1 -> {
                        // 2. 할인 정보 입력
                        System.out.println("할인 정보를 입력해주세요.");
                        UserDiscountType.printUserDiscountType();

                        int discountTypeAns = scanner.nextInt();
                        UserDiscountType userDiscountType = UserDiscountType.getUserDiscountType(discountTypeAns);
                        double discountRate = userDiscountType.getDiscountRate();
                        String discountName = userDiscountType.getDiscountName();

                        // 3. 주문
                        System.out.println("주문이 완료되었습니다. " + discountName + " 사용자로, 금액은 W " + shoppingCartPriceSum * (1 - discountRate) + " 입니다.\n");
                        shoppingCartList.clear();
                        return;
                    }
                    case 2 -> {
                        System.out.println("메뉴판으로 돌아갑니다.\n");
                        return;
                    }
                    default -> throw new IndexOutOfBoundsException("[오류] 선택사항을 입력해주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[오류] 정수값을 입력해주세요.");
                scanner.nextLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // 장바구니 삭제
    public void cancelShoppingCart() {
        // 1. 장바구니 조회
        showShoppingCart();
        System.out.println("삭제할 메뉴의 검색어를 입력하세요.");
        System.out.println("0. 뒤로가기\t\t | 뒤로가기");

        // 2. 삭제할 검색어 입력
        scanner.nextLine();
        String lookupShoppingCartString = scanner.nextLine();
        if ("0".equals(lookupShoppingCartString)) return;

        // 3. 검색된 상품 출력
        List<MenuItem<Double>> lookupList = shoppingCartList.stream()
                .filter(list -> list.getMenuName().contains(lookupShoppingCartString))
                .toList();
        if (lookupList.isEmpty()) {
            System.out.println("검색된 상품이 없습니다.");
            System.out.println("메뉴판으로 돌아갑니다.\n");
            return;
        }

        // 4. 검색한 메뉴 삭제
        System.out.println();
        for (MenuItem<Double> menuItem : lookupList) {
            System.out.println("\"" + menuItem.toString() + "\"");
        }
        System.out.println("위 메뉴를 장바구니에서 삭제하시겠습니까?");
        System.out.println("1. 삭제\t\t2. 메뉴판");

        while (true) {
            try {
                int shoppingCartAns = scanner.nextInt();
                switch (shoppingCartAns) {
                    case 1 -> {
                        shoppingCartList.removeAll(lookupList);
                        System.out.println("해당 메뉴가 장바구니에서 삭제되었습니다.\n");
                        return;
                    }
                    case 2 -> {
                        System.out.println("메뉴판으로 돌아갑니다.\n");
                        return;
                    }
                    default -> throw new IndexOutOfBoundsException("[오류] 선택사항을 입력해주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[오류] 정수값을 입력해주세요.");
                scanner.nextLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
