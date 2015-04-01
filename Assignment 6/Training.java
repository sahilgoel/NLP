/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nlp;
import java.io.File;
import java.io.FileReader;
import opennlp.maxent.*;
import opennlp.maxent.io.*;
import opennlp.model.EventStream;
/**
 *
 * @author sahil
 */
public class NLP {
public static void main (String[] args) {
    String dataFileName = "/Users/sahil/Downloads/Text/src/features.txt";
    String modelFileName = "model1";
    try {
        FileReader datafr = new FileReader(new File(dataFileName));
        EventStream es = new BasicEventStream(new PlainTextByLineDataStream(datafr));
        GISModel model = GIS.trainModel(es, 100, 4);
        File outputFile = new File(modelFileName);
        GISModelWriter writer = new SuffixSensitiveGISModelWriter(model, outputFile);
        writer.persist();
    } catch (Exception e) {
        System.out.print("Unable to create model due to exception: ");
        System.out.println(e);
    }
}
    
}
