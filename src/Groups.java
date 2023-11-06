import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Groups implements UserGroupComponent{
    private List<UserGroupComponent> components = new ArrayList<>();
    private String id;

    public Groups(String id){
        this.id = id;

    }

    public List<UserGroupComponent> getComponents() {
        // You could return an unmodifiable list to prevent external modification:
        return Collections.unmodifiableList(components);
        // Or you could return a shallow copy:
        // return new ArrayList<>(components);
    }
    public void add(UserGroupComponent component) {
        components.add(component);
    }

    public void remove(UserGroupComponent component) {
        // this function is in beta so doesn't work properly yet
        components.remove(component);
    }

    public UserGroupComponent getChild(int i) {
        return components.get(i);
    }

    public String getId() {
        return id;
    }
    public void setId(String userID) {
        this.id = id;
    }
}
