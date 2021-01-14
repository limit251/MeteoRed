package ro.mta.se.lab;


import javafx.beans.property.*;

public class WeatherForecast {
    
    StringProperty desc;
    FloatProperty temp, windSpeed;
    IntegerProperty humidity;

    public WeatherForecast(){
        desc = new SimpleStringProperty();
        temp = new SimpleFloatProperty();
        windSpeed = new SimpleFloatProperty();
        humidity = new SimpleIntegerProperty();
    }

    public void setDesc(String desc) {
        this.desc.set(desc);
    }

    public void setTemp(float temp) {
        this.temp.set(temp);
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed.set(windSpeed);
    }

    public void setHumidity(int humidity) {
        this.humidity.set(humidity);
    }

    public String getDesc() {
        return desc.get();
    }

    public float getWindSpeed() {
        return windSpeed.get();
    }

    public int getHumidity() {
        return humidity.get();
    }

    public float getTemp() {
        return temp.get();
    }
}
