package dlock;

public class FourThreadsDeadlock {

    public static void main(String[] args) {

        final Object resource1 = "First resource";
        final Object resource2 = "Second resource";
        final Object resource3 = "Third resource";
        final Object resource4 = "Fourth resource";

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

                    synchronized (resource3) {
                        System.out.println(Thread.currentThread().getName() + " : locked third resource");
                    }
                }
            }
        };

        Thread thread3 = new Thread() {
            public void run() {
                synchronized (resource3) {
                    System.out.println(Thread.currentThread().getName() + " : locked third resource");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }

                    synchronized (resource4) {
                        System.out.println(Thread.currentThread().getName() + " : locked fourth resource");
                    }
                }
            }
        };

        Thread thread4 = new Thread() {
            public void run() {
                synchronized (resource4) {
                    System.out.println(Thread.currentThread().getName() + " : locked fourth resource");

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
        thread3.start();
        thread4.start();
    }
}
