package ro.mta.se.lab;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryLogger {

    private static HistoryLogger historyLogger = null;
    private File logFile;
    private FileWriter fileWriter;
    private final String logString = "[INFO]: ";

    public static HistoryLogger getInstance(){
        if(historyLogger == null){
          historyLogger = new HistoryLogger();
        }
        return historyLogger;
    }

    public HistoryLogger() {
        logFile = new File("loggers");
        try {
            logFile.createNewFile();
            fileWriter = new FileWriter(logFile);
        }
        catch (IOException e){
            AlertClass alertClass = new AlertClass(1, "Eroare", "Nu s-a putut deschide fisierul");
            alertClass.showAlert();
        }
    }

    public void close(){
        try {
            fileWriter.close();
        }
        catch (IOException e){
            AlertClass alertClass = new AlertClass(1, "Eroare", "Nu s-a putut închide fișierul");
            alertClass.showAlert();
        }
    }

    public boolean log(String city, String country, WeatherForecast weatherForecast){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String finalString;

        finalString = formatter.format(date) + logString + country + " " + city + " temp" + String.valueOf(weatherForecast.getTemp()) +
                " humidity" + String.valueOf(weatherForecast.getHumidity()) + "\n";

        try {
            fileWriter.write(finalString);
        }
        catch (IOException e){
            AlertClass alertClass = new AlertClass(1, "Eroare", "Nu s-a putut scrie în fișier");
            alertClass.showAlert();
        }
        return true;
    }
}
