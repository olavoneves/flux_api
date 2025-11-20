package br.com.fiap.bo;

import br.com.fiap.dao.FonteDadosDAO;
import br.com.fiap.to.FonteDadosTO;

import java.util.ArrayList;

public class FonteDadosBO {
    private FonteDadosDAO fonteDadosDAO;

    public FonteDadosTO save(FonteDadosTO fonteDados) {
        fonteDadosDAO = new FonteDadosDAO();

        return fonteDadosDAO.save(fonteDados);
    }

    public FonteDadosTO update(FonteDadosTO fonteDados) {
        fonteDadosDAO = new FonteDadosDAO();

        // Se id for vazio
        if (fonteDados.getId() == null) {
            return null;
        }

        return fonteDadosDAO.update(fonteDados);
    }

    public boolean delete(Long id) {
        fonteDadosDAO = new FonteDadosDAO();

        // Se id for vazio
        if (id == null) {
            return false;
        }

        return fonteDadosDAO.delete(id);
    }

    public FonteDadosTO findById(Long id) {
        fonteDadosDAO = new FonteDadosDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return fonteDadosDAO.findById(id);
    }

    public ArrayList<FonteDadosTO> findAll() {
        fonteDadosDAO = new FonteDadosDAO();

        return fonteDadosDAO.findAll();
    }
}
