package console;

import com.google.inject.Inject;
import domaine.ServiceTemperatureMaximum;

public class AfficheurTempératureMaximum implements Runnable{

    @Inject
    public AfficheurTempératureMaximum(ServiceTemperatureMaximum serviceTemperatureMaximum, Logger logger) {
        this.serviceTemperatureMaximum = serviceTemperatureMaximum;
        this.logger = logger;
    }

    @Override
    public void run() {
        logger.log(String.valueOf(serviceTemperatureMaximum.obtiensTempérature()));
    }

    private final ServiceTemperatureMaximum serviceTemperatureMaximum;
    private final Logger logger;
}
