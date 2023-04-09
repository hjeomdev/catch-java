package thread.daemonThread;

public class Main {
    public static void main(String[] args) {
        /*
        t1(주 스레드): 5초 간 몇 초인지 출력
        t2(데몬 스레드): 3초에 "끝!"이라고 출력 & 종료
        */
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println((i + 1) + "초");
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("끝!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.setDaemon(true); // t1이 데몬이기 때문에 t2가 종료되면 더 이상 스레드가 처리되지 않는다.

        t1.start();
        t2.start();
    }
}