/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Caballo;

import java.util.LinkedList;
import static laberintov.LaberintoV.c;
import laberintov.Regla;

public class CaballoMain {
public static void main(String[] args) {
        int m[][] = new int[4][5];   
        
        //laberintoCaballo(m,2,2,3,4,1);
        //laberintoCaballoMejorado(m,2,2,3,4,1);
        //laberintoCaballoSinFinal(m,2,2,1);
        laberintoCaballoMejoradoSinFinal(m,2,2,1);
        
        //laberintoCaballo(m,0,0,0,0,1);
        System.out.println("Sol= "+c);
    }
    
    public static void mostrar(int m[][]){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print("\t"+m[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public static boolean posValida(int m[][], int i, int j){
        return i >= 0 && i < m.length && j >= 0
                && j < m[i].length && m[i][j] == 0;
    }
    
    public static void laberintoCaballoMejorado(int m[][], int i, int j, 
                                int i1, int j1, int paso){
        m[i][j]=paso;
        if(i==i1 && j==j1) { mostrar(m); c++; }
        
        LinkedList<Regla> L1 = reglasAplicablesCaballo(m,i,j);
        while(!L1.isEmpty()){
            Regla R = elegirReglaA(L1, i1, j1);
            //Regla R = elegirReglaB(L1, i1, j1);
            m[R.fil][R.col]=paso;
            laberintoCaballoMejorado(m, R.fil, R.col, i1, j1, paso+1);
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
       
    private static LinkedList<Regla> reglasAplicablesCaballo(int m[][], int i, int j) {
        LinkedList<Regla> L1 = new LinkedList();
        if(posValida(m,i-1,j-2)) L1.add(new Regla(i-1,j-2));
        if(posValida(m,i-2,j-1)) L1.add(new Regla(i-2,j-1));
        if(posValida(m,i-2,j+1)) L1.add(new Regla(i-2,j+1));
        if(posValida(m,i-1,j+2)) L1.add(new Regla(i-1,j+2));
        if(posValida(m,i+1,j+2)) L1.add(new Regla(i+1,j+2));
        if(posValida(m,i+2,j+1)) L1.add(new Regla(i+2,j+1));
        if(posValida(m,i+2,j-1)) L1.add(new Regla(i+2,j-1));
        if(posValida(m,i+1,j-1)) L1.add(new Regla(i+1,j-1));
        return L1;
    }
     
    /* 1.a
     Algoritmo para mostrar todos los caminos posibles desde una posición inicial 
     a una posición final. Además, mostrar la cantidad de soluciones posibles.
     
     int m[][] = new int[4][5];    
     laberintoCaballo(m,2,2,3,4,1);
     Sol= 5975
     */ 
    
    public static void laberintoCaballo(int m[][], int i, int j, int i1, int j1, int paso) {
       if (!posValida(m, i, j)) {
           return;
       }
       m[i][j] = paso;
       if (i == i1 && j == j1) {
           mostrar(m);
           c = c + 1;
       }

        laberintoCaballo(m,i-1,j-2,i1,j1,paso+1);
	laberintoCaballo(m,i-2,j-1,i1,j1,paso+1);
	laberintoCaballo(m,i-2,j+1,i1,j1,paso+1);
	laberintoCaballo(m,i-1,j+2,i1,j1,paso+1);
	laberintoCaballo(m,i+1,j+2,i1,j1,paso+1);
	laberintoCaballo(m,i+2,j+1,i1,j1,paso+1);
	laberintoCaballo(m,i+2,j-1,i1,j1,paso+1);
	laberintoCaballo(m,i+1,j-1,i1,j1,paso+1);   
        m[i][j] = 0;
    }
     
    
    /* 1.b
    Algoritmo para mostrar todos los caminos posibles desde una posición inicial
    cualquiera (no existe posición final) tal que se visiten todas las casillas 
    de la matriz. Además, mostrar la cantidad de soluciones posibles.  
    
    int m[][] = new int[4][5];    
    laberintoCaballoSinFinal(m,2,2,1);
    Sol: 1
    */
    public static void laberintoCaballoSinFinal(int m[][], int i, int j, 
                                                 int paso){
        laberintoCaballoSinFinal(m, i, j, paso, m.length*m[0].length);
    }
    
    private static void laberintoCaballoSinFinal(int m[][], int i, int j, 
                                                int paso, int tamaño) {
        if (!posValida(m, i, j)) {
            return;
        }
        m[i][j] = paso;
        if (paso == tamaño) { mostrar(m); c = c + 1; }

        laberintoCaballoSinFinal(m, i - 1, j - 2, paso + 1, tamaño);
        laberintoCaballoSinFinal(m, i - 2, j - 1, paso + 1, tamaño);
        laberintoCaballoSinFinal(m, i - 2, j + 1, paso + 1, tamaño);
        laberintoCaballoSinFinal(m, i - 1, j + 2, paso + 1, tamaño);
        laberintoCaballoSinFinal(m, i + 1, j + 2, paso + 1, tamaño);
        laberintoCaballoSinFinal(m, i + 2, j + 1, paso + 1, tamaño);
        laberintoCaballoSinFinal(m, i + 2, j - 1, paso + 1, tamaño);
        laberintoCaballoSinFinal(m, i + 1, j - 1, paso + 1, tamaño);
        m[i][j] = 0;
    }
    
     /* 3.a
     int m[][] = new int[4][5];
     laberintoCaballo(m,0,0,3,4,1);
     Soluciones: 5975
    
     laberintoCaballoMejorado
     reglasAplicablesCaballo
     */
     
     /* 3.b 
     Algoritmo para mostrar todos los caminos posibles desde una posicion inicial
     cualquiera (no existe posición final) tal que se visiten todas las casillas
     de la matriz. Además, mostrar la cantidad de soluciones posibles.
     
     int m[][] = new int[4][5];  
     laberintoCaballoMejoradoSinFinal(m,2,2,1);
     Sol= 1 
     */
    public static void laberintoCaballoMejoradoSinFinal(int m[][], int i, int j, 
                                int paso){
        laberintoCaballoMejoradoSinFinal(m, i, j, paso, m.length * m[0].length);
    }
     
    private static void laberintoCaballoMejoradoSinFinal(int m[][], int i, int j, 
                                                        int paso, int tamaño){
        m[i][j]=paso;
        if(paso==tamaño) { mostrar(m); c++; }
        
        LinkedList<Regla> L1 = reglasAplicablesCaballo(m,i,j);
        while(!L1.isEmpty()){
            Regla R = elegirReglaA(L1, 0, 0);
            m[R.fil][R.col]=paso;
            laberintoCaballoMejoradoSinFinal(m, R.fil, R.col, paso+1, tamaño);
            m[R.fil][R.col]=0;
        }
    } 
}
