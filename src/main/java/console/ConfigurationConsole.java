package console;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import domaine.ServiceTemperatureMaximum;
import domaine.Thermomètre;
import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;
import org.mongolink.Settings;
import org.mongolink.domain.mapper.ContextBuilder;
import persistance.Bulletin;

import java.util.Date;

class ConfigurationConsole extends AbstractModule {
    @Override
    protected void configure() {
        bind(ServiceTemperatureMaximum.class).in(Singleton.class);
        bind(EventBus.class).in(Singleton.class);
        bind(Bulletin.class).asEagerSingleton();
        bind(Thermomètre.class).to(ThermomètreAléatoire.class);
        bindConstant().annotatedWith(Names.named("graine")).to(new Date().getTime());
        bindConstant().annotatedWith(Names.named("période")).to(1000);

    }

    @Provides
    public MongoSession session() {
        MongoSessionManager manager = MongoSessionManager.create(new ContextBuilder("persistance.mapping"), Settings.defaultInstance().withDbName("thermos"));
        MongoSession session = manager.createSession();
        session.start();
        return session;
    }
}
