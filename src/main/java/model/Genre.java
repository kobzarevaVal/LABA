package model;

import java.util.ArrayList;

public class Genre {
    private String name;
    private ArrayList<Serial> serials;

    public Genre() {
        this("",new ArrayList<>());
    }

    public Genre(String name, ArrayList<Serial> serials) {
        this.name = name;
        this.serials = serials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Serial> getSerials() {
        return serials;
    }

    public void setSerials(ArrayList<Serial> serials) {
        this.serials = serials;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Genre{");
        sb.append("name='").append(name).append('\'');
        sb.append(", serials=").append(serials);
        sb.append('}');
        return sb.toString();
    }
}
