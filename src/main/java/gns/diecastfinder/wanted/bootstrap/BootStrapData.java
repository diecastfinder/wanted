package gns.diecastfinder.wanted.bootstrap;

import gns.diecastfinder.wanted.domain.WantedModel;
import gns.diecastfinder.wanted.repositories.WantedModelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final WantedModelRepository repository;

    public BootStrapData(WantedModelRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        WantedModel wm = WantedModel.builder()
                .name("Mercedes W196R stromliner")
                .producer("CMC")
                .maxPrice(2000)
                .minPrice(400)
                .currency("PLN")
                .build();

        repository.save(wm);

        WantedModel wm2 = WantedModel.builder()
                .name("Mercedes W196R stromliner")
                .producer("GT Replicas")
                .maxPrice(1000)
                .minPrice(250)
                .currency("PLN")
                .build();

        repository.save(wm2);

        System.out.println("Started in bootstrap");
        System.out.println("Number of models: " + repository.count());
    }
}
