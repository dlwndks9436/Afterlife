public class ShopWeapon extends Shop {

    int type = 1;
    int Cap = 4;
    Weapon weapon[][] = new Weapon[type][Cap];

    Weapon rustySword = new Weapon("러스티 소드", 1, 10, 400, 200);
    Weapon noviceSword = new Weapon("노비스 소드", 5, 15, 800, 400);
    Weapon mithrilSword = new Weapon("미스릴 소드", 10, 20, 1600, 800);
    Weapon adamantiumSword = new Weapon("아다만티움 소드", 15, 25, 3200, 1600);

    public ShopWeapon() {
        super.itemCap = 4;
        super.type = 1;
        weapon[0][0] = rustySword;
        weapon[0][1] = noviceSword;
        weapon[0][2] = mithrilSword;
        weapon[0][3] = adamantiumSword;

        super.itemName[0][0] = weapon[0][0].name;
        super.itemName[0][1] = weapon[0][1].name;
        super.itemName[0][2] = weapon[0][2].name;
        super.itemName[0][3] = weapon[0][3].name;

        super.itemLevel[0][0] = weapon[0][0].level;
        super.itemLevel[0][1] = weapon[0][1].level;
        super.itemLevel[0][2] = weapon[0][2].level;
        super.itemLevel[0][3] = weapon[0][3].level;

        super.itemPrice[0][0] = weapon[0][0].priceB;
        super.itemPrice[0][1] = weapon[0][1].priceB;
        super.itemPrice[0][2] = weapon[0][2].priceB;
        super.itemPrice[0][3] = weapon[0][3].priceB;
    }

    public void sellWeapon(Novice character, int number) {

        int count;
        int goldbefore = character.gold;
        if (weapon[0][number - 1].priceB > character.gold) {
            System.out.println("▶ 골드가 부족합니다.\n");
            Game.pause();
        } else if (weapon[0][number - 1].level > character.level) {
            System.out.println("▶ 요구레벨을 충족하지 않았습니다.\n");
            Game.pause();
        } else {
            for (count = 0; count < character.inventory.cap; count++) {
                if (character.inventory.weapons[count] == null) {
                    character.inventory.weapons[count] = weapon[0][number - 1];
                    character.gold -= weapon[0][number - 1].priceB;
                    System.out.println("▶ \"" + weapon[0][number - 1].name + "\"을 성공적으로 구매하셨습니다!\n");
                    System.out.println(goldbefore + " 골드 → " + character.gold + " 골드\n");
                    Game.pause();
                    break;
                } else if (count == character.inventory.cap - 1) {
                    System.out.println("▶ 남아있는 무기슬롯이 없습니다.\n");
                    Game.pause();
                }
            }
        }
    }

    public void buyWeapon(Novice character, int number) {
        int goldbefore = character.gold;
        character.gold += character.inventory.weapons[number - 1].priceS;
        System.out.println("▶ 무기를 성공적으로 판매하였습니다!\n");
        System.out.println(character.inventory.weapons[number - 1].priceS + " 골드를 획득했습니다!");
        character.inventory.weapons[number - 1] = null;
        System.out.println(goldbefore + " 골드 → " + character.gold + " 골드\n");
        Game.pause();
    }
}
