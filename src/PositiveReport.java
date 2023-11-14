import java.util.List;
import java.util.Set;

// A visitor class that generates a report on positive word usage in user posts.
public class PositiveReport implements Visitor{
    private Set<String> words;
    private int counter;

    public PositiveReport(Set<String> words) {
        this.words = words;
        this.counter = 0;
    }
    // Visits a group and processes its components.
    public void visitGroup(Groups group) {
        for (UserGroupComponent component : group.getComponents()) {
            component.accept(this);
        }
    }
    // Visits a user and counts the occurrence of positive words in their posts.
    public void visitUser(User user) {
        List<Post> posts = user.getPosts();
        for (Post post : posts) {
            for (String word : words) {
                if (post.getText().contains(word)) {
                    counter++;
                }
            }
        }
    }
    public int getCounter() {
        return counter;
    }
}
