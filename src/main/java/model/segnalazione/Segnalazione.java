/*
Project: MyAssistance
Author: Andrea
Date: 23/12/2018
*/
package model.segnalazione;

import java.time.LocalDate;
import java.util.Objects;

import model.ufficio_tecnico.UfficioTecnico;
import model.utente.Utente;

/**
 * The Class Segnalazione.
 */
public final class Segnalazione {

    /**
     * The cod.
     */
    private int cod;

    /**
     * The titolo.
     */
    private String titolo;

    /**
     * The descrizione.
     */
    private String descrizione;

    /**
     * The stato.
     */
    private short stato;

    /**
     * The data segnalazione.
     */
    private LocalDate dataSegnalazione;

    /**
     * The data rifiuto.
     */
    private LocalDate dataRifiuto;

    /**
     * The data assegnazione.
     */
    private LocalDate dataAssegnazione;

    /**
     * The data risoluzione.
     */
    private LocalDate dataRisoluzione;

    /**
     * The motivazione rifiuto.
     */
    private String motivazioneRifiuto;

    /**
     * The tipologia.
     */
    private Tipologia tipologia;

    /**
     * The autore.
     */
    private Utente autore;

    /**
     * The tecnico.
     */
    private UfficioTecnico tecnico;

    /**
     * The Constant STATO_APERTO.
     */
    public static final short STATO_APERTO = 0;

    /**
     * The Constant STATO_RIFIUTATO.
     */
    public static final short STATO_RIFIUTATO = -1;

    /**
     * The Constant STATO_ASSEGNATO.
     */
    public static final short STATO_ASSEGNATO = 1;

    /**
     * The Constant STATO_RISOLTO.
     */
    public static final short STATO_RISOLTO = 2;

    /**
     * Instantiates a new segnalazione.
     */
    public Segnalazione() {
    }

    /**
     * Gets the cod.
     *
     * @return the cod
     */
    public int getCod() {
        return cod;
    }

    /**
     * Sets the cod.
     *
     * @param aCod the cod to set
     */
    public void setCod(final int aCod) {
        cod = aCod;
    }

    /**
     * Gets the titolo.
     *
     * @return the titolo
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Sets the titolo.
     *
     * @param aTitolo the titolo to set
     */
    public void setTitolo(final String aTitolo) {
        titolo = aTitolo;
    }

    /**
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * @param aDescrizione the descrizione to set
     */
    public void setDescrizione(final String aDescrizione) {
        descrizione = aDescrizione;
    }

    /**
     * Gets the stato.
     *
     * @return the stato
     */
    public short getStato() {
        return stato;
    }

    /**
     * Sets the stato.
     *
     * @param aStato the stato to set
     */
    public void setStato(final short aStato) {
        stato = aStato;
    }

    /**
     * Gets the data segnalazione.
     *
     * @return the dataSegnalazione
     */
    public LocalDate getDataSegnalazione() {
        return dataSegnalazione;
    }

    /**
     * Sets the data segnalazione.
     *
     * @param aDataSegnalazione the dataSegnalazione to set
     */
    public void setDataSegnalazione(final LocalDate aDataSegnalazione) {
        dataSegnalazione = aDataSegnalazione;
    }

    /**
     * Gets the data rifiuto.
     *
     * @return the dataRifiuto
     */
    public LocalDate getDataRifiuto() {
        return dataRifiuto;
    }

    /**
     * Sets the data rifiuto.
     *
     * @param aDataRifiuto the dataRifiuto to set
     */
    public void setDataRifiuto(final LocalDate aDataRifiuto) {
        dataRifiuto = aDataRifiuto;
    }

    /**
     * @return the dataAssegnazione
     */
    public LocalDate getDataAssegnazione() {
        return dataAssegnazione;
    }

    /**
     * @param aDataAssegnazione the dataAssegnazione to set
     */
    public void setDataAssegnazione(final LocalDate aDataAssegnazione) {
        dataAssegnazione = aDataAssegnazione;
    }

