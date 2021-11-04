public class ShopArmor extends Shop{

    int Cap = 4;
    int type = 3;
    Armor armors[][] = new Armor[type][Cap];

    Armor rustyHelmet = new Armor("러스티 헬멧", "헬멧", 1, 2, 200, 100);
    Armor rustyPlateBody = new Armor("러스티 플레이트 바디", "플레이트 바디", 1, 2, 200, 100);
    Armor rustyPlateLeg = new Armor("러스티 플레이트 레그", "플레이트 레그", 1, 2, 200, 100);
    Armor demonHelmet = new Armor("데몬 헬멧", "헬멧", 5, 4, 400, 200);
    Armor demonPlateBody = new Armor("데몬 플레이트 바디", "플레이트 바디", 5, 4, 400, 200);
    Armor demonPlateLeg = new Armor("데몬 플레이트 레그", "플레이트 레그", 5, 4, 400, 200);
    Armor voidHelmet = new Armor("보이드 헬멧", "헬멧", 10, 6, 800, 400);
    Armor voidPlateBody = new Armor("보이드 플레이트 바디", "플레이트 바디", 10, 6, 800, 400);
    Armor voidPlateLeg = new Armor("보이드 플레이트 레그", "플레이트 레그", 10, 6, 800, 400);
    Armor infinityHelmet = new Armor("인피니티 헬멧", "헬멧", 15, 8, 1600, 800);
    Armor infinityPlateBody = new Armor("인피니티 플레이트 바디", "플레이트 바디", 15, 8, 1600, 800);
    Armor infinityPlateLeg = new Armor("인피니티 플레이트 레그", "플레이트 레그", 15, 8, 1600, 800);


    public ShopArmor() {
        super.itemCap = 4;
        super.type = 3;

        armors[0][0] = rustyHelmet;
        armors[0][1] = demonHelmet;
        armors[0][2] = voidHelmet;
        armors[0][3] = infinityHelmet;
        armors[1][0] = rustyPlateBody;
        armors[1][1] = demonPlateBody;
        armors[1][2] = voidPlateBody;
        armors[1][3] = infinityPlateBody;
        armors[2][0] = rustyPlateLeg;
        armors[2][1] = demonPlateLeg;
        armors[2][2] = voidPlateLeg;
        armors[2][3] = infinityPlateLeg;

        super.itemName[0][0] = armors[0][0].name;
        super.itemName[0][1] = armors[0][1].name;
        super.itemName[0][2] = armors[0][2].name;
        super.itemName[0][3] = armors[0][3].name;

        super.itemLevel[0][0] = armors[0][0].level;
        super.itemLevel[0][1] = armors[0][1].level;
        super.itemLevel[0][2] = armors[0][2].level;
        super.itemLevel[0][3] = armors[0][3].level;

        super.itemPrice[0][0] = armors[0][0].priceB;
        super.itemPrice[0][1] = armors[0][1].priceB;
        super.itemPrice[0][2] = armors[0][2].priceB;
        super.itemPrice[0][3] = armors[0][3].priceB;

        super.itemName[1][0] = armors[1][0].name;
        super.itemName[1][1] = armors[1][1].name;
        super.itemName[1][2] = armors[1][2].name;
        super.itemName[1][3] = armors[1][3].name;

        super.itemLevel[1][0] = armors[1][0].level;
        super.itemLevel[1][1] = armors[1][1].level;
        super.itemLevel[1][2] = armors[1][2].level;
        super.itemLevel[1][3] = armors[1][3].level;

        super.itemPrice[1][0] = armors[1][0].priceB;
        super.itemPrice[1][1] = armors[1][1].priceB;
        super.itemPrice[1][2] = armors[1][2].priceB;
        super.itemPrice[1][3] = armors[1][3].priceB;

        super.itemName[2][0] = armors[2][0].name;
        super.itemName[2][1] = armors[2][1].name;
        super.itemName[2][2] = armors[2][2].name;
        super.itemName[2][3] = armors[2][3].name;

        super.itemLevel[2][0] = armors[2][0].level;
        super.itemLevel[2][1] = armors[2][1].level;
        super.itemLevel[2][2] = armors[2][2].level;
        super.itemLevel[2][3] = armors[2][3].level;

        super.itemPrice[2][0] = armors[2][0].priceB;
        super.itemPrice[2][1] = armors[2][1].priceB;
        super.itemPrice[2][2] = armors[2][2].priceB;
        super.itemPrice[2][3] = armors[2][3].priceB;
    }

    public void sellArmor(Novice character, int type, int number) {
        int count;
        int goldbefore = character.gold;
        if (armors[type - 1][number - 1].priceB > character.gold) {
            System.out.println("▶ 골드가 부족합니다.\n");
            Game.pause();
        }
        else if (armors[type - 1][number - 1].level > character.level) {
            System.out.println("▶ 요구레벨을 충족하지 않았습니다.\n");
            Game.pause();
        }
        else {
            for (count = 0; count < character.inventory.cap; count++) {
                if (character.inventory.armors[count] == null) {
                    character.inventory.armors[count] = armors[type - 1][number - 1];
                    character.gold -= armors[type - 1][number - 1].priceB;
                    System.out.println("▶ \"" + armors[type - 1][number - 1].name + "\"을 성공적으로 구매하셨습니다!\n");
                    System.out.println(goldbefore + " 골드 → " + character.gold + " 골드\n");
                    Game.pause();
                    break;
                } else if (count == character.inventory.cap - 1) {
                    System.out.println("▶ 남아있는 방어구 슬롯이 없습니다.\n");
                    Game.pause();
                }
            }
        }
    }

    public void buyArmor(Novice character, int number) {
        int goldbefore = character.gold;
        character.gold += character.inventory.armors[number - 1].priceS;
        System.out.println("▶ 방어구를 성공적으로 판매하였습니다!\n");
        System.out.println(character.inventory.armors[number - 1].priceS + " 골드를 획득했습니다!");
        character.inventory.armors[number - 1] = null;
        System.out.println(goldbefore + " 골드 → " + character.gold + " 골드\n");
        Game.pause();
    }
}
