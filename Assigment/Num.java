package Assigment;

import java.util.concurrent.Semaphore;

class NumberPrinter {
    public void printZero() {
        System.out.print(0);
    }

    public void printEven(int num) {
        System.out.print(num);
    }

    public void printOdd(int num) {
        System.out.print(num);
    }
}

class ThreadController {
    private int n;
    private int current = 1;

    private Semaphore zeroSemaphore = new Semaphore(1);
    private Semaphore evenSemaphore = new Semaphore(0);
    private Semaphore oddSemaphore = new Semaphore(0);

    public ThreadController(int n) {
        this.n = n;
    }

    public void printZero(NumberPrinter printer) {
        for (int i = 0; i < n; i++) {
            try {
                zeroSemaphore.acquire();
                printer.printZero();
                if (current % 2 == 0) {
                    evenSemaphore.release();
                } else {
                    oddSemaphore.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printEven(NumberPrinter printer) {
        for (int i = 2; i <= n; i += 2) {
            try {
                evenSemaphore.acquire();
                printer.printEven(i);
                current++;
                zeroSemaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printOdd(NumberPrinter printer) {
        for (int i = 1; i <= n; i += 2) {
            try {
                oddSemaphore.acquire();
                printer.printOdd(i);
                current++;
                zeroSemaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Num {
    public static void main(String[] args) {
        int n = 5;
        NumberPrinter printer = new NumberPrinter();
        ThreadController controller = new ThreadController(n);

        Thread zeroThread = new Thread(() -> controller.printZero(printer));
        Thread evenThread = new Thread(() -> controller.printEven(printer));
        Thread oddThread = new Thread(() -> controller.printOdd(printer));

        zeroThread.start();
        evenThread.start();
        oddThread.start();
    }
}
