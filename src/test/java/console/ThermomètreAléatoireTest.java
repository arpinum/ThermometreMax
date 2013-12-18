package console;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class ThermomètreAléatoireTest {

    @Before
    public void setUp() throws Exception {
        thermomètre = new ThermomètreAléatoire(1);
    }

    @Test
    public void peutDonnerUnNombreAléatoire() {
        double premièreValeur = thermomètre.getTempératureCourante();
        double deuxièmeValeur = thermomètre.getTempératureCourante();
        double troisièmeValeur = thermomètre.getTempératureCourante();

        assertThat(premièreValeur).isNotEqualTo(deuxièmeValeur);
        assertThat(deuxièmeValeur).isNotEqualTo(troisièmeValeur);
    }

    @Test
    public void laValeurEstSupérieureA1() {
        double températureCourante = thermomètre.getTempératureCourante();

        assertThat(températureCourante).isGreaterThan(10).isLessThan(100);
    }

    @Test
    public void laValeurEstArrondie() {
        double températureCourante = thermomètre.getTempératureCourante();


        assertThat(températureCourante).isEqualTo(39.2);
    }

    private ThermomètreAléatoire thermomètre;
}
