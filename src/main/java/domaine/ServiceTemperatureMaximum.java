package domaine;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

import java.util.List;

public class ServiceTemperatureMaximum {

    @Inject
    public ServiceTemperatureMaximum(Thermomètre thermomètre, EventBus eventBus) {
        this.thermomètre = thermomètre;
        this.eventBus = eventBus;
    }

    public void rejoues(List<Releve> relevés) {
        for (Releve relevé : relevés) {
            metAJourTempérature(relevé.température);
        }

    }

    public double obtiensTempérature() {
        metsAJourTempératureMax();
        return températureMax;
    }

    private void metsAJourTempératureMax() {
        double températureCourante = thermomètre.getTempératureCourante();
        eventBus.post(new Releve(températureCourante));
        metAJourTempérature(températureCourante);
    }

    private void metAJourTempérature(double températureCourante) {
        if(températureCourante > températureMax) {
            températureMax = températureCourante;
        }
    }

    private final Thermomètre thermomètre;
    private final EventBus eventBus;
    private double températureMax;
}
