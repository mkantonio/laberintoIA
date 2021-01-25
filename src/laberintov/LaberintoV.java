/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintov;

import java.util.LinkedList;

/**
 *
 * @author mkcho
 */
public class LaberintoV {
//Vanessa
    public static int c=0;
    
    public static void main(String[] args) {
        int m[][] = new int[3][3];
        m[1][1]= 1;
        laberinto(m,2,2,1,1,2);
        //laberintoTodasCasillas(m,2,2,1,1,1);
        //laberintoAlgunasCasillas(m,2,2,0,0,2);
        //laberintoMejoradoDiagonal(m,0,0,2,2,1);
        //laberintoMejoradoDiagonalTodasCasillas(m,0,0,2,2,1);
        //laberintoMejoradoDiagonalAlgunasCasillas(m,0,0,2,2,1);
        System.out.println("Sol= "+c);
        
    }
    
    public static void mostrar(int m[][]){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j] + "  ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public static boolean posValida(int m[][], int i, int j){
        return i >= 0 && i < m.length && j >= 0
                && j < m[i].length && m[i][j] == 0;
    }
    
    public static void laberinto(int m[][], int i, int j, 
                                int i1, int j1, int paso){
        m[i][j]=paso;
        if(i==i1 && j==j1) { mostrar(m); c++; }
        
        LinkedList<Regla> L1 = reglasAplicables(m,i,j);
        while(!L1.isEmpty()){
            Regla R = elegirReglaA(L1, i1, j1);
            //Regla R = elegirReglaB(L1, i1, j1);
            m[R.fil][R.col]=paso;
            laberinto(m, R.fil, R.col, i1, j1, paso+1);
            m[R.fil][R.col]=0;
        }
    }

    private static LinkedList<Regla> reglasAplicables(int m[][], int i, int j) {
        LinkedList<Regla> L1 = new LinkedList();
        if(posValida(m,i,j-1)) L1.add(new Regla(i,j-1));
        if(posValida(m,i-1,j)) L1.add(new Regla(i-1,j));
        if(posValida(m,i,j+1)) L1.add(new Regla(i,j+1));
        if(posValida(m,i+1,j)) L1.add(new Regla(i+1,j));
        return L1;
    }

    private static Regla elegirReglaA(LinkedList<Regla> L1, int i1, int j1) {
        return L1.removeFirst();
    }
    
    private static Regla elegirReglaB(LinkedList<Regla> L1, int i1, int j1){
        int i=0, men=Integer.MAX_VALUE, posMen=0;
        while(i<L1.size()){
            int dist=distancia(L1.get(i).fil, L1.get(i).col, i1, j1);
            if(dist<men){
                men=dist;
                posMen=i;
            }
            i++;
        }
        return L1.remove(posMen);
    }
    
    public static int distancia(int x1, int y1, int x2, int y2){
        return (int)Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }
    
    
    
    /*
    1.a) Algoritmo para mostrar todos los caminos posibles desde una posición 
    inicial a una posición final. Además, mostrar la cantidad de soluciones posibles.
    */
    public static void laberintoMejorado(int m[][], int i, int j, 
                                int i1, int j1, int paso){
        m[i][j]=paso;
        if(i==i1 && j==j1) { mostrar(m); c++; }
        
        LinkedList<Regla> L1 = reglasAplicables(m,i,j);
        while(!L1.isEmpty()){
            Regla R = elegirReglaA(L1, i1, j1);
            //Regla R = elegirReglaB(L1, i1, j1);
            m[R.fil][R.col]=paso;
            laberintoMejorado(m, R.fil, R.col, i1, j1, paso+1);
            m[R.fil][R.col]=0;
        }
    }
    
    /*
    1.b) Algoritmo para mostrar todos los caminos posibles desde una posición 
    inicial a una posición final tal que se visiten todas las casillas de la 
    matriz. Además, mostrar la cantidad de soluciones posibles.
    */
    public static void laberintoTodasCasillas(int m[][], int i, int j, 
                                int i1, int j1, int paso){
        m[i][j]=paso;
        if(i==i1 && j==j1 && !existeCero(m)) { mostrar(m); c++; }
        
        LinkedList<Regla> L1 = reglasAplicables(m,i,j);
        while(!L1.isEmpty()){
            Regla R = elegirReglaA(L1, i1, j1);
            //Regla R = elegirReglaB(L1, i1, j1);
            m[R.fil][R.col]=paso;
            laberintoTodasCasillas(m, R.fil, R.col, i1, j1, paso+1);
            m[R.fil][R.col]=0;
        }
    }

    private static boolean existeCero(int m[][]) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if(m[i][j]==0) { 
                    return true; 
                }
            }
        }
        return false;
    }
    
    
    /*
    1.c) Algoritmo para mostrar todos los caminos posibles desde una posición 
    inicial a una posición final tal que NO se visiten todas las casillas de la 
    matriz. Además, mostrar la cantidad de soluciones posibles.
    */
    public static void laberintoAlgunasCasillas(int m[][], int i, int j, 
                                int i1, int j1, int paso){
        m[i][j]=paso;
        if(i==i1 && j==j1 && existeCero(m)) { mostrar(m); c++; }
        
        LinkedList<Regla> L1 = reglasAplicables(m,i,j);
        while(!L1.isEmpty()){
            Regla R = elegirReglaA(L1, i1, j1);
            //Regla R = elegirReglaB(L1, i1, j1);
            m[R.fil][R.col]=paso;
            laberintoAlgunasCasillas(m, R.fil, R.col, i1, j1, paso+1);
            m[R.fil][R.col]=0;
        }
    }
    
    
    /*
    3.a) Algoritmo que muestra todos los caminos posibles incluyendo los pasos
    en diagonales
    */
    public static void laberintoMejoradoDiagonal(int m[][], int i, int j, 
                                int i1, int j1, int paso){
        m[i][j]=paso;
        if(i==i1 && j==j1) { mostrar(m); c++; }
        
        LinkedList<Regla> L1 = reglasAplicablesDiagonal(m,i,j);
        while(!L1.isEmpty()){
            Regla R = elegirReglaA(L1, i1, j1);
            //Regla R = elegirReglaB(L1, i1, j1);
            m[R.fil][R.col]=paso;
            laberintoMejoradoDiagonal(m, R.fil, R.col, i1, j1, paso+1);
            m[R.fil][R.col]=0;
        }
    }
    
    private static LinkedList<Regla> reglasAplicablesDiagonal(int m[][], int i, int j) {
        LinkedList<Regla> L1 = new LinkedList();
        if(posValida(m,i,j-1)) L1.add(new Regla(i,j-1));
        if(posValida(m,i-1,j-1)) L1.add(new Regla(i-1,j-1));
        if(posValida(m,i-1,j)) L1.add(new Regla(i-1,j));
        if(posValida(m,i-1,j+1)) L1.add(new Regla(i-1,j+1));
        if(posValida(m,i,j+1)) L1.add(new Regla(i,j+1));
        if(posValida(m,i+1,j+1)) L1.add(new Regla(i+1,j+1));
        if(posValida(m,i+1,j)) L1.add(new Regla(i+1,j));
        if(posValida(m,i+1,j-1)) L1.add(new Regla(i+1,j-1));
        return L1;
    }
    
    
    /*
    3.b) Algoritmo que muestra todos los caminos posibles incluyendo los pasos
    en diagonales y que se visiten todas las casillas
    */
    public static void laberintoMejoradoDiagonalTodasCasillas(int m[][], int i, int j, 
                                int i1, int j1, int paso){
        m[i][j]=paso;
        if(i==i1 && j==j1 && !existeCero(m)) { mostrar(m); c++; }
        
        LinkedList<Regla> L1 = reglasAplicablesDiagonal(m,i,j);
        while(!L1.isEmpty()){
            Regla R = elegirReglaA(L1, i1, j1);
            //Regla R = elegirReglaB(L1, i1, j1);
            m[R.fil][R.col]=paso;
            laberintoMejoradoDiagonalTodasCasillas(m, R.fil, R.col, i1, j1, paso+1);
            m[R.fil][R.col]=0;
        }
    }
    
    /*
    3.c) Algoritmo que muestra todos los caminos posibles incluyendo los pasos
    en diagonales y que NO se visiten todas las casillas
    */
    public static void laberintoMejoradoDiagonalAlgunasCasillas(int m[][], int i, int j, 
                                int i1, int j1, int paso){
        m[i][j]=paso;
        if(i==i1 && j==j1 && existeCero(m)) { mostrar(m); c++; }
        
        LinkedList<Regla> L1 = reglasAplicablesDiagonal(m,i,j);
        while(!L1.isEmpty()){
            Regla R = elegirReglaA(L1, i1, j1);
            //Regla R = elegirReglaB(L1, i1, j1);
            m[R.fil][R.col]=paso;
            laberintoMejoradoDiagonalAlgunasCasillas(m, R.fil, R.col, i1, j1, paso+1);
            m[R.fil][R.col]=0;
        }
    }
    
}
