package studentskills.util;

public class CreateUniqueID {
    static int UniqueID = 0;

    public static int getID(){
        return UniqueID++;
    }
}
