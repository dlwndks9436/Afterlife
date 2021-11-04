public class DemonHunter extends Novice{

    public DemonHunter(Novice character){
        super.gold = character.gold;
        super.currentEXP = character.currentEXP;
        super.level = character.level;
        super.location = character.location;
        super.name = character.name;
        super.role = "데몬 헌터";
        super.inventory = character.inventory;
        super.protection = character.protection;

        super.weapon = character.weapon;
        super.helmet = character.helmet;
        super.plateBody = character.plateBody;
        super.plateLeg = character.plateLeg;

        super.arroganceRedemption = character.arroganceRedemption;
        super.lustRedemption = character.lustRedemption;
        super.indolenceRedemption = character.indolenceRedemption;
        super.dayAndNight = character.dayAndNight;

        super.wraith = 5+(character.level-1)*2;
        super.maxHP = 40+(character.level-1)*4;
        super.currentHP = super.maxHP;
        super.maxEXP = character.level*30;
        super.bgm = character.bgm;
        super.bgmPlayer = character.bgmPlayer;
        super.antiquity = character.antiquity;
        super.staff = character.staff;
    }

    int skillCap = 4;
    int bonusAP = 0;
    int bonusDP = 0;
    int maxEP = 20+(super.level-1)*5;
    int currentEP = maxEP;

    Skill skill[] = new Skill[skillCap];
    Skill bufferS = new Skill();

    public void gainEXP(int exp){
        if(super.currentEXP+exp >= super.maxEXP){
            int remain = (super.currentEXP+exp) - super.maxEXP;
            super.level++;
            super.currentEXP = 0;
            System.out.println("├──────────────────────────────────────────────────────────────────────────────────────────┤");
            System.out.println(" ******************************************레벨업******************************************");
            super.wraith += 4;
            super.maxHP += 6;
            this.maxEP += 4;
            super.currentHP += 6;
            this.currentEP += 4;
            super.maxEXP = level*30;
            gainEXP(remain);
        }
        else
            super.currentEXP += exp;
    }

    public int weaponAP() {
        int ap = this.wraith + this.bonusAP;
        if (weapon != null)
            ap += weapon.attackPoint;
        return ap;
    }

    public int defensePoint() {
        int dp = this.bonusDP;
        if (helmet != null)
            dp += helmet.defensePoint;
        if (plateBody != null)
            dp += plateBody.defensePoint;
        if (plateLeg != null)
            dp += plateLeg.defensePoint;
        return dp;
    }

    public void showCharacter() {
        System.out.println("──────────────────────────────────────");
        System.out.println(this.name + "(Lv : " + this.level + ")             Gold : " + this.gold);
        System.out.println("체력 : " + this.currentHP + " / " + this.maxHP);
        System.out.println("에너지 : " + this.currentEP + " / " + this.maxEP);
        if (super.dayAndNight.sunny)
            System.out.println("현재 위치 : " + this.location + " (낮)");
        else
            System.out.println("현재 위치 : " + this.location + " (밤)");
        System.out.println("──────────────────────────────────────");
    }

    public void showStatus() {
        System.out.println("──────────────────────────────────────");
        System.out.println("캐릭터 이름 : " + this.name);
        System.out.println("레벨 : " + this.level);
        System.out.println("경험치 : " + this.currentEXP + " / " + this.maxEXP);
        System.out.println("\n체력 : " + this.currentHP + " / " + this.maxHP);
        System.out.println("에너지 : " + this.currentEP + " / " + this.maxEP);
        System.out.println("공격력 : " + this.weaponAP());
        System.out.println("방어력 : " + this.defensePoint());
        System.out.println("\n소지 골드 : " + this.gold);
        System.out.println("──────────────────────────────────────");
        System.out.println("그만보기 << press enter");
    }

    public void fightStatus(Monster monster) {
        System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("\t\t\t\t\t"+this.name + " Lv." + this.level + " \t\t\t\t\t\t\t     " + monster.name + " Lv." + monster.level);
        System.out.println("\t\t\t\t\tHP : " + this.currentHP + " / " + this.maxHP + " \t\t\tVS.  \t\t\t " + "HP : " + monster.currentHP + " / " + monster.maxHP);
        System.out.println("\t\t\t\t\tEP : " + this.currentEP + " / " + this.maxEP + " \t\t\t     \t\t\t " + "EP : " + monster.currentEP + " / " + monster.maxEP);
        System.out.println("├──────────────────────────────────────────────────────────────────────────────────────────┤");
        System.out.println("      1. 공격                2. 스킬                3. 아이템                4. 도망가기 ");
        System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────┘");
        System.out.print(">>");
    }

    public void buySkill(Skill skill) {
        int count;
        if (skill.price > this.gold) {
            System.out.println("▶ 골드가 부족합니다.\n");
            Game.pause();
        }
        else if (skill.level > this.level) {
            System.out.println("▶ 요구레벨을 충족하지 않았습니다.\n");
            Game.pause();
        }
        else {
            for (count = 0; count < this.skillCap; count++) {
                if (this.skill[count] == null) {
                    this.skill[count] = skill;
                    this.gold -= skill.price;
                    System.out.println("▶ \"" + skill.name + "\"을 성공적으로 구매하셨습니다! \n");
                    Game.pause();
                    break;
                } else if (count == this.skillCap - 1) {
                    System.out.println("▶ 남아있는 스킬슬롯이 없습니다. \n");
                    Game.pause();
                }
            }
        }
    }

    public int showSkill() {
        int count;
        int exist = 0;
        for (count = 0; count < this.skillCap; count++) {
            if (this.skill[count] != null)
                exist++;
        }
        if (exist > 0) {
            System.out.println("──────────────────────────────────────");
            for (count = 0; count < exist; count++) {
                System.out.println((count + 1) + ". " + this.skill[count].name);
            }
            System.out.println("──────────────────────────────────────");
            System.out.print("\n>>");
        } else {
            System.out.println("┌─────────────────────────────┐");
            System.out.println("   보유하고 있는 스킬이 없습니다!");
            System.out.println("└─────────────────────────────┘");
        }
        return exist;
    }

    public void chooseSkill(int number) {
        this.bufferS = this.skill[number - 1];
    }

    public boolean checkEnergy() {
        if (this.currentEP >= this.bufferS.energy) {
            return true;
        } else {
            System.out.println("┌────────────────────────────────────────┐");
            System.out.println("        \"" + this.name + "\"의 에너지가 부족합니다!");
            System.out.println("└────────────────────────────────────────┘");
            return false;
        }
    }

    public void useASkill(Monster monster) {
        int damage;
        if (this.currentEP >= this.bufferS.energy) {
            System.out.println("┌────────────────────────────────────────┐");
            System.out.println("           \"" + this.name + "\"의 " + this.bufferS.name + "!");
            this.currentEP -= this.bufferS.energy;
            if (this.bufferS.name.equals("크리티컬 블로우")) {
                damage = (weaponAP()*3 + random.nextInt(weaponAP())) / 2;
            } else if (this.bufferS.name.equals("회심의 일격")) {
                damage = weaponAP()*3 + random.nextInt(weaponAP());
            } else {
                System.out.println("useASkill 오류");
                damage = 0;
            }

            damage -= monster.defensePoint;
            if(damage < 0)
                damage = 0;
            System.out.println("    \""+monster.name+"\"이(가) "+damage+"의 피해를 받았습니다!\n");
            System.out.println("└────────────────────────────────────────┘");
            if(damage <= monster.currentHP)
                monster.currentHP -= damage;
            else if(damage > monster.currentHP)
                monster.currentHP = 0;
        } else {
            System.out.println("┌────────────────────────────────────────┐");
            System.out.println("        \"" + this.name + "\"의 에너지가 부족합니다!");
            System.out.println("└────────────────────────────────────────┘");
        }
    }

    public void useBSkill() {
        if (this.currentEP >= this.bufferS.energy) {
            System.out.println("┌────────────────────────────────────────┐");
            System.out.println("           \"" + this.name + "\"의 " + this.bufferS.name + "!");
            System.out.println("└────────────────────────────────────────┘");
            this.currentEP -= this.bufferS.energy;
            if (this.bufferS.name.equals("집중")) {
                this.bonusAP += bufferS.intensity;
            } else if (this.bufferS.name.equals("아이언스킨")) {
                this.bonusDP += bufferS.intensity;
            } else {
                System.out.println("useBSkill 오류");
            }
        } else {
            System.out.println("┌────────────────────────────────────────┐");
            System.out.println("        \"" + this.name + "\"의 에너지가 부족합니다!");
            System.out.println("└────────────────────────────────────────┘");
        }
    }

}
