/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author LP D
 */
public class MyToys {
    
    private static final Scanner sc = new Scanner(System.in);   
    
    public static String getString(String input, String error, String format) {
        String s;
        boolean match;
        while (true) { 
            System.out.print(input);
            s = sc.nextLine().trim();
            match = s.matches(format);
            if (s.isEmpty() || match == false)
                System.out.print(error);
            else
                return s;
        }
    }
    
    public static String getFormat(String input, String error, String format1, String format2) {
        String f;
        boolean match;
        while (true) { 
            System.out.print(input);
            f = sc.nextLine().trim();
            if (f.equalsIgnoreCase(format1) || f.equalsIgnoreCase(format2))
                return f;
            else
                System.out.print(error);
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
                System.out.print(error);
        }
    }
    
    public static String getFormat2(String input, String error, String format1, String format2) {
        String f;
        boolean match;
        while (true) { 
            System.out.print(input);
            f = sc.nextLine().trim();
            if (f.equalsIgnoreCase(format1) || f.equalsIgnoreCase(format2) || f.isEmpty())
                return f;
            else
                System.out.print(error);
        }
    }
        
    public static int getInteger(String input, String error) {
        int n;
        while (true) {
            try {
                System.out.print(input);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) { 
                System.out.print(error);                       
            }
        }
    }
            
    public static double getPoint(String input, String error) {
        double p;
        while (true) {
            try {
                System.out.print(input);
                p = Double.parseDouble(sc.nextLine());
                if (!(p < 0 || p > 10))
                    return p;
                else
                    System.out.println("score from 0 to 10.");
            } catch (Exception e) {
                System.out.println(error);
            }            
        }
    }
    
    public static boolean leapYear(int y) {
        boolean result = false;
        if ((y % 400 == 0) || (y % 4 == 0) && (y % 100 != 0))
            result = true;
        return result;
    }
    
    public static boolean valid(int d, int m, int y) {
        int max = 31;
        if (d < 0 || d > 31 ||  m < 0 || m > 12 || y < 0)
            return false;
        if (m == 4 || m == 6 || m == 9 || m == 11)
            max = 30;
        if (m == 2){
            if (leapYear(y))
                max = 29;
            else
                max = 28;
        }
        return d <= max;
    }
    
    public static int toDate(String dmy) {
        StringTokenizer st = new StringTokenizer(dmy, "/");
        int d = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        if (!valid(d, m, y))
            return -1;
        return 0;
    }
    
}
