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
        User user1 = new User("Danny-111");

        User user2 = new User("fluffy23");
        User user3 = new User("ace12");
        User user4 = new User("cryptoking200");


        user1.addFollowing(user2);
        user1.addFollowing(user3);

        user2.addFollowing(user3);
        user2.addFollowing(user4);

        user3.addFollowing(user1);
        user3.addFollowing(user2);

        user4.addFollowing(user1);
        user4.addFollowing(user3);

        // create the groups
        Groups root = new Groups("Root");
        Groups csMajor = new Groups("CS majors");
        Groups Athletes = new Groups("Athletes");

        csMajor.add(user1);
        csMajor.add(user2);
        Athletes.add(user3);

        root.add(csMajor);
        root.add(Athletes);
        root.add(user4);

        //userManager.addRootGroup(root);



        AdminWindow frame = AdminWindow.getInstance();

        //UserWindow frame2 = new UserWindow(user1);


    }
}