    /**
     * Gets the data risoluzione.
     *
     * @return the dataRisoluzione
     */
    public LocalDate getDataRisoluzione() {
        return dataRisoluzione;
    }

    /**
     * Sets the data risoluzione.
     *
     * @param aDataRisoluzione the dataRisoluzione to set
     */
    public void setDataRisoluzione(final LocalDate aDataRisoluzione) {
        dataRisoluzione = aDataRisoluzione;
    }

    /**
     * Gets the motivazione rifiuto.
     *
     * @return the motivazioneRifiuto
     */
    public String getMotivazioneRifiuto() {
        return motivazioneRifiuto;
    }

    /**
     * Sets the motivazione rifiuto.
     *
     * @param aMotivazioneRifiuto the motivazioneRifiuto to set
     */
    public void setMotivazioneRifiuto(final String aMotivazioneRifiuto) {
        motivazioneRifiuto = aMotivazioneRifiuto;
    }

    /**
     * Gets the tipologia.
     *
     * @return the tipologia
     */
    public Tipologia getTipologia() {
        return tipologia;
    }

    /**
     * Sets the tipologia.
     *
     * @param aTipologia the tipologia to set
     */
    public void setTipologia(final Tipologia aTipologia) {
        tipologia = aTipologia;
    }

    /**
     * Gets the autore.
     *
     * @return the autore
     */
    public Utente getAutore() {
        return autore;
    }

    /**
     * Sets the autore.
     *
     * @param aAutore the autore to set
     */
    public void setAutore(final Utente aAutore) {
        autore = aAutore;
    }

    /**
     * Gets the tecnico.
     *
     * @return the tecnico
     */
    public UfficioTecnico getTecnico() {
        return tecnico;
    }

    /**
     * Sets the tecnico.
     *
     * @param aTecnico the tecnico to set
     */
    public void setTecnico(final UfficioTecnico aTecnico) {
        tecnico = aTecnico;
    }

    /**
     * Hash code.
     *
     * @return the hash
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(autore,
                dataAssegnazione,
                dataRifiuto,
                dataRisoluzione,
                dataSegnalazione,
                descrizione,
                motivazioneRifiuto,
                tecnico,
                tipologia,
                titolo);
    }

    /**
     * Equals.
     *
     * @param obj the obj
     * @return true, if successful
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Segnalazione)) {
            return false;
        }
        final Segnalazione other = (Segnalazione) obj;

        // if cod or other.cod is 0 do not compare them
        return (cod == 0 || other.cod == 0 || cod == other.cod)
                && stato == other.stato && Objects.equals(autore, other.autore)
                && Objects.equals(dataAssegnazione, other.dataAssegnazione)
                && Objects.equals(dataRifiuto, other.dataRifiuto)
                && Objects.equals(dataRisoluzione, other.dataRisoluzione)
                && Objects.equals(dataSegnalazione, other.dataSegnalazione)
                && Objects.equals(descrizione, other.descrizione)
                && Objects.equals(motivazioneRifiuto, other.motivazioneRifiuto)
                && Objects.equals(tecnico, other.tecnico)
                && Objects.equals(tipologia, other.tipologia)
                && Objects.equals(titolo, other.titolo);
    }

    /**
     * To string.
     *
     * @return the string
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Segnalazione [cod=" + cod + ", titolo=" + titolo
                + ", descrizione=" + descrizione + ", stato=" + stato
                + ", dataSegnalazione=" + dataSegnalazione + ", dataRifiuto="
                + dataRifiuto + ", dataAssegnazione=" + dataAssegnazione
                + ", dataRisoluzione=" + dataRisoluzione
                + ", motivazioneRifiuto=" + motivazioneRifiuto + ", tipologia="
                + tipologia + ", autore=" + autore + ", tecnico=" + tecnico
                + "]";
    }

}
