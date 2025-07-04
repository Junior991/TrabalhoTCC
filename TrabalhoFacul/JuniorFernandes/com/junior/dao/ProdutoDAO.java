package com.junior.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.junior.to.Produto;

/**
 * @author Junior
 */
public class ProdutoDAO implements IDAO<Produto> {

    @Override
    public void inserir(Produto produto) throws Exception {
        Conexao c = new Conexao();
        String sql = "INSERT INTO PRODUTO (NOME, PRECOCOMPRA, PRECOVENDA, QUANTIDADEESTOQUE) VALUES (?, ?, ?, 0)";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setString(1, produto.getNome());
        ps.setDouble(2, produto.getPrecoCompra());
        ps.setDouble(3, produto.getPrecoVenda());
        ps.execute();
        c.confirmar();
    }

    @Override
    public void alterar(Produto produto) throws Exception {
        Conexao c = new Conexao();
        String sql = "UPDATE PRODUTO SET NOME=?, PRECOCOMPRA=?, PRECOVENDA=? WHERE CODIGO=?";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setString(1, produto.getNome());
        ps.setDouble(2, produto.getPrecoCompra());
        ps.setDouble(3, produto.getPrecoVenda());
        ps.setInt(4, produto.getCodigo());
        ps.execute();
        c.confirmar();
    }

    public void entradaEstoque(Conexao c, int codigo, int quantidade) throws Exception {
        String sql = "UPDATE PRODUTO SET QUANTIDADEESTOQUE= QUANTIDADEESTOQUE  + ? WHERE CODIGO=?";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setInt(1, quantidade);
        ps.setInt(2, codigo);
        ps.execute();
    }

    public void saidaEstoque(Conexao c, int codigo, int quantidade) throws Exception {
        String sql = "UPDATE PRODUTO SET QUANTIDADEESTOQUE= QUANTIDADEESTOQUE  - ? WHERE CODIGO=?";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setInt(1, quantidade);
        ps.setInt(2, codigo);
        ps.execute();
    }

    @Override
    public void excluir(Produto produto) throws Exception {
        Conexao c = new Conexao();
        String sql = "DELETE FROM PRODUTO WHERE CODIGO=?";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setInt(1, produto.getCodigo());
        ps.execute();
        c.confirmar();
    }

    @Override
    public ArrayList<Produto> listarTodos() throws Exception {
        Conexao c = new Conexao();
        String sql = "SELECT * FROM PRODUTO ORDER BY NOME";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList listaProdutos = new ArrayList();
        while (rs.next()) {
            Produto produto = new Produto();
            produto.setCodigo(rs.getInt("CODIGO"));
            produto.setNome(rs.getString("NOME"));
            produto.setPrecoCompra(rs.getDouble("PRECOCOMPRA"));
            produto.setPrecoVenda(rs.getDouble("PRECOVENDA"));
            produto.setQuantidade(rs.getInt("QUANTIDADEESTOQUE"));
            listaProdutos.add(produto);
        }

        return listaProdutos;
    }

    @Override
    public Produto recuperar(int codigo) throws Exception {
        Conexao c = new Conexao();
        String sql = "SELECT * FROM PRODUTO WHERE CODIGO=?";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();

        Produto produto = new Produto();
        if (rs.next()) {
            produto.setCodigo(rs.getInt("CODIGO"));
            produto.setNome(rs.getString("NOME"));
            produto.setPrecoCompra(rs.getDouble("PRECOCOMPRA"));
            produto.setPrecoVenda(rs.getDouble("PRECOVENDA"));
            produto.setQuantidade(rs.getInt("QUANTIDADEESTOQUE"));
        }

        return produto;
    }
}
