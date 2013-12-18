package console;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import domaine.Thermomètre;

import java.util.Random;

public class ThermomètreAléatoire implements Thermomètre {

    @Inject
    public ThermomètreAléatoire(@Named("graine") long graine) {
        random = new Random(graine);
    }

    @Override
    public double getTempératureCourante() {
        return arrondisAu10ieme(génèreAléatoirement());
    }

    private double génèreAléatoirement() {
        return (random.nextDouble() * 40) + 10;
    }

    private double arrondisAu10ieme(double prochaineTempérature) {
        return Math.round(prochaineTempérature *10.0)/10.0;
    }

    private final Random random;
}
