package manager;

import model.Serial;

public class SerialManager implements ISerialManager {
    @Override
    public void addSeasons(Serial serial, int count) {
        serial.setSeasonCount(serial.getSeasonCount() + count);
    }

    @Override
    public void changeRating(Serial serial, float newRating) {
        serial.setRating(newRating);
    }
}
