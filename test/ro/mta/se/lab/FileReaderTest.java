package ro.mta.se.lab;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    //alerta care se trigger-ește trebuie afișată vizual si nu poate fi mock"ănitiă"
    //deoarece nu este incărcat ecranul
    @Test
    void fileScanner() {
        FileReader fileReader = new FileReader("wrongPath");

        assertFalse(fileReader.fileScanner());
    }
}