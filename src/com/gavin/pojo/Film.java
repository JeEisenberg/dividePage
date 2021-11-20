package com.gavin.pojo;

import java.io.Serializable;
import java.util.Date;

public class Film implements Serializable {

//    private static final long serialVersionUID = 95594597L;
    //属性
    private   Long film_id;
    private String title;
    private String description;
    private Date release_year;
    private  Long rental_duration ;
    private  Double rental_rate ;
    private Long length;
    private  Double replacement_cost;
    private String rating;
    private String special_features;

    public Film() {
    }
//构造


    public Film(Long film_id, String title, String description, Date release_year, Long rental_duration, Double rental_rate, Long length, Double replacement_cost, String rating, String special_features) {
        this.film_id = film_id;
        this.title = title;
        this.description = description;
        this.release_year = release_year;
        this.rental_duration = rental_duration;
        this.rental_rate = rental_rate;
        this.length = length;
        this.replacement_cost = replacement_cost;
        this.rating = rating;
        this.special_features = special_features;
    }

    public Long getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Long film_id) {
        this.film_id = film_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRelease_year() {
        return release_year;
    }

    public void setRelease_year(Date release_year) {
        this.release_year = release_year;
    }

    public Long getRental_duration() {
        return rental_duration;
    }

    public void setRental_duration(Long rental_duration) {
        this.rental_duration = rental_duration;
    }

    public Double getRental_rate() {
        return rental_rate;
    }

    public void setRental_rate(Double rental_rate) {
        this.rental_rate = rental_rate;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Double getReplacement_cost() {
        return replacement_cost;
    }

    public void setReplacement_cost(Double replacement_cost) {
        this.replacement_cost = replacement_cost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecial_features() {
        return special_features;
    }

    public void setSpecial_features(String special_features) {
        this.special_features = special_features;
    }

    @Override
    public String toString() {
        return "Film{" +
                "film_id=" + film_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", release_year=" + release_year +
                ", rental_duration=" + rental_duration +
                ", rental_rate=" + rental_rate +
                ", length=" + length +
                ", replacement_cost=" + replacement_cost +
                ", rating='" + rating + '\'' +
                ", special_feature='" + special_features + '\'' +
                '}';
    }
}
