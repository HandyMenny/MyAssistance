/*
Project: MyAssistance
Author: Pio
Date: 30/12/2018
*/
package model.utente;

import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import pool.Database;

/**
 * This class manages stored data about Users into the database.
 */
public final class UtenteDB implements UtenteDBInterface {
    /**
     * This private attribute is a string that contains
     * the name of the table.
     */
    private static final String TABLE_NAME = "utente";

    /**
     * This private attribute is a string that contains
     * the query insert user by id.
     */
    private static final String INSERT = "INSERT INTO " + UtenteDB.TABLE_NAME
        + " (username,pass,email,nome,cognome,"
        + "sesso,data_di_nascita,data_sospensione,is_gestore) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?,? ,?);";

    /**
     * This private attribute is a string that contains
     * the query get user by id.
     */
    private static final String GET_BY_ID =
        "SELECT * FROM " + UtenteDB.TABLE_NAME + " WHERE id = ?";

    /**
     * This private attribute is a string that contains
     * the query get user by username.
     */
    private static final String GET_BY_USERNAME =
        "SELECT * FROM " + UtenteDB.TABLE_NAME + " WHERE username = ?";

    /**
     * This private attribute is a string that contains
     * the query get all user.
     */
    private static final String GET_ALL =
        "SELECT * FROM " + UtenteDB.TABLE_NAME;

    /**
     * This private attribute is a string that contains
     * the query to delete user by username.
     */
    private static final String DELETE =
        "DELETE FROM " + UtenteDB.TABLE_NAME + " WHERE email =?;";

    /**
     * This private attribute is a string that contains
     * the query to update data user by username.
     */
    private static final String UPDATE =
        "UPDATE " + UtenteDB.TABLE_NAME + " SET data_sospensione = ?"
            + " WHERE id = ?";

    /**
     * Default constructor.
     */
    public UtenteDB() {

    }

    /**
     * This method inserts a user into the database.
     *
     * @param aUtente is the object of the Utente type that
     *                must be inserted into the database.
     * @return res is 0 if the insert operation is not made,
     * otherwise an integer greater than 0.
     * @throws SQLException is the exception that can be thrown
     *                      during the execution.
     */
    public int insert(final Utente aUtente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        int res;
        Utente utente = aUtente;

        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);
            int i = 1;
            preparedStatement.setString(i++, aUtente.getUserName());
            preparedStatement.setString(i++, aUtente.getPassword());
            preparedStatement.setString(i++, aUtente.getEmail());
            preparedStatement.setString(i++, aUtente.getNome());
            preparedStatement.setString(i++, aUtente.getCognome());
            preparedStatement.setInt(i++, aUtente.getSesso());
            preparedStatement
                .setDate(i++, Date.valueOf(aUtente.getDataDiNascita()));

