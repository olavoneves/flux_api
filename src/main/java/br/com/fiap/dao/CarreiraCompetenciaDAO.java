package br.com.fiap.dao;

import br.com.fiap.to.CarreiraCompetenciaTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CarreiraCompetenciaDAO {

    public CarreiraCompetenciaTO save(CarreiraCompetenciaTO carreiraCompetencia) {
        String sql = "INSERT INTO T_FLUX_USUARIO(nm_email, ds_senha_hash, nm_completo) VALUES(?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {

            if (preparedStatement.executeUpdate() > 0) {
                return carreiraCompetencia;
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

    public CarreiraCompetenciaTO update(CarreiraCompetenciaTO carreiraCompetencia) {
        String sql = "UPDATE T_FLUX_USUARIO SET nm_email = ?, ds_senha_hash = ?, nm_completo = ?, ds_cargo_atual = ?, ds_carreira_alvo = ? WHERE id_usuario = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {

            preparedStatement.setLong(6, carreiraCompetencia.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return carreiraCompetencia;
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

    public CarreiraCompetenciaTO findById(Long id) {
        String sql = "SELECT * FROM T_FLUX_USUARIO WHERE id_usuario = ?";
        CarreiraCompetenciaTO carreiraCompetencia = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                carreiraCompetencia = new CarreiraCompetenciaTO();

            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar usuario: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return carreiraCompetencia;
    }

    public ArrayList<CarreiraCompetenciaTO> findAll() {
        String sql = "SELECT * FROM T_FLUX_USUARIO ORDER BY id_usuario";
        ArrayList<CarreiraCompetenciaTO> carreiraCompetencias = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    CarreiraCompetenciaTO carreiraCompetencia = new CarreiraCompetenciaTO();

                    carreiraCompetencias.add(carreiraCompetencia);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar usuarios: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return carreiraCompetencias;
    }
}
