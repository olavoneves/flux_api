package br.com.fiap.dao;

import br.com.fiap.to.ConversaChatTO;
import br.com.fiap.to.UsuarioTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ConversaChatDAO {

    public ConversaChatTO save(ConversaChatTO conversaChat) {
        String sql = "INSERT INTO T_FLUX_CONVERSA_CHAT(id_usuario, ds_mensagem_user, ds_resposta_bot, ds_contexto, dt_envio) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, conversaChat.getUsuario().getId());
            preparedStatement.setString(2, conversaChat.getMensagemUser());
            preparedStatement.setString(3, conversaChat.getRespostaBot());
            preparedStatement.setString(4, conversaChat.getContexto());
            preparedStatement.setDate(5, Date.valueOf(conversaChat.getDataEnvio()));
            if (preparedStatement.executeUpdate() > 0) {
                return conversaChat;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar conversa chat: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public ConversaChatTO update(ConversaChatTO conversaChat) {
        String sql = "UPDATE T_FLUX_CONVERSA_CHAT SET id_usuario = ?, ds_mensagem_user = ?, ds_resposta_bot = ?, ds_contexto = ?, dt_envio = ? WHERE id_mensagem = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, conversaChat.getUsuario().getId());
            preparedStatement.setString(2, conversaChat.getMensagemUser());
            preparedStatement.setString(3, conversaChat.getRespostaBot());
            preparedStatement.setString(4, conversaChat.getContexto());
            preparedStatement.setDate(5, Date.valueOf(conversaChat.getDataEnvio()));
            preparedStatement.setLong(6, conversaChat.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return conversaChat;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar conversa chat: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_FLUX_CONVERSA_CHAT WHERE id_mensagem = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir conversa chat: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public ConversaChatTO findById(Long id) {
        String sql = "SELECT * FROM T_FLUX_CONVERSA_CHAT WHERE id_mensagem = ?";
        ConversaChatTO conversaChat = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                conversaChat = new ConversaChatTO();
                conversaChat.setId(resultSet.getLong("id_mensagem"));

                UsuarioDAO usuarioDAO =  new UsuarioDAO();
                UsuarioTO usuario = usuarioDAO.findById(resultSet.getLong("id_usuario"));
                conversaChat.setUsuario(usuario);

                conversaChat.setMensagemUser(resultSet.getString("ds_mensagem_user"));
                conversaChat.setRespostaBot(resultSet.getString("ds_resposta_bot"));
                conversaChat.setContexto(resultSet.getString("ds_contexto"));
                conversaChat.setDataEnvio(resultSet.getDate("dt_envio").toLocalDate());
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar conversa chat: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return conversaChat;
    }

    public ArrayList<ConversaChatTO> findAll() {
        String sql = "SELECT * FROM T_FLUX_CONVERSA_CHAT ORDER BY id_mensagem";
        ArrayList<ConversaChatTO> conversaChats = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    ConversaChatTO conversaChat = new ConversaChatTO();
                    conversaChat.setId(resultSet.getLong("id_mensagem"));

                    UsuarioDAO usuarioDAO =  new UsuarioDAO();
                    UsuarioTO usuario = usuarioDAO.findById(resultSet.getLong("id_usuario"));
                    conversaChat.setUsuario(usuario);

                    conversaChat.setMensagemUser(resultSet.getString("ds_mensagem_user"));
                    conversaChat.setRespostaBot(resultSet.getString("ds_resposta_bot"));
                    conversaChat.setContexto(resultSet.getString("ds_contexto"));
                    conversaChat.setDataEnvio(resultSet.getDate("dt_envio").toLocalDate());
                    conversaChats.add(conversaChat);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar conversas chat: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return conversaChats;
    }
}
