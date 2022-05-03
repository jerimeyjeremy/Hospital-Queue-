/**
 *Name: Jerimey Simons 
 *Date: 3/13/2022
 *Description: Main Method to create, sort, and parse through linked list.
 */

package whackyhospital;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class WhackyHospital {
    

    public static void main(String[] args) {
        //variable library
        int interval = 2;
        int intervalMI = 2;
        int intervalGA = 4;
        int intervalC = 8;
        
        String FIELD_SEP  = ";";
        
        PatientNode headMI = null; //Myocardial Infarction head pointer(MI)
        PatientNode headGA = null;//Gushing Artery head pointer (GA)
        PatientNode headC = null; //Cephalalgia head pointer (C)
        PatientNode workHorse = new PatientNode(); //Wile Horse
        
        Path recordsPath = null;
        File recordsFile = null;
        /**********************************/
        
        //Pulls records from file, creates nodes and linked list with queue priority
        recordsPath = Paths.get("Patient-2.txt");
        if(Files.exists(recordsPath)){   
            recordsFile = recordsPath.toFile();
            try(BufferedReader in = new BufferedReader(
                                    new FileReader(recordsFile))){
                String line = in.readLine();
                //parse through file and collect data 
                while(line != null){    
                    String[] fields = line.split(FIELD_SEP); 
                    PatientNode patient = new PatientNode();
                    patient.name = fields[0].trim();
                    patient.employee = Boolean.parseBoolean(fields[1].trim());
                    patient.insured = Boolean.parseBoolean(fields[2].trim());
                    patient.queue = fields[3].trim();
                    //create structure
                    switch(patient.queue){
                        case "0":
                            headMI = workHorse.sortList(headMI,patient);
                            break;
                        case "1":
                            headGA = workHorse.sortList(headGA,patient);
                            break;
                        case "2":
                            headC = workHorse.sortList(headC, patient);
                            break;
                    }
                    line = in.readLine();  
                }
            }catch(IOException e){
                System.out.println(e.fillInStackTrace());
            }
        }else{
            System.out.println(recordsPath.toAbsolutePath() + "can't be found");
        }
        
       //Parse through singly linked list 
       while(headMI !=null && headGA !=null && headC !=null){
           
            try {
                Thread.sleep(2000);
                if(interval ==intervalMI){
                    System.out.print(interval +"sec ");
                    headMI = workHorse.pop(headMI);
                    intervalMI +=2;
                }
                if(interval ==intervalGA){
                    System.out.print(interval +"sec ");
                    headGA = workHorse.pop(headGA);
                    intervalGA +=4;
                }
                if(interval == intervalC){
                    System.out.print(interval +"sec ");
                   headC = workHorse.pop(headC); 
                   intervalC +=8;
                }
                interval +=2;
            }catch(InterruptedException e){    
            }  
       }
       
      
    }  
}
