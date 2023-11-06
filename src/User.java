import java.util.List;

public class User  implements UserGroupComponent{

    private String userID;
    private String parentGroup;
    private List<User> following;
    private List<User> followers;
    private String[] posts;

    public User(String userID){
        this.userID = userID;
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

    public void setId(String userID) {
        this.userID = userID;
    }

}
