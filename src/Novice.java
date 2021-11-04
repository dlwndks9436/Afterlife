import java.util.Random;

public class Novice implements Runnable {

    Weapon weapon;
    Armor helmet;
    Armor plateBody;
    Armor plateLeg;

    String name;
    String location = "마을";
    String destination;
    String role = "초보자";

    int level = 1;
    int wraith = 5;
    int maxHP = 40;
    int currentHP = maxHP;
    int gold = 0;
    int maxEXP = this.level * 30;
    int currentEXP = 0;
    int redemption;
    int protection = 0;

    boolean escape;
    boolean active;
    boolean inBattle;

    int arroganceRedemption;
    int lustRedemption;
    int indolenceRedemption;

    boolean antiquity[] = new boolean[3];
    boolean staff;

    DayAndNight dayAndNight;
    Inventory inventory;
    Monster monster;

    BGM bgm;
    Thread bgmPlayer;

    Random random = new Random();

    //캐릭터 이름 정하기
    public void submitName(String name) {
        System.out.println("\n캐릭터의 이름을 \"" + name + "\"으로 정하시겠습니까?");
        System.out.println("1.네\t       0.아니오");
        System.out.print("\n>>");
        this.name = name;
    }

    public void getDayAndNight(DayAndNight dayAndNight) {
        this.dayAndNight = dayAndNight;
    }

    public void gainEXP(int exp) {
        if (this.currentEXP + exp >= this.maxEXP) {
            int remain = (this.currentEXP + exp) - this.maxEXP;
            this.level++;
            this.currentEXP = 0;
            System.out.println("├──────────────────────────────────────────────────────────────────────────────────────────┤");
            System.out.println(" ******************************************레벨업******************************************");
            this.wraith += 2;
            this.maxHP += 4;
            this.currentHP += 4;
            this.maxEXP = level * 30;
            gainEXP(remain);
        } else
            this.currentEXP += exp;
    }

    //장소 도착했을 때 안내문
    public void move(String field) {
        if (this.bgmPlayer != null) {
            this.bgm.exit();
        }
        this.location = field;

        this.bgm = new BGM(this.location);
        this.bgmPlayer = new Thread(bgm);
        this.bgmPlayer.start();
    }

    public void equipWeapon(int number) {
        if (this.level < this.inventory.weapons[number - 1].level) {
            System.out.println("▶ 장비의 요구레벨을 충족하지 못했습니다.\n");
            Game.pause();
        } else {
            Weapon weapon = this.weapon;
            this.weapon = this.inventory.weapons[number - 1];
            System.out.println("▶ " + this.weapon.name + "을 착용하였습니다.\n");
            this.inventory.weapons[number - 1] = weapon;
            Game.pause();
        }
    }

    public void equipArmor(int number) {
        if (this.level < this.inventory.armors[number - 1].level) {
            System.out.println("▶ 장비의 요구레벨을 충족하지 못했습니다.\n");
            Game.pause();
        } else {
            if (this.inventory.armors[number - 1].type.equals("헬멧")) {
                Armor armor = this.helmet;
                this.helmet = this.inventory.armors[number - 1];
                this.inventory.armors[number - 1] = armor;
                System.out.println("▶ " + this.helmet.name + "을 착용하였습니다.\n");
                Game.pause();
            } else if (this.inventory.armors[number - 1].type.equals("플레이트 바디")) {
                Armor armor = this.plateBody;
                this.plateBody = this.inventory.armors[number - 1];
                this.inventory.armors[number - 1] = armor;
                System.out.println("▶ " + this.plateBody.name + "을 착용하였습니다.\n");
                Game.pause();
            } else if (this.inventory.armors[number - 1].type.equals("플레이트 레그")) {
                Armor armor = this.plateLeg;
                this.plateLeg = this.inventory.armors[number - 1];
                this.inventory.armors[number - 1] = armor;
                System.out.println("▶ " + this.plateLeg.name + "을 착용하였습니다.\n");
                Game.pause();
            }
        }
    }

