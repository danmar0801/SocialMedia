import java.util.List;
import java.util.stream.Collectors;

public class FormatFeed {
    public static String[] formatFeed(List<Post> posts) {
        return posts.stream()
                .map(post -> formatPost(post))
                .toArray(String[]::new); // Convert to a string array
    }

    private static String formatPost(Post post) {
        String userId = post.getOwner().getId();
        String text = post.getText();
        String time = post.getTime();
        return userId + " : " + text + " (" + time + ")";
    }
}
