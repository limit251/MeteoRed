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

    public boolean fileScanner(){
        Scanner scanner = null;
        String line;

        try{
            scanner = new Scanner(new File(filePath));
        }
        catch (FileNotFoundException e){
            AlertClass alertClass = new AlertClass(1, "Eroare", "Nu s-a găsit fișierul de intrare");
            alertClass.showAlert();
            return false;
        }

        while (true){
            assert scanner != null;
            if (!scanner.hasNextLine()){
                scanner.close();
                break;
            }
            line = scanner.nextLine();
            if (!locationManager.addCity(line)){
                AlertClass alertClass = new AlertClass(1, "Eroare parsare fișier",
                        "Verificați fisierul și restartați aplicația.");
                alertClass.showAlert();
                return false;
            };
        }
        return true;
    }

}
