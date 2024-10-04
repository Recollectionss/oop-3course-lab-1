package candy.DAO;

import candy.interfaces.CandyDAOInterface;
import candy.model.Candy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CandyDAO implements CandyDAOInterface {
    Connection _connection;
    public CandyDAO(Connection connection) {
        if(connection == null) {
            throw new NullPointerException("connection is null");
        }
        this._connection = connection;

        try {
            if (!checkTable()){
                createTable();
                System.out.println("Table created");
            }else{
                System.out.println("Table already exists");
            }
            System.out.println("Connection established");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void create(Candy candy) throws SQLException {
        String sqlQuery = "INSERT INTO Candy(name, type, price, weight, sugar, sugar_per_100g) VALUES (?,?,?,?,?,?)";
        try(java.sql.PreparedStatement statement = prepareStatement(sqlQuery,candy)) {

            statement.execute();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sqlQuery = "";
        try(Statement statement = _connection.createStatement()) {

        }
    }

    @Override
    public void update(Candy candy) throws SQLException {
        String sqlQuery = "UPDATE Candy SET name = ?, type = ?, price = ?, weight = ?, sugar = ?, sugar_per_100g = ? WHERE id = ?";
        try(java.sql.PreparedStatement statement = prepareStatement(sqlQuery,candy)) {
            statement.setInt(6, candy.getId());

            statement.execute();
        }
    }

    @Override
    public void select(int id) throws SQLException {
        String sqlQuery = "";
        try(Statement statement = _connection.createStatement()) {

        }
    }

    @Override
    public ArrayList<Candy> selectAll() throws SQLException {
        String sqlQuery = "";
        try(Statement statement = _connection.createStatement()) {

        }
        return null;
    }

    @Override
    public boolean checkTable() {
        String sqlQuery = "SELECT id FROM Candy";
        try (Statement statement = _connection.createStatement()){
            statement.execute(sqlQuery);
            return true;
        } catch (SQLException e) {
            System.out.println("Table not exists" + e.getMessage());
            return false;
        }
    }

    @Override
    public void createTable() throws SQLException {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS Candy(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "type Text," +
                "price INTEGER," +
                "weight INTEGER," +
                "sugar INTEGER," +
                "sugar_per_100g INTEGER" +
                ");";
        try (Statement statement = _connection.createStatement()){
            statement.execute(sqlQuery);
        }
    }

    private PreparedStatement prepareStatement(String sql, Candy candy) throws SQLException {
        PreparedStatement statement = _connection.prepareStatement(sql);

        statement.setString(1, candy.getName());
        statement.setString(2, String.valueOf(candy.getCandyType()));
        statement.setInt(3, candy.getPrice());
        statement.setInt(4, candy.getWeight());
        statement.setInt(5, candy.getSugar());
        statement.setInt(6,candy.getSugarPercentagePer100g());

        return statement;
    }
}
