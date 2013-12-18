package persistance;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import domaine.Releve;
import org.mongolink.MongoSession;

import java.util.List;

public class Bulletin {

    @Inject
    public Bulletin(EventBus eventBus, MongoSession currentSession) {
        this.currentSession = currentSession;
        eventBus.register(this);
    }

    @Subscribe
    public void quandRelevé(Releve releve) {
        currentSession.save(releve);
        currentSession.flush();
    }

    public List<Releve> relevés() {
        return currentSession.createCriteria(Releve.class).list();
    }

    private final MongoSession currentSession;
}
