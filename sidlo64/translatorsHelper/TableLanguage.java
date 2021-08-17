package sidlo64.translatorsHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 * Table model for list of languages.
 * For JMRI project.
 *
 * @author Petr Šídlo (c) 2021
 */
public class TableLanguage extends AbstractTableModel {

    private static final int COLUMN_FLAG            = 0 ;
    private static final int COLUMN_LOCALE_NAME     = COLUMN_FLAG + 1 ;
    private static final int COLUMN_LANGUAGE_SUFFIX = COLUMN_LOCALE_NAME + 1 ;
    private static final int COLUMN_TRANSLATED      = COLUMN_LANGUAGE_SUFFIX + 1 ;
    private static final int COLUMN_PROGRESS        = COLUMN_TRANSLATED + 1 ;
    
    private static final int COLUMN_COUNT           = COLUMN_PROGRESS + 1 ;
    
    private static final String TITLE_FLAG              = "Flag" ;
    private static final String TITLE_LOCALE_NAME       = "Language" ;
    private static final String TITLE_LANGUAGE_SUFFIX   = "Suffix" ;
    private static final String TITLE_TRANSLATED        = "Translated" ;
    private static final String TITLE_PROGRESS          = "Progress" ;
    
    private List<LanguageVersion> data = new ArrayList<>() ;

    public TableLanguage(CbmLanguage listOfLanguageVersion) {
        data = listOfLanguageVersion.getData() ;
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
        
        LanguageVersion oneRecord = data.get(rowIndex) ;
        
        switch (columnIndex) {
            case COLUMN_FLAG :
                return oneRecord.getIcon() ;
            case COLUMN_LOCALE_NAME :
                return oneRecord.getLocale().getDisplayName() ;
            case COLUMN_LANGUAGE_SUFFIX :
                return oneRecord.getSuffix() ;
            case COLUMN_TRANSLATED :
                return oneRecord.getTranslated() ;
            case COLUMN_PROGRESS :
                float floatNumber = oneRecord.getPercentTranslated() ;
                int integerNumber = Math.round(floatNumber) ;
                return integerNumber ;
        }
        
        return null ;
    }

    @Override
    public Class<?> getColumnClass(int col) {

        switch (col) {
            case COLUMN_FLAG :
                return Icon.class ;
            case COLUMN_LOCALE_NAME :
                return String.class ;
            case COLUMN_LANGUAGE_SUFFIX :
                return String.class ;
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
            case COLUMN_FLAG :
                return TITLE_FLAG ;
            case COLUMN_LOCALE_NAME :
                return TITLE_LOCALE_NAME ;
            case COLUMN_LANGUAGE_SUFFIX :
                return TITLE_LANGUAGE_SUFFIX ;
            case COLUMN_TRANSLATED :
                return TITLE_TRANSLATED ;
            case COLUMN_PROGRESS :
                return TITLE_PROGRESS ;
        }
        
        return null ;
    }
    
    public void addRow(LanguageVersion recordLanguageVersion) {
        data.add(recordLanguageVersion) ;
        fireTableDataChanged();
    }

    public LanguageVersion getRow(int row) {
        return data.get(row) ;
    }
    
    public LanguageVersion getRowLocale(Locale locale) {
        for (LanguageVersion oneRecord : data) {
            if (oneRecord.getLocale().equals(locale)) {
                return oneRecord ;
            }
        }
        return null ;
    }
    
    
    /**
     * Set columns width and renderes.
     * 
     * @param table 
     */
    public void setColumns(JTable table) {

        table.getColumnModel().getColumn(COLUMN_FLAG).setPreferredWidth(30);
        table.getColumnModel().getColumn(COLUMN_LOCALE_NAME).setPreferredWidth(500);
        table.getColumnModel().getColumn(COLUMN_LANGUAGE_SUFFIX).setPreferredWidth(80);
        table.getColumnModel().getColumn(COLUMN_TRANSLATED).setPreferredWidth(80);
        table.getColumnModel().getColumn(COLUMN_PROGRESS).setPreferredWidth(120);

        table.getColumnModel().getColumn(COLUMN_TRANSLATED).setCellRenderer(new RendererInteger());
        table.getColumnModel().getColumn(COLUMN_PROGRESS).setCellRenderer(new ProgressRenderer(0, 100));
        
    }

}
