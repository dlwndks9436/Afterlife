public class Satan extends Monster implements Runnable {

    boolean first = true;

    public Satan() {
        super.name = "사탄";
        super.level = 66;
        super.maxHP = 666;
        super.maxEP = 666;
        super.attackPoint = 2 + super.level;
        super.defensePoint = 5 + super.level;
        super.eXP = super.level * 20;
        super.currentHP = maxHP;
        super.currentEP = maxEP;
        super.gold = (super.level * 40 + random.nextInt(super.level * 10)) * 2;
    }

    public synchronized void act() {
        int damage = 95 + random.nextInt(6) - this.character.defensePoint();
        if (damage < 0)
            damage = 0;
        System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒  콰아아아아아아아아아아아아아아아앙!!  ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\"" + this.character.name + "\"이(가) " + damage + "의 피해를 받았습니다!━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        if (damage <= this.character.currentHP)
            this.character.currentHP -= damage;
        else if (damage > this.character.currentHP)
            this.character.currentHP = 0;
    }

    public void run() {
        super.active = false;

        for (int i = 0; i < 15; i++) {
            if (!super.character.isDead() && !this.isDead() && !super.character.escape) {
                if (this.character.staff) {
                    if (this.first) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                        if (i == 0) {
                            System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────┐");
                            System.out.println("                                사탄 : 목숨이 아까운 줄 모르는군..");
                            System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────┘");
                        }
                        if (i == 4) {
                            System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────┐");
                            System.out.println("                               사탄 : 무사히 돌아갈 생각하지 마라.");
                            System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────┘");
                        }
                        if (i == 8) {
                            System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────┐");
                            System.out.println("                                   사탄 : 후회하게 해주마...");
                            System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────┘");
                        }
                        this.first = false;
                    } else {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                        if (i == 5) {
                            System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────┐");
                            System.out.println("                                     사탄 : 건방지다!!");
                            System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────┘");
                        }
                        if (i == 10) {
                            System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────┐");
                            System.out.println("                                   사탄 : 먼지가 되어라!!!");
                            System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────┘");
                        }
                    }
                }
                else {
                    if (this.first) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                        if (i == 0) {
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t┌────────────────────────────────────────┐");
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t      사탄 : 목숨이 아까운 줄 모르는군..");
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t└────────────────────────────────────────┘");
                        }
                        if (i == 4) {
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t┌────────────────────────────────────────┐");
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t      사탄 : 무사히 돌아갈 생각하지 마라..");
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t└────────────────────────────────────────┘");
                        }
                        if (i == 8) {
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t┌────────────────────────────────────────┐");
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t           사탄 : 후회하게 해주마...");
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t└────────────────────────────────────────┘");
                        }
                        this.first = false;
                    } else {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                        if (i == 5) {
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t┌────────────────────────────────────────┐");
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t              사탄 : 건방지다!!");
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t└────────────────────────────────────────┘");
                        }
                        if (i == 10) {
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t┌────────────────────────────────────────┐");
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t            사탄 : 먼지가 되어라!!!");
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t└────────────────────────────────────────┘");
                        }
                    }
                }
            } else
                break;
        }
        if (!super.character.isDead() && !this.isDead() && !super.character.escape) {
            this.act();
        }
        super.active = true;
    }

}
