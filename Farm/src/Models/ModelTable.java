
package Models;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Igor Martins
 */
public class ModelTable extends AbstractTableModel{
   
    private ArrayList linhas = null;
    private String[] colunas = null;
    
    //MÉTODOS
    public ModelTable(ArrayList lin,String[] col){
        setLinhas(lin);
        setColunas(col);
    }

    
    //MÉTODOS ESPECIAIS
    public ArrayList getLinhas() {
        return linhas;
    }

    public void setLinhas(ArrayList linhas) {
        this.linhas = linhas;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
    @Override
    public int getColumnCount(){
        return colunas.length;//retorna a quantidade de colunas
    }
    @Override
    public int getRowCount(){
        return linhas.size();// retorna a quantidade de linhas
    }
    @Override
    public String getColumnName(int numCol){//pega o nome da coluna
        return colunas[numCol];// retorna o número da coluna
    }
    @Override
    public Object getValueAt(int numLin,int numCol){//Ele que vai construir a tabela. 
        Object[] linha = (Object[])getLinhas().get(numLin);
        return linha[numCol];
    }
    
}
