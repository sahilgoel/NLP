/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crf_f_measure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author sahil
 */
public class CRF_F_Measure {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
         int total = 0;
         int correct = 0;
         int wrong = 0;
         int re = 0;
         int total_tag = 0;
         int total_correct = 0;
         float f_measure;
         boolean flag = false, current_match = true;
         boolean r_flag = false, r_current_match = true;
       Scanner in1 = new Scanner(new File("/Users/sahil/Documents/Sahil_Study/NYU/NLP/Assignment_6/test.txt"));
       Scanner in2 = new Scanner(new File("/Users/sahil/Documents/Sahil_Study/NYU/NLP/Assignment_6/CRF_test"));
       while(in1.hasNextLine())
         {
             String c_line = in1.nextLine();
             String arr[] = c_line.split(" ");
             if(arr.length == 1)
             {
                 if(flag)
                 {
                    flag = false;
                    if(current_match)
                        correct++;
                    total++;
                 }
                 continue;
             }
            String[] temp = in2.nextLine().split(" ");
            String result = temp[0];
           
            if(!flag && (arr[2].equals("I") || arr[2].equals("B")))
            {
                flag = true;
                if(result.equals(arr[2]))
                    current_match = true;
                else
                {
                    System.out.println(result+" "+arr[2]+" "+arr[0]);
                    current_match = false;
                }
            }
            else if(flag  && (arr[2].equals("I") || arr[2].equals("B")))
            {
                if(!result.equals(arr[2]))
                {   
                    
                    System.out.println(result+" "+arr[2]+" "+arr[0]);
                    current_match = false;
                }
                
            }
            else if(arr[2].equals("O") && flag)
            {
                flag= false;
                total++;
                if(result.equals("O") && current_match)
                    correct++;
                else
                {
                    System.out.println(result+" "+arr[2]+" "+arr[0]);
                    wrong++;
                }
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
