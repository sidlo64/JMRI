package sidlo64.translatorsHelper;

import java.text.DecimalFormat;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Renderer for data type Integer.
 * For JMRI project.
 *
 * @author Petr Šídlo (c) 2021
 */
public class RendererInteger extends DefaultTableCellRenderer {

    private DecimalFormat formatter ;

    public RendererInteger() {
        super();
        formatter = new DecimalFormat("#,##0");
        setHorizontalAlignment(SwingConstants.RIGHT);
    }

@Override
    public void setValue(Object value) {
        if ((value != null) && (value instanceof Number)) {
            value = formatter.format(value);
        }

        super.setValue(value);
    }
}
