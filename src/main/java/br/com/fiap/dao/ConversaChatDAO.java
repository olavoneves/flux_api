package br.com.fiap.dao;

import br.com.fiap.to.ConversaChatTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ConversaChatDAO {

    public ConversaChatTO save(ConversaChatTO conversaChat) {
        String sql = "INSERT INTO T_FLUX_USUARIO(nm_email, ds_senha_hash, nm_completo) VALUES(?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {

            if (preparedStatement.executeUpdate() > 0) {
                return conversaChat;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar usuario: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public ConversaChatTO update(ConversaChatTO conversaChat) {
        String sql = "UPDATE T_FLUX_USUARIO SET nm_email = ?, ds_senha_hash = ?, nm_completo = ?, ds_cargo_atual = ?, ds_carreira_alvo = ? WHERE id_usuario = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {

            preparedStatement.setLong(6, conversaChat.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return conversaChat;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar usuario: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_FLUX_USUARIO WHERE id_usuario = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir usuario: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public ConversaChatTO findById(Long id) {
        String sql = "SELECT * FROM T_FLUX_USUARIO WHERE id_usuario = ?";
        ConversaChatTO conversaChat = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                conversaChat = new ConversaChatTO();

            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar usuario: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return conversaChat;
    }

    public ArrayList<ConversaChatTO> findAll() {
        String sql = "SELECT * FROM T_FLUX_USUARIO ORDER BY id_usuario";
        ArrayList<ConversaChatTO> conversaChats = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    ConversaChatTO conversaChat = new ConversaChatTO();

                    conversaChats.add(conversaChat);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar usuarios: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return conversaChats;
    }
}
