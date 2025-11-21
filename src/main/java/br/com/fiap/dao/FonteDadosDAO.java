package br.com.fiap.dao;

import br.com.fiap.to.FonteDadosTO;
import br.com.fiap.to.UsuarioTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FonteDadosDAO {

    public FonteDadosTO save(FonteDadosTO fonteDados) {
        String sql = "INSERT INTO T_FLUX_FONTE_DADOS(id_usuario, tp_fonte, ds_identificador, dt_conexao, dt_ultima_sync, st_ativo) VALUES(?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, fonteDados.getUsuario().getId());
            preparedStatement.setString(2, fonteDados.getFonte());
            preparedStatement.setString(3, fonteDados.getIdentificador());
            preparedStatement.setDate(4, Date.valueOf(fonteDados.getDataConexao()));
            preparedStatement.setDate(5, Date.valueOf(fonteDados.getDataUltimaSync()));
            preparedStatement.setString(6, String.valueOf(fonteDados.getAtivo()));
            if (preparedStatement.executeUpdate() > 0) {
                return fonteDados;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar fonte de dados: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public FonteDadosTO update(FonteDadosTO fonteDados) {
        String sql = "UPDATE T_FLUX_FONTE_DADOS SET id_usuario = ?, tp_fonte = ?, ds_identificador = ?, dt_conexao = ?, dt_ultima_sync = ?, st_ativo = ? WHERE id_fonte = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, fonteDados.getUsuario().getId());
            preparedStatement.setString(2, fonteDados.getFonte());
            preparedStatement.setString(3, fonteDados.getIdentificador());
            preparedStatement.setDate(4, Date.valueOf(fonteDados.getDataConexao()));
            preparedStatement.setDate(5, Date.valueOf(fonteDados.getDataUltimaSync()));
            preparedStatement.setString(6, String.valueOf(fonteDados.getAtivo()));
            preparedStatement.setLong(7, fonteDados.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return fonteDados;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar fonte de dados: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_FLUX_FONTE_DADOS WHERE id_fonte = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir fonte de dados: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public FonteDadosTO findById(Long id) {
        String sql = """
                SELECT
                    f.id_fonte,
                    f.tp_fonte,
                    f.ds_identificador,
                    f.dt_conexao,
                    f.dt_ultima_sync,
                    f.st_ativo,
                    u.id_usuario,
                    u.nm_email,
                    u.nm_completo
                FROM T_FLUX_FONTE_DADOS f
                INNER JOIN T_FLUX_USUARIO u ON f.id_usuario = u.id_usuario
                WHERE f.id_fonte = ?
                """;
        FonteDadosTO fonteDados = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                fonteDados = new FonteDadosTO();
                fonteDados.setId(resultSet.getLong("id_fonte"));

                UsuarioTO usuario = new UsuarioTO();
                usuario.setId(resultSet.getLong("id_usuario"));
                usuario.setEmail(resultSet.getString("nm_email"));
                usuario.setNomeCompleto(resultSet.getString("nm_completo"));

                fonteDados.setUsuario(usuario);
                fonteDados.setFonte(resultSet.getString("tp_fonte"));
                fonteDados.setIdentificador(resultSet.getString("ds_identificador"));
                fonteDados.setDataConexao(resultSet.getDate("dt_conexao").toLocalDate());
                fonteDados.setDataUltimaSync(resultSet.getDate("dt_ultima_sync").toLocalDate());
                fonteDados.setAtivo(resultSet.getString("st_ativo").charAt(0));
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar fonte de dados: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return fonteDados;
    }

    public ArrayList<FonteDadosTO> findAll() {
        String sql = """
                SELECT
                    f.id_fonte,
                    f.tp_fonte,
                    f.ds_identificador,
                    f.dt_conexao,
                    f.dt_ultima_sync,
                    f.st_ativo,
                    u.id_usuario,
                    u.nm_email,
                    u.nm_completo
                FROM T_FLUX_FONTE_DADOS f
                INNER JOIN T_FLUX_USUARIO u ON f.id_usuario = u.id_usuario
                ORDER BY f.id_fonte
                """;
        ArrayList<FonteDadosTO> fontesDeDados = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    FonteDadosTO fonteDados = new FonteDadosTO();
                    fonteDados.setId(resultSet.getLong("id_fonte"));

                    UsuarioTO usuario = new UsuarioTO();
                    usuario.setId(resultSet.getLong("id_usuario"));
                    usuario.setEmail(resultSet.getString("nm_email"));
                    usuario.setNomeCompleto(resultSet.getString("nm_completo"));

                    fonteDados.setUsuario(usuario);
                    fonteDados.setFonte(resultSet.getString("tp_fonte"));
                    fonteDados.setIdentificador(resultSet.getString("ds_identificador"));
                    fonteDados.setDataConexao(resultSet.getDate("dt_conexao").toLocalDate());
                    fonteDados.setDataUltimaSync(resultSet.getDate("dt_ultima_sync").toLocalDate());
                    fonteDados.setAtivo(resultSet.getString("st_ativo").charAt(0));
                    fontesDeDados.add(fonteDados);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar fontes de dados: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return fontesDeDados;
    }
}
