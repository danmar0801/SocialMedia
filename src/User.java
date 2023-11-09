import java.util.ArrayList;
import java.util.List;


public class User  implements UserGroupComponent{
    UserManager userManager = UserManager.getInstance();
    private String userID;
    private List<User> following;
    private List<User> followers;
    private List<String> posts;

    public User(String userID){
        this.userID = userID;
        this.following = new ArrayList<>();
        userManager.addUserToMap(this);
    }
    public void accept(Visitor visitor) {
        visitor.visitUser(this);
    }

    // users cannot add or remove no does it have children
    public void add(UserGroupComponent component) {
        throw new UnsupportedOperationException();
    }

    public void remove(UserGroupComponent component) {
        throw new UnsupportedOperationException();
    }

    public UserGroupComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    public String getId() {
        return userID;
    }

    public void setID(String ID) {
        this.userID = ID;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public void addFollowing(User user){
        following.add(user);
    }

    public List<String> getPosts() {
        return posts;
    }

    public void setPosts(List<String> posts) {
        this.posts = posts;
        userManager.setPostCount(userManager.getPostCount()+posts.size());
    }
    public void addPost(String post){
        posts.add(post);
        userManager.incPostCount();
    }
}
