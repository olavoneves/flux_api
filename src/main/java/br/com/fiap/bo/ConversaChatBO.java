package br.com.fiap.bo;

import br.com.fiap.dao.ConversaChatDAO;
import br.com.fiap.to.ConversaChatTO;

import java.util.ArrayList;

public class ConversaChatBO {
    private ConversaChatDAO conversaChatDAO;

    public ConversaChatTO save(ConversaChatTO conversaChat) {
        conversaChatDAO = new ConversaChatDAO();

        return conversaChatDAO.save(conversaChat);
    }

    public ConversaChatTO update(ConversaChatTO conversaChat) {
        conversaChatDAO = new ConversaChatDAO();

        // Se id for vazio
        if (conversaChat.getId() == null) {
            return null;
        }

        return conversaChatDAO.update(conversaChat);
    }

    public boolean delete(Long id) {
        conversaChatDAO = new ConversaChatDAO();

        // Se id for vazio
        if (id == null) {
            return false;
        }

        return conversaChatDAO.delete(id);
    }

    public ConversaChatTO findById(Long id) {
        conversaChatDAO = new ConversaChatDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return conversaChatDAO.findById(id);
    }

    public ArrayList<ConversaChatTO> findAll() {
        conversaChatDAO = new ConversaChatDAO();

        return conversaChatDAO.findAll();
    }
}
