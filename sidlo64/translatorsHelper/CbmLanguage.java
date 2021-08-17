package sidlo64.translatorsHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 * Combo box model for languages.
 * For JMRI project.
 *
 * @author Petr Šídlo (c) 2021
 */
public class CbmLanguage 
            extends AbstractListModel<LanguageVersion> 
            implements ComboBoxModel<LanguageVersion> {

    private Object               selectedItem ;
    private List<LanguageVersion> data ;
    
    public CbmLanguage() {    
        data = new ArrayList<LanguageVersion>() ;
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public LanguageVersion getElementAt(int i) {
        return data.get(i);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem = anItem;
        fireContentsChanged(this, -1, -1) ;
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    public void addRecord(LanguageVersion record) {
        data.add(record) ;
    }
    
    public List<LanguageVersion> getData() {
        return data ;
    }
    
    public void sort() {
        Collections.sort(data);
    }
    
}
