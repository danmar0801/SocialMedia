import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Post {
    private final String text;
    private final User owner;
    private final String time;

    public Post(String text, User user){
        this.text = text;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime now = LocalTime.now();
        this.time = now.format(formatter);
        this.owner = user;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public User getOwner() {
        return owner;
    }
}

