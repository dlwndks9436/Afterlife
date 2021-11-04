import java.io.IOException;
import java.util.Scanner;


public class Game {

    static public void pause() {
        try {
            System.in.read();
        } catch (IOException e) {
        }
    }

    public static int input(int standard) {
        Scanner scanner = new Scanner(System.in);
        int result;
        do {
            result = scanner.nextInt();
        } while (!(result >= 0 && result <= standard));
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        //변수 생성
        boolean nameDecided = false;

        //인스턴트 생성
        Scanner scanner = new Scanner(System.in);
        Novice character = new Novice();
        character.inventory = new Inventory();
        ShopWeapon shopWeapon = new ShopWeapon();
        ShopArmor shopArmor = new ShopArmor();
        ShopGeneral shopGeneral = new ShopGeneral();
        shopGeneral.setChar(character);
        Elder elder = new Elder();
        BurnHealth burnHealth = new BurnHealth();
        DayAndNight dayAndNight = new DayAndNight();
        Thread time = new Thread(dayAndNight);
        time.setDaemon(true);
        character.getDayAndNight(dayAndNight);
        //게임 시작
        character.move("시작");
        System.out.println("     ___       _______ .___________. _______ .______       __       __   _______  _______");
        System.out.println("    /   \\     |   ____||           ||   ____||   _  \\     |  |     |  | |   ____||   ____|");
        System.out.println("   /  ^  \\    |  |__   `---|  |----`|  |__   |  |_)  |    |  |     |  | |  |__   |  |__");
        System.out.println("  /  /_\\  \\   |   __|      |  |     |   __|  |      /     |  |     |  | |   __|  |   __|");
        System.out.println(" /  _____  \\  |  |         |  |     |  |____ |  |\\  \\----.|  `----.|  | |  |     |  |____");
        System.out.println("/__/     \\__\\ |__|         |__|     |_______|| _| `._____||_______||__| |__|     |_______|");
        pause();
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("나 : \".........................................으으.......\"       ");
        pause();
        System.out.println("눈을 떠보니 나는 낯선 곳에 누워있었다.");
        System.out.println("정신 차리고 주변을 돌아보니 세상이 온통 빨간색이고, 들어본 적이 없는 괴기한 울음소리들이 들린다.");
        pause();
        System.out.println("??? : 어서오게나. 나는 이 마을의 장로라네");
        pause();
        System.out.println("장로 : 자네의 이름이 뭔가?\n");

        //이름 정하기
        while (!nameDecided) {
            int nameConfirm = 0;
            System.out.println("────────────────────────────────────────────");
            System.out.println("캐릭터의 이름을 입력하십시오.");
            System.out.print(">>");
            character.submitName(scanner.next());
            while (nameConfirm != 1) {
                nameConfirm = scanner.nextByte();
                if (nameConfirm == 0)
                    break;
            }
            if (nameConfirm == 1)
                nameDecided = true;
        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        //게임 설명

        System.out.println("\"" + character.name + ": 제 이름은 \"" + character.name + "\"입니다.");
        pause();
        System.out.println("장로 : 그렇군, 반갑네 \"" + character.name + "\"군");
        System.out.println("장로 : 여기는 지옥이라네, 자네가 여기 온 이유는 자네가 죽었기 때문이지");
        pause();
        System.out.println("그랬다, 기억이 났다.");
        System.out.println("나는 죽었었다. 근데 지옥이라니...");
        pause();
        System.out.println("장로 : 지옥에 온 자는 이승에서 저질렀던 죄악들을 맞딱드려야하네.");
        System.out.println("장로 : 각 죄악을 정화하기 위해서는 악마들을 5회 이상 죽여야하네 .");
        System.out.println("장로 : 여기서 이룬 업적에 따라 다음 생이 결정된다네.");
        pause();
        System.out.println("장로 : 데몬헌터로 전직하고 싶으면 언제든지 나를 찾아오게나.");
        System.out.println("장로 : 전직은 레벨 5 이상부터 시도해볼 수 있다네.");
        System.out.println("장로 : 몬스터 사냥하기 벅찰 때 마을에 있는 상점에서 아이템을 구매해보면 도움이 될 걸세.");
        pause();
        System.out.println("장로 : 이대로 마을에서 나가면 자네는 죽은 목숨이나 다름없네.");
        System.out.println("장로 : 여기 500골드를 줄테니, 요긴하게 쓰게나.");
        character.gold += 500;
        System.out.println("장로 : 행운을 비네.");
        pause();
        character.move("마을");
        time.start();

        while (true) {
            int choice;
            //캐릭터 행동 선택
            if (character.location.equals("나태한 자들의 평원")
                    || character.location.equals("속삭임이 가득한 숲")
                    || character.location.equals("드높은 협곡")) {
                //캐릭터가 '사냥터에' 있을 때 선택할 수 있는 행동
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                character.showCharacter();
                System.out.println("──────────────────────────────────────");
                System.out.println("1. 몬스터 사냥");
                System.out.println("2. 마을로 이동");
                System.out.println("3. 캐릭터 상태창");
                System.out.println("4. 캐릭터 장비");
                System.out.println("5. 인벤토리");
                System.out.println("6. 진행 상황");
                System.out.println("10. 환생");
                System.out.println("──────────────────────────────────────");
                System.out.print("\n>>");
                choice = input(10);
                if (choice == 1) {
                    //1.몬스터와 싸우기
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    Monster monster = null;
                    Thread monThread = null;
                    Thread burn;
                    Thread charThread = null;
                    if (character.location.equals("나태한 자들의 평원")) {
                        monster = new Gluttony(1);
                    } else if (character.location.equals("속삭임이 가득한 숲")) {
                        monster = new Succubus(5);
                    } else if (character.location.equals("드높은 협곡")) {
                        monster = new Gargoyle(10);
                    }
                    monster.setEnemy(character);
                    character.getMonster(monster);
                    monster.active = true;
                    character.active = true;
                    burnHealth.active = true;
                    character.inBattle = true;
                    character.escape = false;
                    burnHealth.on = true;
                    burnHealth.setObject(monster, character, dayAndNight);
                    while (true) {
                        if (monster.active) {
                            if (character.location.equals("나태한 자들의 평원"))
                                monThread = new Thread((Gluttony) monster);
                            else if (character.location.equals("속삭임이 가득한 숲"))
                                monThread = new Thread((Succubus) monster);
                            else if (character.location.equals("드높은 협곡"))
                                monThread = new Thread((Gargoyle) monster);
                            monThread.start();
                        }
                        if (character.active) {
                            charThread = new Thread(character);
                            charThread.start();
                        }
                        if (burnHealth.active) {
                            burn = new Thread(burnHealth);
                            burn.start();
                        }
                        if (character.isDead() || monster.isDead() || character.escape) {
                            try {
                                character.inBattle = false;
                                burnHealth.exit();
                                monThread.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                    }
                    if (character.isDead()) {
                        character.exit();
                        break;
                    } else if (monster.isDead()) {
                        character.acquire(monster);
                    }
                    if (character.escape)
                        pause();
                } else if (choice == 2) {
                    //2.마을로 이동하기
                    character.move("마을");
                } else if (choice == 3) {
                    //3.캐릭터 상태 확인하기
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    character.showStatus();
                    pause();
                } else if (choice == 4) {
                    //4.캐릭터 장비 확인
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    character.showEquip();
                    pause();
                } else if (choice == 5) {
                    //4.인벤토리 확인하기
                    while (true) {
                        int category;
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        System.out.println("인벤토리");
                        System.out.println("──────────────────────────────────────");
                        System.out.println("1.무기");
                        System.out.println("2.방어구");
                        System.out.println("3.포션");
                        System.out.println("0.나가기");
                        System.out.println("──────────────────────────────────────");
                        System.out.print("\n>>");
                        category = scanner.nextByte();
                        if (category == 0)
                            break;
                        else if (category == 1 || category == 2) {
                            while (true) {
                                int equipment;
                                boolean exist;
                                if (category == 1) {
                                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    exist = character.inventory.showWeapon();
                                } else {
                                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    exist = character.inventory.showArmor();
                                }
                                if (exist) {
                                    System.out.println("------------------");
                                    System.out.println("착용할 장비를 선택하십시오.");
                                    System.out.print("\n>>");
                                    equipment = input(9);
                                    if (equipment == 0)
                                        break;
                                    else if (equipment > 0 && equipment < 10) {
                                        if (category == 1) {
                                            character.equipWeapon(equipment);
                                        } else {
                                            character.equipArmor(equipment);
                                        }
                                        break;
                                    }
                                }
                                if (!exist) {
                                    System.out.println("▶ 장비를 소지하고 있지 않습니다.\n");
                                    pause();
                                    break;
                                }
                            }
                        } else if (category == 3) {
                            //물약확인하기 & 물약 마시기
                            int number;
                            boolean exist;
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            exist = character.inventory.showPotion();
                            if (exist) {
                                character.showCharacter();
                                System.out.println("\n사용할 포션을 선택하십시오.");
                                System.out.print("\n<<");
                                number = input(9);
                                if(number != 0) {
                                    character.usePotion(number);
                                }
                            }
                            else {
                                System.out.println("\n▶ 포션을 소지하고 있지 않습니다.\n");
                                pause();
                            }

                        }
                    }
                } else if (choice == 6) {
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    character.showCurrentStatus();
                    pause();
                } else if (choice == 10) {
                    character.exit();
                    break;
                }
            }
            //캐릭터가 '마을에' 있을 때 선택할 수 있는 행동
            else if (character.location.equals("마을")) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                character.showCharacter();
                System.out.println("──────────────────────────────────────");
                System.out.println("1. 장로와 대화");
                System.out.println("2. 무기상점");
                System.out.println("3. 방어구상점");
                System.out.println("4. 잡화점");
                System.out.println("5. 사냥터로 이동");
                System.out.println("6. 캐릭터 상태창");
                System.out.println("7. 캐릭터 장비");
                System.out.println("8. 인벤토리");
                System.out.println("9. 진행 상황");
                System.out.println("10. 환생");
                System.out.println("──────────────────────────────────────");
                System.out.print("\n>>");
                choice = input(10);
                if (choice == 1) {
                    //1.장로한테 말걸기
                    int choice2;
                    System.out.println("장로 : " + character.name + "군, 오늘도 무사하구만. 내가 도울 일 있는가?");
                    if (character.antiquity[0] || character.antiquity[1] || character.antiquity[2]) {
                        if (!elder.checkAntiquity) {
                            System.out.println("장로 : 오오오 자네!! 지금 들고 있는 것 좀 보여 줄 수 있겠나?");
                            pause();
                            System.out.println("장로 : 틀림 없군!! 이건 악마들을 퇴치할 때 쓰였던 오래된 지팡이의 조각이라네");
                            System.out.println("장로 : 이 지팡이는 신비한 힘이 깃들어 있는 것으로 전해져있다네");
                            System.out.println("장로 : 재료만 있다면 온전히 복원할 수 있을게야.");
                            System.out.println("장로 : 혹여나 다른 조각들을 찾거늘 나에게 꼭 찾아와주게나.\n");
                            elder.checkAntiquity = true;
                        }
                    }
                    if (character.role.equals("초보자")) {
                        System.out.println("1. 전직하기\t 0. 취소");
                        choice2 = input(1);
                        if (choice2 == 1) {
                            //전직하기
                            if (character.level < 5) {
                                System.out.println("장로 : 전직은 레벨 5 이상부터 할 수 있다네.");
                                System.out.println("장로 : 더 강해지면 다시 찾아와주게나.");
                                pause();
                            } else {
                                int choice3;
                                System.out.println("장로 : 축하한다네, 자네는 이제 전직이 가능하다네.");
                                System.out.println("장로 : 지금 전직을 하겠는가?");
                                System.out.println("1.네\t 2.아니오");
                                choice3 = input(3);
                                if (choice3 == 1) {
                                    //전직 시작
                                    character = new DemonHunter(character);
                                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────┐");
                                    System.out.println("   ◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁◁ 데몬헌터 전직 ▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷");
                                    System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────┘");
                                    System.out.println("\n장로 : 자네는 앞으로 데몬헌터라네.");
                                    System.out.println("장로 : 앞으로 스킬을 구매하고 싶으면 나한테 오게.");
                                } else if (choice3 == 2) {
                                    //전직 거부
                                    System.out.println("장로 : 준비가 되면 다시 오게나.");
                                    pause();
                                }
                            }
                        }
                    } else {
                        if (!character.staff) {
                            System.out.println("1. 스킬 구매하기\t 2.지팡이 복원하기\t 0. 취소");
                            choice2 = input(2);
                            if (choice2 == 1) {
                                int choice3;
                                while (true) {
                                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    elder.showItem(1);
                                    choice3 = input(4);
                                    if (choice3 == 0)
                                        break;
                                    else {
                                        int choice4;
                                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                        character.showCharacter();
                                        elder.showItemSpec(1, choice3);
                                        choice4 = input(1);
                                        if (choice4 == 1) {
                                            Skill skill;
                                            skill = elder.sellSkill(choice3);
                                            ((DemonHunter) character).buySkill(skill);
                                        }
                                    }
                                }
                            } else if (choice2 == 2) {
                                if (elder.fixingAntiquity) {
                                    if (elder.antiquityReady) {
                                        System.out.println("장로 : 복원이 다 끝났다네!");
                                        System.out.println("장로 : 이 지팡이가 그 대의 앞길을 밝혀주길 바라네.");
                                        System.out.println("\n▶ 유물 지팡이를 획득했다!\n");
                                        character.antiquity[0] = false;
                                        character.antiquity[1] = false;
                                        character.antiquity[2] = false;
                                        character.staff = true;
                                        pause();
                                    } else {
                                        System.out.println("장로 : 아직 지팡이를 복원하고 있는 중일세");
                                        System.out.println("장로 : 이따가 다시 찾아오게나.");
                                    }
                                } else if (character.antiquity[0] && character.antiquity[1] && character.antiquity[2]) {
                                    System.out.println("장로 : 오오오! 조각들을 다 찾아왔구먼!");
                                    System.out.println("장로 : 지팡이를 다시 쓸 수 있도록 내가 복원해주겠네. ");
                                    System.out.println("장로 : 시간이 좀 걸릴테니 시간이 지나면 다시 찾아와주게나.");
                                    elder.fixingAntiquity = true;
                                    Thread thread = new Thread(elder);
                                    thread.start();
                                } else {
                                    System.out.println("장로 : 아직 조각들이 부족하구먼..");
                                    System.out.println("장로 : 다 모이면 다시 찾아와주게나.");
                                }
                            }
                        } else {
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            System.out.println("1. 스킬 구매하기\t 0. 취소");
                            choice2 = input(1);
                            if (choice2 == 1) {
                                int choice3;
                                while (true) {
                                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    elder.showItem(1);
                                    choice3 = input(4);
                                    if (choice3 == 0)
                                        break;
                                    else {
                                        int choice4;
                                        character.showCharacter();
                                        elder.showItemSpec(1, choice3);
                                        choice4 = input(1);
                                        if (choice4 == 1) {
                                            Skill skill;
                                            skill = elder.sellSkill(choice3);
                                            ((DemonHunter) character).buySkill(skill);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    System.out.println("장로 : 부디 몸 조심하게나.\n");
                    pause();
                } else if (choice == 2) {
                    //2.무기상점 가기
                    while (true) {
                        int choice2;
                        int choice3;
                        int choice4;
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        System.out.println("무기 상점 메뉴");
                        System.out.println("──────────────────────────────────────");
                        System.out.println("1.무기 구매");
                        System.out.println("2.무기 판매");
                        System.out.println("0.나가기");
                        System.out.println("──────────────────────────────────────");
                        System.out.print(">>");
                        choice2 = input(2);
                        if (choice2 == 1) {
                            while (true) {
                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                character.showCharacter();
                                shopWeapon.showItem(1);
                                choice3 = input(7);
                                if (choice3 == 0) //무기 선택 취소
                                    break;
                                else {
                                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    character.showCharacter();
                                    shopWeapon.showItemSpec(1,choice3);
                                    choice4 = input(1);
                                    if (choice4 == 1) {
                                        shopWeapon.sellWeapon(character, choice3);
                                        break;
                                    }
                                }
                            }
                        } else if (choice2 == 2) {
                            int equipment;
                            boolean exist;
                            while (true) {
                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                character.showCharacter();
                                exist = character.inventory.showWeapon();
                                System.out.println("──────────────────────────────────────");
                                System.out.println("판매할 장비를 선택하십시오.");
                                System.out.print("\n>>");
                                if (exist) {
                                    equipment = input(9);
                                    if (equipment == 0)
                                        break;
                                    else if (equipment > 0 && equipment < 10) {
                                        shopWeapon.buyWeapon(character, equipment);
                                        break;
                                    }
                                } else
                                    break;
                            }
                        } else if (choice2 == 0) {
                            //나가기
                            break;
                        }
                    }
                } else if (choice == 3) {
                    //3.방어구상점 가기
                    while (true) {
                        int choice2;
                        int choice3;
                        int choice4;
                        int choice5;
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        character.showCharacter();
                        System.out.println("방어구 상점 메뉴");
                        System.out.println("──────────────────────────────────────");
                        System.out.println("1.방어구 구매");
                        System.out.println("2.방어구 판매");
                        System.out.println("0.나가기");
                        System.out.println("──────────────────────────────────────");
                        System.out.print(">>");
                        choice2 = input(2);
                        if (choice2 == 1) {
                            while (true) {
                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                character.showCharacter();
                                System.out.println("──────────────────────────────────────");
                                System.out.println("1.헬멧 구매");
                                System.out.println("2.플레이트 바디 구매");
                                System.out.println("3.플레이드 레그 구매");
                                System.out.println("0.나가기");
                                System.out.println("──────────────────────────────────────");
                                System.out.print(">>");
                                choice3 = input(3);
                                if (choice3 == 0)
                                    break;
                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                character.showCharacter();
                                shopArmor.showItem(choice3);
                                choice4 = input(7);
                                if (choice4 == 0) //방어구 선택 취소
                                    break;
                                else {
                                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    character.showCharacter();
                                    shopArmor.showItemSpec(choice3, choice4);
                                    choice5 = input(1);
                                    if (choice5 == 1) {
                                        Armor armor;
                                        shopArmor.sellArmor(character, choice3, choice4);
                                        break;
                                    }
                                }
                            }
                        } else if (choice2 == 2) {
                            int equipment;
                            boolean exist;
                            while (true) {
                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                character.showCharacter();
                                exist = character.inventory.showArmor();
                                System.out.println("──────────────────────────────────────");
                                System.out.println("판매할 장비를 선택하십시오.");
                                System.out.print("\n>>");
                                if (exist) {
                                    equipment = input(9);
                                    if (equipment == 0)
                                        break;
                                    else if (equipment > 0 && equipment < 10) {
                                        shopArmor.buyArmor(character, equipment);
                                        break;
                                    }
                                } else
                                    break;
                            }
                        } else if (choice2 == 0) {
                            //나가기
                            break;
                        }
                    }
                } else if (choice == 4) {
                    //4.잡화점 가기
                    while (true) {
                        int choice2;
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        character.showCharacter();
                        System.out.println("잡화점 메뉴");
                        System.out.println("──────────────────────────────────────");
                        System.out.println("1.포션 구매");
                        System.out.println("2.캐릭터 치료");
                        System.out.println("3.햇빛 보호 효과 받기");
                        System.out.println("0.나가기");
                        System.out.println("──────────────────────────────────────");
                        System.out.print(">>");
                        choice2 = input(3);
                        if (choice2 == 1) {
                            //포션 구매
                            while (true) {
                                int choice3;
                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                character.showCharacter();
                                shopGeneral.showPotion();
                                choice3 = input(6);
                                if (choice3 == 0) //포션 선택 취소
                                    break;
                                else {
                                    int quantity;
                                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    character.showCharacter();
                                    shopGeneral.showPotionSpec( choice3);
                                    quantity = scanner.nextInt();
                                    if (quantity == 0)
                                        break;
                                    else if (quantity > 0) {
                                        shopGeneral.sellPotion(character, choice3, quantity);
                                    }
                                }
                            }
                        } else if (choice2 == 2) {
                            //캐릭터 치유
                            int choice3;
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            character.showCharacter();
                            System.out.println("──────────────────────────────────────");
                            System.out.println("치료 받으시겠습니까?  [100 골드]");
                            System.out.println("1.네          0.아니오");
                            System.out.println("──────────────────────────────────────");
                            choice3 = input(1);
                            if (choice3 == 1) {
                                shopGeneral.heal(character);
                            }
                        } else if (choice2 == 3) {
                            //캐릭터 햇빛 보호 효과 받기
                            int choice3;
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            character.showCharacter();
                            System.out.println("──────────────────────────────────────");
                            System.out.println("햇빛 보호 효과를 받으시겠습니까?  [50]골드");
                            System.out.println("1.네          0.아니오");
                            System.out.println("──────────────────────────────────────");
                            choice3 = input(1);
                            if (choice3 == 1) {
                                boolean enoughMoney;
                                enoughMoney = shopGeneral.sellProtect();
                                if (enoughMoney) {
                                    if(character.protection > 0){
                                        character.protection = 0;
                                    }
                                    System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────┐");
                                    System.out.println("                                   햇빛 보호 작업 시작합니다");
                                    Thread.sleep(2000);
                                    character.protection = 2400;
                                    Thread thread = new Thread(shopGeneral);
                                    thread.start();
                                    System.out.println("                                 햇빛 보호 작업이 완료되었습니다.");
                                    System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────┘");
                                    pause();
                                }
                            }
                        } else if (choice2 == 0)
                            break;
                    }
                } else if (choice == 5) {
                    //5.사냥터로 이동하기
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    character.showCharacter();
                    System.out.println("──────────────────────────────────────");
                    System.out.println("어디로 이동하시겠습니까?");
                    System.out.println("1. 나태한 자들의 평원 (권장 level 1~5)");
                    System.out.println("2. 속삭임이 가득한 숲 (권장 level 5~10)");
                    System.out.println("3. 드높은 협곡 (권장 level 10~15)");
                    System.out.println("4. 붉은 왕좌 (권장 level ???)");
                    System.out.println("0. 취소");
                    System.out.println("──────────────────────────────────────");
                    System.out.print("\n>>");
                    while (true) {
                        int choice2;
                        choice2 = input(4);
                        if (choice2 == 0)
                            break;
                        else {
                            switch (choice2) {
                                case 1:
                                    character.move("나태한 자들의 평원");
                                    break;
                                case 2:
                                    character.move("속삭임이 가득한 숲");
                                    break;
                                case 3:
                                    character.move("드높은 협곡");
                                    break;
                                case 4:
                                    character.move("붉은 왕좌");
                                    break;
                            }
                        }
                        if (!character.location.equals("마을"))
                            break;
                    }
                } else if (choice == 6) {
                    //6.캐릭터 상태 확인하기
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    character.showStatus();
                    pause();
                } else if (choice == 7) {
                    //캐릭터 장비 확인하기
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    character.showEquip();
                    pause();
                } else if (choice == 8) {
                    //7.인벤토리 확인하기
                    while (true) {
                        int category;
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        character.showCharacter();
                        System.out.println("인벤토리");
                        System.out.println("──────────────────────────────────────");
                        System.out.println("1.무기");
                        System.out.println("2.방어구");
                        System.out.println("3.포션");
                        System.out.println("0.나가기");
                        System.out.println("──────────────────────────────────────");
                        System.out.print("\n>>");
                        category = scanner.nextByte();
                        if (category == 0)
                            break;
                        else if (category == 1 || category == 2) {
                            while (true) {
                                int equipment;
                                boolean exist;
                                if (category == 1) {
                                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    character.showCharacter();
                                    exist = character.inventory.showWeapon();
                                } else {
                                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    character.showCharacter();
                                    exist = character.inventory.showArmor();
                                }
                                if (exist) {
                                    System.out.println("──────────────────────────────────────");
                                    System.out.println("착용할 장비를 선택하십시오.");
                                    System.out.print("\n>>");
                                    equipment = input(9);
                                    if (equipment == 0)
                                        break;
                                    else if (equipment > 0 && equipment < 10) {
                                        if (category == 1) {
                                            character.equipWeapon(equipment);
                                        } else {
                                            character.equipArmor(equipment);
                                        }
                                        break;
                                    }
                                }
                                if (!exist) {
                                    System.out.println("▶ 장비를 소지하고 있지 않습니다.\n");
                                    pause();
                                    break;
                                }
                            }
                        } else if (category == 3) {
                            //물약확인하기 & 물약 마시기
                            int number;
                            boolean exist;
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            exist = character.inventory.showPotion();
                            if (exist) {
                                character.showCharacter();
                                System.out.println("\n사용할 포션을 선택하십시오.");
                                System.out.print("\n<<");
                                number = input(9);
                                if(number != 0) {
                                    character.usePotion(number);
                                }
                            }
                            else {
                                System.out.println("\n▶ 포션을 소지하고 있지 않습니다.\n");
                                pause();
                            }
                        }
                    }
                } else if (choice == 9) {
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    character.showCurrentStatus();
                    pause();
                } else if (choice == 10) {
                    character.exit();
                    break;
                }
            } else {
                //사탄이랑 싸우기
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                Thread monThread = null;
                Thread charThread = null;
                Monster monster = new Satan();
                monster.setEnemy(character);
                character.getMonster(monster);
                monster.active = true;
                character.active = true;
                character.inBattle = true;
                character.escape = false;
                while (true) {
                    if (monster.active) {
                        monThread = new Thread((Satan) monster);
                        monThread.start();
                    }
                    if (character.active) {
                        if (!character.staff) {
                            charThread = new Thread(character);
                            charThread.start();
                        } else {
                            character.active = false;
                            Thread.sleep(6000);
                            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ 가방에 있는 스태프가 빛난다. ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                            Thread.sleep(2000);
                            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━ 스태프의 요란한 빛이 \"사탄\"을 향해 쏘아졌다. ━━━━━━━━━━━━━━━━━━━━━━━━━");
                            Thread.sleep(1000);
                            System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────┐");
                            System.out.println("                                사탄 : 끄아아아아아아아악!!!!");
                            System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────┘");
                            monster.currentHP = 0;
                        }
                    }
                    if (character.isDead() || monster.isDead() || character.escape) {
                        try {
                            character.inBattle = false;
                            monThread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
                if (character.isDead()) {
                    character.exit();
                    break;
                } else if (monster.isDead()) {
                    //엔딩
                    Thread.sleep(2000);
                    System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────┐");
                    System.out.println("                         사탄 : 필멸자 따위가 나를 이기다니이이이!!!!");
                    System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────┘");
                    Thread.sleep(3000);
                    System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────┐");
                    System.out.println("                              사탄 : 말도 안돼애애애애애애애!!!!");
                    System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────┘");
                    Thread.sleep(4000);
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡉⢠⠀⠉⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣧⠸⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⢹⡀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠈⣇⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡆⢹⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⡤⠤⠤⠤⠤⠤⠤⠤⠤⠼⣷⠈⠀⢀⠧⠤⠤⠤⠤⠤⠤⠤⠤⢤⣤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡗⡄⢀⣀⣀⣀⣀⣀⣀⣀⣈⣢⣄⣁⣀⣀⣀⣀⣀⣀⣀⣀⣠⠂⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣟⣓⣒⣶⣶⣤⣤⣌⣉⣉⢽⡇⠈⠻⣲⣶⣦⣤⣈⣉⣙⣓⣲⣧⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⢷⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣇⢸⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⢿⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⢰⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠘⡇⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡆⠀⠀⠀");
                    System.out.println("⠀⠀⠀⢾⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡄⢻⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡷⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠸⡳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣧⠘⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢞⡇⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⢷⣟⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⢹⡄⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣫⡾⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠹⣷⣌⠑⠦⣄⡀⠀⠀⠀⠀⠀⠀⠀⣠⣶⡿⢸⠈⣇⠀⠀⡇⢿⣶⣄⠀⠀⠀⠀⠀⠀⠀⢀⣠⠴⠊⣡⣾⠏⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠈⠻⢿⣷⣴⣨⡛⠶⢤⣀⠀⠀⣾⣿⣿⡏⠸⠆⢹⠀⠀⡇⢛⣛⣿⣷⠀⠀⣀⡤⠶⢛⣅⣷⣾⡿⠟⠁⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠿⣿⣷⣤⡉⠳⡄⣿⣿⣷⣷⠃⣷⡐⣄⢤⡐⢸⣿⣿⣿⢠⠞⢉⣤⣾⣿⡿⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠿⣿⣶⡇⢻⣿⣿⣿⣆⣿⣷⣿⣿⣷⣽⣿⢿⡞⢸⣶⣿⠿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣽⢃⣿⣿⣽⣟⣿⣿⣿⣿⣻⣭⣾⣿⡘⣯⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⡾⠟⠓⠒⠽⣿⡟⢻⡿⠡⠒⠒⠻⢷⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡇⠀⠀⠀⠘⢽⣷⣞⡥⠃⠀⠀⠀⢸⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣤⡇⠀⠀⣀⣀⣤⣼⣧⣤⣀⣀⠀⠀⢸⣤⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⠷⠶⠿⣻⣿⡿⠁⠈⢿⣿⣟⠿⠶⠾⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣦⣼⣧⣴⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣿⣟⣻⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                    System.out.println("\n┌────────────────────────────────────────────────────┐");
                    System.out.println("                  사탄을 처치하였습니다.");
                    System.out.println("   이제 지옥은 당신의 손 아래에 질서를 되찾을 수 있을 것입니다.");
                    System.out.println("└────────────────────────────────────────────────────┘");
                    character.bgmPlayer.interrupt();
                    character.bgm.exit();
                    break;
                }
            }
        }
    }
}