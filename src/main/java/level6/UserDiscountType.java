package level6;

public enum UserDiscountType {
    // 할인 유형을 enum 으로 지정
    NATIONAL(1,"국가유공자",0.1),
    ARMY(2, "군인", 0.05),
    STUDENT(3, "학생", 0.03),
    GENERAL(4, "일반인", 0);

    // 필드: 입력받게될 인덱스, 이름, 할인율
    private final int discountIndex;
    private final String discountName;
    private final double discountRate;

    UserDiscountType(int discountIndex, String discountName, double discountRate) {
        this.discountIndex = discountIndex;
        this.discountName = discountName;
        this.discountRate = discountRate;
    }

    public int getDiscountIndex() {
        return discountIndex;
    }

    public String getDiscountName() {
        return discountName;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    // 해당되는 인덱스인지 검사하는 메서드
    public static UserDiscountType getUserDiscountType(int index){
        for (UserDiscountType discountType : values()) {
            if (discountType.getDiscountIndex() == index) return discountType;
        }
        throw new IndexOutOfBoundsException("[오류] 해당되는 할인유형이 없습니다.");
    }

    // 할인율 안내 출력 메서드
    public static void printUserDiscountType(){
        int i = 1;
        for (UserDiscountType discountType : values()) {
            System.out.println(i + ". " + discountType.toString());
            i++;
        }
    }

    @Override
    public String toString() {
        return discountName + "\t: " + (int) (discountRate * 100) + "%";
    }
}
