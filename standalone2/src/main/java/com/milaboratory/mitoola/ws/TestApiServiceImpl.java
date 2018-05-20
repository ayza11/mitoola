package com.milaboratory.mitoola.ws;

import com.milaboratory.mitoola.api.TestApiService;
import com.milaboratory.mitoola.api.dto.HelloDto;
import com.milaboratory.mitoola.domain.StatefulCounter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.logging.Logger;

/**
 * @author Alexei Zakharov (ayza)
 */
@Singleton
public class TestApiServiceImpl implements TestApiService {
  private static Logger LOG = Logger.getLogger(TestApiServiceImpl.class.getName());

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
    return new HelloDto(name, counter.incrementAndGet());
  }
}
