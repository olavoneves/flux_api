package br.com.fiap.dao;

import br.com.fiap.to.MatchCarreiraTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MatchCarreiraDAO {

    public MatchCarreiraTO save(MatchCarreiraTO matchCarreira) {
        String sql = "INSERT INTO T_FLUX_USUARIO(nm_email, ds_senha_hash, nm_completo) VALUES(?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {

            if (preparedStatement.executeUpdate() > 0) {
                return matchCarreira;
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

    public MatchCarreiraTO update(MatchCarreiraTO matchCarreira) {
        String sql = "UPDATE T_FLUX_USUARIO SET nm_email = ?, ds_senha_hash = ?, nm_completo = ?, ds_cargo_atual = ?, ds_carreira_alvo = ? WHERE id_usuario = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {

            preparedStatement.setLong(6, matchCarreira.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return matchCarreira;
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

    public MatchCarreiraTO findById(Long id) {
        String sql = "SELECT * FROM T_FLUX_USUARIO WHERE id_usuario = ?";
        MatchCarreiraTO matchCarreira = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                matchCarreira = new MatchCarreiraTO();

            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar usuario: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return matchCarreira;
    }

    public ArrayList<MatchCarreiraTO> findAll() {
        String sql = "SELECT * FROM T_FLUX_USUARIO ORDER BY id_usuario";
        ArrayList<MatchCarreiraTO> matchCarreiras = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    MatchCarreiraTO matchCarreira = new MatchCarreiraTO();

                    matchCarreiras.add(matchCarreira);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar usuarios: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return matchCarreiras;
    }
}
