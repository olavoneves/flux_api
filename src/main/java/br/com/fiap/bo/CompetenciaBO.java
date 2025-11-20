package br.com.fiap.bo;

import br.com.fiap.dao.CompetenciaDAO;
import br.com.fiap.to.CompetenciaTO;

import java.util.ArrayList;

public class CompetenciaBO {
    private CompetenciaDAO competenciaDAO;

    public CompetenciaTO save(CompetenciaTO usuario) {
        competenciaDAO = new CompetenciaDAO();

        return competenciaDAO.save(usuario);
    }

    public CompetenciaTO update(CompetenciaTO usuario) {
        competenciaDAO = new CompetenciaDAO();

        // Se id for vazio
        if (usuario.getId() == null) {
            return null;
        }

        return competenciaDAO.update(usuario);
    }

    public boolean delete(Long id) {
        competenciaDAO = new CompetenciaDAO();

        // Se id for vazio
        if (id == null) {
            return false;
        }

        return competenciaDAO.delete(id);
    }

    public CompetenciaTO findById(Long id) {
        competenciaDAO = new CompetenciaDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return competenciaDAO.findById(id);
    }

    public ArrayList<CompetenciaTO> findAll() {
        competenciaDAO = new CompetenciaDAO();

        return competenciaDAO.findAll();
    }
}
