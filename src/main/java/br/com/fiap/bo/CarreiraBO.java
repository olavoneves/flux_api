package br.com.fiap.bo;

import br.com.fiap.dao.CarreiraDAO;
import br.com.fiap.to.CarreiraTO;

import java.util.ArrayList;

public class CarreiraBO {
    private CarreiraDAO carreiraDAO;

    public CarreiraTO save(CarreiraTO carreira) {
        carreiraDAO = new CarreiraDAO();

        return carreiraDAO.save(carreira);
    }

    public CarreiraTO update(CarreiraTO carreira) {
        carreiraDAO = new CarreiraDAO();

        // Se id for vazio
        if (carreira.getId() == null) {
            return null;
        }

        return carreiraDAO.update(carreira);
    }

    public boolean delete(Long id) {
        carreiraDAO = new CarreiraDAO();

        // Se id for vazio
        if (id == null) {
            return false;
        }

        return carreiraDAO.delete(id);
    }

    public CarreiraTO findById(Long id) {
        carreiraDAO = new CarreiraDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return carreiraDAO.findById(id);
    }

    public ArrayList<CarreiraTO> findAll() {
        carreiraDAO = new CarreiraDAO();

        return carreiraDAO.findAll();
    }
}
