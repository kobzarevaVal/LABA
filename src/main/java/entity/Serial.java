package entity;

import java.io.Serializable;

public class Serial implements Serializable {

    private String name;
    private int seasonCount;
    private Float rating;

    public Serial(){
        this("",0,0.0f);
    }

    public Serial(String name, int seasonCount, Float rating) {
        this.name = name;
        this.seasonCount = seasonCount;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeasonCount() {
        return seasonCount;
    }

    public void setSeasonCount(int seasonCount) {
        this.seasonCount = seasonCount;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Serial{");
        sb.append("name='").append(name).append('\'');
        sb.append(", seasonCount=").append(seasonCount);
        sb.append(", rating=").append(rating);
        sb.append('}');
        return sb.toString();
    }
}
