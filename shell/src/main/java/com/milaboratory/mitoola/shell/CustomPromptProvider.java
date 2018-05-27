package com.milaboratory.mitoola.shell;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

/**
 * @author Alexei Zakharov (ayza)
 */
@Component
public class CustomPromptProvider implements PromptProvider {
    @Override
    public AttributedString getPrompt() {
        return new AttributedString("mitoola>", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }
}
