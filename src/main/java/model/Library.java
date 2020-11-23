package model;

import java.util.ArrayList;

public class Library {
    private String title;
    private ArrayList<Genre> genres;

    public Library() {
        this("",new ArrayList<>());
    }

    public Library(String title, ArrayList<Genre> genres) {
        this.title = title;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Library{");
        sb.append("title='").append(title).append('\'');
        sb.append(", genres=").append(genres);
        sb.append('}');
        return sb.toString();
    }
}
