import java.util.Random;

public class Monster {
    Random random = new Random();

    Novice character;

    String name;
    int level;
    int maxHP;
    int maxEP;
    int attackPoint;
    int defensePoint;
    int currentHP;
    int currentEP;
    int gold;
    int eXP;

    boolean active;

    public Monster(){}

    public void setEnemy(Novice character) {
        this.character = character;
    }

    public int basicAttack(){
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t┌────────────────────────────────────────┐");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \""+this.name + "\"의 공격!");
        return (attackPoint+random.nextInt(this.level+5))/2+2;
    }

    public boolean isDead(){
        if(this.currentHP == 0)
            return true;
        else if(this.currentHP > 0)
            return false;
        else {
            System.out.println(this.name+"의 체력 관련 오류 발생");
            return false;
        }
    }
}
