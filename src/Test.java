/*wait():令当前线程放弃了cpu的资源，使别的线程可以访问共享的资源，
 *       而当前的线程排队等待，再次对资源的访问
 * notify():唤醒正在排队的等待的同步资源的线程，
 * notifyAll()：唤醒正在排队等待的所有的线程
 *
 *在 java.lang.Object:
 *用这三个方法的注意点：  同步方法或者同步代码块里
 *
 * 使用两个线程打印1----100.线程1和线程2交替打印
 *
 * 分析： 1.我先使用两个线程打印1---100，（先不用交替打印）
 *     2.然后在使用上面的三个方法，在代码里添加
 * */

class PrintNum implements Runnable {

    int num = 1;


    @Override
    public void run() {
        // TODO Auto-generated method stub
        synchronized (this) {
            while (true) {
                notify();//唤醒wait()的一个或者所有线程
                if (num <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":"
                            + num);
                    num++;
                } else {
                    break;
                }
                try {
                    wait();//释放当前的锁，另一个线程就会进来
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

}

public class Test {
    public static void main(String[] args) {
        PrintNum p = new PrintNum();
        System.out.println(p instanceof PrintNum);
        Thread t1 = new Thread(p);
        Thread t2 = new Thread(p);

        t1.setName("甲");
        t2.setName("乙");

        t1.start();
        t2.start();
    }
}
	
