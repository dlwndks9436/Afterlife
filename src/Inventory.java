public class Inventory {
    int cap = 9;

    int potionQty[] = new int[this.cap];


    Weapon weapons[] = new Weapon[this.cap];
    Armor armors[] = new Armor[this.cap];
    Potion potions[] = new Potion[this.cap];

    public boolean showWeapon() {
        int count;
        boolean exist = false;
        System.out.println("──────────────────────────────────────");
        for (count = 0; count < this.cap; count++) {
            if (this.weapons[count] != null) {
                System.out.println((count + 1) + ". " + this.weapons[count].name);
                exist = true;
            } else if (this.weapons[count] == null) {
                System.out.println(count + 1 + ". '비어있음'");
            }
        }
        return exist;
    }

    public boolean showArmor() {
        int count;
        boolean exist = false;
        for (count = 0; count < this.cap; count++) {
            if (this.armors[count] != null) {
                System.out.println((count + 1) + ". " + this.armors[count].name);
                exist = true;
            } else if (this.armors[count] == null) {
                System.out.println(count + 1 + ". '비어있음'");
            }
        }
        return exist;
    }

    public boolean showPotion() {
        int count;
        boolean exist = false;
        System.out.println("──────────────────────────────────────");
        for (count = 0; count < this.cap; count++) {
            if (this.potions[count] != null) {
                System.out.println((count + 1) + ". " + this.potions[count].name + " \t수량 : " + potionQty[count]);
                exist = true;
            } else if (this.potions[count] == null) {
                System.out.println(count + 1 + ". '비어있음'");
            }
        }
        System.out.println("──────────────────────────────────────");
        return exist;
    }
}
