package bot;

import entity.City;

public class GetInfo {
    static public String getInfoStringFromCity(City city){
        String main = "Вот вам погода по городу "+city.getName()+"!\n";
        String country = "Страна "+city.getSys().getCountry()+"\n";
        System.out.println(city.toString());
        String sky = "небо: "+city.getWeather().get(0).getDescription()+"\n";
        String temp = "температура в кельвинах: "+city.getMain().getTemp()+"\n";
        String tempFeels = "ощущаеться как : "+city.getMain().getFeels_like()+"\n";
        String pressure = "давление в hPa : "+city.getMain().getPressure()+"\n";
        String humidity = "влажность : "+city.getMain().getHumidity()+"%\n";
        String windSpeed = "скорость ветра в м/с : "+city.getWind().getSpeed()+"\n";
        String all = main+country+sky+temp+tempFeels+pressure+humidity+windSpeed;
        return all;
    }
}
