import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidateID implements Visitor{
    private Set<String> ids = new HashSet<>();
    private int totalInvalidIds;


    // Visits a group and processes its components.
    public void visitGroup(Groups group) {
        validateId(group.getId());
        for (UserGroupComponent component : group.getComponents()) {
            component.accept(this);
        }
    }
    // Visits a user and counts the occurrence of positive words in their posts.
    public void visitUser(User user) {
        validateId(user.getId());
    }
    private void validateId(String id){
        if( id == null || id.contains(" ") || !ids.add(id)){
            totalInvalidIds++;
        }
    }
    public int getTotalInvalidIds() {
        return totalInvalidIds;
    }
}

