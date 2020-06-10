package utility;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.UIResource;
import javax.swing.table.TableCellRenderer;

public class CustomCellRenderer implements TableCellRenderer, UIResource {
	JRadioButton radioButton;
	Border emptyBorder;

	public CustomCellRenderer() {
		radioButton = new JRadioButton();
		radioButton.setHorizontalAlignment(JRadioButton.CENTER);
		radioButton.setBorderPainted(true);
		emptyBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
		if (isSelected) {
			radioButton.setBackground(table.getSelectionBackground());
			radioButton.setForeground(table.getSelectionForeground());
			uncheckOthers(table, row, col);
		} else {
			radioButton.setBackground(table.getBackground());
			radioButton.setForeground(table.getForeground());
		}

		if (hasFocus)
			radioButton.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		else
			radioButton.setBorder(emptyBorder);

		radioButton.setSelected(((Boolean) value).booleanValue());
		return radioButton;
	}

	private void uncheckOthers(JTable table, int row, int col) {
		for (int i = 0; i < table.getRowCount(); i++)
			if (i != row)
				table.setValueAt(false, i, col);
	}
}