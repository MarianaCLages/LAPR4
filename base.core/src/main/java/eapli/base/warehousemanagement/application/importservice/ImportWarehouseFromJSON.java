package eapli.base.warehousemanagement.application.importservice;


import eapli.base.warehousemanagement.domain.Accessibility;
import eapli.base.warehousemanagement.domain.Location;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.domain.WarehouseBuilder;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImportWarehouseFromJSON implements ImportWarehouseFromFile {
    private final WarehouseBuilder warehouseBuilder = new WarehouseBuilder();

    @Override
    public Optional<Warehouse> importWarehouse(String path) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader fileReader = new FileReader(path)) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);


            String name = (String) jsonObject.get("Warehouse");

            long length = (long) jsonObject.get("Length");
            long width = (long) jsonObject.get("Width");
            long square = (long) jsonObject.get("Square");
            String unit = (String) jsonObject.get("Unit");
            JSONArray aisles = (JSONArray) jsonObject.get("Aisles");
            JSONArray AGVDocks = (JSONArray) jsonObject.get("AGVDocks");

            //Import the aisles, rows and shelves
            for (Object aisle : aisles.toArray()) {
                JSONObject aisleObject = (JSONObject) aisle;
                JSONObject begin = (JSONObject) aisleObject.get("begin");
                JSONObject end = (JSONObject) aisleObject.get("end");
                JSONObject depth = (JSONObject) aisleObject.get("depth");

                long beginLsquared = (long) begin.get("lsquare");
                long beginWsquared = (long) begin.get("wsquare");
                long endLsquared = (long) end.get("lsquare");
                long endWsquared = (long) end.get("wsquare");
                long depthLsquared = (long) depth.get("lsquare");
                long depthWsquared = (long) depth.get("wsquare");
                warehouseBuilder.addAisle(Math.toIntExact((Long) aisleObject.get("Id"))
                        , new Location(beginLsquared, beginWsquared)
                        , new Location(endLsquared, endWsquared)
                        , new Location(depthLsquared, depthWsquared)
                        , Accessibility.get((String) aisleObject.get("accessibility")).get());


                JSONArray rows = (JSONArray) aisleObject.get("rows");

                for (Object row : rows.toArray()) {
                    JSONObject rowObject = (JSONObject) row;
                    begin = (JSONObject) aisleObject.get("begin");
                    end = (JSONObject) aisleObject.get("end");
                    beginLsquared = (long) begin.get("lsquare");
                    beginWsquared = (long) begin.get("wsquare");
                    endLsquared = (long) end.get("lsquare");
                    endWsquared = (long) end.get("wsquare");

                    warehouseBuilder.addRow(Math.toIntExact((Long) aisleObject.get("Id"))
                            , Math.toIntExact((Long) rowObject.get("Id"))
                            , new Location(beginLsquared, beginWsquared)
                            , new Location(endLsquared, endWsquared)
                            , Math.toIntExact((Long) rowObject.get("shelves"))
                    );
                }
            }

            //Import the AGV Docks
            for (Object AGVDock : AGVDocks.toArray()) {
                JSONObject AGVDockObject = (JSONObject) AGVDock;
                JSONObject begin = (JSONObject) AGVDockObject.get("begin");
                JSONObject end = (JSONObject) AGVDockObject.get("end");
                JSONObject depth = (JSONObject) AGVDockObject.get("depth");
                int beginLsquared = Math.toIntExact((Long) begin.get("lsquare"));
                int beginWsquared = Math.toIntExact((Long) begin.get("wsquare"));
                int endLsquared = Math.toIntExact((Long) end.get("lsquare"));
                int endWsquared = Math.toIntExact((Long) end.get("wsquare"));
                int depthLsquared = Math.toIntExact((Long) depth.get("lsquare"));
                int depthWsquared = Math.toIntExact((Long) depth.get("wsquare"));
                warehouseBuilder.addAgvDock(String.valueOf(AGVDockObject.get("Id"))
                        , new Location(beginLsquared, beginWsquared)
                        , new Location(endLsquared, endWsquared)
                        , new Location(depthLsquared, depthWsquared)
                        , Accessibility.get((String) AGVDockObject.get("accessibility")).get()
                );
            }


            //Import the name
            warehouseBuilder.withName(name);
            //Import the Lenght
            warehouseBuilder.withLength(Math.toIntExact(length));
            //Import the With
            warehouseBuilder.withWidth(Math.toIntExact(width));
            //Import the Square
            warehouseBuilder.withSquare(Math.toIntExact(square));
            //Import the Unit
            warehouseBuilder.withUnit(unit);


            return Optional.ofNullable(warehouseBuilder.build());


        } catch (ParseException e) {
            Logger logger = Logger.getLogger(Warehouse.class.getName());
            logger.log(Level.SEVERE, "Error while parsing the JSON file", e.getMessage());
            return Optional.empty();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(Warehouse.class.getName());
            logger.log(Level.SEVERE, "Cannot find the file to Import!!", e.getMessage());
            return Optional.empty();

        }


    }
}



