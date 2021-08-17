package sidlo64.translatorsHelper;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 * Table model for list of files.
 * For JMRI project.
 *
 * @author Petr Šídlo (c) 2021
 */
public class TableFile extends AbstractTableModel {

    private static final int COLUMN_FILE           = 0 ;
    private static final int COLUMN_NUMBER_OF_KEYS = COLUMN_FILE + 1 ;
    private static final int COLUMN_TRANSLATED     = COLUMN_NUMBER_OF_KEYS + 1 ;
    private static final int COLUMN_PROGRESS       = COLUMN_TRANSLATED + 1 ;
    
    private static final int COLUMN_COUNT          = COLUMN_PROGRESS + 1 ;
    
    private static final String TITLE_FILE           = "File" ;
    private static final String TITLE_NUMBER_OF_KEYS = "Keys" ;
    private static final String TITLE_TRANSLATED     = "Translated" ;
    private static final String TITLE_PROGRESS       = "Progress" ;
    
    private List<RecordFile> data = new ArrayList<>() ;
    private int showLanguage ;
    private final CbmLanguage cbmLanguage ;

    public TableFile(CbmLanguage listOfLanguageVersions) {
        this.cbmLanguage = listOfLanguageVersions;
    }
    
    public void setShowLanguage(int showLanguage) {    
        if (showLanguage > cbmLanguage.getSize()) {
            showLanguage = 0 ;
        }
        this.showLanguage = showLanguage;
        
        fireTableDataChanged();
    }

    public int getShowLanguage() {
        return showLanguage ; 
    }

    @Override
    public int getRowCount() {
        return data.size() ; 
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT ;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        RecordFile oneRecord = data.get(rowIndex) ;
        
        switch (columnIndex) {
            case COLUMN_FILE :
                return oneRecord.getFileName() ;
            case COLUMN_NUMBER_OF_KEYS :
                return oneRecord.getNumberOfKeys() ;
            case COLUMN_TRANSLATED :
                return oneRecord.getListOfLanguageVersions().getElementAt(showLanguage).getTranslated() ;
            case COLUMN_PROGRESS :
                float floatNumber = oneRecord.getListOfLanguageVersions().getElementAt(showLanguage).getPercentTranslated() ;
                int integerNumber = Math.round(floatNumber) ;
                return integerNumber ;
        }
        
        return null ;
    }

    @Override
    public Class<?> getColumnClass(int col) {

        switch (col) {
            case COLUMN_FILE :
                return String.class ;
            case COLUMN_NUMBER_OF_KEYS :
                return Integer.class ;
            case COLUMN_TRANSLATED :
                return Integer.class ;
            case COLUMN_PROGRESS :
                return Float.class ;
        }
        
        return null ;
    }
    
    @Override
    public String getColumnName(int col) {
        
        switch (col) {
            case COLUMN_FILE :
                return TITLE_FILE ;
            case COLUMN_NUMBER_OF_KEYS :
                return TITLE_NUMBER_OF_KEYS ;
            case COLUMN_TRANSLATED :
                return TITLE_TRANSLATED ;
            case COLUMN_PROGRESS :
                return TITLE_PROGRESS ;
        }
        
        return null ;
    }
    
    public void addRow(RecordFile recordFile) {
        boolean addRecord = true ;
        for (RecordFile oneRecord : data) {
            if (oneRecord.getFileName().equals(recordFile.getFileName())) {
                addRecord = false ;
            }
        }
        if (addRecord) {
            data.add(recordFile) ;
        }
    }

    public RecordFile getRow(int row) {
        return data.get(row) ;
    }
    
    /**
     * Set columns width and renderes.
     * 
     * @param table 
     */
    public void setColumns(JTable table) {

        table.getColumnModel().getColumn(COLUMN_FILE).setPreferredWidth(500);
        table.getColumnModel().getColumn(COLUMN_NUMBER_OF_KEYS).setPreferredWidth(60);
        table.getColumnModel().getColumn(COLUMN_TRANSLATED).setPreferredWidth(60);
        table.getColumnModel().getColumn(COLUMN_PROGRESS).setPreferredWidth(120);

        table.getColumnModel().getColumn(COLUMN_NUMBER_OF_KEYS).setCellRenderer(new RendererInteger());
        table.getColumnModel().getColumn(COLUMN_TRANSLATED).setCellRenderer(new RendererInteger());
        table.getColumnModel().getColumn(COLUMN_PROGRESS).setCellRenderer(new ProgressRenderer(0, 100));
        
    }

}
