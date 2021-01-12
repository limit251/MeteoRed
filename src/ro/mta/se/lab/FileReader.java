package ro.mta.se.lab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private String filePath;
    private LocationManager locationManager;

    public FileReader(String path){
        filePath = path;
        locationManager = LocationManager.getInstance();
    }

    public void fileScanner(){
        Scanner scanner = null;
        String line;

        try{
            scanner = new Scanner(new File(filePath));
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }

        while (true){
            assert scanner != null;
            if (!scanner.hasNextLine()){
                scanner.close();
                break;
            }
            line = scanner.nextLine();
            locationManager.addCity(line);
        }

    }

}
