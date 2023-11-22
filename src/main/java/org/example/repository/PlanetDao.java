package org.example.repository;

import org.example.entities.Planet;

import java.util.List;

public interface PlanetDao {

    boolean createPlanet(Planet planet);

    boolean updatePlanet(Planet planet);

    Planet getPlanetById(String planetId);

    List<Planet> getAllPlanets();

    void deletePlanetById(String planetId);

    void deletePlanet(Planet planet);
}
