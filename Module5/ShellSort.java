public class ShellSort {
    public static void sort(Comparable[] a){
        int N = a.length;
        int  h= 1;
        while(h < N/3) h = 3*h + 1; // 1 ,4, 13, 40, 121, 364, ...

        while(h >= 1){
            // h-sort array
            for(int i= h; i < N; i++){
                for(int j = i; j >=h && less(a[j], a[j-h]); j-=h){
                    exch(a, j, j-h);
                }
            }
            h = h/3; // ..., 364, 121, 40, 13, 4, 1
        }
    }
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] arr = {2, 6, 4, 10, 223, 45, 22, 1, 0, 23, 98, 7};

        System.out.println("WITHOUT SORTING");
        for(int a: arr){
            System.out.print(a + " ");
        }
        System.out.println();

        sort(arr);

        System.out.println("AFTER SORTING");
        for(int a: arr){
            System.out.print(a + " ");
        }


    }
}
