package br.com.fiap.bo;

import br.com.fiap.dao.AcaoAprendizDAO;
import br.com.fiap.to.AcaoAprendizTO;

import java.util.ArrayList;

public class AcaoAprendizBO {
    private AcaoAprendizDAO acaoAprendizDAO;

    public AcaoAprendizTO save(AcaoAprendizTO acaoAprendiz) {
        acaoAprendizDAO = new AcaoAprendizDAO();

        return acaoAprendizDAO.save(acaoAprendiz);
    }

    public AcaoAprendizTO update(AcaoAprendizTO acaoAprendiz) {
        acaoAprendizDAO = new AcaoAprendizDAO();

        // Se id for vazio
        if (acaoAprendiz.getId() == null) {
            return null;
        }

        return acaoAprendizDAO.update(acaoAprendiz);
    }

    public boolean delete(Long id) {
        acaoAprendizDAO = new AcaoAprendizDAO();

        // Se id for vazio
        if (id == null) {
            return false;
        }

        return acaoAprendizDAO.delete(id);
    }

    public AcaoAprendizTO findById(Long id) {
        acaoAprendizDAO = new AcaoAprendizDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return acaoAprendizDAO.findById(id);
    }

    public ArrayList<AcaoAprendizTO> findAll() {
        acaoAprendizDAO = new AcaoAprendizDAO();

        return acaoAprendizDAO.findAll();
    }
}
