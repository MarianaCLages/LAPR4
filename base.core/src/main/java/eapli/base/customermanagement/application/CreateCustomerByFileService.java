package eapli.base.customermanagement.application;

import eapli.base.customermanagement.domain.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CreateCustomerByFileService {

    public List<String []> createCustomerByFile(File path) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(String.valueOf(path)));
        sc.nextLine();
        List<String []> customerList = new ArrayList<>();

        do {
            String[] line = sc.nextLine().split(",");

            String [] stringsArray = {line[0],line[1],line[2],line[4],line[3],line[5],line[6],line[7],line[8],line[9],line[10],line[11],line[12]};


            customerList.add(stringsArray);

        }while (sc.hasNextLine());

        return customerList;
    }
}
