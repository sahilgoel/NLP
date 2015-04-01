/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author sahil
 */
public class Text {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner in = new Scanner(new File("src/training.txt"));
                File file = new File("src/features.txt");
 
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
 
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
                
        int i = 0;
                String p_line = null, c_line, n_line= null;
                int beg = 1;
                c_line = in.nextLine();
        while(in.hasNextLine()){
                         if(in.hasNextLine())
                             n_line = in.nextLine();
                         
                         String arr[] = c_line.split(" ");
                         if(arr.length == 1)
                         {
                             p_line = null;
                             c_line = n_line;
                             continue;
                         }
                         if(p_line == null || (p_line.split(" ")).length ==1)
                         {
                       //      System.out.print("Previous_Word=<START>  ");
                             bw.write("Previous_Word=<START> ");
                             bw.write("Previous_Tag=<> ");
                             bw.write("Previous_Upper_Case=false ");
                             bw.write("Previous_BIO=<@> ");
                         }
                         else
                         {  
                         //    System.out.print("Previous_Word=" + (p_line.split(" "))[0]+ " ");
                             bw.write("Previous_Word=" + (p_line.split(" "))[0]+ " ");
                             bw.write("Previous_Tag=" + (p_line.split(" "))[1]+ " ");
                             bw.write("Previous_Upper_Case=" + Character.isUpperCase((p_line.split(" "))[0].charAt(0)) + " ");
                             bw.write("Previous_BIO=" + (p_line.split(" "))[2]+ " ");
                         }
                         
                         bw.write("Current_Word=" + arr[0] + " ");
                         bw.write("Upper_Case=" + Character.isUpperCase(arr[0].charAt(0)) + " ");
                         bw.write("Current_Tag=" + arr[1] + " ");
                         //System.out.print("Current_Word=" + arr[0] + " ");
                         
                         if(n_line == null || (n_line.split(" ")).length == 1)
                         {    
                         //    System.out.println("Next_Word=<END>  ");
                             bw.write("Next_Word=<END>  ");
                             bw.write("Next_Tag=<!> ");
          //                   bw.write("Next_Upper_Case=false");
                         }
                         else
                         {
                         //    System.out.print("Next_Word=" + (n_line.split(" "))[0] + " ");
                             bw.write("Next_Word=" + (n_line.split(" "))[0] + " ");
                             bw.write("Next_Tag=" + (n_line.split(" "))[1] + " ");
              //               bw.write("Next_Upper_Case="  + Character.isUpperCase((n_line.split(" "))[0].charAt(0)) + " ");
                         }
                         //System.out.println(" " + arr[1]);
                         bw.write(" " + arr[2]);
                         bw.write("\n");
                         p_line = c_line;
                         c_line = n_line;
         }
                bw.close();
    }
}
