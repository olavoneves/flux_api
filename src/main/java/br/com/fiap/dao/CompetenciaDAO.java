package br.com.fiap.dao;

import br.com.fiap.to.CompetenciaTO;
import br.com.fiap.to.UsuarioTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CompetenciaDAO {

    public CompetenciaTO save(CompetenciaTO competencia) {
        String sql = "INSERT INTO T_FLUX_COMPETENCIA(id_usuario, nm_competencia, vl_proficiencia, tp_verificacao, ds_prova_url, dt_verificacao, dt_ultima_uso, vl_decay_diario, st_ativo) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, competencia.getUsuario().getId());
            preparedStatement.setString(2, competencia.getNomeCompetencia());
            preparedStatement.setInt(3, competencia.getValorProficiencia());
            preparedStatement.setString(4, competencia.getTipoVerificacao());
            preparedStatement.setString(5, competencia.getProvaUrl());
            preparedStatement.setDate(6, Date.valueOf(competencia.getDataVerificacao()));
            preparedStatement.setDate(7, Date.valueOf(competencia.getDataUltimoUso()));
            preparedStatement.setInt(8, competencia.getValorDecayDiario());
            preparedStatement.setString(9, String.valueOf(competencia.getAtivo()));
            if (preparedStatement.executeUpdate() > 0) {
                return competencia;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar competencia: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public CompetenciaTO update(CompetenciaTO competencia) {
        String sql = "UPDATE T_FLUX_COMPETENCIA SET id_usuario = ?, nm_competencia = ?, vl_proficiencia = ?, tp_verificacao = ?, ds_prova_url = ?, dt_verificacao = ?, dt_ultima_uso = ?, vl_decay_diario = ?, st_ativo = ? WHERE id_competencia = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, competencia.getUsuario().getId());
            preparedStatement.setString(2, competencia.getNomeCompetencia());
            preparedStatement.setInt(3, competencia.getValorProficiencia());
            preparedStatement.setString(4, competencia.getTipoVerificacao());
            preparedStatement.setString(5, competencia.getProvaUrl());
            preparedStatement.setDate(6, Date.valueOf(competencia.getDataVerificacao()));
            preparedStatement.setDate(7, Date.valueOf(competencia.getDataUltimoUso()));
            preparedStatement.setInt(8, competencia.getValorDecayDiario());
            preparedStatement.setString(9, String.valueOf(competencia.getAtivo()));
            preparedStatement.setLong(10, competencia.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return competencia;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar competencia: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_FLUX_COMPETENCIA WHERE id_competencia = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir competencia: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public CompetenciaTO findById(Long id) {
        String sql = "SELECT * FROM T_FLUX_COMPETENCIA WHERE id_competencia = ?";
        CompetenciaTO competencia = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                competencia = new CompetenciaTO();
                competencia.setId(resultSet.getLong("id_competencia"));

                UsuarioDAO usuarioDAO =  new UsuarioDAO();
                UsuarioTO usuario = usuarioDAO.findById(resultSet.getLong("id_usuario"));
                competencia.setUsuario(usuario);

                competencia.setNomeCompetencia(resultSet.getString("nm_competencia"));
                competencia.setValorProficiencia(resultSet.getInt("vl_proficiencia"));
                competencia.setTipoVerificacao(resultSet.getString("tp_verificacao"));
                competencia.setProvaUrl(resultSet.getString("ds_prova_url"));
                competencia.setDataVerificacao(resultSet.getDate("dt_verificacao").toLocalDate());
                competencia.setDataUltimoUso(resultSet.getDate("dt_ultima_uso").toLocalDate());
                competencia.setValorDecayDiario(resultSet.getInt("vl_decay_diario"));
                competencia.setAtivo(resultSet.getString("st_ativo").charAt(0));
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar competencia: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return competencia;
    }

    public ArrayList<CompetenciaTO> findAll() {
        String sql = "SELECT * FROM T_FLUX_COMPETENCIA ORDER BY id_competencia";
        ArrayList<CompetenciaTO> competencias = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    CompetenciaTO competencia = new CompetenciaTO();
                    competencia.setId(resultSet.getLong("id_competencia"));

                    UsuarioDAO usuarioDAO =  new UsuarioDAO();
                    UsuarioTO usuario = usuarioDAO.findById(resultSet.getLong("id_usuario"));
                    competencia.setUsuario(usuario);

                    competencia.setNomeCompetencia(resultSet.getString("nm_competencia"));
                    competencia.setValorProficiencia(resultSet.getInt("vl_proficiencia"));
                    competencia.setTipoVerificacao(resultSet.getString("tp_verificacao"));
                    competencia.setProvaUrl(resultSet.getString("ds_prova_url"));
                    competencia.setDataVerificacao(resultSet.getDate("dt_verificacao").toLocalDate());
                    competencia.setDataUltimoUso(resultSet.getDate("dt_ultima_uso").toLocalDate());
                    competencia.setValorDecayDiario(resultSet.getInt("vl_decay_diario"));
                    competencia.setAtivo(resultSet.getString("st_ativo").charAt(0));
                    competencias.add(competencia);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar competencias: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return competencias;
    }
}
