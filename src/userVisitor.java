import java.util.List;

public class userVisitor implements Visitor{

    public void visitUser(User user) {

    }

    @Override
    public void visitGroup(Groups group) {

    }

    public String[] getFollowing(User user){
        List<User> following = user.getFollowing();
        String[] list = new String[following.size()];
        for (int i = 0; i < following.size(); i++) {
            list[i] = following.get(i).getId();
        }
        return list;
    }

}
