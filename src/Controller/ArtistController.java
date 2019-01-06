package Controller;

import Resources.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class ArtistController extends ControllerAbstract {

    public ArtistController() throws IOException, SQLException{

    }


    @Override
    public TableInfo getInfoFromGUI(Map<String, ArrayList<String>> infoFromGUI) throws SQLException {
        ArtistQueryInfo queryInfo = new ArtistQueryInfo();
        String temp;
        DataContainer dc = null;

        for (Map.Entry<String, ArrayList<String>> entry : infoFromGUI.entrySet())
        {
            switch (entry.getKey()){
                case "familiarity":
                    float val = entry.getValue().size();
                    FamiliarityContainer familiarityContainer = new FamiliarityContainer(val);

                    break;
                case "artist_name":
                    temp = matchStringToPattern(entry.getValue().get(0));
                    ArtistContainer artistContainer = new ArtistContainer(temp);
                    queryInfo.setArtist(artistContainer);
                    break;
                case "popularity":
                    float popularity = Float.parseFloat(entry.getValue().get(0));
                    PopularityContainer popularityContainer = new PopularityContainer(popularity);
                    queryInfo.setPopularity(popularityContainer);

                    break;
                case "song_id":
                    temp = matchStringToPattern(entry.getValue().get(0));
                    SongIdContainer song = new SongIdContainer(temp);
                    queryInfo.setSong(song);
                    break;
            }
        }

        dc = model.getData(queryInfo);

        //create and return the TableInfo
        return createTableInfo(dc);
    }


    /**
     *
     * @param dc
     * @return
     */
    private TableInfo createTableInfo(DataContainer dc){
        int row = dc.getCount();
        int col = dc.getColumns().length;
        ArrayList<String> fields = parseToArrayList(dc.getColumns());
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

        TableInfo ti = new TableInfo(col,row,fields,data);
        return ti;
    }
}