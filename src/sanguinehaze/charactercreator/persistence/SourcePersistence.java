package sanguinehaze.charactercreator.persistence;

import sanguinehaze.charactercreator.persistence.method.PersistenceMethod;

import java.util.ArrayList;

public class SourcePersistence {

    private PersistenceMethod _persistenceMethod;

    public SourcePersistence(PersistenceMethod persistenceMethod) {
        _persistenceMethod = persistenceMethod;
    }

    public ArrayList<String> GetData(String identifier) {
        return _persistenceMethod.GetData(identifier);
    }

}
