import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    public static  String getWeather (String message, Model model) throws IOException {

        // I was using https://openweathermap.org as a free weather forecast
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid= Here shoud be a weather API.");


        Scanner input = new Scanner((InputStream) url.getContent());
        String result ="";
        while (input.hasNext()){
            result += input.nextLine();
    }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray jsonArray = object.getJSONArray("weather");
        for (int i =0; i < jsonArray.length(); i++){

         JSONObject obj = jsonArray.getJSONObject(i);
         model.setIcon((String) obj.get("icon"));
         model.setMain((String)obj.get("main"));

        }
return "City: " + model.getName() + "\n"
        + "Temperature: " + model.getTemp() + " C"+ "\n"
        + "Humidity: " + model.getHumidity() + " %" + "\n"
        + "Weather condition:" + model.getMain() + "\n"
        +  "http://openweathermap.org/img/w/" + model.getIcon()+".png";
    }
}
