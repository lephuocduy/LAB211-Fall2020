/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Scanner;

/**
 *
 * @author LP D
 */
public class MyToys {
    
    private static Scanner sc = new Scanner (System.in);
    
    public static String getString(String input, String error, String format) {
        String s;
        boolean match;
        while (true) {            
            System.out.print(input);
            s = sc.nextLine().trim();
            match = s.matches(format);
            if (s.isEmpty() || match == false)
                System.out.println(error);
            else
                return s;
        }
    }
    
    public static String getString2(String input, String error, String format) {
        String s;
        boolean match;
        while (true) {            
            System.out.print(input);
            s = sc.nextLine().trim();
            match = s.matches(format);
            if (match == true || s.isEmpty())
                return s;
            if (match == false)
                System.out.println(error);
        }
    }
    
    public static String getFormat(String input, String error, String format1, String format2) {
        String f;
        while (true) {            
            System.out.print(input);
            f = sc.nextLine().trim();
            if (f.equals(format1) || f.equals(format2))
                return f;
            else
                System.out.println(error);
        }
    }
    
    public static int getInteger(String input, String error) {
        int n;
        while (true) {    
            try {
                System.out.print(input);
                n = Integer.parseInt(sc.nextLine().trim());
                return n;
            } catch (Exception e) {
                System.out.println(error);
            }           
        }
    }
    
}
