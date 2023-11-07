import java.util.ArrayList;
import java.util.List;

public class User  implements UserGroupComponent{
    UserManager userManager = UserManager.getInstance();
    private String userID;
    private List<User> following;
    private List<User> followers;
    private String[] posts;

    public User(String userID){
        this.userID = userID;
        this.following = new ArrayList<>();
        userManager.addUserToMap(this);
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

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public void addFollowing(User user){
        following.add(user);
    }
    // this method is for testing purposes
    public void printFollowing() {
        for (User user : following) {
            System.out.println(user.getId());
        }
    }

}
