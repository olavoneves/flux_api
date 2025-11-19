package br.com.fiap.dao;

import br.com.fiap.to.LoginTO;
import br.com.fiap.to.UsuarioTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAO {

    public UsuarioTO save(UsuarioTO usuario) {
        String sql = "INSERT INTO T_FLUX_USUARIO(nm_email, ds_senha_hash, nm_completo) VALUES(?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getEmail());
            preparedStatement.setString(2, usuario.getSenha());
            preparedStatement.setString(3, usuario.getNomeCompleto());
            if (preparedStatement.executeUpdate() > 0) {
                return usuario;
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

    public UsuarioTO update(UsuarioTO usuario) {
        String sql = "UPDATE T_FLUX_USUARIO SET nm_email = ?, ds_senha_hash = ?, nm_completo = ?, ds_cargo_atual = ?, ds_carreira_alvo = ?, dt_cadastro = ?, dt_ultima_atualizacao = ?, st_ativo = ? WHERE id_usuario = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getEmail());
            preparedStatement.setString(2, usuario.getSenha());
            preparedStatement.setString(3, usuario.getNomeCompleto());
            preparedStatement.setString(4, usuario.getCargoAtual());
            preparedStatement.setString(5, usuario.getCarreiraAlvo());
            preparedStatement.setDate(6, Date.valueOf(usuario.getDataCadastro()));
            preparedStatement.setDate(7, Date.valueOf(usuario.getDataUltimaAtualizacao()));
            preparedStatement.setString(8, String.valueOf(usuario.getAtivo()));
            preparedStatement.setLong(9, usuario.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return usuario;
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

    public UsuarioTO findById(Long id) {
        String sql = "SELECT * FROM T_FLUX_USUARIO WHERE id_usuario = ?";
        UsuarioTO usuario = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuario = new UsuarioTO();
                usuario.setId(resultSet.getLong("id_usuario"));
                usuario.setEmail(resultSet.getString("nm_email"));
                usuario.setSenha(resultSet.getString("ds_senha_hash"));
                usuario.setNomeCompleto(resultSet.getString("nm_completo"));
                usuario.setCargoAtual(resultSet.getString("ds_cargo_atual"));
                usuario.setCarreiraAlvo(resultSet.getString("ds_carreira_alvo"));
                usuario.setDataCadastro(resultSet.getDate("dt_cadastro").toLocalDate());
                usuario.setDataUltimaAtualizacao(resultSet.getDate("dt_ultima_atualizacao").toLocalDate());
                usuario.setAtivo(resultSet.getString("st_ativo").charAt(0));
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar usuario: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuario;
    }

    public ArrayList<UsuarioTO> findAll() {
        String sql = "SELECT * FROM T_FLUX_USUARIO ORDER BY id_usuario";
        ArrayList<UsuarioTO> usuarios = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    UsuarioTO usuario = new UsuarioTO();
                    usuario.setId(resultSet.getLong("id_usuario"));
                    usuario.setEmail(resultSet.getString("nm_email"));
                    usuario.setSenha(resultSet.getString("ds_senha_hash"));
                    usuario.setNomeCompleto(resultSet.getString("nm_completo"));
                    usuario.setCargoAtual(resultSet.getString("ds_cargo_atual"));
                    usuario.setCarreiraAlvo(resultSet.getString("ds_carreira_alvo"));
                    usuario.setDataCadastro(resultSet.getDate("dt_cadastro").toLocalDate());
                    usuario.setDataUltimaAtualizacao(resultSet.getDate("dt_ultima_atualizacao").toLocalDate());
                    usuario.setAtivo(resultSet.getString("st_ativo").charAt(0));
                    usuarios.add(usuario);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar usuarios: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuarios;
    }

    public LoginTO login(String email, String senha) {
        String sql = "SELECT usuario.id_usuario, usuario.nm_email, usuario.ds_senha_hash FROM T_FLUX_USUARIO usuario WHERE nm_email = ? AND ds_senha_hash = ?";
        LoginTO login = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, senha);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                login = new LoginTO();
                login.setIdUsuario(resultSet.getLong("id_usuario"));
                login.setEmail(resultSet.getString("nm_email"));
                login.setSenha(resultSet.getString("ds_senha_hash"));
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao logar usuario: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return login;
    }
}
