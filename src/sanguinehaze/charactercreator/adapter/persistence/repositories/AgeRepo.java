package sanguinehaze.charactercreator.adapter.persistence.repositories;

import sanguinehaze.charactercreator.domain.dtos.Age;

import java.util.List;

public interface AgeRepo {
    List<Age> getAllAges();
}
