package bot;

import entity.City;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    //думал спрашивать город сохраняя мембера в память что бы принять ответ и отдать город но хз, и так ведь работает))
    String botUserName = "TestWeatherrBot";
    String botToken = "1876257632:AAEnfMC8_hF2KSUTLu7bUPvx4yxB7khUM9o";
    HttpGetWeather http = new HttpGetWeather();


    public String getBotUsername() {
        return botUserName;
    }

    public String getBotToken() {
        return botToken;
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String command = "0";
        String probableCity = "0";
        if(message.getText().length() > 12){
            command = message.getText().substring(0, 12);
            probableCity = message.getText().substring(12);
        }
        System.out.println("автор: "+message.getFrom().getFirstName()+", текст: "+message.getText());

        switch(message.getText()) {
            case "/help":
            sendMsg(message, "С помощью этого бота вы можете узнать погоду в любом городе! \nПросто введите /getWeather и название города через пробел на английском, например: Kiev");
                break;
            case "/getWeather":
                sendMsg(message, "Введите название города через пробел после команды!");
                break;
            default:
                if(command.equals("/getWeather ")){
                    City city;
                    try {
                        city = http.getWeatherFromCity("http://api.openweathermap.org/data/2.5/weather?q="+probableCity+"&appid=97238627e9497618ae7edec3e92e2fa4");
                        if(city.getName() != null){
                            sendMsg(message, GetInfo.getInfoStringFromCity(city));
                        } else {
                            sendMsg(message, "Такого города нет в базе данных. Попробуйте другой!");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    public void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
