package se.popcorn_time.base.model.video.info;

import android.text.TextUtils;

import com.player.utils.StringUtil;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class VideoInfo {

    private final String type;

    private int tmdbID = 0;

    private String imdb;
    private String title;
    private int year;
    private float rating;
    private int durationMinutes;
    private String description;
    private String actors;

    private String poster;
    private String posterBig;
    private String trailer;

    private String[] genres;
    private String[] backdrops;

    //TODO: imlement imdb info in the app, backend is ready
    //IMDB info
    private long budgetInUSD;
    private long revenueInUSD;
    private String homepage;
    private List<String> productionCompanies;
    private List<String> productionCountries;
    private List<Person.Actor> cast;
    private List<Person.CrewMember> crew;
    private List<BasicVideoInfo> recommendedMOVIESorTVShows;

    @Override
    public String toString() {
        String result = "VideoInfo {" +
                getBudgetInUSD() + " - budget\n" +
                getActors() + " - actors\n" +
                TextUtils.join(", ", getBackdrops()) + " - backdrops\n";
        result += "cast: "+cast.size();
        for (Person.Actor actor: getCast()) {
            result += actor.toString();
        }
        result += "cast\n";
        for (Person.CrewMember actor: getCrew()) {
            result += actor.toString();
        }
        result += "crew\n";
                result += TextUtils.join(", ", getGenres()) + " - genres\n" +
                TextUtils.join(", ", getProductionCompanies()) + " - companies\n" +
                TextUtils.join(", ", getProductionCountries()) + " - countries\n";
        for (BasicVideoInfo actor: getRecommendedMOVIESorTVShows()) {
            result += actor.toString();
        }
        result += "recommended\n";
                result += getHomepage() + " - homepage\n" +
                getRevenueInUSD() + " - revenue\n";
        return result;
    }

    public long getBudgetInUSD() {
        return budgetInUSD;
    }

    public void setBudgetInUSD(long budgetInUSD) {
        this.budgetInUSD = budgetInUSD;
    }

    public int getTmdbID() {
        return tmdbID;
    }

    public void setTmdbID(int tmdbID) {
        this.tmdbID = tmdbID;
    }

    public long getRevenueInUSD() {
        return revenueInUSD;
    }

    public void setRevenueInUSD(long revenueInUSD) {
        this.revenueInUSD = revenueInUSD;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public List<String> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<String> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<String> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<String> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public List<Person.Actor> getCast() {
        return cast;
    }

    public void setCast(List<Person.Actor> cast) {
        this.cast = cast;
    }

    public List<Person.CrewMember> getCrew() {
        return crew;
    }

    public void setCrew(List<Person.CrewMember> crew) {
        this.crew = crew;
    }

    public List<BasicVideoInfo> getRecommendedMOVIESorTVShows() {
        return recommendedMOVIESorTVShows;
    }

    public void setRecommendedMOVIESorTVShows(List<BasicVideoInfo> recommendedMOVIESorTVShows) {
        this.recommendedMOVIESorTVShows = recommendedMOVIESorTVShows;
    }



    public VideoInfo(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPosterBig() {
        return posterBig;
    }

    public void setPosterBig(String posterBig) {
        this.posterBig = posterBig;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String[] getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(String[] backdrops) {
        this.backdrops = backdrops;
    }

}