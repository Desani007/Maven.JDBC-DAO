package daos;

import Connection.Connecting;
import models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao{

    ArrayList cars;
    public CarDaoImpl(){

        cars= new ArrayList<Car>();
    }


    public Car findById(int id) {

        try {
            Connection connection= Connecting.getDatabaseConnection();
          Statement stm = connection.createStatement();
            ResultSet rs =stm.executeQuery("SELECT * FROM testCar where id="+id);
            while (rs.next()){
                Car car = extractCarFromResultSet(rs);
                if(car.getId()==id) {
                   return car;
                }
            }
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


        return null;
    }


    public ArrayList<Car> findAll() {
        ArrayList<Car> result = null;

        try {
            Connection connection = Connecting.getDatabaseConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM testCar");
            while (rs.next()) {
               Car car = extractCarFromResultSet(rs);
                cars.add(car);
            }
            result = cars;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }




    public boolean update(String make, String model, String year, String color, String vin, int id ) {
        Car car = new Car(make,model,year,color,vin);
        try {
            try {
                Connection connection = Connecting.getDatabaseConnection();
                PreparedStatement ps =connection.prepareStatement("UPDATE testCar SET Make=?, Model=?, Year=?,Color=?, Vin=? where id="+ id);
                ps.setString(1,car.getMake());
                ps.setString(2,car.getModel());
                ps.setString(3,car.getYear());
                ps.setString(4,car.getColor());
                ps.setString(5,car.getVin());
                int i = ps.executeUpdate();
                if(i == 1) {
                    return true;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        } finally {

        }
        return false;

    }

    public boolean create() {

            try {
                Connection connection = Connecting.getDatabaseConnection();
                String creatingTable ="CREATE TABLE testCar( id int, Make varchar(20)," +
                        " Model varchar(20), Year varchar(20), Color varchar(20), vin varchar(20));";
                PreparedStatement ps =connection.prepareStatement(creatingTable );
                int i = ps.executeUpdate();
                if(i == 1) {
                    return true;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return false;

    }
    public boolean delete() {

        try{
        Connection connection = Connecting.getDatabaseConnection();

        PreparedStatement ps =connection.prepareStatement("DROP TABLE IF EXISTS testCar" );
        int i = ps.executeUpdate();
        if(i == 1) {
            return true;
        }
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }

        return false;
    }

    private Car extractCarFromResultSet(ResultSet rs) throws  SQLException{
        Car car = new Car();
        car.setId(rs.getInt("id"));
        car.setMake(rs.getString("Make"));
        car.setModel(rs.getString("Model"));
        car.setYear(rs.getString("Year"));
        car.setColor(rs.getString("Color"));
        car.setVin(rs.getString("Vin"));
        return car;

    }
}
