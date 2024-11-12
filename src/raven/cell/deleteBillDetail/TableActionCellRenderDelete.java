package raven.cell.deleteBillDetail;


import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ACCER
 */
public class TableActionCellRenderDelete extends DefaultTableCellRenderer {


     @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        PanelActionDelete action = new PanelActionDelete();
        if (!isSelected && row % 2 == 0) {
            action.setBackground(Color.WHITE);
        } else {
            action.setBackground(table.getSelectionBackground());
        }
        return action;
    }
}
