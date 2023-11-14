import javax.swing.tree.DefaultMutableTreeNode;
import java.util.*;


// this class is responsible for user and group operations. this class
// implements the singleton design patterns and acts as a client to the user and group classes
// it
public class UserManager {
    private static UserManager instance = null;
    // the purpose of this list is to be able to have a master list of users to help see if a user exist or
    // to connect a string to an object
    Map<String, User> userMap;
    // master list of all groups
    Map<String, Groups> groupsList;

    // this group is used when building the tree view for the admin panel,
    // also to retrieve the largest component in the user group hierarchy
    Groups root;
    int postCount = 0;

    private UserManager() {
        this.userMap = new HashMap<>();
        this.groupsList = new HashMap<>();

    }

    public static UserManager getInstance(){
        // lazy init
        if(instance == null){
            instance = new UserManager();
        }
        return instance;
    }

    //methods to the user master list
    public void addUserToMap(User user){
        userMap.put(user.getId(),user);
    }
    public User getUserRef(String input){
        return userMap.get(input);
    }
    public boolean isUserExist(String ID){
        return userMap.containsKey(ID);
    }

    // methods to the group master list
    public void addGroup(Groups group){
        if (group.getId().equals("Root")){
            root = group;
        }
        groupsList.put(group.getId(),group);
    }
    public Groups getRootGroup(){
        return root;
    }
    public Groups getGroupRef(String selectedObject) {
        return groupsList.get(selectedObject);
    }

    // this method is to format the user following from the user.getfollowing
    // to a string array to display in the user window
    public String[] getUserFollowing(User user){
        List<User> following = user.getFollowing();
        String[] list = new String[following.size()];
        for (int i = 0; i < following.size(); i++) {
            list[i] = following.get(i).getId();
        }
        return list;
    }
    public String[] getUserFeed(User user){
        return user.getFeed();
    }



    // methods for the stats
    public int getTotalUsers(){
        return userMap.size();
    }
    public int getTotalGroups(){
        return groupsList.size();
    }

    public int getPositivityScore(){
        Set<String> listWords = new HashSet<>();
        listWords.add("Message");
        listWords.add("from");
        PositiveReport wordCounter = new PositiveReport(listWords);
        root.accept(wordCounter);
        int totalOccurrences = wordCounter.getCounter();
        return totalOccurrences;
    }

    public int getPostCount(){
        return postCount;
    }
    public void incPostCount(){
        postCount++;
    }
}
