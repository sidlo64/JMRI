package sidlo64.translatorsHelper;

import java.util.Set;

/**
 * Record for table file.
 * For JMRI project.
 *
 * @author Petr Šídlo (c) 2021
 */
public class RecordFile {

    private String fileName ;
    private int numberOfKeys ;
    private CbmLanguage cbmLanguage ;
    private Set defaultKeys ;
    
    public RecordFile(String fileName, CbmLanguage listOfLanguageVersions) {    
        this.fileName = fileName;
        this.cbmLanguage = new CbmLanguage() ;
        for (int i = 0; i < listOfLanguageVersions.getSize(); i++) {
            LanguageVersion languageVersion = new LanguageVersion(listOfLanguageVersions.getElementAt(i).getLocale()) ;
            this.cbmLanguage.addRecord(languageVersion);            
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getNumberOfKeys() {
        return numberOfKeys;
    }

    public void setNumberOfKeys(int numberOfKeys) {
        this.numberOfKeys = numberOfKeys;
    }
    
    public CbmLanguage getListOfLanguageVersions() {
        return cbmLanguage ;
    }

    public void setListOfLanguageVersions(CbmLanguage listOfLanguageVersions) {
        this.cbmLanguage = listOfLanguageVersions;
    }
    
    public Set getDefaultKeys() {
        return defaultKeys;
    }

    public void setDefaultKeys(Set defaultKeys) {
        this.defaultKeys = defaultKeys;
    }
    
}
