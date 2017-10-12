package sanguinehaze.charactercreator.adapter.persistence;

import sanguinehaze.charactercreator.adapter.persistence.method.FileSystemPersistenceMethod;
import sanguinehaze.charactercreator.adapter.persistence.method.PersistenceMethod;

public class SourcePersistanceFactory {
    public static SourcePersistence Create() {

        PersistenceMethod persistanceMethod = new FileSystemPersistenceMethod();
        return new SourcePersistence(persistanceMethod);
    }
}
