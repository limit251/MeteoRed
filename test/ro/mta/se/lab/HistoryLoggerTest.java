package ro.mta.se.lab;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HistoryLoggerTest {

    @Mock
    WeatherForecast weatherForecast = mock(WeatherForecast.class);

    @Test
    void log() {
        when(weatherForecast.getHumidity()).thenReturn(98);
        when(weatherForecast.getTemp()).thenReturn((float) 283.32);

        HistoryLogger historyLogger = HistoryLogger.getInstance();
        assertTrue(historyLogger.log("City", "cOUNTRY", weatherForecast));
    }
}