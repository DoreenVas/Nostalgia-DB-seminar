package Resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TableInfo {

    //number of info columns
    private int colsNum;
    // number of info rows
    private int rowsNum;
    // the given fields in the info, such as name, etc...
    private ArrayList<String> fields;
    /*
    the values of the info. each cell is a line, in which the values are in the order of the
    fields.
    for example, the fields: [name, id,....] the values will be: [[name,id...],[name,id...]...]
     */
    private ArrayList<ArrayList<ArrayList<String>>> fieldsValues;

    /***
     * Constructor
     * @param colsNum number of columns in the table
     * @param rowsNum number of rows in the table
     * @param fields the fields in table
     * @param fieldsValues the values of each field (for each row)
     */
    public TableInfo(int colsNum, int rowsNum, ArrayList<String> fields,
                     ArrayList<ArrayList<ArrayList<String>>> fieldsValues){
        this.colsNum = colsNum;
        this.rowsNum = rowsNum;
        this.fields = fields;
        this.fieldsValues = fieldsValues;
    }

    /***
     * returns the number of columns
     * @return the number of columns in the table
     */
    public int getColsNum(){
        return this.colsNum;
    }

    /***
     * returns the number of rows in the table
     * @return the number of rows in the table
     */
    public int getRowsNum(){
        return this.rowsNum;
    }

    /***
     * returns the fields of the table
     * @return a list with the fields of the table
     */
    public ArrayList<String> getFields(){
        return this.fields;
    }

    /***
     * returns the values of fields of the table
     * @return a list with the values of fields in the table (the values for each row)
     */
    public ArrayList<ArrayList<ArrayList<String>>> getFieldsValues(){
        return this.fieldsValues;
    }
}
