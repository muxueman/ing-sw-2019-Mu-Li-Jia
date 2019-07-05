package it.polimi.ingsw.se2019.Adrenaline.client.view.CLI;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * the template for print values in a table, used for cli view
 *
 */
public class CommandLineTable {
    private static final String HORIZONTAL_SEP = "-";
    private String verticalSep;
    private String joinSep;
    private String[] headers;
    private List<String[]> rows = new ArrayList<>();
    private boolean rightAlign;
    private String colorLine;
    private String colorWord;

    /**
     *
     * to print the commandline table
     *
     * @param colorLine color
     * @param colorWord color
     */
    public CommandLineTable(String colorLine, String colorWord) {
        this.colorLine = colorLine;
        this.colorWord = colorWord;
        setShowVerticalLines(false);
    }

    /**
     *
     * set the rignt align
     *
     * @param rightAlign right align
     */
    public void setRightAlign(boolean rightAlign) {
        this.rightAlign = rightAlign;
    }

    /**
     * to show the vertical lines
     *
     * @param showVerticalLines whether to show vertial lines
     */

    public void setShowVerticalLines(boolean showVerticalLines) {
        verticalSep = showVerticalLines ? "|" : "";
        joinSep = showVerticalLines ? "+" : " ";
    }

    /**
     *
     * set the headers
     *
     * @param headers set headers
     */
    void setHeaders(String... headers) {
        this.headers = headers;
    }

    /**
     *add the cells in row
     *
     * @param cells set rows
     */
    public void addRow(String... cells) {
        rows.add(cells);
    }

    /**
     *
     *show to the user all the matrx
     */
    public void print() {
        int[] maxWidths = headers != null ?
                Arrays.stream(headers).mapToInt(String::length).toArray() : null;

        for (String[] cells : rows) {
            if (maxWidths == null) {
                maxWidths = new int[cells.length];
            }
            if (cells.length != maxWidths.length) {
                throw new IllegalArgumentException("Number of row-cells and headers should be consistent");
            }
            for (int i = 0; i < cells.length; i++) {
                maxWidths[i] = Math.max(maxWidths[i], cells[i].length());
            }
        }

        if (headers != null) {
            printLine(maxWidths);
            printRow(headers, maxWidths);
            printLine(maxWidths);
        }
        for (String[] cells : rows) {
            printRow(cells, maxWidths);
        }
        if (headers != null) {
            try{
                printLine(maxWidths);
            }catch (Exception e){
                Logger.getGlobal().warning(e.getMessage());
            }

        }
    }

    /**
     *
     * print the colunm line
     *
     * @param columnWidths within the boarder
     */
    private void printLine(int[] columnWidths) {
        for (int i = 0; i < columnWidths.length; i++) {
            String line = String.join("", Collections.nCopies(columnWidths[i] +
                    verticalSep.length() + 1, HORIZONTAL_SEP));
            System.out.print(colorWord+ joinSep + line + (i == columnWidths.length - 1 ? joinSep : ""));
        }
        System.out.println(colorWord);
    }

    /**
     *
     * print the row line
     *
     * @param cells
     * @param maxWidths
     */
    private void printRow(String[] cells, int[] maxWidths) {
        for (int i = 0; i < cells.length; i++) {
            String s = cells[i];
            String verStrTemp = i == cells.length - 1 ? verticalSep : "";
            if (rightAlign) {
                System.out.printf(colorLine + "%s %" + maxWidths[i] + "s %s", verticalSep, s, verStrTemp);
            } else {
                System.out.printf(colorLine + "%s %-" + maxWidths[i] + "s %s", verticalSep, s, verStrTemp);
            }
        }
        System.out.println(colorLine);
    }

}
