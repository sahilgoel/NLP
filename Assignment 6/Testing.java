/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nlpt;
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
import java.io.File;
import java.io.FileReader;
import opennlp.maxent.*;
import opennlp.maxent.io.*;
import opennlp.model.EventStream;
/**
 *
 * @author sahil
 */
public class NLPT {

    /**
     * @param args the command line arguments
     */public static void main(String[] args) throws FileNotFoundException, IOException {
         String modelFileName ="/Users/sahil/Downloads/NLP/model1";
         GISModel m = (GISModel) new SuffixSensitiveGISModelReader(new File(modelFileName)).getModel();
         Scanner in = new Scanner(new File("src/test.txt"));
         String[] features = new String[10];
         int total = 0;
         int correct = 0;
         int wrong = 0;
         int re = 0;
         int total_tag = 0;
         int total_correct = 0;
         float f_measure;
         boolean flag = false, current_match = true;
         boolean r_flag = false, r_current_match = true;
         String p_line = null, c_line = in.nextLine(), n_line = null;
         String p_tag= "<@>";
         while(in.hasNextLine())
         {
             if(in.hasNextLine())
                n_line = in.nextLine();
             
             String arr[] = c_line.split(" ");
             if(arr.length == 1)
             {
                  p_line = null;
                  c_line = n_line;
                  p_tag = "<@>";
                  continue;
             }
             
             if(p_line==null || (p_line.split(" ")).length == 1)
             {    
                 features[0] = "Previous_Word=<START>";
                 features[1] = "Previous_Tag=<>";
                 features[2] = "Previous_Upper_Case=false";
                 features[3] = "Previous_BIO=<@>";
             }
             else
             {
                 features[0] = "Previous_Word="+(p_line.split(" "))[0];
                 features[1] = "Previous_Word="+(p_line.split(" "))[1];
                 features[2] = "Previous_Upper_Case="+(Character.isUpperCase((p_line.split(" "))[0].charAt(0)));
                 features[3] = "Previous_BIO="+p_tag;
             }
             
             features[4] = "Current_Word="+arr[0];
             features[5] = "Upper_Case=" + Character.isUpperCase(arr[0].charAt(0));
             features[6] = "Current_Tag=" +arr[1];
             
             if(n_line==null || (n_line.split(" ")).length==1)
             {   
                 features[7] ="Next_Word=<END>";
                 features[8] ="Next_Tag=<!>";
                 features[9] ="Next_Upper_Case=false";
             }
             else
             {
                 features[7] ="Next_Word="+(n_line.split(" "))[0];
                 features[8] ="Next_Tag="+(n_line.split(" "))[1];
                 features[9] ="Next_Upper_Case="+(Character.isUpperCase((n_line.split(" "))[0].charAt(0)));
             }
             p_line = c_line;
             c_line = n_line;
             
            System.out.print(arr[0]+"=");
            String result = m.getBestOutcome(m.eval(features));
            p_tag = result;
            if(!flag && (arr[2].equals("I") || arr[2].equals("B")))
            {
                flag = true;
                if(result.equals(arr[2]))
                    current_match = true;
                else
                    current_match = false;
            }
            else if(flag  && (arr[2].equals("I") || arr[2].equals("B")))
            {
                if(!result.equals(arr[2]))
                    current_match = false;
            }
            else if(arr[2].equals("O") && flag)
            {
                flag= false;
                total++;
                if(result.equals("O") && current_match)
                    correct++;
                else
                    wrong++;
                current_match=true;
            }
            
            if(!r_flag && (result.equals("I") || result.equals("B")))
            {
                r_flag = true;
            }
            else if(r_flag && (result.equals("I") || result.equals("B")))
            {
                r_flag = true;
            }
            else if(r_flag && (result.equals("O")))
            {
                r_flag = false;
                re++;
            }
            System.out.print(m.getBestOutcome(m.eval(features)));
            System.out.println("  " + arr[2]);
            
            if(result.equals(arr[2]))
                total_correct++;
            total_tag++;
            
         }
         
         float num = (float) (correct);
         float den = (float) (total);
         float numw = (float) (wrong);
         float f_re = (float) (re);
         float precision = num*100/den;
         float recall = num*100/f_re;
         float totalnum = (float) total_correct;
         float totalden = (float) total_tag;
         float tag_acc = totalnum*100/totalden;
         f_measure = 2*precision*recall / (precision+recall);
         System.out.println("Precision = " + precision);
         System.out.println("Recall = " + recall);
         System.out.println("f_measure = " + f_measure);
         System.out.println("Tag Accuracy = " + tag_acc);
         
    }
     
}
