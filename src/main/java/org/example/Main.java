package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {       

        Scanner scanner = new Scanner(System.in);
        char[] input = scanner.nextLine().toCharArray();

        
        printBoarder();
        for(int i = 0; i < input.length;) {
           printStart();
           for(int j = 0; j < 3 &&  i < input.length; j++) {
              System.out.print(" " + input[i++]); 
           }
           printEnd(); 
        }
        printBoarder();   
    }

    private static void printStart() {
        System.out.print("|");            
    }

    private static void printEnd() {
        System.out.print(" |\n");            
    }
    
    private static void printBoarder() {
        System.out.println("---------");
    }
}