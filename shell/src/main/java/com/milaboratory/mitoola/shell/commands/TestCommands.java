package com.milaboratory.mitoola.shell.commands;

/**
 * @author Alexei Zakharov (ayza)
 */

import com.milaboratory.mitoola.api.TestApiService;
import com.milaboratory.mitoola.util.json.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;


import static java.lang.System.out;

@ShellComponent
public class TestCommands {

    private final TestApiService testService;

    @Autowired
    public TestCommands(TestApiService testApiService) {
        this.testService = testApiService;
    }

    @ShellMethod(value = "Prints valuable wisdom.", key = "test wisdom")
    public String wisdom1() {
        return "This too will pass.";
    }

    @ShellMethod(value = "Runs 'hello' test", key = "test hello")
    public String hello(String name) {
        return JsonUtils.write(testService.hello(name));
    }

}