    public int weaponAP() {
        int ap = this.wraith;
        if (weapon != null)
            ap += weapon.attackPoint;
        return ap;
    }

    public int defensePoint() {
        int dp = 0;
        if (helmet != null)
            dp += helmet.defensePoint;
        if (plateBody != null)
            dp += plateBody.defensePoint;
        if (plateLeg != null)
            dp += plateLeg.defensePoint;
        return dp;
    }

    public void showStatus() {
        System.out.println("──────────────────────────────────────");
        System.out.println("캐릭터 이름 : " + this.name);
        System.out.println("레벨 : " + this.level);
        System.out.println("경험치 : " + this.currentEXP + " / " + this.maxEXP);
        System.out.println("체력 : " + this.currentHP + " / " + this.maxHP);
        System.out.println("공격력 : " + this.weaponAP());
        System.out.println("방어력 : " + this.defensePoint());
        System.out.println("\n소지 골드 : " + this.gold);
        System.out.println("──────────────────────────────────────");
        System.out.println("그만보기 << press enter");
    }

    public void showCurrentStatus() {
        System.out.println("진행 상황");
        System.out.println("──────────────────────────────────────");
        System.out.println("나태 : " + indolenceRedemption + " / 5");
        System.out.println("음욕 : " + lustRedemption + " / 5");
        System.out.println("교만 : " + arroganceRedemption + " / 5");
        System.out.println("──────────────────────────────────────");
        if (!staff) {
            if (this.antiquity[0] || this.antiquity[1] || this.antiquity[2]) {
                System.out.println("──────────────────────────────────────");
                if (this.antiquity[0])
                    System.out.println("부서진 유물 조각(상) - 획득");
                else
                    System.out.println("부서진 유물 조각(상) - 없음");
                if (this.antiquity[1])
                    System.out.println("부서진 유물 조각(중) - 획득");
                else
                    System.out.println("부서진 유물 조각(중) - 없음");
                if (this.antiquity[2])
                    System.out.println("부서진 유물 조각(하) - 획득");
                else
                    System.out.println("부서진 유물 조각(하) - 없음");
                System.out.println("──────────────────────────────────────");
            }
        } else {
            System.out.println("──────────────────────────────────────");
            System.out.println("유물 지팡이 - 획득");
            System.out.println("──────────────────────────────────────");
        }
        System.out.println("그만보기 << press enter");
    }

    public void showCharacter() {
        System.out.println("──────────────────────────────────────");
        System.out.println(this.name + "(Lv : " + this.level + ")             Gold : " + this.gold);
        System.out.println("체력 : " + this.currentHP + " / " + this.maxHP);
        if (this.dayAndNight.sunny)
            System.out.println("현재 위치 : " + this.location + " (낮)");
        else
            System.out.println("현재 위치 : " + this.location + " (밤)");
        System.out.println("──────────────────────────────────────");
    }

    public void showEquip() {
        System.out.println("\"" + name + "\"의 장비");
        System.out.println("──────────────────────────────────────");
        if (weapon == null)
            System.out.println("무기 : '비어있음'");
        else
            System.out.println("무기 : " + weapon.name);
        if (helmet == null)
            System.out.println("모자 : '비어있음'");
        else
            System.out.println("모자 : " + helmet.name);
        if (plateBody == null)
            System.out.println("상의 : '비어있음'");
        else
            System.out.println("상의 : " + plateBody.name);
        if (plateLeg == null)
            System.out.println("하의 : '비어있음'");
        else
            System.out.println("하의 : " + plateLeg.name);
        System.out.println("──────────────────────────────────────");
    }

    public void fightStatus(Monster monster) {
        System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("\t\t\t\t\t" + this.name + " Lv." + this.level + " \t\t\t\t\t\t\t     " + monster.name + " Lv." + monster.level);
        System.out.println("\t\t\t\t\tHP : " + this.currentHP + " / " + this.maxHP + " \t\t\tVS.  \t\t\t " + "HP : " + monster.currentHP + " / " + monster.maxHP);
        System.out.println("     \t\t\t\t\t\t\t\t\t\t\t\t\t\t " + "EP : " + monster.currentEP + " / " + monster.maxEP);
        System.out.println("├──────────────────────────────────────────────────────────────────────────────────────────┤");
        System.out.println("      1. 공격                2. 스킬                3. 아이템                4. 도망가기 ");
        System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────┘");
        System.out.print(">>");
    }

