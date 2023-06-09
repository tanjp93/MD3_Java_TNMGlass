package projectBase_00.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Config<T> {
    public static Scanner scanner = new Scanner(System.in);
    public static final String PATH_USER = "src/projectBase_00/database/listUser.csv";
    public static final String PATH_LOGIN = "src/projectBase_00/database/user_principal.csv";
    public static final String PATH_LIST_PRODUCT = "src/projectBase_00/database/listProduct.csv";
    public static final String PATH_CATEGORY = "src/projectBase_00/database/categories.csv";
    public static final String PATH_CART = "src/projectBase_00/database/listCarts.csv";
    public static final String PATH_ABOUT_US = "src/projectBase_00/database/aboutUs.csv";
    public static final String PATH_INFO_COMPANY = "src/projectBase_00/database/info_company.csv";

    public List<T> readFromFile(String pathFile) {
        File file = new File(pathFile);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        List<T> tlist = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(pathFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            tlist = (List<T>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (ClassNotFoundException e) {
            System.err.println(pathFile + " ClassNotFoundException ");
        } catch (IOException e) {
//            System.err.println(pathFile + " IOException ");
        }
        return tlist;
    }

    public void writeToFile(String pathFile, List<T> tList) {
        File file = new File(pathFile);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Create a new file " + pathFile + " because of IOException ");
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pathFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tList);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            System.err.println(pathFile + " IOException ");
        }
    }

    public List<T> readFileText(String pathFile) {
        File file = new File(pathFile);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.err.println("File not found ! ");
        }
        return null;
    }

    public void writeFileText(String pathFile, List<T> tList) {
        File file = new File(pathFile);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException e){
            }
        }
        try {
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file));
            for (int i = 0; i <tList.size() ; i++) {
                bufferedWriter.write((String) tList.get(i));
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("IOException--> bufferedWriter");
        }
    }
}
