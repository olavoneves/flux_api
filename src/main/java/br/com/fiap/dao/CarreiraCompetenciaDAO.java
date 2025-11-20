package br.com.fiap.dao;

import br.com.fiap.to.CarreiraCompetenciaTO;
import br.com.fiap.to.CarreiraTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CarreiraCompetenciaDAO {

    public CarreiraCompetenciaTO save(CarreiraCompetenciaTO carreiraCompetencia) {
        String sql = "INSERT INTO T_FLUX_CARREIRA_COMPETENCIA(id_carreira, nm_competencia, vl_importancia, st_essencial) VALUES(?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, carreiraCompetencia.getCarreira().getId());
            preparedStatement.setString(2, carreiraCompetencia.getNomeCompetencia());
            preparedStatement.setInt(3, carreiraCompetencia.getImportancia());
            preparedStatement.setString(4, String.valueOf(carreiraCompetencia.getEssencial()));
            if (preparedStatement.executeUpdate() > 0) {
                return carreiraCompetencia;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar carreira competencia: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public CarreiraCompetenciaTO update(CarreiraCompetenciaTO carreiraCompetencia) {
        String sql = "UPDATE T_FLUX_CARREIRA_COMPETENCIA SET id_carreira = ?, nm_competencia = ?, vl_importancia = ?, st_essencial = ? WHERE id_carreira_comp = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, carreiraCompetencia.getCarreira().getId());
            preparedStatement.setString(2, carreiraCompetencia.getNomeCompetencia());
            preparedStatement.setInt(3, carreiraCompetencia.getImportancia());
            preparedStatement.setString(4, String.valueOf(carreiraCompetencia.getEssencial()));
            preparedStatement.setLong(5, carreiraCompetencia.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return carreiraCompetencia;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar carreira competencia: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_FLUX_CARREIRA_COMPETENCIA WHERE id_carreira_comp = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir carreira competencia: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public CarreiraCompetenciaTO findById(Long id) {
        String sql = "SELECT * FROM T_FLUX_CARREIRA_COMPETENCIA WHERE id_carreira_comp = ?";
        CarreiraCompetenciaTO carreiraCompetencia = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                carreiraCompetencia = new CarreiraCompetenciaTO();
                carreiraCompetencia.setId(resultSet.getLong("id_carreira_comp"));

                CarreiraDAO carreiraDAO = new CarreiraDAO();
                CarreiraTO carreira = carreiraDAO.findById(resultSet.getLong("id_carreira"));
                carreiraCompetencia.setCarreira(carreira);

                carreiraCompetencia.setNomeCompetencia(resultSet.getString("nm_competencia"));
                carreiraCompetencia.setImportancia(resultSet.getInt("vl_importancia"));
                carreiraCompetencia.setEssencial(resultSet.getString("st_essencial").charAt(0));
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar carreira competencia: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return carreiraCompetencia;
    }

    public ArrayList<CarreiraCompetenciaTO> findAll() {
        String sql = "SELECT * FROM T_FLUX_CARREIRA_COMPETENCIA ORDER BY id_carreira_comp";
        ArrayList<CarreiraCompetenciaTO> carreiraCompetencias = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    CarreiraCompetenciaTO carreiraCompetencia = new CarreiraCompetenciaTO();
                    carreiraCompetencia.setId(resultSet.getLong("id_carreira_comp"));

                    CarreiraDAO carreiraDAO = new CarreiraDAO();
                    CarreiraTO carreira = carreiraDAO.findById(resultSet.getLong("id_carreira"));
                    carreiraCompetencia.setCarreira(carreira);

                    carreiraCompetencia.setNomeCompetencia(resultSet.getString("nm_competencia"));
                    carreiraCompetencia.setImportancia(resultSet.getInt("vl_importancia"));
                    carreiraCompetencia.setEssencial(resultSet.getString("st_essencial").charAt(0));
                    carreiraCompetencias.add(carreiraCompetencia);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar carreira competencia: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return carreiraCompetencias;
    }
}
