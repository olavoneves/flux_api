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
        String sql = """
                SELECT
                    m.id_match,
                    m.vl_compatibilidade,
                    m.ds_gaps,
                    m.dt_calculo,
                    u.id_usuario,
                    u.nm_email,
                    u.nm_completo,
                    u.ds_cargo_atual,
                    u.ds_carreira_alvo,
                    c.id_carreira,
                    c.nm_carreira,
                    c.ds_descricao,
                    c.vl_crescimento,
                    c.vl_salario_medio,
                    c.dt_emergente_desde
                FROM T_FLUX_MATCH_CARREIRA m
                INNER JOIN T_FLUX_USUARIO u ON m.id_usuario = u.id_usuario
                INNER JOIN T_FLUX_CARREIRA c ON m.id_carreira = c.id_carreira
                WHERE m.id_match = ?
                """;
        MatchCarreiraTO matchCarreira = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                matchCarreira = new MatchCarreiraTO();
                matchCarreira.setId(resultSet.getLong("id_match"));

                UsuarioTO usuario = new UsuarioTO();
                usuario.setId(resultSet.getLong("id_usuario"));
                usuario.setEmail(resultSet.getString("nm_email"));
                usuario.setNomeCompleto(resultSet.getString("nm_completo"));
                usuario.setCargoAtual(resultSet.getString("ds_cargo_atual"));
                usuario.setCarreiraAlvo(resultSet.getString("ds_carreira_alvo"));

                matchCarreira.setUsuario(usuario);

                CarreiraTO carreira = new CarreiraTO();
                carreira.setId(resultSet.getLong("id_carreira"));
                carreira.setNomeCarreira(resultSet.getString("nm_carreira"));
                carreira.setDescricao(resultSet.getString("ds_descricao"));
                carreira.setValorCrescimento(resultSet.getInt("vl_crescimento"));
                carreira.setValorSalarioMedio(resultSet.getInt("vl_salario_medio"));
                carreira.setDataEmergenteDesde(resultSet.getDate("dt_emergente_desde").toLocalDate());

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
        String sql = """
                SELECT
                    m.id_match,
                    m.vl_compatibilidade,
                    m.ds_gaps,
                    m.dt_calculo,
                    u.id_usuario,
                    u.nm_email,
                    u.nm_completo,
                    u.ds_cargo_atual,
                    u.ds_carreira_alvo,
                    c.id_carreira,
                    c.nm_carreira,
                    c.ds_descricao,
                    c.vl_crescimento,
                    c.vl_salario_medio,
                    c.dt_emergente_desde
                FROM T_FLUX_MATCH_CARREIRA m
                INNER JOIN T_FLUX_USUARIO u ON m.id_usuario = u.id_usuario
                INNER JOIN T_FLUX_CARREIRA c ON m.id_carreira = c.id_carreira
                ORDER BY m.id_match
                """;
        ArrayList<MatchCarreiraTO> matchCarreiras = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    MatchCarreiraTO matchCarreira = new MatchCarreiraTO();
                    matchCarreira.setId(resultSet.getLong("id_match"));

                    UsuarioTO usuario = new UsuarioTO();
                    usuario.setId(resultSet.getLong("id_usuario"));
                    usuario.setEmail(resultSet.getString("nm_email"));
                    usuario.setNomeCompleto(resultSet.getString("nm_completo"));
                    usuario.setCargoAtual(resultSet.getString("ds_cargo_atual"));
                    usuario.setCarreiraAlvo(resultSet.getString("ds_carreira_alvo"));

                    matchCarreira.setUsuario(usuario);

                    CarreiraTO carreira = new CarreiraTO();
                    carreira.setId(resultSet.getLong("id_carreira"));
                    carreira.setNomeCarreira(resultSet.getString("nm_carreira"));
                    carreira.setDescricao(resultSet.getString("ds_descricao"));
                    carreira.setValorCrescimento(resultSet.getInt("vl_crescimento"));
                    carreira.setValorSalarioMedio(resultSet.getInt("vl_salario_medio"));
                    carreira.setDataEmergenteDesde(resultSet.getDate("dt_emergente_desde").toLocalDate());

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
