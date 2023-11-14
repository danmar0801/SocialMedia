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
        // things to do
        // make sure user can't be added to more than one group, add a visitor to visit groups
        // add groups to other groups
        // visitor to go through the posts of every user and find specific words
        // maybe add option to unfollow users
        // add option to delete users from admin panel/
        // Singleton - admin window. user manager
        // composite - user/group hierarchy. Swing in admin window and user window
        // visitor - user manager
        // observer - posting feature/
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
        Groups Major = new Groups("majors");
        Groups Athletes = new Groups("Athletes");

        csMajor.add(user1);
        csMajor.add(user2);
        Athletes.add(user3);
        Major.add(user4);
        root.add(csMajor);
        root.add(Major);
        root.add(Athletes);


        user2.addPost("Message from user 2, happy, good, nice");
        user3.addPost("Message from user 3, nice, nice, happy");


        AdminWindow.getInstance();



        //new UserWindow(user1);


    }
}