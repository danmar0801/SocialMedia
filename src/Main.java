public class Main {
    // some notes
    /*Singleton pattern for the admin panel
    Composite pattern for the user/groups
    visitor pattern for the stats buttons
    observer pattern for the feed.

    // admin window done
    user window

    * */
    public static void main(String[] args) {
        AdminWindow frame = AdminWindow.getInstance();
        // user 1
        User user1 = new User("Dany");
        user1.setId("Dany123");


    }
}