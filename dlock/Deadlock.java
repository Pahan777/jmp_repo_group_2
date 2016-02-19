package dlock;

public class Deadlock {

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

        Thread thread3 = new Thread() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : Try to lock first resource");
                synchronized (resource1) {
                    System.out.println(Thread.currentThread().getName() + " : successfuly locked first resource");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };

        Thread thread4 = new Thread() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : Try to lock first resource");
                synchronized (resource1) {
                    System.out.println(Thread.currentThread().getName() + " : successfuly locked first resource");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };

        Thread thread5 = new Thread() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : Try to lock second resource");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread().getName() + " : successfuly locked second resource");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };

        Thread thread6 = new Thread() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : Try to lock second resource");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread().getName() + " : successfuly locked second resource");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
    }
}
