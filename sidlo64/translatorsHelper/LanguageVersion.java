package sidlo64.translatorsHelper;

import java.util.Locale;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Record for combo box language.
 * For JMRI project.
 *
 * @author Petr Šídlo (c) 2021
 */
public class LanguageVersion 
    implements Comparable<LanguageVersion> {

    private int translated ;
    private float percentTranslated ;
    private Locale locale ;
    private String suffix ;
    private Icon icon ;
    
    public LanguageVersion(String language) {
        locale = new Locale(language) ;
        suffix = "_" + language ;

        setIcon() ;
    }
    
    public LanguageVersion(String language, String country) {
        locale = new Locale(language, country) ;
        suffix = "_" + language + "_" + country ;
        setIcon() ;
    }
    
    public LanguageVersion(Locale locale) {
        this.locale = locale ;
        String language = this.locale.getLanguage() ;
        String country = this.locale.getCountry() ;
        if (country.isEmpty()) {
            suffix = "_" + language ;
        } else {
            suffix = "_" + language + "_" + country ;            
        }
        setIcon() ;
    }
    
    public int getTranslated() {
        return translated;
    }

    public void setTranslated(int translated) {
        this.translated = translated;
    }

    public float getPercentTranslated() {
        return percentTranslated;
    }

    public void setPercentTranslated(float percentTranslated) {
        this.percentTranslated = percentTranslated;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    
    public Icon getIcon() {
        return icon ;
    }
    
    @Override
    public int compareTo(LanguageVersion o) {
        return locale.getDisplayName().compareTo(o.locale.getDisplayName()) ;
    }
    
    @Override
    public String toString() {
        return locale.getDisplayName() ;
    }

    private void setIcon() {

        ClassLoader imageDir = this.getClass().getClassLoader();
        String iconPath = "sidlo64/translatorsHelper/flag" + suffix + ".png" ;
        java.net.URL imageURL = imageDir.getResource(iconPath);

        if (imageURL != null) {
            icon = (Icon ) new ImageIcon(imageURL) ;
        } else {
            iconPath = "sidlo64/translatorsHelper/flag.png" ;
            imageURL = imageDir.getResource(iconPath);
            icon = (Icon ) new ImageIcon(imageURL) ;
        }

    }
    
}
