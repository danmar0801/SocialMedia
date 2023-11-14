public interface Subject {
    void registerObserver(User o);
    void removeObserver(User o);
    void notifyObservers();

}
