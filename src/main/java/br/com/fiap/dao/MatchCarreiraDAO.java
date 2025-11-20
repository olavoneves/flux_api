package br.com.fiap.dao;

import br.com.fiap.to.CarreiraTO;
import br.com.fiap.to.MatchCarreiraTO;
import br.com.fiap.to.UsuarioTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MatchCarreiraDAO {

    public MatchCarreiraTO save(MatchCarreiraTO matchCarreira) {
        String sql = "INSERT INTO T_FLUX_MATCH_CARREIRA(id_usuario, id_carreira, vl_compatibilidade, ds_gaps, dt_calculo) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, matchCarreira.getUsuario().getId());
            preparedStatement.setLong(2, matchCarreira.getCarreira().getId());
            preparedStatement.setInt(3, matchCarreira.getValorCompatibilidade());
            preparedStatement.setString(4, matchCarreira.getGaps());
            preparedStatement.setDate(5, Date.valueOf(matchCarreira.getDataCalculo()));
            if (preparedStatement.executeUpdate() > 0) {
                return matchCarreira;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar match carreira: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public MatchCarreiraTO update(MatchCarreiraTO matchCarreira) {
        String sql = "UPDATE T_FLUX_MATCH_CARREIRA SET id_usuario = ?, id_carreira = ?, vl_compatibilidade = ?, ds_gaps = ?, dt_calculo = ? WHERE id_match = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, matchCarreira.getUsuario().getId());
            preparedStatement.setLong(2, matchCarreira.getCarreira().getId());
            preparedStatement.setInt(3, matchCarreira.getValorCompatibilidade());
            preparedStatement.setString(4, matchCarreira.getGaps());
            preparedStatement.setDate(5, Date.valueOf(matchCarreira.getDataCalculo()));
            preparedStatement.setLong(6, matchCarreira.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return matchCarreira;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar match carreira: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_FLUX_MATCH_CARREIRA WHERE id_match = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir match carreira: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public MatchCarreiraTO findById(Long id) {
        String sql = "SELECT * FROM T_FLUX_MATCH_CARREIRA WHERE id_match = ?";
        MatchCarreiraTO matchCarreira = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                matchCarreira = new MatchCarreiraTO();
                matchCarreira.setId(resultSet.getLong("id_match"));

                UsuarioDAO usuarioDAO =  new UsuarioDAO();
                UsuarioTO usuario = usuarioDAO.findById(resultSet.getLong("id_usuario"));
                matchCarreira.setUsuario(usuario);

                CarreiraDAO carreiraDAO = new CarreiraDAO();
                CarreiraTO carreira = carreiraDAO.findById(resultSet.getLong("id_carreira"));
                matchCarreira.setCarreira(carreira);

                matchCarreira.setValorCompatibilidade(resultSet.getInt("vl_compatibilidade"));
                matchCarreira.setGaps(resultSet.getString("ds_gaps"));
                matchCarreira.setDataCalculo(resultSet.getDate("dt_calculo").toLocalDate());
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar match carreira: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return matchCarreira;
    }

    public ArrayList<MatchCarreiraTO> findAll() {
        String sql = "SELECT * FROM T_FLUX_MATCH_CARREIRA ORDER BY id_match";
        ArrayList<MatchCarreiraTO> matchCarreiras = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    MatchCarreiraTO matchCarreira = new MatchCarreiraTO();
                    matchCarreira.setId(resultSet.getLong("id_match"));

                    UsuarioDAO usuarioDAO =  new UsuarioDAO();
                    UsuarioTO usuario = usuarioDAO.findById(resultSet.getLong("id_usuario"));
                    matchCarreira.setUsuario(usuario);

                    CarreiraDAO carreiraDAO = new CarreiraDAO();
                    CarreiraTO carreira = carreiraDAO.findById(resultSet.getLong("id_carreira"));
                    matchCarreira.setCarreira(carreira);

                    matchCarreira.setValorCompatibilidade(resultSet.getInt("vl_compatibilidade"));
                    matchCarreira.setGaps(resultSet.getString("ds_gaps"));
                    matchCarreira.setDataCalculo(resultSet.getDate("dt_calculo").toLocalDate());
                    matchCarreiras.add(matchCarreira);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar matchs carreiras: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return matchCarreiras;
    }
}
