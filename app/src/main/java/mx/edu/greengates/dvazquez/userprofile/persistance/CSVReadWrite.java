package mx.edu.greengates.dvazquez.userprofile.persistance;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class CSVReadWrite {


    public final int ID = 0;
    public final int QUESTION = 1;
    public final int ANSWER = 2;
    public final int A1 = 3;
    public final int A2 = 4;
    public final int A3 = 5;
    public final int SUBJECT = 6;
    Context context;
    private String filename;
    private ArrayList<String[]> questions;
    private ArrayList<String[]> userData;


    public CSVReadWrite(Context context, String filename) {
        this.context = context;
        this.questions = this.readQuestionCSV(filename);
    }

    /**
     * This method reads the file assigned to the object and returns a String[] array
     * @return String[]
     */
    public ArrayList<String[]> readUserDataCSV(String fileName){
        ArrayList<String[]> document = new ArrayList<>();

        try {
            FileInputStream fileInputStream = context.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader csvReader = new BufferedReader(inputStreamReader);

            String row;
            int rowNum = 0;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                document.add(data);
            }
            csvReader.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
            System.out.println("Error" + ioe.getMessage());
        }
        return document;
    }

    /**
     * This method reads the file assigned to the object and returns a String[] array
     * @return String[]
     */
    public void writeUserDataCSV(String fileName,String[] userData) throws IOException {

        try {
            StringBuffer contentBuffer = new StringBuffer();
            int size = userData.length;
            int count = 0;
            for(String data:userData){
                contentBuffer.append(data);
                if(count < size - 1){
                    contentBuffer.append(",");
                    count++;
                }
            }
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(contentBuffer.toString().getBytes());
            bufferedOutputStream.close();

        } catch (IOException e){
            throw new IOException("Unable to access Application Data.");
        }

    }


    /**
     * This method reads the file assigned to the object and returns a String[] array
     * @return String[]
     */
    public ArrayList<String[]> readQuestionCSV(String filename){
        ArrayList<String[]> document = new ArrayList<>();

        try {
            InputStream inputStream = context.getAssets().open(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader csvReader = new BufferedReader(inputStreamReader);
            String row;
            int rowNum = 0;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                document.add(data);
            }
            csvReader.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
            System.out.println("Error" + ioe.getMessage());
        }
        return document;
    }
}
