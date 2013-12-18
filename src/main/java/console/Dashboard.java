package console;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Dashboard {

    @Inject
    public Dashboard(AfficheurTempératureMaximum afficheur, @Named("période") int période) {
        pool.scheduleAtFixedRate(afficheur, 0, période, TimeUnit.MILLISECONDS);
    }

    ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
}
