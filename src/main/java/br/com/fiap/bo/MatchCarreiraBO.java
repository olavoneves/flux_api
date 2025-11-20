package br.com.fiap.bo;

import br.com.fiap.dao.MatchCarreiraDAO;
import br.com.fiap.to.MatchCarreiraTO;

import java.util.ArrayList;

public class MatchCarreiraBO {
    private MatchCarreiraDAO matchCarreiraDAO;

    public MatchCarreiraTO save(MatchCarreiraTO matchCarreira) {
        matchCarreiraDAO = new MatchCarreiraDAO();

        return matchCarreiraDAO.save(matchCarreira);
    }

    public MatchCarreiraTO update(MatchCarreiraTO matchCarreira) {
        matchCarreiraDAO = new MatchCarreiraDAO();

        // Se id for vazio
        if (matchCarreira.getId() == null) {
            return null;
        }

        return matchCarreiraDAO.update(matchCarreira);
    }

    public boolean delete(Long id) {
        matchCarreiraDAO = new MatchCarreiraDAO();

        // Se id for vazio
        if (id == null) {
            return false;
        }

        return matchCarreiraDAO.delete(id);
    }

    public MatchCarreiraTO findById(Long id) {
        matchCarreiraDAO = new MatchCarreiraDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return matchCarreiraDAO.findById(id);
    }

    public ArrayList<MatchCarreiraTO> findAll() {
        matchCarreiraDAO = new MatchCarreiraDAO();

        return matchCarreiraDAO.findAll();
    }
}
