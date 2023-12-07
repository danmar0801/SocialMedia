public class LastActiveUser implements Visitor{
    private long lastTime;
    private User lastUser;

    public LastActiveUser(){
        this.lastTime = 0;
    }

    public void visitUser(User user) {
        findLastActiveUser(user);
    }


    public void visitGroup(Groups group) {
        for (UserGroupComponent component : group.getComponents()) {
            component.accept(this);
        }
    }

    private void findLastActiveUser(User user){
        if(user.getLastUpadteTime() > lastTime){
            lastTime = user.getLastUpadteTime();
            lastUser = user;
        }
    }

    public User getLastUser() {
        return lastUser;
    }
}
