import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class getUserFeed implements Visitor{
    // how this class will work
    // get following list
    // get the feed from the user
    // get the feed from the following
    // merge the user feed and following feed
    // must sort them in the list based on the time of post

    private List<Post> masterFeed;


    public getUserFeed(User user){
        this.masterFeed = new ArrayList<>();
        user.accept(this);
    }
    public void visitUser(User user) {
        // get the user feed and add it to the
        List<Post> userPosts = user.getPosts();

        // get the posts from the following
        // list of following
        List<Post> follwoingPosts = new ArrayList<>();
        // go through every following and add their posts to following feed
        for (User followingUser : user.getFollowing()) {
            follwoingPosts.addAll(followingUser.getPosts());
        }


        // add the users posts and following posts to the list
        masterFeed.addAll(userPosts);
        masterFeed.addAll(follwoingPosts);

        // sort the master list based on when the posts have been added;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        masterFeed.sort(Comparator.comparing(post -> LocalTime.parse(post.getTime(), formatter)));

    }

    public void visitGroup(Groups group) {
        // this method isn't need in this class
    }

    public List<Post> getMasterFeed() {
        return masterFeed;
    }


}
