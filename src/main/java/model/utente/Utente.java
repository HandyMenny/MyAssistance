/*
Project: MyAssistance
Author: Gaetano
Date: 23/12/2018
*/
package model.utente;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The Class Utente.
 */
public abstract class Utente {

    /**
     * The Constant SESSO_ALTRO.
     */
    public static final int SESSO_ALTRO = 0;

    /**
     * The Constant SESSO_MASCHILE.
     */
    public static final int SESSO_MASCHILE = 1;

    /**
     * The Constant SESSO_FEMMINILE.
     */
    public static final int SESSO_FEMMINILE = 2;

    /**
     * l' id.
     */
    private int id;

    /**
     * lo user name.
     */
    private String userName;

    /**
     * la password.
     */
    private String password;

    /**
     * la email.
     */
    private String email;

    /**
     * il nome.
     */
    private String nome;

    /**
     * il cognome.
     */
    private String cognome;

    /**
     * il sesso.
     */
    private int sesso;

    /**
     * Data di nascità.
     */
    private LocalDate dataDiNascita;

    /**
     * Costruttore vuoto.
     */
    public Utente() {
    }

    /**
     * Instantiates a new utente.
     *
     * @param aUserName      the user name
     * @param aPassword      the password
     * @param aEmail         the email
     * @param aNome          the nome
     * @param aCognome       the cognome
     * @param aSesso         the sesso
     * @param aDataDiNascita the data di nascita
     */
    public Utente(final String aUserName, final String aPassword,
            final String aEmail, final String aNome, final String aCognome,
            final int aSesso, final LocalDate aDataDiNascita) {
        userName = aUserName;
        password = aPassword;
        email = aEmail;
        nome = aNome;
        cognome = aCognome;
        sesso = aSesso;
        dataDiNascita = aDataDiNascita;
    }

    /**
     * Effetuare Ovveride.
     *
     * @return Boolean
     */
    public abstract Boolean isGestore();

    /**
     * Gets the id.
     *
     * @return id ottienti l'identificatore dell'utente.
     */
    public final Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param aId imposta il valore id dell'utente.
     */
    public final void setId(final int aId) {
        id = aId;
    }

    /**
     * Ottieni l' username.
     *
     * @return username Ottieni l'username dell'utente.
     */
    public final String getUserName() {
        return userName;
    }

    /**
     * imposta l'username.
     *
     * @param aUserName imposta il valore di username.
     */
    public final void setUserName(final String aUserName) {
        userName = aUserName;
    }

    /**
     * Ottieni the password.
     *
     * @return password ottieni la passoword dell'utente.
     */
    public final String getPassword() {
        return password;
    }

    /**
     * Imposta la password.
     *
     * @param aPassword imposta la password dell'utente.
     */
    public final void setPassword(final String aPassword) {
        password = aPassword;
    }

    /**
     * Ottieni le email.
     *
     * @return email ottieni l'email dell'utente.
     */
    public final String getEmail() {
        return email;
    }

    /**
     * Imposta la email.
     *
     * @param aEmail imposta l'emaio dell'utente.
     */
    public final void setEmail(final String aEmail) {
        email = aEmail;
    }

    /**
     * Ottieni il nome.
     *
     * @return nome ottieni il nome dell'utente.
     */
    public final String getNome() {
        return nome;
    }

    /**
     * Imposta il nome.
     *
     * @param aNome imposta il nome dell'utente.
     */
    public final void setNome(final String aNome) {
        this.nome = aNome;
    }

    /**
     * Ottieni il cognome.
     *
     * @return cognome ottieni il cognome dell'utente.
     */
    public final String getCognome() {
        return cognome;
    }

    /**
     * Imposta il cognome.
     *
     * @param aCognogme imposta il cognome dell'utente.
     */
    public final void setCognome(final String aCognogme) {
        cognome = aCognogme;
    }

    /**
     * Ottieni il sesso dell'utente.
     *
     * @return sesso
     */
    public final int getSesso() {
        return sesso;
    }

    /**
     * Impsti sesso dell'utente.
     *
     * @param aSesso imposta il valore sesso dell'utente.
     */
    public final void setSesso(final int aSesso) {
        sesso = aSesso;
    }

    /**
     * Ottieni la data di nascità dell'utente.
     *
     * @return Date
     */
    public final LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     * Impsti la data di nascità dell'utente.
     *
     * @param aData imposta il valore sesso dell'utente.
     */
    public final void setDataDiNascita(final LocalDate aData) {
        dataDiNascita = aData;
    }

    /**
     * Confronta se due utenti sono identici.
     *
     * @return boolean
     */
    @Override public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Utente utente = (Utente) o;
        return sesso == utente.sesso && Objects.equals(id, utente.id)
                && Objects.equals(userName, utente.userName)
                && Objects.equals(password, utente.password)
                && Objects.equals(email, utente.email)
                && Objects.equals(nome, utente.nome)
                && Objects.equals(cognome, utente.cognome);
    }

    /**
     * Genera un intero per la tabella hash.
     *
     * @return int Utente
     */
    @Override public final int hashCode() {
        return Objects
                .hash(id, userName, password, email, nome, cognome, sesso);
    }

    /**
     * ritorna tutti i valori istanziati.
     *
     * @return String
     */
    @Override public final String toString() {
        return "Utente {" + "id = " + id + ", userName = '" + userName + '\''
            + ", password = " + password + ", email = '" + email + '\''
            + ", nome = '" + nome + '\'' + ", cognome = '" + cognome + '\''
            + "}";
    }

}
