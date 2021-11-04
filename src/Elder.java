public class Elder extends Shop implements Runnable{

    int skillCap = 4;
    boolean fixingAntiquity;
    boolean antiquityReady;
    boolean checkAntiquity;
    Skill criticalBlow = new Skill("크리티컬 블로우", "공격스킬", 5, 2, 5, 1000);
    Skill concentration = new Skill("집중", "보조스킬", 10, 5, 10, 2000);
    Skill ironSkin = new Skill("아이언스킨", "보조스킬", 15, 5, 20, 4000);
    Skill decisiveStrike = new Skill("회심의 일격", "공격스킬", 20, 4, 40, 8000);
    Skill skill[][] = new Skill[1][skillCap];

    public Elder() {
        criticalBlow.info = ("기본 공격보다 2배 강한 참격을 적에게 휘두릅니다.");
        concentration.info = ("전투 중에 사용하여 공격력을 5만큼 상승시킬 수 있습니다.");
        ironSkin.info = ("전투 중에 사용하여 방어력을 5만큼 상승시킬 수 있습니다.");
        decisiveStrike.info = ("기본 공격의 4배에 달하는 무시무시한 위력으로 적의 몸을 베어버립니다.");
        skill[0][0] = criticalBlow;
        skill[0][1] = concentration;
        skill[0][2] = ironSkin;
        skill[0][3] = decisiveStrike;

        super.itemCap = 4;
        super.type = 1;

        super.itemName[0][0] = skill[0][0].name;
        super.itemName[0][1] = skill[0][1].name;
        super.itemName[0][2] = skill[0][2].name;
        super.itemName[0][3] = skill[0][3].name;

        super.itemLevel[0][0] = skill[0][0].level;
        super.itemLevel[0][1] = skill[0][1].level;
        super.itemLevel[0][2] = skill[0][2].level;
        super.itemLevel[0][3] = skill[0][3].level;

        super.itemPrice[0][0] = skill[0][0].price;
        super.itemPrice[0][1] = skill[0][1].price;
        super.itemPrice[0][2] = skill[0][2].price;
        super.itemPrice[0][3] = skill[0][3].price;
    }

    public Skill sellSkill(int number) {
        return skill[0][number - 1];
    }

    public void fixAniquity() throws InterruptedException {
        Thread.sleep(60000);
        System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("   ************************************지팡이 복원 완료************************************");
        System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────┘\n");
        this.antiquityReady = true;
    }

    public void run(){
        try {
            fixAniquity();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
