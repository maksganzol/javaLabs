package sample.Output;

import java.io.*;

public class ReportHandler {

    private String directory, fileName;
    private FileWriter fW;

    public ReportHandler(String directory, String fileName) {
        this.directory = directory;
        this.fileName = fileName;
        try {
            fW= new FileWriter(directory + fileName, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public ReportHandler(String fileName) {
        this.fileName = fileName;
        directory = "C:/";
        try {
            fW = new FileWriter(directory + fileName, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void writeRow(String[] text){
        for(String str: text){
            try {
                fW.write(str+",");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fW.write("\n");
            fW.flush();
            fW.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Written!");

    }




}
