package persistance;

import com.google.common.eventbus.EventBus;
import domaine.Releve;
import org.junit.Rule;
import org.junit.Test;
import org.mongolink.test.MongolinkRule;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class BulletinTest {

    @Rule
    public MongolinkRule rule = MongolinkRule.withPackage("persistance.mapping");

    @Test
    public void peutSauvegarderUnRelevé() {
        EventBus eventBus = new EventBus();
        Bulletin bulletin = new Bulletin(eventBus, rule.getCurrentSession());

        eventBus.post(new Releve(23));
        rule.cleanSession();

        List<Releve> releves = rule.getCurrentSession().createCriteria(Releve.class).list();
        assertThat(releves).hasSize(1);
        assertThat(releves.get(0).température).isEqualTo(23);
    }

    @Test
    public void peutDonnerLaListeDesRelevés() {
        Bulletin bulletin = new Bulletin(new EventBus(), rule.getCurrentSession());
        rule.getCurrentSession().save(new Releve(23));
        rule.cleanSession();

        List<Releve> relevés = bulletin.relevés();

        assertThat(relevés).hasSize(1);
    }
}
