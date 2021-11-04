public class Gargoyle extends Monster implements Runnable{

    public Gargoyle(int level){
        super.name = "가고일";
        super.level = level + random.nextInt(5);
        super.maxHP = 60 + super.level*10;
        super.maxEP = 5 + super.level;
        super.attackPoint = 2*super.level+14;
        super.defensePoint = super.level;
        super.eXP = super.level*20;
        super.currentHP = maxHP;
        super.currentEP = maxEP;
        super.gold = (super.level*40 + random.nextInt(super.level*10))*3;
    }

    public int ironWing(){
        int damage;
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t┌────────────────────────────────────────┐");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \""+super.name + "\"의 아이언윙!");
        super.currentEP -= 5;
        damage = (super.level+ random.nextInt(super.level))*2;
        super.defensePoint += 5;
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t가고일의 날개가 더 단단해졌습니다.  (방어력 +3)");
        return damage;
    }

    public synchronized void act() {
        int event, damage;
        if (this.currentEP >= 5) {
            event = random.nextInt(2);
            if (event == 0)
                damage = super.basicAttack();
            else
                damage = this.ironWing();
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

        for (int i = 0; i < 6; i++) {
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
