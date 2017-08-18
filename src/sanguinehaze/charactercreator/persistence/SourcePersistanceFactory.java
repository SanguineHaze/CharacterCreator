package sanguinehaze.charactercreator.persistence;

import sanguinehaze.charactercreator.persistence.method.FileSystemPersistenceMethod;
import sanguinehaze.charactercreator.persistence.method.PersistenceMethod;

public class SourcePersistanceFactory {
    public static SourcePersistence Create() {

        PersistenceMethod persistanceMethod = new FileSystemPersistenceMethod();
        return new SourcePersistence(persistanceMethod);
    }
}
