public interface UserGroupComponent {
    // common operations that users and groups will have
    void add(UserGroupComponent component);   // Used to add a component to a group
    void remove(UserGroupComponent component); // Used to remove a component from a group
    UserGroupComponent getChild(int i);        // Used to get a child component of a group
    String getId();                          // To retrieve the unique identifier of the component
    void setID(String ID);                  // To set the unique identifier of the component

    void accept(Visitor visitor);            // to accept visitors
}
