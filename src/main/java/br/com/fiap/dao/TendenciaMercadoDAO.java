package br.com.fiap.dao;

import br.com.fiap.to.CarreiraTO;
import br.com.fiap.to.TendenciaMercadoTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TendenciaMercadoDAO {

    public TendenciaMercadoTO save(TendenciaMercadoTO tendenciaMercado) {
        String sql = "INSERT INTO T_FLUX_TENDENCIA_MERCADO(id_carreira, nm_competencia, vl_demanda, vl_crescimento_30d, dt_referencia, dt_calculo) VALUES(?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, tendenciaMercado.getCarreira().getId());
            preparedStatement.setString(2, tendenciaMercado.getNomeCompetencia());
            preparedStatement.setInt(3, tendenciaMercado.getValorDemanda());
            preparedStatement.setInt(4, tendenciaMercado.getValorCrescimentoTrintaDias());
            preparedStatement.setDate(5, Date.valueOf(tendenciaMercado.getDataReferencia()));
            preparedStatement.setDate(6, Date.valueOf(tendenciaMercado.getDataCalculo()));
            if (preparedStatement.executeUpdate() > 0) {
                return tendenciaMercado;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar tendencia de mercado: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public TendenciaMercadoTO update(TendenciaMercadoTO tendenciaMercado) {
        String sql = "UPDATE T_FLUX_TENDENCIA_MERCADO SET id_carreira = ?, nm_competencia = ?, vl_demanda = ?, vl_crescimento_30d = ?, dt_referencia = ?, dt_calculo = ? WHERE id_tendencia = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, tendenciaMercado.getCarreira().getId());
            preparedStatement.setString(2, tendenciaMercado.getNomeCompetencia());
            preparedStatement.setInt(3, tendenciaMercado.getValorDemanda());
            preparedStatement.setInt(4, tendenciaMercado.getValorCrescimentoTrintaDias());
            preparedStatement.setDate(5, Date.valueOf(tendenciaMercado.getDataReferencia()));
            preparedStatement.setDate(6, Date.valueOf(tendenciaMercado.getDataCalculo()));
            preparedStatement.setLong(7, tendenciaMercado.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return tendenciaMercado;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar tendencia de mercado: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_FLUX_TENDENCIA_MERCADO WHERE id_tendencia = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir tendencia de mercado: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public TendenciaMercadoTO findById(Long id) {
        String sql = """
                SELECT
                    t.id_tendencia,
                    t.nm_competencia,
                    t.vl_demanda,
                    t.vl_crescimento_30d,
                    t.dt_referencia,
                    t.dt_calculo,
                    c.id_carreira,
                    c.nm_carreira,
                    c.vl_crescimento
                FROM T_FLUX_TENDENCIA_MERCADO t
                INNER JOIN T_FLUX_CARREIRA c ON t.id_carreira = c.id_carreira
                WHERE t.id_tendencia = ?
                """;
        TendenciaMercadoTO tendenciaMercado = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tendenciaMercado = new TendenciaMercadoTO();
                tendenciaMercado.setId(resultSet.getLong("id_tendencia"));

                CarreiraTO carreira = new CarreiraTO();
                carreira.setId(resultSet.getLong("id_carreira"));
                carreira.setNomeCarreira(resultSet.getString("nm_carreira"));
                carreira.setValorCrescimento(resultSet.getInt("vl_crescimento"));

                tendenciaMercado.setCarreira(carreira);
                tendenciaMercado.setNomeCompetencia(resultSet.getString("nm_competencia"));
                tendenciaMercado.setValorDemanda(resultSet.getInt("vl_demanda"));
                tendenciaMercado.setValorCrescimentoTrintaDias(resultSet.getInt("vl_crescimento_30d"));
                tendenciaMercado.setDataReferencia(resultSet.getDate("dt_referencia").toLocalDate());
                tendenciaMercado.setDataCalculo(resultSet.getDate("dt_calculo").toLocalDate());
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar tendencia de mercado: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return tendenciaMercado;
    }

    public ArrayList<TendenciaMercadoTO> findAll() {
        String sql = """
                SELECT
                    t.id_tendencia,
                    t.nm_competencia,
                    t.vl_demanda,
                    t.vl_crescimento_30d,
                    t.dt_referencia,
                    t.dt_calculo,
                    c.id_carreira,
                    c.nm_carreira,
                    c.vl_crescimento
                FROM T_FLUX_TENDENCIA_MERCADO t
                INNER JOIN T_FLUX_CARREIRA c ON t.id_carreira = c.id_carreira
                ORDER BY t.id_tendencia
                """;
        ArrayList<TendenciaMercadoTO> tendenciasMercado = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    TendenciaMercadoTO tendenciaMercado = new TendenciaMercadoTO();
                    tendenciaMercado.setId(resultSet.getLong("id_tendencia"));

                    CarreiraTO carreira = new CarreiraTO();
                    carreira.setId(resultSet.getLong("id_carreira"));
                    carreira.setNomeCarreira(resultSet.getString("nm_carreira"));
                    carreira.setValorCrescimento(resultSet.getInt("vl_crescimento"));

                    tendenciaMercado.setCarreira(carreira);
                    tendenciaMercado.setNomeCompetencia(resultSet.getString("nm_competencia"));
                    tendenciaMercado.setValorDemanda(resultSet.getInt("vl_demanda"));
                    tendenciaMercado.setValorCrescimentoTrintaDias(resultSet.getInt("vl_crescimento_30d"));
                    tendenciaMercado.setDataReferencia(resultSet.getDate("dt_referencia").toLocalDate());
                    tendenciaMercado.setDataCalculo(resultSet.getDate("dt_calculo").toLocalDate());
                    tendenciasMercado.add(tendenciaMercado);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar tendencias de mercado: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return tendenciasMercado;
    }
}
