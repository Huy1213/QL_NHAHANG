/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.cell.deleteBillDetail;
import raven.cell.*;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;



/**
 *
 * @author ACCER
 */
public class TableActionCellEditorDelete extends DefaultCellEditor {
    private TableActionEvent event;

    public TableActionCellEditorDelete(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        PanelActionDelete action = new PanelActionDelete();
        action.initEvent(event, row);  // Đảm bảo rằng `initEvent` được gọi với `event` và `row`
        return action;
    }
}

