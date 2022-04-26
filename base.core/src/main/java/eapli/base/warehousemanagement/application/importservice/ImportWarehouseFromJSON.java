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

            System.out.println(jsonObject.keySet());

            String name = (String) jsonObject.get("Warehouse");
            System.out.printf("%s%n", name);

            long length = (long) jsonObject.get("Length");
            System.out.printf("%d%n", length);
            long width = (long) jsonObject.get("Width");
            System.out.printf("%d%n", width);
            long square = (long) jsonObject.get("Square");
            System.out.printf("%d%n", square);
            String unit = (String) jsonObject.get("Unit");
            System.out.printf("%s%n", unit);
            JSONArray aisles = (JSONArray) jsonObject.get("Aisles");
            System.out.println(Arrays.toString(aisles.toArray()));
            JSONArray AGVDocks = (JSONArray) jsonObject.get("AGVDocks");
            System.out.println(Arrays.toString(AGVDocks.toArray()));

            //Import the aisles, rows and shelves
            for (Object aisle : aisles.toArray()) {
                JSONObject aisleObject = (JSONObject) aisle;
                JSONObject begin = (JSONObject) aisleObject.get("begin");
                System.out.printf("%s%n", begin.keySet());
                JSONObject end = (JSONObject) aisleObject.get("end");
                System.out.printf("%s%n", end.keySet());
                JSONObject depth = (JSONObject) aisleObject.get("depth");
                System.out.printf("%s%n", depth.keySet());
                System.out.println("Começa os numeros");

                long beginLsquared = (long) begin.get("lsquare");
                System.out.printf("%d%n", beginLsquared);
                long beginWsquared = (long) begin.get("wsquare");
                System.out.printf("%d%n", beginWsquared);
                long endLsquared = (long) end.get("lsquare");
                System.out.printf("%d%n", endLsquared);
                long endWsquared = (long) end.get("wsquare");
                System.out.printf("%d%n", endWsquared);
                long depthLsquared = (long) depth.get("lsquare");
                System.out.printf("%d%n", depthLsquared);
                long depthWsquared = (long) depth.get("wsquare");
                System.out.printf("%d%n", depthWsquared);
                System.out.println(aisleObject.get("accessibility"));
                System.out.println(Accessibility.get((String) aisleObject.get("accessibility")));
                warehouseBuilder.addAisle(Math.toIntExact((Long) aisleObject.get("Id"))
                        , new Location(beginLsquared, beginWsquared)
                        , new Location(endLsquared, endWsquared)
                        , new Location(depthLsquared, depthWsquared)
                        , Accessibility.get((String) aisleObject.get("accessibility")).get());


                JSONArray rows = (JSONArray) aisleObject.get("rows");

                for (Object row : rows.toArray()) {
                    JSONObject rowObject = (JSONObject) row;
                    System.out.printf("%s%n", rowObject.keySet());
                    begin = (JSONObject) aisleObject.get("begin");
                    System.out.printf("%s%n", begin.keySet());
                    end = (JSONObject) aisleObject.get("end");
                    System.out.printf("%s%n", end.keySet());
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

            System.out.println(name);
            System.out.println(length);
            System.out.println(width);
            System.out.println(square);
            System.out.println(unit);
            System.out.println(aisles.size());
            System.out.println(AGVDocks.size());


            return Optional.ofNullable(warehouseBuilder.build());


        } catch (ParseException e) {
            System.out.println("Error parsing JSON");
            Logger logger = Logger.getLogger(Warehouse.class.getName());
            logger.log(Level.SEVERE, "Error while parsing the JSON file", e.getMessage());
            return Optional.empty();
        } catch (IOException e) {
            System.out.println("Error reading JSON");
            Logger logger = Logger.getLogger(Warehouse.class.getName());
            logger.log(Level.SEVERE, "Error while reading the JSON file", e.getMessage());
            return Optional.empty();

        }


    }
}



