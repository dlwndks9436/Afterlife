public class DayAndNight implements Runnable {

    boolean sunny;

    public void timeflow() {
        while (true) {
            if (sunny) {
                sunny = false;
                System.out.println("   ●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●● 해가 졌습니다 ●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
                try {
                    for (int i = 0; i < 120; i++) {
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                sunny = true;
                System.out.println("   ○○○○○○○○○○○○○○○○○○○○○○ 해가 떴습니다 ○○○○○○○○○○○○○○○○○○○○○○");
                try {
                    for (int i = 0; i < 120; i++) {
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timeflow();
    }
}
