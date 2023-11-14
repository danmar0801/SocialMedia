import java.util.*;


// this class is responsible for user and group operations. this class
// implements the singleton design patterns and acts as a client to the user and group classes
public class UserManager {
    private static UserManager instance = null;
    // the purpose of this list is to be able to have a master list of users to help see if a user exist or
    // to connect a UserId to a User ref
    Map<String, User> userMap;
    Map<String, Groups> groupsList; // master list of all groups
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
