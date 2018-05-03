package com.milaboratory.mitoola.ws;

import com.milaboratory.mitoola.api.TestApiService;

/**
 * @author Alexei Zakharov (ayza)
 */
public class TestApiServiceImpl implements TestApiService {
  @Override
  public String hello(String name) {
    return String.format("Hello %s!", name == null ? "anonymous" : name);
  }
}
