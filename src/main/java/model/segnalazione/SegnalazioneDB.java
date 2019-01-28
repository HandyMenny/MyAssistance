/*
Project: MyAssistance
Author: Andrea
Date: 23/12/2018
*/
package model.segnalazione;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.ufficio_tecnico.UfficioTecnico;
import model.utente.CSU;
import model.utente.Utente;
import pool.Database;

/**
 * The Class SegnalazioneDB.
 */
public final class SegnalazioneDB implements SegnalazioneDBInterface {

    /**
     * The Constant TABLE_NAME.
     */
    private static final String TABLE_NAME = "segnalazione";

    /**
     * The Constant INSERT_SEGNALAZIONE.
     */
    private static final String INSERT_SEGNALAZIONE = "INSERT INTO "
            + TABLE_NAME
            + " (titolo,descrizione,stato,data_segnalazione,data_rifiuto,"
            + "data_assegnazione,data_risoluzione,motivazione_rifiuto,"
            + "tipologia, autore, tecnico) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

    /**
     * The Constant UPDATE_SEGNALAZIONE.
     */
    private static final String UPDATE_SEGNALAZIONE = "UPDATE " + TABLE_NAME
            + " SET titolo = ?, descrizione = ?, stato = ?,"
            + " data_segnalazione = ?, data_rifiuto = ?, data_assegnazione = ?,"
            + " data_risoluzione = ?, motivazione_rifiuto = ?, "
            + "tipologia = ?,  autore = ?,  tecnico= ? " + " WHERE cod = ?";

    /**
     * The Constant SELECT_BY_AUTHOR.
     */
    private static final String SELECT_BY_AUTHOR = " SELECT * FROM "
            + TABLE_NAME + " WHERE autore = ?";

    /**
     * The Constant SELECT_BY_ID.
     */
    private static final String SELECT_BY_COD = "SELECT * FROM " + TABLE_NAME
            + " WHERE cod = ?";

    /**
     * The Constant SELECT_ALL.
     */
    private static final String SELECT_ALL = " SELECT * FROM " + TABLE_NAME;

    /**
     * The Constant DELETE_BY_ID.
     */
    private static final String DELETE_BY_COD = "DELETE FROM " + TABLE_NAME
            + " WHERE cod = ?";

    /**
     * Insert.
     *
     * @param aSegnalazione the segnalazione
     * @return the int
     * @throws Exception the exception
     */
    @Override
    public int insert(final Segnalazione aSegnalazione) throws Exception {
        return genericInsertUpdate(INSERT_SEGNALAZIONE, aSegnalazione);
    }

    /**
     * Update.
     *
     * @param aSegnalazione the segnalazione
     * @return the int
     * @throws Exception the SQL exception
     */
    @Override
    public int update(final Segnalazione aSegnalazione) throws Exception {
        return genericInsertUpdate(UPDATE_SEGNALAZIONE, aSegnalazione);
    }

    /**
     * Gets the by autore.
     *
     * @param aAutorId the autor id
     * @return the by autore
     * @throws Exception the exception
     */
    @Override
    public List<Segnalazione> getByAutore(final int aAutorId) throws Exception {
        return genericGet(SELECT_BY_AUTHOR, aAutorId);
    }

    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the SQL exception
     */
    @Override
    public List<Segnalazione> getAll() throws Exception {
        return genericGet(SELECT_ALL, -1);
    }

    /**
     * Gets the by cod.
     *
     * @param aCod the cod
     * @return the by cod
     * @throws Exception the exception
     */
    @Override
    public Segnalazione getByCod(final int aCod) throws Exception {
        final List<Segnalazione> segnalazioneList = genericGet(SELECT_BY_COD,
                aCod);
        if (segnalazioneList.size() > 0) {
            return segnalazioneList.get(0);
        } else {
            return null;
        }
    }

