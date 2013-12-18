package console;

import domaine.ServiceTemperatureMaximum;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class AfficheurTempératureMaximumTest {

    @Test
    public void peutAfficherLaTempératureMax() throws Exception {
        ServiceTemperatureMaximum service = mock(ServiceTemperatureMaximum.class);
        Logger logger = mock(Logger.class);
        when(service.obtiensTempérature()).thenReturn(30.1);
        AfficheurTempératureMaximum afficheur = new AfficheurTempératureMaximum(service, logger);

        afficheur.run();

        verify(logger).log("30.1");
    }
}
