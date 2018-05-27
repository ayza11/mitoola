package com.milaboratory.mitoola.ws;

import com.milaboratory.mitoola.api.TestApiService;
import com.milaboratory.mitoola.api.dto.HelloDto;
import com.milaboratory.mitoola.domain.StatefulCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

/**
 * @author Alexei Zakharov (ayza)
 */
@Singleton
public class TestApiServiceImpl implements TestApiService {
  private static Logger LOG = LoggerFactory.getLogger(TestApiServiceImpl.class);

  private final StatefulCounter counter;

  @Autowired
  public TestApiServiceImpl(StatefulCounter counter) {
    this.counter = counter;
  }

  @PostConstruct
  public void init() {
    LOG.info("TestApiServiceImpl initialized");
  }

  @Override
  public HelloDto hello(String name) {
    LOG.debug("Invoking hello...");
    return new HelloDto(name, counter.incrementAndGet());
  }
}
