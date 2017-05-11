package persistence;

import persistence.method.FileSystemPersistenceMethod;
import persistence.method.PersistenceMethod;

public class SourcePersistanceFactory {
    public static SourcePersistence Create() {

        PersistenceMethod persistanceMethod = new FileSystemPersistenceMethod();
        return new SourcePersistence(persistanceMethod);
    }
}
