package domaine;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

public class ServiceTemperatureMaximumTest {

    @Before
    public void setUp() throws Exception {
        eventBus = new EventBus();
    }

    @Test
    public void peutObtenirUneTempérature() {
        laTempératureCouranteEst(25);

        double température = service.obtiensTempérature();

        assertThat(température).isEqualTo(25);
    }

    @Test
    public void peutObtenirUneTempératureSiElleVarie() {
        laTempératureCouranteEst(24);

        double température = service.obtiensTempérature();

        assertThat(température).isEqualTo(24);
    }

    private void laTempératureCouranteEst(double températureCourante) {
        thermommètre = unThermomètre();
        when(thermommètre.getTempératureCourante()).thenReturn(températureCourante);
        service = new ServiceTemperatureMaximum(thermommètre, eventBus);
    }

    @Test
    public void siLaTempératureBaisseLaTempératureMaxEstLaBonne() {
        lesTempératuresRelevéesSeront(24, 20);

        service.obtiensTempérature();
        double températureMax = service.obtiensTempérature();

        assertThat(températureMax).isEqualTo(24);
    }

    @Test
    public void peutEmettreUnRelevé() {
        FakeHandlerEvenement handler = étantDonnéUnHandler();
        laTempératureCouranteEst(23.0);

        service.obtiensTempérature();

        assertThat(handler.releve).isNotNull();
        assertThat(handler.releve.température).isEqualTo(23.0);
    }

    @Test
    public void peutRejouerLesRelevés() {
        ServiceTemperatureMaximum service = new ServiceTemperatureMaximum(unThermomètre(), eventBus);
        List<Releve> relevés = Lists.newArrayList(new Releve(23), new Releve(25));

        service.rejoues(relevés);

        assertThat(service.obtiensTempérature()).isEqualTo(25);
    }

    private Thermomètre unThermomètre() {
        return mock(Thermomètre.class);
    }

    private FakeHandlerEvenement étantDonnéUnHandler() {
        FakeHandlerEvenement handler = new FakeHandlerEvenement();
        eventBus.register(handler);
        return handler;
    }

    private void lesTempératuresRelevéesSeront(double première, double deuxième) {
        Thermomètre mock = unThermomètre();
        when(mock.getTempératureCourante()).thenReturn(première, deuxième);
        service = new ServiceTemperatureMaximum(mock, new EventBus());
    }

    private ServiceTemperatureMaximum service;
    private Thermomètre thermommètre;
    private EventBus eventBus;

    public static class FakeHandlerEvenement {

        @Subscribe
        public void réagis(Releve releve) {

            this.releve = releve;
        }

        private Releve releve;
    }
}
