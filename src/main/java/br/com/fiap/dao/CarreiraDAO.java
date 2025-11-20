package br.com.fiap.dao;

import br.com.fiap.to.CarreiraTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CarreiraDAO {

    public CarreiraTO save(CarreiraTO carreira) {
        String sql = "INSERT INTO T_FLUX_CARREIRA(nm_carreira, ds_descricao, vl_crescimento, vl_salario_medio, dt_emergente_desde, dt_atualizacao, st_ativo) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, carreira.getNomeCarreira());
            preparedStatement.setString(2, carreira.getDescricao());
            preparedStatement.setInt(3, carreira.getValorCrescimento());
            preparedStatement.setInt(4, carreira.getValorSalarioMedio());
            preparedStatement.setDate(5, Date.valueOf(carreira.getDataEmergenteDesde()));
            preparedStatement.setDate(6, Date.valueOf(carreira.getDataAtualizacao()));
            preparedStatement.setString(7, String.valueOf(carreira.getAtivo()));
            if (preparedStatement.executeUpdate() > 0) {
                return carreira;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar carreira: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public CarreiraTO update(CarreiraTO carreira) {
        String sql = "UPDATE T_FLUX_CARREIRA SET nm_carreira = ?, ds_descricao = ?, vl_crescimento = ?, vl_salario_medio = ?, dt_emergente_desde = ?, dt_atualizacao = ?, st_ativo = ? WHERE id_carreira = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, carreira.getNomeCarreira());
            preparedStatement.setString(2, carreira.getDescricao());
            preparedStatement.setInt(3, carreira.getValorCrescimento());
            preparedStatement.setInt(4, carreira.getValorSalarioMedio());
            preparedStatement.setDate(5, Date.valueOf(carreira.getDataEmergenteDesde()));
            preparedStatement.setDate(6, Date.valueOf(carreira.getDataAtualizacao()));
            preparedStatement.setString(7, String.valueOf(carreira.getAtivo()));
            preparedStatement.setLong(8, carreira.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return carreira;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar carreira: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_FLUX_CARREIRA WHERE id_carreira = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir carreira: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public CarreiraTO findById(Long id) {
        String sql = "SELECT * FROM T_FLUX_CARREIRA WHERE id_carreira = ?";
        CarreiraTO carreira = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                carreira = new CarreiraTO();
                carreira.setId(resultSet.getLong("id_carreira"));
                carreira.setNomeCarreira(resultSet.getString("nm_carreira"));
                carreira.setDescricao(resultSet.getString("ds_descricao"));
                carreira.setValorCrescimento(resultSet.getInt("vl_crescimento"));
                carreira.setValorSalarioMedio(resultSet.getInt("vl_salario_medio"));
                carreira.setDataEmergenteDesde(resultSet.getDate("dt_emergente_desde").toLocalDate());
                carreira.setDataAtualizacao(resultSet.getDate("dt_atualizacao").toLocalDate());
                carreira.setAtivo(resultSet.getString("st_ativo").charAt(0));
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar carreira: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return carreira;
    }

    public ArrayList<CarreiraTO> findAll() {
        String sql = "SELECT * FROM T_FLUX_CARREIRA ORDER BY id_carreira";
        ArrayList<CarreiraTO> carreiras = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    CarreiraTO carreira = new CarreiraTO();
                    carreira.setId(resultSet.getLong("id_carreira"));
                    carreira.setNomeCarreira(resultSet.getString("nm_carreira"));
                    carreira.setDescricao(resultSet.getString("ds_descricao"));
                    carreira.setValorCrescimento(resultSet.getInt("vl_crescimento"));
                    carreira.setValorSalarioMedio(resultSet.getInt("vl_salario_medio"));
                    carreira.setDataEmergenteDesde(resultSet.getDate("dt_emergente_desde").toLocalDate());
                    carreira.setDataAtualizacao(resultSet.getDate("dt_atualizacao").toLocalDate());
                    carreira.setAtivo(resultSet.getString("st_ativo").charAt(0));
                    carreiras.add(carreira);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar carreiras: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return carreiras;
    }
}
