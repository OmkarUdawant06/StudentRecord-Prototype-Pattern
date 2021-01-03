package studentskills.mytree;

public interface SubjectI {
    void registerObserver(StudentRecord studentRecord);
    void removeObserver(StudentRecord studentRecord);
    void notifyAll(StudentRecord studentRecord, Operation operation);
}
