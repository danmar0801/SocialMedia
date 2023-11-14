import java.util.ArrayList;
import java.util.List;

public class User  implements UserGroupComponent, Observer, Subject{
    private static final UserManager userManager = UserManager.getInstance();
    private String userID;
    private List<User> following; // users this user follow
    private List<User> followers; // users that follow this user
    private String[] feed;        // this list includes their posts and the posts of the accounts they follow
    private List<Post> posts;     // list of posts from this user only


    public User(String userID){
        this.userID = userID;
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.posts = new ArrayList<>();
        userManager.addUserToMap(this); // this will add the user to the master list of all users

    }
    // method to accept visitor
    public void accept(Visitor visitor) {
        visitor.visitUser(this);
    }

    // methods from the UserGroup Interface
    // users cannot add or remove nor does it have children
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

    public void addFollowing(User user){
        following.add(user);
        // this will update the followers list of the user to include this user
        user.registerObserver(this);

    }
    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(String text){
        Post post = new Post(text, this);
        posts.add(post);
        userManager.incPostCount();
        notifyObservers(); // Notify all followers about the new post
    }

    public void registerObserver(User o) {
        if (!followers.contains(o)) {
            followers.add(o);
        }
    }

    public void removeObserver(User o) {
        // this method won't be implemented now
    }

    public void notifyObservers() {
        for (Observer follower : followers) {
            follower.updateFeed();
        }
    }

    public void updateFeed() {
        getUserFeed userFeed = new getUserFeed(this);
        List<Post> masterFeed = userFeed.getMasterFeed();
        feed = FormatFeed.formatFeed(masterFeed);
    }

    public String[] getFeed(){
        // update the feed to make sure we get the latest posts no matter what
        updateFeed();
        return feed;
    }
}
