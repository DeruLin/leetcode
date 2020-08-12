import java.util.*;

public class ALi3 {

    static class Pai {
        public int h;
        public int w;

        public Pai(int h, int w) {
            this.h = h;
            this.w = w;
        }
    }

    public static void main(String[] args) {
        // write your code here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Pai[] pai = new Pai[n];
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            pai[i] = new Pai(in.nextInt(),in.nextInt());
        }
        //先对前面进行排序，后面只要看递增序列有多长就行
        Arrays.sort(pai, new Comparator<Pai>() {
            @Override
            public int compare(Pai o1, Pai o2) {
                if (o1.h < o2.h)
                    return 1;
                else return -1;
            }
        });
        for (int i = 0; i < n; i++)
            System.out.println(pai[i].h + " " + pai[i].w);
    }

}
