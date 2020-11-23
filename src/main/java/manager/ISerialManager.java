package manager;

import model.Serial;

public interface ISerialManager {
    public void addSeasons(Serial serial, int count);
    public void changeRating(Serial serial, float newRating);
}
