public class ShopGeneral extends Shop implements Runnable {

    int Cap = 6;
    int type = 1;
    Potion potions[][] = new Potion[type][Cap];
    Novice character = new Novice();

    Potion healthPotionS = new Potion("초급 체력 포션", 30, 0,1, 50);
    Potion healthPotionM = new Potion("중급 체력 포션", 60, 0,5, 250);
    Potion healthPotionL = new Potion("고급 체력 포션", 90, 0,10, 1000);
    Potion energyPotionS = new Potion("초급 에너지 포션", 0, 20,1, 50);
    Potion energyPotionM = new Potion("중급 에너지 포션", 0, 40,5, 250);
    Potion energyPotionL = new Potion("고급 에너지 포션", 0, 80,10, 1000);

    public ShopGeneral() {
        super.itemCap = 6;
        super.type = 1;
        potions[0][0] = healthPotionS;
        potions[0][1] = healthPotionM;
        potions[0][2] = healthPotionL;
        potions[0][3] = energyPotionS;
        potions[0][4] = energyPotionM;
        potions[0][5] = energyPotionL;

        super.itemName[0][0] = potions[0][0].name;
        super.itemName[0][1] = potions[0][1].name;
        super.itemName[0][2] = potions[0][2].name;
        super.itemName[0][3] = potions[0][3].name;
        super.itemName[0][4] = potions[0][4].name;
        super.itemName[0][5] = potions[0][5].name;

        super.itemLevel[0][0] = potions[0][0].level;
        super.itemLevel[0][1] = potions[0][1].level;
        super.itemLevel[0][2] = potions[0][2].level;
        super.itemLevel[0][3] = potions[0][3].level;
        super.itemLevel[0][4] = potions[0][4].level;
        super.itemLevel[0][5] = potions[0][5].level;

        super.itemPrice[0][0] = potions[0][0].price;
        super.itemPrice[0][1] = potions[0][1].price;
        super.itemPrice[0][2] = potions[0][2].price;
        super.itemPrice[0][3] = potions[0][3].price;
        super.itemPrice[0][4] = potions[0][4].price;
        super.itemPrice[0][5] = potions[0][5].price;
    }


    public void heal(Novice character) {
        if (character.currentHP == character.maxHP && ((DemonHunter) character).currentEP == ((DemonHunter) character).maxEP) {
            System.out.println("▶ \"" + character.name + "\" 은(는) 이미 온전한 상태입니다. ");
            Game.pause();
        } else {
            if (character.gold >= 100) {
                int goldbefore = character.gold;
                character.currentHP = character.maxHP;
                if (character.role.equals("데몬 헌터"))
                    ((DemonHunter) character).currentEP = ((DemonHunter) character).maxEP;
                character.gold -= 100;
                System.out.println("▶ \"" + character.name + "\" 이(가) 완전히 치유되었습니다. \n");
                System.out.println(goldbefore + " 골드 → " + character.gold + " 골드\n");
                Game.pause();
            } else {
                System.out.println("▶ 보유하고 있는 골드가 부족합니다. ");
                Game.pause();
            }
        }
    }

    public void setChar(Novice character) {
        this.character = character;
    }

    public boolean sellProtect() {
        if (this.character.gold >= 50) {
            int goldbefore = this.character.gold;
            this.character.gold -= 50;
            System.out.println(goldbefore + " 골드 → " + this.character.gold + " 골드\n");
            return true;
        } else {
            System.out.println("▶ 보유하고 있는 골드가 부족합니다.");
            Game.pause();
            return false;
        }
    }

    public void protectChar() {
        while (this.character.protection > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.character.protection -= 1;
            if (this.character.protection % 600 == 0) {
                if(this.character.protection == 0){
                    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"+this.character.name + "의 햇빛 보호 효과가 끝났습니다.━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                }
                else {
                    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━"+this.character.name + "의 햇빛 보호 효과가 " + this.character.protection / 600 + "분 남았습니다.━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                }
            }
        }
    }

    @Override
    public void run() {
        protectChar();
    }

    public void showPotion() {
        int count;
        System.out.println("포션 목록");
        System.out.println("──────────────────────────────────────");
        for (count = 0; count < Cap; count++) {
            System.out.println(count + 1 + ". " + this.potions[0][count].name);
        }
        System.out.println("0. 취소");
        System.out.println("──────────────────────────────────────");
        System.out.print(">>");
    }

    public void showPotionSpec(int number) {
        System.out.println("──────────────────────────────────────");
        System.out.println("이름 : " + potions[0][number - 1].name);
        System.out.println("체력 회복량 : " + potions[0][number - 1].HPHeal);
        System.out.println("에너지 회복량 : " + potions[0][number - 1].EPHeal);
        System.out.println("가격 : " + potions[0][number - 1].price);
        System.out.println("──────────────────────────────────────");
        System.out.println("포션 몇 개를 구매하시겠습니까?");
        System.out.print(">>");
    }

    public void sellPotion(Novice character, int number, int quantity) {

        int count;
        int goldbefore = character.gold;
        boolean exist = false;
        if (potions[0][number - 1].price * quantity > character.gold)
            System.out.println("▶ 골드가 부족합니다.");
        else {
            for (count = 0; count < character.inventory.cap; count++) {
                if (character.inventory.potions[count] == potions[0][number - 1]) {
                    character.inventory.potionQty[count] += quantity;
                    character.gold -= potions[0][number - 1].price * quantity;
                    System.out.println("▶ \"" + potions[0][number - 1].name + "\" " + quantity + "개를 성공적으로 구매하셨습니다!\n");
                    System.out.println(goldbefore + " 골드 → " + character.gold + " 골드\n");
                    Game.pause();
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                for (count = 0; count < character.inventory.cap; count++) {
                    if (character.inventory.potions[count] == null) {
                        character.inventory.potions[count] = potions[0][number - 1];
                        character.inventory.potionQty[count] = quantity;
                        character.gold -= potions[0][number - 1].price * quantity;
                        System.out.println("▶ \"" + potions[0][number - 1].name + "\" " + quantity + "개를 성공적으로 구매하셨습니다!\n");
                        System.out.println(goldbefore + " 골드 → " + character.gold + " 골드\n");
                        Game.pause();
                        break;
                    } else if (count == character.inventory.cap - 1) {
                        System.out.println("▶ 남아있는 포션슬롯이 없습니다.");
                        Game.pause();
                    }
                }
            }
        }
    }
}
