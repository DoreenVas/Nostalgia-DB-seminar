package Controller;

import Resources.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/***
 * A class for album controller
 */
public class AlbumController extends ControllerAbstract {

    /***
     * Constructor
     * @throws IOException IOException
     * @throws SQLException SQLException
     */
    public AlbumController() throws IOException, SQLException {
    }

    @Override
    public TableInfo getInfoFromGUI(Map<String, ArrayList<String>> infoFromGUI) throws SQLException {
        AlbumQueryInfo queryInfo = new AlbumQueryInfo();
        String temp;
        DataContainer dc = null;
        // go over the key and values
        for (Map.Entry<String, ArrayList<String>> entry : infoFromGUI.entrySet()) {
            switch (entry.getKey()) {
                case "album_name":
                    temp = matchStringToPattern(entry.getValue().get(0));
                    AlbumIdContainer albumContainer = new AlbumIdContainer(temp);
                    queryInfo.setAlbum(albumContainer);
                    break;
                case "artist_name":
                    temp = matchStringToPattern(entry.getValue().get(0));
                    ArtistContainer artistContainer = new ArtistContainer(temp);
                    queryInfo.setArtist(artistContainer);
                    break;
                case "song_id":
                    temp = matchStringToPattern(entry.getValue().get(0));
                    SongIdContainer song = new SongIdContainer(temp);
                    queryInfo.setSong(song);
                    break;
            }
        }

        dc = model.getData(queryInfo);
        if(dc == null) {
            return null;
        }

        //create and return the TableInfo
        return createTableInfo(dc);
    }


    /**
     * @param dc a dataContainer
     * @return a tableInfo object
     */
    private TableInfo createTableInfo(DataContainer dc) {
        // get number of rows and columns in the table
        int row = dc.getCount();
        int col = dc.getColumns().length;
        // get the fields of the table
        ArrayList<String> fields = parseToArrayList(dc.getColumns());
        // get the data of the table
        ArrayList<ArrayList<ArrayList<String>>> data = new ArrayList<>();
        ArrayList<ArrayList<String>> tempArrayList = new ArrayList<>();

        ArrayList<String> newRow = new ArrayList<>();
        for(String item : dc.getData()) {
            String[] temp = item.split(",");
            for (int j = 0; j < temp.length; j++) {
                newRow.add(j, temp[j]);
            }
            tempArrayList.add(newRow);
        }

        //tempArrayList.add(parseToArrayList(dc.getData()));
        data.add(tempArrayList);

        TableInfo ti = new TableInfo(col, row, fields, data);
        return ti;
    }
}