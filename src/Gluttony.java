public class Gluttony extends Monster implements Runnable {

    int delay = 0;
    public Gluttony(int level) {
        super.name = "글루토니";
        super.level = level + random.nextInt(5);
        super.maxHP = 60 + super.level * 5;
        super.maxEP = 10;
        super.attackPoint = 2 + super.level;
        super.defensePoint =2 + super.level;
        super.eXP = super.level * 20;
        super.currentHP = maxHP;
        super.currentEP = maxEP;
        super.gold = (super.level * 40 + random.nextInt(super.level * 10)) * 3;
    }

    public int gobble() {
        int damage;
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t┌────────────────────────────────────────┐");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \"" + super.name + "\"의 물어뜯기!");
        super.currentEP -= 5;
        this.delay +=2;
        damage = super.attackPoint + random.nextInt(super.level);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t글루토니의 식탐이 강해져 더 난폭해졌습니다.");
        return damage;
    }

    public synchronized void act() {
        int event, damage;
        if (this.currentEP >= 5) {
            event = random.nextInt(2);
            if (event == 0)
                damage = super.basicAttack();
            else
                damage = this.gobble();
        } else
            damage = super.basicAttack();

        damage -= this.character.defensePoint();
        if (damage < 0)
            damage = 0;
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t \"" + this.character.name + "\"이(가) " + damage + "의 피해를 받았습니다!");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t└────────────────────────────────────────┘");
        if (damage <= this.character.currentHP)
            this.character.currentHP -= damage;
        else if (damage > this.character.currentHP)
            this.character.currentHP = 0;
    }

    public void run() {
        super.active = false;

        for (int i = 0; i < 8-delay; i++) {
            if (!super.character.isDead() && !this.isDead() && !super.character.escape) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(e);
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
