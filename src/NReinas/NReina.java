/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NReinas;

import sun.applet.Main;
import java.util.LinkedList;
/**
 *
 * @author VIVIAN
 */
public class NReina {
    public static long c = 0;
    public static int b = 1;
    public static boolean flag = true;
    public static int up = 0;
    public static int down = 0;
    
    public static void main(String[] args) {
        
        /*for (int n = 1; n <= 30; n++) {
            int m[][] = new int [n][n];
            if(nReinas(m, 0)){
                System.out.println(c);
            }else{
                System.out.println(c + " No existe solución 😑👎");

            }
            c=0;
        }
        */
            
        int n = 10;
        int m[][] = new int[n][n];
        if(nReinas(m,0)){
            System.out.println("Existe Solucion... !");
            mostrar(m);
        }
        else 
        System.out.println("No Existe Solucion... !");
        System.out.println("vueltas = " + c)  ;
       
    }
    
    public static int filaAplicable(int m[][], int i)
    {
        down = m.length - i;
        up = i;
        if(down - up <= 0)
        {
            System.out.println(i);
            return down;
        }
        else
        {
            if(!flag)
            {                
                System.out.println(i + "u");
                flag = false;
                return up;
            }   
            else{ 
                System.out.println(i + "d");
                flag = true;
                return down;
            }
        
        }
           
    }
    
    public static boolean nReinas(int m[][], int i){
        if(i >= m.length) return true;
        LinkedList<Regla> L1 = reglasAplicables(m,i);
        while(!L1.isEmpty()){
            Regla R = elegirReglaB(L1);
            m[R.fil][R.col] = i + 1;
            if(nReinas(m, filaAplicable(m, i + 1))) return true;
            m[R.fil][R.col] = 0;
            c++;
        }            
        return false;
    }
    
    //Sin heuristica
    public static Regla elegirReglaA(LinkedList<Regla> L1){
        return L1.removeFirst();
    }
    //Con heuristica
    public static Regla elegirReglaB(LinkedList<Regla> L1){
    return L1.remove(L1.size() / 2);
    }
    
    public static Regla elegirReglaC(LinkedList<Regla> L1){
        return L1.remove((int) (Math.random() * L1.size()));    
    }
    
    public static Regla elegirReglaD(LinkedList<Regla> L1){
        Regla R = new Regla(0,0);
        switch (b)
        {
            case 1:
                b++;
                R = L1.removeFirst();
                //System.out.println("Case 1");
                break;
                
            case 2:
                b++;
                R = L1.remove(L1.size() / 2);
                //System.out.println("Case 2");
                break;
            case 3:
                b=1;
                R = L1.remove(L1.size()-1);
                //System.out.println("Case 3");
                break;
        }
        return R;
                
    }
    //public static Regla elegirReglaE(LinkedList<Regla> L1){
        
    //}
    
    public static LinkedList<Regla> reglasAplicables(int m[][], int i)
    {
        LinkedList<Regla> L1 = new LinkedList<>();
        for(int j = 0; j < m[i].length; j++){
            if(posValido(m, i , j)) L1.add(new Regla(i, j));
        }
        return L1;
    }
    
    public static boolean posValido(int m[][], int i, int j){
    return filValido(m, i)      && colValido(m,j)      && 
            diagSupIzq(m, i, j) && diagSupDer(m, i, j) &&
            diagInfIzq(m, i, j) && diagInfDer(m, i, j);
            
    }

    public static boolean filValido(int m[][], int i){
        int j = 0;
        while(j < m[i].length){
            if(m[i][j] != 0) return false;
            j++;
        }
        return true;
    }
    
    public static boolean colValido(int m[][], int j){
        int i = 0;
        while(i < m.length){
            if(m[i][j] != 0) return false;
            i++;
        }
        return true;
    }    
    
    public static boolean diagSupIzq(int m[][], int i1, int j1){
        int i = i1, j = j1;
        while(i >= 0 && j >= 0){
            if(m[i][j] != 0) return false;
            i = i - 1;
            j = j - 1;
        }
        return true;
    }
    
    public static boolean diagSupDer(int m[][], int i1, int j1){
        int i = i1, j = j1;
        while(i >= 0 && j < m[i1].length){
            if(m[i][j] != 0) return false;
            i--;
            j++;
        }
        return true;
    }    

    public static boolean diagInfIzq(int m[][], int i1, int j1){
        int i = i1, j = j1;
        while(i < m.length && j >= 0){
            if(m[i][j] != 0) return false;
            i++;
            j--;
        }
        return true;
    }

    public static boolean diagInfDer(int m[][], int i1, int j1){
        int i = i1, j = j1;
        while(i < m.length && j < m[i1].length){
            if(m[i][j] != 0) return false;
            i++;
            j++;
        }
        return true;
    }
    
    public static void mostrar(int m[][]){
        for(int i = 0; i < m.length; i++)
        {
            for(int j = 0; j < m[i].length; j++)
            {
                System.out.print(m[i][j] + " ");            
            }
            System.out.println("");
        }
        System.out.println("");
    }
}

