package br.com.fiap.dao;

import br.com.fiap.to.AcaoAprendizTO;
import br.com.fiap.to.UsuarioTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AcaoAprendizDAO {

    public AcaoAprendizTO save(AcaoAprendizTO acaoAprendiz) {
        String sql = "INSERT INTO T_FLUX_ACAO_APRENDIZ(id_usuario, tp_acao, ds_acao, nm_competencia_alvo, vl_prioridade, st_status, dt_criacao, dt_conclusao) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, acaoAprendiz.getUsuario().getId());
            preparedStatement.setString(2, acaoAprendiz.getTipoAcao());
            preparedStatement.setString(3, acaoAprendiz.getAcao());
            preparedStatement.setString(4, acaoAprendiz.getNomeCompetenciaAlvo());
            preparedStatement.setInt(5, acaoAprendiz.getValorPrioridade());
            preparedStatement.setString(6, acaoAprendiz.getStatus());
            preparedStatement.setDate(7, Date.valueOf(acaoAprendiz.getDataCriacao()));
            preparedStatement.setDate(8, Date.valueOf(acaoAprendiz.getDataConclusao()));
            if (preparedStatement.executeUpdate() > 0) {
                return acaoAprendiz;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar ação aprendiz: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public AcaoAprendizTO update(AcaoAprendizTO acaoAprendiz) {
        String sql = "UPDATE T_FLUX_ACAO_APRENDIZ SET id_usuario = ?, tp_acao = ?, ds_acao = ?, nm_competencia_alvo = ?, vl_prioridade = ?, st_status = ?, dt_criacao = ?, dt_conclusao = ? WHERE id_acao = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, acaoAprendiz.getUsuario().getId());
            preparedStatement.setString(2, acaoAprendiz.getTipoAcao());
            preparedStatement.setString(3, acaoAprendiz.getAcao());
            preparedStatement.setString(4, acaoAprendiz.getNomeCompetenciaAlvo());
            preparedStatement.setInt(5, acaoAprendiz.getValorPrioridade());
            preparedStatement.setString(6, acaoAprendiz.getStatus());
            preparedStatement.setDate(7, Date.valueOf(acaoAprendiz.getDataCriacao()));
            preparedStatement.setDate(8, Date.valueOf(acaoAprendiz.getDataConclusao()));
            preparedStatement.setLong(9, acaoAprendiz.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return acaoAprendiz;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar ação aprendiz: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_FLUX_ACAO_APRENDIZ WHERE id_acao = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir ação aprendiz: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public AcaoAprendizTO findById(Long id) {
        String sql = """
                SELECT
                    a.id_acao,
                    a.tp_acao,
                    a.ds_acao,
                    a.nm_competencia_alvo,
                    a.vl_prioridade,
                    a.st_status,
                    a.dt_criacao,
                    a.dt_conclusao,
                    u.id_usuario,
                    u.nm_email,
                    u.nm_completo,
                    u.ds_cargo_atual
                FROM T_FLUX_ACAO_APRENDIZ a
                INNER JOIN T_FLUX_USUARIO u ON a.id_usuario = u.id_usuario
                WHERE a.id_acao = ?
                """;
        AcaoAprendizTO acaoAprendiz = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                acaoAprendiz = new AcaoAprendizTO();
                acaoAprendiz.setId(resultSet.getLong("id_acao"));

                UsuarioTO usuario = new UsuarioTO();
                usuario.setId(resultSet.getLong("id_usuario"));
                usuario.setEmail(resultSet.getString("nm_email"));
                usuario.setNomeCompleto(resultSet.getString("nm_completo"));
                usuario.setCargoAtual(resultSet.getString("ds_cargo_atual"));

                acaoAprendiz.setUsuario(usuario);
                acaoAprendiz.setTipoAcao(resultSet.getString("tp_acao"));
                acaoAprendiz.setAcao(resultSet.getString("ds_acao"));
                acaoAprendiz.setNomeCompetenciaAlvo(resultSet.getString("nm_competencia_alvo"));
                acaoAprendiz.setValorPrioridade(resultSet.getInt("vl_prioridade"));
                acaoAprendiz.setStatus(resultSet.getString("st_status"));
                acaoAprendiz.setDataCriacao(resultSet.getDate("dt_criacao").toLocalDate());
                acaoAprendiz.setDataConclusao(resultSet.getDate("dt_conclusao").toLocalDate());
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar ação aprendiz: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return acaoAprendiz;
    }

    public ArrayList<AcaoAprendizTO> findAll() {
        String sql = """
                SELECT
                    a.id_acao,
                    a.tp_acao,
                    a.ds_acao,
                    a.nm_competencia_alvo,
                    a.vl_prioridade,
                    a.st_status,
                    a.dt_criacao,
                    a.dt_conclusao,
                    u.id_usuario,
                    u.nm_email,
                    u.nm_completo,
                    u.ds_cargo_atual
                FROM T_FLUX_ACAO_APRENDIZ a
                INNER JOIN T_FLUX_USUARIO u ON a.id_usuario = u.id_usuario
                """;
        ArrayList<AcaoAprendizTO> acaoAprendizes = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    AcaoAprendizTO acaoAprendiz = new AcaoAprendizTO();
                    acaoAprendiz.setId(resultSet.getLong("id_acao"));

                    UsuarioTO usuario = new UsuarioTO();
                    usuario.setId(resultSet.getLong("id_usuario"));
                    usuario.setEmail(resultSet.getString("nm_email"));
                    usuario.setNomeCompleto(resultSet.getString("nm_completo"));
                    usuario.setCargoAtual(resultSet.getString("ds_cargo_atual"));

                    acaoAprendiz.setUsuario(usuario);
                    acaoAprendiz.setTipoAcao(resultSet.getString("tp_acao"));
                    acaoAprendiz.setAcao(resultSet.getString("ds_acao"));
                    acaoAprendiz.setNomeCompetenciaAlvo(resultSet.getString("nm_competencia_alvo"));
                    acaoAprendiz.setValorPrioridade(resultSet.getInt("vl_prioridade"));
                    acaoAprendiz.setStatus(resultSet.getString("st_status"));
                    acaoAprendiz.setDataCriacao(resultSet.getDate("dt_criacao").toLocalDate());
                    acaoAprendiz.setDataConclusao(resultSet.getDate("dt_conclusao").toLocalDate());
                    acaoAprendizes.add(acaoAprendiz);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar ações aprendizes: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return acaoAprendizes;
    }
}
