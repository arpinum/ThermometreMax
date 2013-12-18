package console;

import com.google.inject.Guice;
import com.google.inject.Injector;
import domaine.ServiceTemperatureMaximum;
import persistance.Bulletin;

public class Main {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new ConfigurationConsole());
        ServiceTemperatureMaximum service = injector.getInstance(ServiceTemperatureMaximum.class);
        Bulletin bulletin = injector.getInstance(Bulletin.class);
        service.rejoues(bulletin.relevés());
        injector.getInstance(Dashboard.class);
    }

}
