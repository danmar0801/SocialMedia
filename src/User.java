public class User  implements UserGroupComponent{

    private String userID;
    private String parentGroup;
    private String[] following;
    private String[] followers;
    private String[] posts;

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

    public String getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(String parentGroup) {
        this.parentGroup = parentGroup;
    }



    public String[] getFollowing() {
        return following;
    }

    public void setFollowing(String[] following) {
        this.following = following;
    }

    public String[] getFollowers() {
        return followers;
    }

    public void setFollowers(String[] followers) {
        this.followers = followers;
    }

    public String[] getPosts() {
        return posts;
    }

    public void setPosts(String[] posts) {
        this.posts = posts;
    }
}
