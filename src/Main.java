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
        UserManager userManager = UserManager.getInstance();
        AdminWindow frame = AdminWindow.getInstance();
        User user1 = new User("Danny-111");

        User user2 = new User("fluffy23");
        User user3 = new User("ace12");
        User user4 = new User("cryptoking200");


        user1.addFollowing(user2);
        user1.addFollowing(user3);





        //UserWindow frame2 = new UserWindow(user1);


    }
}