package sidlo64.translatorsHelper;

import java.awt.Component;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Renderer for progress bar.
 * For JMRI project.
 *
 * @author Petr Šídlo (c) 2021
 */
public class ProgressRenderer extends JProgressBar implements TableCellRenderer {

    public ProgressRenderer(int min, int max) {
        super(min, max);
        this.setStringPainted(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
    
        this.setValue((Integer) value);
        return this;
    }
}
