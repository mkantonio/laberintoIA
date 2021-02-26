package nreinasM;

import java.util.LinkedList;
import nreinasM.Regla;


/**
 *
 * @author mkcho
 */
public class Nreinas {
    
    public static long c = 0;
    public static int flag = 0;
    public static void main(String[] args) {
        for (int n = 1; n <= 30; n++) {
            int m[][] = new int [n][n];
        if(nReinas(m, 0)){
            System.out.println(c);
        }else{
            System.out.println(c + " No existe solución 😑👎");
            
        }
        c=0;
        }
//        int n = 20;
//        int m[][] = new int [n][n];
//        if(nReinas(m, 0)){
//            System.out.println("Existe solución! 🎉😄");
//            mostrar(m);
//        }else{
//            System.out.println("No existe solución 😑👎");
//        }
//        System.out.println("vueltas: "+c);
    }
    
    public static boolean nReinas(int m[][], int i){
        if(i >= m.length){ return true; }
        LinkedList<Regla> L1 = reglasAplicables(m, i);
        while (!L1.isEmpty()){
            //Regla R = elegirReglaA(L1);
            //Regla R = elegirReglaB(L1);
            //Regla R = elegirReglaC(L1);
            Regla R = elegirReglaD(L1, flag);
            m[R.fil][R.col] = 1;
            if (nReinas(m, i+1)){ return true; }
            m[R.fil][R.col] = 0;
            c++;
        }
        return false;
    }

    private static LinkedList<Regla> reglasAplicables(int[][] m, int i) {
        LinkedList<Regla> L1 = new LinkedList();
        for (int j = 0; j < m[i].length; j++) {
            if(posValida(m, i, j)) L1.add(new Regla(i, j));
        }
        return L1;
    }

    private static boolean posValida(int[][] m, int i, int j) {
        return filValida(m ,i) && colValida(m, j) &&
                diagSupIzq(m, i, j) && diagSupDer(m ,i, j) &&
                diagInfIzq(m, i, j) && diagInfDer(m, i, j);
    }

    private static boolean filValida(int[][] m, int i) {
        for (int j = 0; j < m[i].length; j++) {
            if(m[i][j]!=0)
                return false;
        }
        return true;
    }

    private static boolean colValida(int[][] m, int j) {
        for (int i = 0; i < m.length; i++) {
            if(m[i][j]!=0)
                return false;
        }
        return true;       
    }

    private static boolean diagSupIzq(int[][] m, int i1, int j1) {
         for (int i=i1, j=j1; i >= 0 && j>=0; i--, j--) {
            if(m[i][j]!=0) return false;
        }
        return true;
    }

    private static boolean diagSupDer(int[][] m, int i1, int j1) {
        for (int i=i1, j=j1; i >= 0 && j<m[i1].length; i--, j++) {
            if(m[i][j]!=0) return false;
        }
        return true;
    }

    private static boolean diagInfIzq(int[][] m, int i1, int j1) {
        for (int i=i1, j=j1; i < m.length && j>=0; i++, j--) {
            if(m[i][j]!=0) return false;
        }
        return true;
    }

    private static boolean diagInfDer(int[][] m, int i1, int j1) {
        for (int i=i1, j=j1; i < m.length && j<m[i1].length; i++, j++) {
            if(m[i][j]!=0) return false;
        }
        return true;
    }

    private static Regla elegirReglaA(LinkedList<Regla> L1) {
        return L1.removeFirst();
    }
    
    private static Regla elegirReglaB(LinkedList<Regla> L1) {
        return L1.remove(L1.size()/2);
        
    }
    
    private static Regla elegirReglaC(LinkedList<Regla> L1) {
        return L1.remove((int)(Math.random() * L1.size()));
    }
    
    private static Regla elegirReglaD(LinkedList<Regla> L1, int flag) {
        if (flag>17){
            
            return L1.remove(L1.size()/2);
        }
        
        return L1.remove((int)(Math.random() * L1.size()));
    }

    private static void mostrar(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
    
    
}