    public void getMonster(Monster monster) {
        this.monster = monster;
    }

    public synchronized void myTurn() {
        int choice;
        this.escape = false;
        while (this.inBattle) {
            this.fightStatus(this.monster);
            choice = Game.input(4);
            if (this.inBattle) {

                if (choice == 1) {
                    //기본공격하기
                    this.basicAttack(this.monster);
                    break;
                } else if (choice == 2) {
                    //스킬 사용하기
                    if (this.role.equals("초보자")) {
                        System.out.println("\n▶ 스킬을 사용하려면 전직부터 하십시오.\n");
                    } else if (this.role.equals("데몬 헌터")) {
                        int exist, skillNum;
                        boolean enoughEP;
                        exist = ((DemonHunter) this).showSkill();
                        if (exist != 0) {
                            skillNum = Game.input(exist);
                            if (this.inBattle) {
                                if (skillNum != 0) {
                                    ((DemonHunter) this).chooseSkill(skillNum);
                                    enoughEP = ((DemonHunter) this).checkEnergy();
                                    if (enoughEP) {
                                        if (((DemonHunter) this).bufferS.type.equals("공격스킬")) {
                                            ((DemonHunter) this).useASkill(this.monster);
                                            break;
                                        } else if (((DemonHunter) this).bufferS.type.equals("보조스킬")) {
                                            ((DemonHunter) this).useBSkill();
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (choice == 3) {
                    //아이템 사용하기
                    int number;
                    boolean exist;
                    exist = this.inventory.showPotion();
                    if (exist) {
                        this.showCharacter();
                        System.out.println("\n사용할 포션을 선택하십시오.");
                        System.out.print("\n<<");
                        number = Game.input(9);
                        if (this.inBattle) {
                            if (number != 0) {
                                this.usePotion(number);
                                break;
                            }
                        }
                    }
                    else {
                        System.out.println("\n▶ 포션을 소지하고 있지 않습니다.\n");
                    }
                } else if (choice == 4) {
                    //도망치기
                    if(!this.monster.name.equals("사탄")) {
                        this.escape = true;
                        System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────┐");
                        System.out.println("                               \"" + this.monster.name + "\"와의 전투에서 벗어났습니다!");
                        System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────┘");
                        System.out.println("                                                                             >>Press Enter");
                        break;
                    }
                    else {
                        System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────┐");
                        System.out.println("                           \"" + this.monster.name + "\"와의 전투에서 벗어날 수 없습니다!");
                        System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────┘");
                    }
                }
            }

        }

    }

    public void run() {
        this.active = false;
        if (!this.isDead() && !this.monster.isDead() && !this.escape)
            this.myTurn();
        for (int i = 0; i < 2; i++) {
            if (!this.isDead() && !this.monster.isDead() && !this.escape) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            } else
                break;
        }
        this.active = true;
    }

    public void basicAttack(Monster monster) {
        int damage;
        System.out.println("┌────────────────────────────────────────┐");
        System.out.println("            \"" + this.name + "\"의 기본공격!");
        damage = (weaponAP() * 3 + random.nextInt(weaponAP())) / 4;

        damage -= monster.defensePoint;
        if (damage < 0)
            damage = 0;
        System.out.println("    \"" + monster.name + "\"이(가) " + damage + "의 피해를 받았습니다!");
        System.out.println("└────────────────────────────────────────┘");
        if (damage <= monster.currentHP)
            monster.currentHP -= damage;
        else if (damage > monster.currentHP) {
            monster.currentHP = 0;
        }
    }

    public void usePotion(int number) {
        boolean usage = false;
        if (this.inventory.potions[number - 1].HPHeal > 0) {
            if (this.currentHP == this.maxHP)
                System.out.println("▶ 체력이 이미 Max입니다.\n");
            else if (this.currentHP + this.inventory.potions[number - 1].HPHeal > this.maxHP) {
                usage = true;
                System.out.println("▶ \"" + this.name + "\"의 체력이 " + (this.maxHP - this.currentHP) + "만큼 회복하였습니다.\n");
                this.currentHP = this.maxHP;
            } else if (this.currentHP + this.inventory.potions[number - 1].HPHeal <= this.maxHP) {
                usage = true;
                System.out.println("▶ \"" + this.name + "\"의 체력이 " + this.inventory.potions[number - 1].HPHeal + "만큼 회복하였습니다.\n");
                this.currentHP += this.inventory.potions[number - 1].HPHeal;
            }
        }
        if (this.inventory.potions[number - 1].EPHeal > 0) {
            if (this.role.equals("초보자"))
                System.out.println("데몬헌터로 전직해야 쓸모가 있을 것 같다.\n");
            else {
                if (((DemonHunter) this).currentEP == ((DemonHunter) this).maxEP)
                    System.out.println("▶ 에너지가 이미 Max입니다.\n");
                else if (((DemonHunter) this).currentEP + this.inventory.potions[number - 1].EPHeal > ((DemonHunter) this).maxEP) {
                    usage = true;
                    System.out.println("▶ \"" + this.name + "\"의 에너지가 " + (((DemonHunter) this).maxEP - ((DemonHunter) this).currentEP) + "만큼 회복하였습니다.\n");
                    ((DemonHunter) this).currentEP = ((DemonHunter) this).maxEP;
                } else if (((DemonHunter) this).currentEP + this.inventory.potions[number - 1].EPHeal <= ((DemonHunter) this).maxEP) {
                    usage = true;
                    ((DemonHunter) this).currentEP += this.inventory.potions[number - 1].EPHeal;
                    System.out.println("▶ \"" + this.name + "\"의 에너지가 " + this.inventory.potions[number - 1].EPHeal + "만큼 회복하였습니다.\n");
                }
            }
        }
        if (usage) {
            this.inventory.potionQty[number - 1]--;
            if (!this.inBattle)
                Game.pause();
        }
        if (this.inventory.potionQty[number - 1] == 0)
            this.inventory.potions[number - 1] = null;
    }

    public void acquire(Monster monster) {
        int obtain;
        System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("                              \"" + monster.name + "\"를 성공적으로 사냥했습니다.");
        System.out.println("                               전리품으로 " + monster.gold + "골드를 획득하였습니다.");
        if (!this.staff) {
            obtain = random.nextInt(3);
            if (obtain == 0) {
                if (this.location.equals("나태한 자들의 평원")) {
                    if (!this.antiquity[0]) {
                        this.antiquity[0] = true;
                        System.out.println("                               부서진 유물 조각(상)을 획득하였습니다.\n");
                    }
                } else if (this.location.equals("속삭임이 가득한 숲")) {
                    if (!this.antiquity[1]) {
                        this.antiquity[1] = true;
                        System.out.println("                               부서진 유물 조각(중)을 획득하였습니다.\n");
                    }
                } else if (this.location.equals("드높은 협곡")) {
                    if (!this.antiquity[2]) {
                        this.antiquity[2] = true;
                        System.out.println("                               부서진 유물 조각(하)를 획득하였습니다.\n");
                    }
                }
            }
        }
        this.gainEXP(monster.eXP);
        System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────┘");
        System.out.println("                                                                             >>Press Enter");
        Game.pause();
        this.gold += monster.gold;
        if (this.role.equals("데몬 헌터")) {
            ((DemonHunter) this).bonusAP = 0;
            ((DemonHunter) this).bonusDP = 0;
        }

        if (this.location.equals("드높은 협곡"))
            this.arroganceRedemption++;
        else if (this.location.equals("속삭임이 가득한 숲"))
            this.lustRedemption++;
        else if (this.location.equals("나태한 자들의 평원"))
            this.indolenceRedemption++;
    }

    public boolean isDead() {
        if (this.currentHP == 0)
            return true;
        else if (this.currentHP > 0)
            return false;
        else {
            System.out.println(this.name + "의 체력 관련 오류 발생");
            return false;
        }
    }

    public void exit() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("┌────────────────────────────────────────────────────┐");
        System.out.println("          주변이 캄캄해지고 모든 기억이 소실됩니다.");
        System.out.println("└────────────────────────────────────────────────────┘");
        if (this.arroganceRedemption >= 5)
            redemption++;
        if (this.lustRedemption >= 5)
            redemption++;
        if (this.indolenceRedemption >= 5)
            redemption++;
        if (redemption == 0) {
            destination = "식물";
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣤⣤⣤⣤⣄⣀⡀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣶⣿⡟⣿⣿⣿⣿⣿⣿⣿⠋⠁");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣶⣿⣿⣿⣿⣦⡀⠀⠀⠀⠀⢀⣴⣾⡿⣿⣿⣿⡟⣾⡟⢸⠫⣡⣿⡿⠁⠀⠀");
            System.out.println("⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⡿⣑⣟⡕⠄⠀⠀⣰⣿⣿⣿⠛⣯⢘⡏⠀⣃⢃⣿⣚⣶⠟⠁⠀⠀⠀");
            System.out.println("⠹⣿⣿⠻⣿⣿⣿⣿⣿⣶⣶⣦⣤⣄⣀⠀⠀⠀⣾⣿⣻⣿⣦⢱⡾⠟⡾⢶⡀⡀⢀⣿⡇⣽⡟⠘⠃⢸⠅⣠⣷⡉⣩⡾⠋⠀⠀⠀⠀");
            System.out.println("⠀⠹⣿⠷⠟⠿⣍⠻⠟⢝⠻⡟⠿⣿⣿⣿⣶⣼⣿⡹⠷⠝⠻⣳⣱⠞⣱⢿⣷⣷⣼⣻⡅⢸⠀⢰⡇⡸⢚⢁⣼⣿⠋⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠙⠿⠶⠖⠚⠷⠄⡈⢃⡈⡖⣞⠫⠺⣿⠿⣿⣿⣾⣗⡐⢸⠀⢚⠋⣿⣿⣵⣷⢿⣣⠙⡄⢠⠟⢛⣿⣿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠚⢿⣛⠛⢒⣀⣙⣓⠅⡨⠀⠀⠀⡤⠛⡽⢿⣿⣿⣾⣶⣤⣾⣿⣿⡟⢿⡌⢷⢀⠟⣑⣾⣿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠻⢿⠏⠻⠟⢓⢂⣉⣁⡂⢧⡀⡀⠃⠸⢿⣿⣿⣿⣿⣿⣿⡯⡿⣜⢗⣼⣯⣬⣿⣿⣿⣶⣶⣶⣶⣦⣤⡀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠙⠺⣿⡿⠿⣋⣠⣐⣪⠽⠓⢤⣐⢸⢿⣿⣿⣿⣿⣿⣷⣇⣻⣞⣷⣿⣿⣿⣿⣿⣿⢿⣻⣿⠿⢿⣿⣧⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⢿⠿⠉⢨⣮⡶⢩⣽⢾⣺⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⠟⡔⠟⣫⡶⠆⢞⣻⣿⡆⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⢟⣃⣶⣾⢋⣼⣿⣿⣿⣿⣿⣿⣿⣿⢟⣿⣿⡿⡟⠱⠁⠘⡀⠝⠭⠂⠌⡻⢼⣿⡗⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠓⠿⣏⣿⣿⣿⣿⣿⣿⡿⠃⣾⣿⠟⡵⣰⠖⢩⣭⠉⣽⣥⣷⣲⣶⣾⡿⠿⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠛⣿⣿⣽⠇⣐⣿⣿⣿⣷⣿⠿⢟⠏⣽⣽⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣴⣶⣦⣤⡀⠀⠀⢻⣿⣿⢈⣹⣿⣿⣿⠻⢉⣠⣺⣿⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⢸⣿⣷⣿⣿⣿⢿⢏⢷⣽⡿⠟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⢸⣿⣿⣿⣿⣿⣸⣿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⠿⣿⣿⣯⣿⢻⣯⣭⣭⣾⣿⣿⡘⣿⡿⠿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠛⠻⠿⢿⣿⣿⣿⣿⣿⣟⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣶⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        } else if (redemption == 1) {
            destination = "벌레";
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣤⡀⠀⠀⠀⠀⠀⠠⡀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠸⠿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⡦⣀⠀⢰⣶⣾⣷⣾⣿⣦⠀⠀⢀⢠⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⡄⣿⣿⣿⣿⣿⣿⣿⡇⢀⣾⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣧⢿⣿⣿⣿⣿⣿⣿⠇⣸⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⡄⠀⢀⣀⠄⣻⣿⣯⣿⣿⣿⣿⣿⣟⣴⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⣠⣄⢠⣾⠀⠈⠉⠉⠈⠛⣿⣿⣿⠙⠋⣀⣤⣴⣦⣵⡌⠻⠟⣵⣾⣶⡦⠀⠽⣿⣦⠀⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣷⣶⣿⣿⠏⠀⠀⠀⠀⠀⠀⠉⠻⠃⠾⠿⠛⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣤⣄⣥⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⣠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣶⣿⠿⠿⠿⣿⣮⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠄⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣦⡤⠊⠁⠀⠀⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠁⠺⣿⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠿⠟⠁⠀⠀⠀⠀⠀⠸⣿⣾⣿⣿⣟⣿⣿⣿⠻⣿⣿⣽⣿⠇⠀⠀⠀⠈⠙⠂⣠⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⣠⣶⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠞⠋⠀⣿⣄⠿⠻⠿⣴⣿⣿⠧⠃⠀⠀⠀⠀⠀⠀⠀⠈⠛⠿⣀⣀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⢀⣴⣦⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⡷⠀⠀⢰⡿⠇⠀⠀⠀⠈⢻⣟⣰⣾⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣷⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠚⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⠃⠀⠀⣴⣧⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⡧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠀⠀⠀⣿⠇⠀⠀⠀⠀⠀⠀⠀⠉⠁⠈⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠿⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        } else if (redemption == 2) {
            destination = "동물";
            System.out.println("⠀⠀⠀⠀⠀⠀⢀⣤⣶⣾⣿⣿⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⣀⣤⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⢴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠈⠻⠿⡿⠿⠿⠛⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣆⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⡟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⡇⢸⣿⣿⣿⠇⠀⠉⢉⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠁⢸⣿⣿⣿⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠘⣶⣦⡀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀⢸⣿⣿⡧⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⣸⣿⣿");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀⢸⣿⣿⠃⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣶⣤⣤⣴⣿⣿⠇");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣼⣿⣿⠀⣼⣿⡟⣠⣶⣶⣶⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠙⠿⣿⣿⣿⣿⣿⣿⡿⠃⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠿⠿⠿⠿⠿⠿⠿⠃⠀⠉⠉⠉⠉⠹⠿⣿⡿⠿⠿⠿⠿⠿⠿⠿⠿⠛⠁⠀⠀⠀⠉⠀⠉⠀⠀⠀⠀⠀");
        } else if (redemption == 3) {
            destination = "사람";
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢄⣀⣄⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣿⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣿⣆⠀⠀⠀⠀⠀⠀⢀⣀⣼⣿⣿⣿⣦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣽⣿⣦⣀⠀⢀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠉⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣿⣿⡇⢹⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⡇⠘⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⡇⠀⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⠇⠀⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⠀⠀⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⣿⣿⡿⠀⠀⢿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠋⠉⠉⠉⠀⠀⣴⣿⡿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        }
        System.out.println("┌────────────────────────────────────────────────────┐");
        System.out.println("              당신은 " + destination + "(으)로 환생하였습니다.");
        System.out.println("└────────────────────────────────────────────────────┘");
        this.bgmPlayer.interrupt();
        this.bgm.exit();
    }
}
