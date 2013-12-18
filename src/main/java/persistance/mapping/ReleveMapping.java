package persistance.mapping;

import domaine.Releve;
import org.mongolink.domain.mapper.AggregateMap;

public class ReleveMapping extends AggregateMap<Releve> {

    public ReleveMapping() {
        super(Releve.class);
    }

    @Override
    public void map() {
        id().onProperty(element().getId()).auto();
        property().onField("température");
    }
}
