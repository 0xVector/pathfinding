package sk.infivi.pathfinding.commands.constants;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.*;
import static net.kyori.adventure.text.format.TextDecoration.*;

public final class GlobalStyles {

    // No instantiation
    private GlobalStyles() {}

    // Constant components
    public static final Component choiceHoverComponent = text("Click to select", GREEN, BOLD);
    public static final Component space = text("   ");
    public static final Component tick = text("✔", GREEN, BOLD);
    public static final Component cross = text("✘", RED, BOLD);


    // Styles

    // Chat prompt styles
    public static final Style success = Style.style(GREEN);
    public static final Style fail = Style.style(RED);

    public static final Style chatOptionSetName = Style.style(GREEN);
    public static final Style chatOptionSetValue = Style.style(WHITE);

    public static final Style infoName = Style.style(AQUA);
    public static final Style infoValue = Style.style(DARK_AQUA);

    public static final Style statusInfo = Style.style(GOLD);
    public static final Style silentInfo = Style.style(GRAY);

    // Chat option menu styles
    public static final Style chatTitle = Style.style(GOLD, BOLD);
    public static final Style headerFooter = Style.style(AQUA);

    public static final Style menuOptionName = Style.style(BLUE, BOLD);
    public static final Style menuOptionChoice = Style.style(GREEN, BOLD);

    public static final Style notSet = Style.style(RED, ITALIC);

    public static final Style trueStyle = Style.style(GREEN);
    public static final Style falseStyle = Style.style(RED);


    public static Component getOptionSetComponent(String name, String value) {
        return text(name, GlobalStyles.chatOptionSetName).append(
               text(value, GlobalStyles.chatOptionSetValue));
    }

    public static Style trueOrFalse(boolean value) {
        if (value) {
            return trueStyle;
        }
        return falseStyle;
    }
}
