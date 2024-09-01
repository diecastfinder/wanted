package org.diecastfinder.wanted.services.crawler;

import java.util.List;
import org.diecastfinder.model.FoundModelDto;
import org.diecastfinder.model.WantedModelDto;

public interface CrawlerService {

    List<FoundModelDto> findModel(WantedModelDto wanted);

}
