package dlock;

public class SimleDeadlock {

    public static void main(String[] args) {

        final Object resource1 = "First resource";
        final Object resource2 = "Second resource";

        Thread thread1 = new Thread() {
            public void run() {
                synchronized (resource1) {
                    System.out.println(Thread.currentThread().getName() + " : locked first resource");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }

                    synchronized (resource2) {
                        System.out.println(Thread.currentThread().getName() + " : locked second resource");
                    }
                }
            }
        };

        Thread thread2 = new Thread() {
            public void run() {
                synchronized (resource2) {
                    System.out.println(Thread.currentThread().getName() + " : locked second resource");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }

                    synchronized (resource1) {
                        System.out.println(Thread.currentThread().getName() + " : locked first resource");
                    }
                }
            }
        };

        thread1.start();
        thread2.start();
    }
}