package com.junior.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.junior.to.Cliente;

/**
  @author Junior
 */
public class ClienteDAO implements IDAO<Cliente> {

    @Override
    public void inserir(Cliente cliente) throws Exception {
        Conexao c = new Conexao();
        String sql = "INSERT INTO CLIENTE (NOME, CPF, DATANASCIMENTO) VALUES (?, ?, ?)";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setString(1, cliente.getNome());
        ps.setString(2, cliente.getCpf());
        ps.setDate(3, new Date(cliente.getDataNascimento().getTime()));
        ps.execute();
        c.confirmar();
    }

    @Override
    public void alterar(Cliente cliente) throws Exception {
        Conexao c = new Conexao();
        String sql = "UPDATE CLIENTE SET NOME=?, CPF=?, DATANASCIMENTO=? WHERE CODIGO=?";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setString(1, cliente.getNome());
        ps.setString(2, cliente.getCpf());
        ps.setDate(3, new Date(cliente.getDataNascimento().getTime()));
        ps.setInt(4, cliente.getCodigo());
        ps.execute();
        c.confirmar();
    }

    @Override
    public void excluir(Cliente cliente) throws Exception {
        Conexao c = new Conexao();
        String sql = "DELETE FROM CLIENTE WHERE CODIGO=?";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setInt(1, cliente.getCodigo());
        ps.execute();
        c.confirmar();
    }

    @Override
    public ArrayList<Cliente> listarTodos() throws Exception {
        Conexao c = new Conexao();
        String sql = "SELECT * FROM CLIENTE ORDER BY NOME";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList listaClientes = new ArrayList();
        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setCodigo(rs.getInt("CODIGO"));
            cliente.setNome(rs.getString("NOME"));
            cliente.setCpf(rs.getString("CPF"));
            cliente.setDataNascimento(rs.getDate("DATANASCIMENTO"));
            listaClientes.add(cliente);
        }

        return listaClientes;
    }

    @Override
    public Cliente recuperar(int codigo) throws Exception {
        Conexao c = new Conexao();
        String sql = "SELECT * FROM CLIENTE WHERE CODIGO=?";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();

        Cliente cliente = new Cliente();
        if (rs.next()) {
            cliente.setCodigo(rs.getInt("CODIGO"));
            cliente.setNome(rs.getString("NOME"));
            cliente.setCpf(rs.getString("CPF"));
            cliente.setDataNascimento(rs.getDate("DATANASCIMENTO"));
        }

        return cliente;
    }
}
