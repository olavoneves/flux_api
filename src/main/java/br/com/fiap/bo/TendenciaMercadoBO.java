package br.com.fiap.bo;

import br.com.fiap.dao.TendenciaMercadoDAO;
import br.com.fiap.to.TendenciaMercadoTO;

import java.util.ArrayList;

public class TendenciaMercadoBO {
    private TendenciaMercadoDAO tendenciaMercadoDAO;

    public TendenciaMercadoTO save(TendenciaMercadoTO tendenciaMercado) {
        tendenciaMercadoDAO = new TendenciaMercadoDAO();

        return tendenciaMercadoDAO.save(tendenciaMercado);
    }

    public TendenciaMercadoTO update(TendenciaMercadoTO tendenciaMercado) {
        tendenciaMercadoDAO = new TendenciaMercadoDAO();

        // Se id for vazio
        if (tendenciaMercado.getId() == null) {
            return null;
        }

        return tendenciaMercadoDAO.update(tendenciaMercado);
    }

    public boolean delete(Long id) {
        tendenciaMercadoDAO = new TendenciaMercadoDAO();

        // Se id for vazio
        if (id == null) {
            return false;
        }

        return tendenciaMercadoDAO.delete(id);
    }

    public TendenciaMercadoTO findById(Long id) {
        tendenciaMercadoDAO = new TendenciaMercadoDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return tendenciaMercadoDAO.findById(id);
    }

    public ArrayList<TendenciaMercadoTO> findAll() {
        tendenciaMercadoDAO = new TendenciaMercadoDAO();

        return tendenciaMercadoDAO.findAll();
    }
}
