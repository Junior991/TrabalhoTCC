package com.junior.gui.tm;

import java.text.NumberFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import com.junior.to.ItemVenda;

/*
 * @author Junior
 */
public class ItemVendaTableModel extends AbstractTableModel {

    private final NumberFormat NF = NumberFormat.getNumberInstance();
    private final NumberFormat NFC = NumberFormat.getCurrencyInstance();
    private String colunas[] = {"Produto", "Quantidade", "Valor Unitário"};
    private List<ItemVenda> dados;

    @Override
    public int getRowCount() {
        if(dados == null){
            return 0;
        }
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int l, int c) {
        ItemVenda itemVenda = dados.get(l);
        switch (c) {
            case 0:
                return itemVenda.getProduto().getNome();
            case 1:
                return NF.format(itemVenda.getQuantidade());
            case 2:
                return NFC.format(itemVenda.getValorUnitario());
            default:
                throw new IndexOutOfBoundsException("Coluna inexistente!");
        }
    }

    @Override
    public String getColumnName(int c) {
        return colunas[c];
    }

    @Override
    public boolean isCellEditable(int l, int c) {
        return false;
    }

    public void setDados(List<ItemVenda> dados) {
        this.dados = dados;
        fireTableDataChanged();
    }

    public ItemVenda getRowValue(int l) {
        return dados.get(l);
    }
}
