package intromultithreading.demo12;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private Account a1 = new Account();
    private Account a2 = new Account();
    private Lock lock1=new ReentrantLock();
    private Lock lock2=new ReentrantLock();

    private void acquireLocks(Lock firstLock,Lock secondLock){
        while(true){
            boolean gotFirstLock=false;
            boolean gotSecondLock=false;
            try {
                gotFirstLock=firstLock.tryLock();
                gotSecondLock=secondLock.tryLock();

            }finally {
                if (gotFirstLock && gotSecondLock)
                    return;
                if (gotFirstLock && !gotSecondLock)
                    gotSecondLock=secondLock.tryLock();
                if (!gotFirstLock && gotSecondLock)
                    gotFirstLock=secondLock.tryLock();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void firstThread() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            acquireLocks(lock1,lock2);
            try {
                Account.tranfer(a1, a2, random.nextInt(1000));
            }finally {
                lock2.unlock();
                lock1.unlock();
            }
        }

    }

    public void secondThread() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            // if you put them opposite you'll get a deadlock
            acquireLocks(lock1,lock2);
            try {
                Account.tranfer(a2, a1, random.nextInt(1000));
            }finally {
                lock2.unlock();
                lock1.unlock();
            }
        }

    }

    public void finished() {
        System.out.println("Account 1 :" + a1.getBalance());
        System.out.println("Account 2:" + a2.getBalance());
        System.out.println("Account Total :" + (a1.getBalance() + a2.getBalance()));
    }
}