            if (aUtente instanceof Gestore) {
                preparedStatement.setDate(i++, null);
                preparedStatement.setBoolean(i, utente.isGestore());
            } else {
                if (((CSU) utente).getDataSospensione() != null) {
                    preparedStatement.setDate(i++,
                        Date.valueOf(((CSU) utente).getDataSospensione()));
                } else {
                    preparedStatement.setDate(i++, null);
                }

                preparedStatement.setBoolean(i, false);
            }
            res = preparedStatement.executeUpdate();
        } finally {
            Database.freeConnection(connection);
        }
        return (res);
    }

    /**
     * This method fetches information about a user
     * given his id.
     *
     * @param aId is the id of the user.
     * @return user is the user whose id is of.
     * @throws SQLException is the exception that can be thrown
     *                      during the execution.
     */
    public Utente getById(final int aId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        Utente user = null;
        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(GET_BY_ID);

            preparedStatement.setInt(1, aId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                boolean b = rs.getBoolean("is_gestore");
                if (b) {
                    user = new Gestore();
                } else {
                    user = new CSU();
                }
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("pass"));
                user.setEmail(rs.getString("email"));
                user.setNome(rs.getString("nome"));
                user.setCognome(rs.getString("cognome"));
                user.setDataDiNascita(rs.getDate("data_di_nascita")
                    .toLocalDate());
                user.setSesso(rs.getInt("sesso"));
                if (!b) {
                    Date tmp = rs.getDate("data_sospensione");
                    if (tmp != null) {
                        ((CSU) user).setDataSospensione(tmp.toLocalDate());
                    }
                }
            }
        } finally {
            Database.freeConnection(connection);
        }

        return user;
    }

    /**
     * This method fetches information about a user
     * given his username address.
     *
     * @param aUserName is the username of the user.
     * @return user is the user whose the username is of.
     * @throws SQLException is the exception that can be thrown
     *                      during the execution.
     */
    public Utente getByUserName(final String aUserName) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        Utente user = null;

        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(GET_BY_USERNAME);

            preparedStatement.setString(1, aUserName);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                boolean b = rs.getBoolean("is_gestore");
                if (b) {
                    user = new Gestore();
                } else {
                    user = new CSU();
                }

                user.setId(rs.getInt("Id"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("pass"));
                user.setEmail(rs.getString("email"));
                user.setNome(rs.getString("nome"));
                user.setCognome(rs.getString("cognome"));
                user.setDataDiNascita(rs.getDate("data_di_nascita")
                    .toLocalDate());
                user.setSesso(rs.getInt("sesso"));
                if (!b) {
                    Date tmp = rs.getDate("data_sospensione");
                    if (tmp != null) {
                        ((CSU) user).setDataSospensione(tmp.toLocalDate());
                    }
                }
            }
        } finally {
            Database.freeConnection(connection);
        }
        return user;
    }

    /**
     * This method fetches information about all the users stored into
     * the database.
     *
     * @return users is a set of all the users.
     * @throws SQLException is the exception that can be thrown
     *                      during the execution.
     */
    public List<Utente> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement;

        List<Utente> users = new ArrayList<>();
        Utente u;

        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                boolean b = rs.getBoolean("is_gestore");
                if (b) {
                    u = new Gestore();
                } else {
                    u = new CSU();
                }

                u.setId(rs.getInt("Id"));
                u.setUserName(rs.getString("username"));
                u.setPassword(rs.getString("pass"));
                u.setEmail(rs.getString("email"));
                u.setNome(rs.getString("nome"));
                u.setCognome(rs.getString("cognome"));
                u.setDataDiNascita(rs.getDate("data_di_nascita").toLocalDate());
                u.setSesso(rs.getInt("sesso"));
                if (!b) {
                    Date tmp = rs.getDate("data_sospensione");
                    if (tmp != null) {
                        ((CSU) u).setDataSospensione(tmp.toLocalDate());
                    }
                }

                users.add(u);
            }

        } finally {
            Database.freeConnection(connection);
        }

        return users;
    }

    /**
     * This method deletes a user from the database given
     * his email address.
     *
     * @param aEmail is the email address
     * @return res is 0 if the delete operation is not made,
     * otherwise an integer greater than 0.
     * @throws SQLException is the exception that can be thrown
     *                      during the execution.
     */
    public int delete(final String aEmail) throws SQLException {
        Connection connection = null;
        PreparedStatement s;
        int res;

        try {
            connection = Database.getConnection();
            s = connection.prepareStatement(DELETE);
            s.setString(1, aEmail);

            res = s.executeUpdate();

        } finally {
            Database.freeConnection(connection);
        }
        return (res);
    }

    /**
     * This method updates information about a given user.
     * THIS UPDATE ONLY DATA SOSPENSIONE!
     * @param aUtente is the object containing updated information
     *                about the user.
     * @return res is 0 if the update operation is not made,
     * otherwise an integer greater than 0.
     * @throws SQLException is the exception that can be thrown
     *                      during the execution.
     */
    public int update(final Utente aUtente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        CSU csu = (CSU) aUtente;
        int res;

        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            LocalDate dataSospensione = csu.getDataSospensione();
            Date tempDate = null;
            if (dataSospensione != null) {
                Date.valueOf(dataSospensione);
            }
            preparedStatement.setDate(1, tempDate);
            preparedStatement.setInt(2, csu.getId());
            res = preparedStatement.executeUpdate();

        } finally {
            Database.freeConnection(connection);
        }

        return (res);
    }
}
