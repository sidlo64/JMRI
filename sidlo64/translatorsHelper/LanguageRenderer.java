package sidlo64.translatorsHelper;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 * Flag renderer for combo box language.
 * For JMRI project.
 *
 * @author Petr Šídlo (c) 2021
 */
public class LanguageRenderer extends DefaultListCellRenderer {

    public LanguageRenderer() {
        super();
    }
  
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                boolean isSelected, boolean cellHasFocus) {
        
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
      
        LanguageVersion mutace = (LanguageVersion) value;
        if (mutace != null) {
            this.setText(mutace.getLocale().getDisplayName());
            this.setIcon(mutace.getIcon());            
        }

        return this;
    }

}
