import java.util.List;
import java.util.ArrayList;
public class YoutubeChannel {
    private List<Observer> subscribers = new ArrayList<>();

    public void subscribe(Observer observer)
    {
        subscribers.add(observer);
    }

    public void uploadVideo(String title)
    {
        System.out.println("New Video: "+ title);

        for(Observer observer : subscribers)
        {
            observer.update(title);
        }
    }
}
