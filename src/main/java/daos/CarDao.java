package daos;


import models.Car;

import java.util.ArrayList;
import java.util.List;

public interface CarDao  {
    Car findById(int id);
    ArrayList<Car> findAll();
    boolean update(String make, String model, String year, String color, String vin, int id);
     boolean create();
     boolean delete();


}
