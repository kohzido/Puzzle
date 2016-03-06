/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto01;

import java.util.List;
import projeto01.Node;
/**
 *
 * @author Matheus
 */
public class Projeto01 {

    static Integer[] problema = {
            1, 2, 8,
            5, 3, 4,
            0, 7, 6
    };
    static Integer[] resultado = {
            1, 2, 3, 
            4, 5, 6, 
            7, 8, 0 
    };
    
    public static void main( String[] args ) {
        Tabuleiro tabuleiro = new Tabuleiro(resultado, problema);
        tabuleiro.PercorreTree();
    }
    
}
