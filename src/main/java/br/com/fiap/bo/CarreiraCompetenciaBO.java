package br.com.fiap.bo;

import br.com.fiap.dao.CarreiraCompetenciaDAO;
import br.com.fiap.to.CarreiraCompetenciaTO;

import java.util.ArrayList;

public class CarreiraCompetenciaBO {
    private CarreiraCompetenciaDAO carreiraCompetenciaDAO;

    public CarreiraCompetenciaTO save(CarreiraCompetenciaTO carreiraCompetencia) {
        carreiraCompetenciaDAO = new CarreiraCompetenciaDAO();

        return carreiraCompetenciaDAO.save(carreiraCompetencia);
    }

    public CarreiraCompetenciaTO update(CarreiraCompetenciaTO carreiraCompetencia) {
        carreiraCompetenciaDAO = new CarreiraCompetenciaDAO();

        // Se id for vazio
        if (carreiraCompetencia.getId() == null) {
            return null;
        }

        return carreiraCompetenciaDAO.update(carreiraCompetencia);
    }

    public boolean delete(Long id) {
        carreiraCompetenciaDAO = new CarreiraCompetenciaDAO();

        // Se id for vazio
        if (id == null) {
            return false;
        }

        return carreiraCompetenciaDAO.delete(id);
    }

    public CarreiraCompetenciaTO findById(Long id) {
        carreiraCompetenciaDAO = new CarreiraCompetenciaDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return carreiraCompetenciaDAO.findById(id);
    }

    public ArrayList<CarreiraCompetenciaTO> findAll() {
        carreiraCompetenciaDAO = new CarreiraCompetenciaDAO();

        return carreiraCompetenciaDAO.findAll();
    }
}
