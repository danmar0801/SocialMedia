import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {
    private static UserManager instance = null;
    Map<String, User> userMap = new HashMap<>();

    private UserManager() {

    }

    public static UserManager getInstance(){
        if(instance == null){
            instance = new UserManager();
        }
        return instance;
    }

    public void addUserToMap(User user){
        userMap.put(user.getId(),user);
    }
    public User getUserRef(String input){
        User user = userMap.get(input);
        return user;
    }
    public String[] getUserFollowing(User user){
        List<User> following = user.getFollowing();
        String[] list = new String[following.size()];
        for (int i = 0; i < following.size(); i++) {
            list[i] = following.get(i).getId();
        }
        return list;
    }
}
