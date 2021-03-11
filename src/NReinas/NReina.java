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
    //public static int cantCasillas= 0;
    
    public static void main(String[] args) {
    /*      int m[][]= new int[3][4];
        if(nCaballos(m,1,0,0)){
            System.out.println("Existe Solucion... !");
            mostrar(m);
        }
        else 
        System.out.println("No Existe Solucion... !");
        System.out.println("vueltas = " + c)  ;
    */      
      
        
        for (int n = 1; n <= 7; n++) {
            int m[][] = new int [n][n];
            if(saltoCaballo(m, 0,0, 0)){
                mostrar(m);
                System.out.println(c);
            }else{
                System.out.println(c + " No existe");
//solución 😑👎
            }
            c=0;
        }
        
            
        /*int n = 10;
        int m[][] = new int[n][n];
        if(nCaballos(m,0)){
            System.out.println("Existe Solucion... !");
            mostrar(m);
        }
        else 
        System.out.println("No Existe Solucion... !");
        System.out.println("vueltas = " + c)  ;
       */
            
    }
    public static int sgteTamanio(Regla R, int m[][])
    {
       LinkedList<Regla> L1 = reglasAplicablesCaballo(m, R.fil, R.col);
       return L1.size();
    }
    
    public static Regla elegirReglaW(LinkedList<Regla> L1, int m[][])
    {
        int i=0, p = 0;
        Regla Menor = L1.get(i);
        while(i < L1.size() - 1)
        {
            if(sgteTamanio(Menor, m)> sgteTamanio(L1.get(i+1), m))
            {
                 Menor = L1.get(i + 1);
                 p = i + 1;
            }
            i++;
        }
        return L1.remove(p);
    }
    
    public static Regla elegirReglaSgte(LinkedList<Regla> L1, int m[][])
    {
        int men = Integer.MAX_VALUE;
        int c = 0;
        for(int i = 0; i < L1.size() ; i++)
        {
            if(men >= sgteTamanio(L1.get(i), m))
            {
                men = sgteTamanio(L1.get(i), m);
                c = i;
            }
        }
        return L1.remove(c);
    }
    private static boolean saltoCaballo(int m[][], int i, int j, int cantCasillas) {         
        if (m[i][j] == m.length * m[i].length) { 
            return true;
        }
        LinkedList<Regla> L1 = reglasAplicablesCaballo(m, i, j);
        while (!L1.isEmpty()) {
            Regla R = elegirReglaSgte(L1, m);
            m[R.fil][R.col] = cantCasillas++;
            if (saltoCaballo(m, R.fil, R.col, cantCasillas )){
                return true;
            }            
            m[R.fil][R.col] = 0;
            cantCasillas--;
            c++;
        }
        return false;
    }

    private static LinkedList<Regla> reglasAplicablesCaballo(int[][] m, int i, int j) {
        LinkedList<Regla> L1 = new LinkedList();
        //SectorSupIzq
        if (posValidaCaballo(m, i - 1, j - 2)) {    //bot
            L1.add(new Regla(i - 1, j - 2));
        }
        if (posValidaCaballo(m, i - 2, j - 1)) {    //top
            L1.add(new Regla(i - 2, j - 1));
        }
        //SectorSupDer
        if (posValidaCaballo(m, i - 2, j + 1)) {    //top
            L1.add(new Regla(i - 2, j + 1));
        }
        if (posValidaCaballo(m, i - 1, j + 2)) {    //bot
            L1.add(new Regla(i - 1, j + 2));
        }
        //SectorInfDer
        if (posValidaCaballo(m, i + 1, j + 2)) {    //top
            L1.add(new Regla(i + 1, j + 2));
        }
        if (posValidaCaballo(m, i + 2, j + 1)) {    //bot
            L1.add(new Regla(i + 2, j + 1));
        }
        //SectorInfIzq
        if (posValidaCaballo(m, i + 2, j - 1)) {    //bot
            L1.add(new Regla(i + 2, j - 1));
        }
        if (posValidaCaballo(m, i + 1, j - 2)) {    //top
            L1.add(new Regla(i + 1, j - 2));
        }
        return L1;
    }

    private static boolean posValidaCaballo(int[][] m, int i, int j) {
        return i >= 0 && i < m.length && j >= 0
                && j < m[i].length && m[i][j] == 0;
    }

    //Heuristicas A

    private static Regla elegirReglaA1(LinkedList<Regla> L1) {
        return L1.removeFirst();
    }
    
    //Heuristica B

    private static Regla elegirReglaB2(LinkedList<Regla> L1) {
        return L1.remove(L1.size() / 2);

    }
    //Heuristica C

    private static Regla elegirReglaC3(LinkedList<Regla> L1) {
        return L1.remove((int) (Math.random() * L1.size()));
    }
    /*
    public static int filaAplicable(int m[][], int i)
    {
        down = m.length - i;
   
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
                flag = true;
                return up;
            }   
            else{ 
                System.out.println(i + "d");
                flag = false;
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
    }*/
    //CABALLO
    /*private static boolean posValidaCaballo(int[][] m, int i, int j) {
        return i >= 0 && i < m.length && j >= 0 
                && j < m[i].length && m[i][j] == 0;
    }
    */
    private static void mostrarL(LinkedList<Regla> L1) {
        for (Regla regla : L1) {
            System.out.print(regla.fil+","+regla.col+" ; ");
        }
        System.out.println("");
    }
    public static boolean nCaballos(int m[][], int paso, int i, int j){
        m[i][j]=1;
        return nCaballos(m, paso, new Regla(i,j) ,m.length*m[i].length);
    }
    
    private static boolean nCaballos(int m[][], int paso, Regla p, int tamaño){
        if(paso == tamaño){ return true; }
   
        LinkedList<Regla> L1 = reglasAplicablesCaballo(m, p); 
   
        while (!L1.isEmpty()){
            Regla R = elegirReglaA(L1);
            m[R.fil][R.col] = paso + 1;
            if (nCaballos(m, paso+1,R, tamaño)){ return true; }
            m[R.fil][R.col] = 0;
            c++;
        }
        return false;
    }
    
    
    private static LinkedList<Regla> reglasAplicablesCaballo(int[][] m, Regla p) {
        LinkedList<Regla> L1 = new LinkedList();
         //Probar en posValidaCaballo con par de k y j    
                if(posValidaCaballo(m,p.fil-1,p.col-2)) L1.add(new Regla(p.fil-1,p.col-2));
                if(posValidaCaballo(m,p.fil-2,p.col-1)) L1.add(new Regla(p.fil-2,p.col-1));
                if(posValidaCaballo(m,p.fil-2,p.col+1)) L1.add(new Regla(p.fil-2,p.col+1));
                if(posValidaCaballo(m,p.fil-1,p.col+2)) L1.add(new Regla(p.fil-1,p.col+2));
                if(posValidaCaballo(m,p.fil+1,p.col+2)) L1.add(new Regla(p.fil+1,p.col+2));
                if(posValidaCaballo(m,p.fil+2,p.col+1)) L1.add(new Regla(p.fil+2,p.col+1));
                if(posValidaCaballo(m,p.fil+2,p.col-1)) L1.add(new Regla(p.fil+2,p.col-1));
                if(posValidaCaballo(m,p.fil+1,p.col-2)) L1.add(new Regla(p.fil+1,p.col-2));

        return L1;        
    }
 
    
    //Sin heuristica
    public static Regla elegirReglaA(LinkedList<Regla> L1){
        return L1.removeFirst();
    }
    public static Regla elegirReglaF(LinkedList<Regla> L1){
        return L1.removeLast();
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

