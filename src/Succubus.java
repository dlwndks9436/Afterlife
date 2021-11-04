public class Succubus extends Monster implements Runnable{

    public Succubus(int level){
        super.name = "서큐버스";
        super.level = level + random.nextInt(5);
        super.maxHP = 60 + super.level*6;
        super.maxEP = 20;
        super.attackPoint = super.level+8;
        super.defensePoint =5 + super.level;
        super.eXP = super.level*20;
        super.currentHP = maxHP;
        super.currentEP = maxEP;
        super.gold = (super.level*40 + random.nextInt(super.level*10))*3;
    }

    public int absorb(){
        int damage;
        int remain;
        remain = super.maxHP - super.currentHP;
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t┌────────────────────────────────────────┐");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \""+super.name + "\"의 정기흡수!");
        super.currentEP -= 5;
        damage = (this.attackPoint + random.nextInt(super.level));
        if(super.currentHP == super.maxHP)
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t서큐버스의 체력이 이미 온전합니다.");
        if(remain < damage/2) {
            super.currentHP = super.maxHP;
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t서큐버스의 체력이 회복되었습니다.");
        }
        else {
            super.currentHP += damage/2;
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t서큐버스의 체력이 회복되었습니다.");
        }
        return damage;
    }

    public synchronized void act() {
        int event, damage;
        if (this.currentEP >= 5) {
            event = random.nextInt(2);
            if (event == 0)
                damage = super.basicAttack();
            else
                damage = this.absorb();
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

        for (int i = 0; i < 4; i++) {
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
