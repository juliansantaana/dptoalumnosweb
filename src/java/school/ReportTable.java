package school;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juliansantaana
 */
public class ReportTable {
    private String Name;
    private ArrayList<ReportTableColumn> Columns = new ArrayList<ReportTableColumn>();
    private ArrayList<ReportTableRow> Rows = new ArrayList<ReportTableRow>();

    public ReportTable(String Name) {
        this.Name = Name;
    }

    public ReportTable() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public ArrayList<ReportTableColumn> getColumns() {
        return Columns;
    }

    public void setColumns(ArrayList<ReportTableColumn> Columns) {
        this.Columns = Columns;
    }

    public ArrayList<ReportTableRow> getRows() {
        return Rows;
    }

    public void setRows(ArrayList<ReportTableRow> Rows) {
        this.Rows = Rows;
    }
}
