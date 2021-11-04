public class Shop {
    int itemCap;
    int type;
    String itemName[][] = new String[3][9];
    int itemLevel[][] = new int[3][9];
    int itemPrice[][] = new int[3][9];

    public void showItem(int type) {
        int count;
        System.out.println("판매 목록");
        System.out.println("──────────────────────────────────────");
        for (count = 0; count < itemCap; count++) {
            System.out.println(count + 1 + ". " + itemName[type-1][count]);
        }
        System.out.println("0. 취소");
        System.out.println("──────────────────────────────────────");
    }

    public void showItemSpec(int type, int number) {
        System.out.println("──────────────────────────────────────");
        System.out.println("이름 : " + itemName[type-1][number - 1]);
        System.out.println("요구 레벨 : " + itemLevel[type-1][number - 1]);
        System.out.println("가격 : " + itemPrice[type-1][number - 1]);
        System.out.println("──────────────────────────────────────");
        System.out.println("1.구매          0.취소");
    }
}
