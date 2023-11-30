package org.example.service.planet;

import org.example.entities.Planet;
import org.example.repository.GenericDao;

import java.util.List;

public class PlanetCrudService {
    private final GenericDao<Planet, String> planetDao;

    public PlanetCrudService(GenericDao<Planet, String> planetDao) {
        this.planetDao = planetDao;
    }

    public boolean createPlanet(Planet planet) {
        return planetDao.create(planet);
    }

    public boolean updatePlanet(Planet planet) {
        return planetDao.update(planet);
    }

    public Planet getPlanetById(String planetId) {
        return planetDao.getById(planetId);
    }

    public List<Planet> getAllPlanets() {
        return planetDao.getAll();
    }

    public void deletePlanetById(String planetId) {
        planetDao.deleteById(planetId);
    }

    public void deletePlanet(Planet planet) {
        planetDao.delete(planet);
    }
}