    /**
     * Generic get.
     *
     * @param aQuery     the query
     * @param aParameter the parameter
     * @return the list
     * @throws Exception the exception
     */
    private List<Segnalazione> genericGet(final String aQuery,
            final int aParameter) throws Exception {
        Connection connection = Database.getConnection();

        final List<Segnalazione> segnalazioneList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(aQuery);
            if (!aQuery.equals(SELECT_ALL)) {
                preparedStatement.setInt(1, aParameter);
            }
            final ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                final Segnalazione segnalazione = new Segnalazione();
                segnalazione.setCod(result.getInt("cod"));
                segnalazione.setDataAssegnazione(
                        toLocalDate(result.getDate("data_assegnazione")));
                segnalazione.setDataRifiuto(
                        toLocalDate(result.getDate("data_rifiuto")));
                segnalazione.setDataRisoluzione(
                        toLocalDate(result.getDate("data_risoluzione")));
                segnalazione.setDataSegnalazione(
                        result.getDate("data_segnalazione").toLocalDate());
                segnalazione.setDescrizione(result.getString("descrizione"));
                segnalazione.setMotivazioneRifiuto(
                        result.getString("motivazione_rifiuto"));
                segnalazione.setStato(result.getShort("stato"));

                // Use a fake author
                final Utente autore = new CSU();
                autore.setId(result.getInt("autore"));
                segnalazione.setAutore(autore);

                // Use a fake Tecnico
                final UfficioTecnico tecnico = new UfficioTecnico();
                tecnico.setId(result.getInt("tecnico"));
                if (!result.wasNull()) {
                    segnalazione.setTecnico(tecnico);
                }

                // Use a fake Tipologia
                final Tipologia tipologia = new Tipologia();
                tipologia.setId(result.getInt("tipologia"));
                segnalazione.setTipologia(tipologia);

                segnalazione.setTitolo(result.getString("titolo"));
                segnalazioneList.add(segnalazione);
            }
            return segnalazioneList;
        } finally {
            Database.freeConnection(connection);
        }
    }

    /**
     * Delete by id.
     *
     * @param aId the id
     * @return the int
     * @throws Exception the exception
     */
    @Override
    public int deleteById(final int aId) throws Exception {
        Connection connection = Database.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(DELETE_BY_COD);
            preparedStatement.setInt(1, aId);

            return preparedStatement.executeUpdate();
        } finally {
            Database.freeConnection(connection);
        }
    }

    /**
     * To date.
     *
     * @param date the date
     * @return the date
     */
    private Date toDate(final LocalDate date) {
        if (date != null) {
            return Date.valueOf(date);
        } else {
            return null;
        }
    }

    /**
     * To date.
     *
     * @param date the date
     * @return the date
     */
    private LocalDate toLocalDate(final Date date) {
        if (date != null) {
            return date.toLocalDate();
        } else {
            return null;
        }
    }

    /**
     * Generic insert update.
     *
     * @param aQuery        the query
     * @param aSegnalazione the segnalazione
     * @return the int
     * @throws Exception the SQL exception
     */
    private int genericInsertUpdate(final String aQuery,
            final Segnalazione aSegnalazione) throws Exception {
        Connection connection = Database.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(aQuery);
            int i = 1;

            preparedStatement.setString(i++, aSegnalazione.getTitolo());
            preparedStatement.setString(i++, aSegnalazione.getDescrizione());
            preparedStatement.setShort(i++, aSegnalazione.getStato());
            preparedStatement.setDate(i++,
                    Date.valueOf(aSegnalazione.getDataSegnalazione()));
            preparedStatement.setDate(i++,
                    toDate(aSegnalazione.getDataRifiuto()));
            preparedStatement.setDate(i++,
                    toDate(aSegnalazione.getDataAssegnazione()));
            preparedStatement.setDate(i++,
                    toDate(aSegnalazione.getDataRisoluzione()));
            preparedStatement.setString(i++,
                    aSegnalazione.getMotivazioneRifiuto());

            preparedStatement.setInt(i++, aSegnalazione.getTipologia().getId());

            preparedStatement.setInt(i++, aSegnalazione.getAutore().getId());

            if (aSegnalazione.getTecnico() != null) {
                preparedStatement.setInt(i++,
                        aSegnalazione.getTecnico().getId());
            } else {
                preparedStatement.setNull(i++, Types.INTEGER);
            }
            if (aQuery.equals(UPDATE_SEGNALAZIONE)) {
                preparedStatement.setInt(i, aSegnalazione.getCod());
            }

            return preparedStatement.executeUpdate();
        } finally {
            Database.freeConnection(connection);
        }
    }
}
