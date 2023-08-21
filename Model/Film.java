package Model;

import java.util.Date;


/**
 * Represents a film with various attributes such as ID, type, title, director,
 * etc.
 */

public class Film {
    private int id;
    private String type;
    private String title;
    private String director;
    private String cast;
    private String country;
    private Date dateAdded;
    private String rating;
    private String description;

    public Film() {
        // Default constructor with no parameters
    }

    /**
     * Constructor for the Film class with essential fields.
     *
     * @param id        The ID of the film.
     * @param title     The title of the film.
     * @param director  The director of the film.
     * @param country   The country of the film.
     * @param dateAdded The date when the film was added.
     */
    public Film(int id, String title, String director, String country, Date dateAdded) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.country = country;
        this.dateAdded = dateAdded;
    }

    /**
     * Constructor for the Film class with all available fields.
     *
     * @param id          The ID of the film.
     * @param type        The type of the film.
     * @param title       The title of the film.
     * @param director    The director of the film.
     * @param cast        The cast of the film.
     * @param country     The country of the film.
     * @param dateAdded   The date when the film was added.
     * @param rating      The rating of the film.
     * @param description The description of the film.
     */
    public Film(int id, String type, String title, String director, String cast, String country,
            Date dateAdded, String rating, String description) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.country = country;
        this.dateAdded = dateAdded;
        this.rating = rating;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the film.
     *
     * @param id The ID to set for the film.
     */
    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    /**
     * Sets the type of the film.
     *
     * @param type The type to set for the film.
     */
    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the film.
     *
     * @param title The title to set for the film.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    /**
     * Sets the director of the film.
     *
     * @param director The director to set for the film.
     */
    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    /**
     * Sets the cast of the film.
     *
     * @param cast The cast to set for the film.
     */
    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of the film.
     *
     * @param country The country to set for the film.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    /**
     * Sets the date when the film was added.
     *
     * @param dateAdded The date to set for when the film was added.
     */
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getRating() {
        return rating;
    }

    /**
     * Sets the rating of the film.
     *
     * @param rating The rating to set for the film.
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the film.
     *
     * @param description The description to set for the film.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
