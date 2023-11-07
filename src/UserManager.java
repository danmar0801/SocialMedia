import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// this class is responsible for user and group operations. this class
// implements the singleton design patterns and acts as a client to the user and group classes
// it
public class UserManager {


    Groups root;
    private static UserManager instance = null;
    Map<String, User> userMap = new HashMap<>();
    List<Groups> groupsList = new ArrayList<>();
    int postCount = 0;

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
    public void addRootGroup(Groups group){
        root = group;
    }
    public void addGroup(Groups group){
        if (group.getId() == "Root"){
            addRootGroup(group);
        }
        groupsList.add(group);
    }
    public Groups getRootGroup(){
        return root;
    }

    public int getTotalUsers(){
        return userMap.size();
    }
    public int getTotalGroups(){
        return groupsList.size();
    }

    public int getPositivityScore(){
        return groupsList.size();
    }

    public void incPostCount(){
        postCount++;
    }

    public int getPostCount(){
        return postCount;
    }

    public void setPostCount(int count){
        postCount = count;
    }

    public void printFollowing(User user){
        for (User i : user.getFollowing()) {
            System.out.println(i.getId());
        }
    }

    public boolean isUserExist(String ID){
        if (userMap.containsKey(ID)) {return true;}
        return false;
    }

}
