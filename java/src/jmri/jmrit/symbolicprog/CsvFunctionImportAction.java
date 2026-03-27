package jmri.jmrit.symbolicprog;

import java.awt.event.ActionEvent;

import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import jmri.jmrit.symbolicprog.tabbedframe.PaneProgFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Action to import the Function Label values from a CSV format file.
 *
 * @author Bob Jacobsen Copyright (C) 2003, 2026
 * @author Dave Heap Copyright (C) 2015
 */
public class CsvFunctionImportAction extends AbstractAction {

    public CsvFunctionImportAction(String actionName, PaneProgFrame parent) {
        super(actionName);
        this.parent = parent;
    }

    PaneProgFrame parent;
    JFileChooser fileChooser;

    @Override
    public void actionPerformed(ActionEvent e) {

        if (fileChooser == null) {
            fileChooser = new jmri.util.swing.JmriJFileChooser();
        }

        int retVal = fileChooser.showSaveDialog(parent);

        if (retVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (log.isDebugEnabled()) {
                log.debug("start to export to CSV file {}", file);
            }

            try {
                var csvFormat = org.apache.commons.csv.CSVFormat.Builder.create(org.apache.commons.csv.CSVFormat.DEFAULT).setHeader().build();
                var csvFile = org.apache.commons.csv.CSVParser.parse(file, java.nio.charset.StandardCharsets.UTF_8, csvFormat);
                for (var record : csvFile.getRecords()) {
        
                    var number = Integer.parseInt(record.get("Number"));
                    var label = record.get("Label");
        
                    parent.getFnLabelPane().setLabel(number, label);
                }
            } catch (IOException ex) {
                log.error("Error reading file", ex);
            }
        }
    }

    private final static Logger log = LoggerFactory.getLogger(CsvFunctionImportAction.class);
}
