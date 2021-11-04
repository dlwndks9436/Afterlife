public class BurnHealth implements Runnable {

    boolean on = true;
    boolean active;
    Monster monster;
    DayAndNight dayAndNight;
    Novice character;

    public void setObject(Monster monster, Novice character, DayAndNight dayAndNight) {
        this.monster = monster;
        this.dayAndNight = dayAndNight;
        this.character = character;
    }

    public void burn() {
        if (this.dayAndNight.sunny) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!this.character.isDead() && !this.monster.isDead() && !this.character.escape) {
                if (this.monster.currentHP > 0)
                    this.monster.currentHP -= 1;
                if (this.character.protection <= 0) {
                    if (this.character.currentHP > 0) {
                        this.character.currentHP -= 1;
                    }
                }
            }
        }
    }

    public void exit() {
        this.on = false;
    }

    @Override
    public void run() {
        this.active = false;
        burn();
        this.active = true;
    }
}
