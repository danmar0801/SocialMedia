import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

public class PositiveReport implements Visitor{
    private Set<String> words;
    private int counter;

    public PositiveReport(Set<String> words) {
        this.words = words;
        this.counter = 0;
    }
    public void visitGroup(Groups group) {
        for (UserGroupComponent component : group.getComponents()) {
            component.accept(this);
        }
    }
    public void visitUser(User user) {
        List<Post> posts = user.getPosts();
        for (Post post : posts) {
            for (String word : words) {
                if (post.getText().contains(word)) {
                    counter++;
                    System.out.println(counter);
                }
            }
        }
    }


    public int getCounter() {
        return counter;
    }
}
