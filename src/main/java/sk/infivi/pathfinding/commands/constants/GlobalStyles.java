package sk.infivi.pathfinding.commands.constants;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.Style;

import static net.kyori.adventure.text.Component.empty;
import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.*;
import static net.kyori.adventure.text.format.TextDecoration.BOLD;
import static net.kyori.adventure.text.format.TextDecoration.ITALIC;

public final class GlobalStyles {

    // No instantiation
    private GlobalStyles() {}

    // Styles

    // Chat prompt styles
    public static final Style success = Style.style(GREEN);
    public static final Style fail = Style.style(RED);

    public static final Style chatOptionSetName = Style.style(GREEN);
    public static final Style chatOptionSetValue = Style.style(WHITE);

    public static final Style infoName = Style.style(AQUA);
    public static final Style infoValue = Style.style(DARK_AQUA);

    public static final Style statusInfo = Style.style(GOLD);
    public static final Style quietInfo = Style.style(GRAY);

    // Chat option menu styles
    public static final Style chatTitle = Style.style(GOLD, BOLD);
    public static final Style headerFooter = Style.style(AQUA);

    public static final Style menuOptionName = Style.style(BLUE, BOLD);
    public static final Style menuOptionChoice = Style.style(WHITE, BOLD);
    public static final Style menuOptionSelected = Style.style(GREEN, BOLD);

    public static final Style notSet = Style.style(RED, ITALIC);

    public static final Style hoverPrompt = Style.style(GREEN, BOLD);
    public static final Style hoverText = Style.style(WHITE, ITALIC);

    public static final Style trueStyle = Style.style(GREEN);
    public static final Style falseStyle = Style.style(RED);


    // Constant components
    public static final Component choiceHoverComponent = text("Click to select", hoverPrompt);
    public static final Component tick = text("✔", GREEN, BOLD);
    public static final Component cross = text("✘", RED, BOLD);
    public static final Component space = text("   ")
        .clickEvent(ClickEvent.suggestCommand(""))
        .hoverEvent(HoverEvent.showText(empty()));


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
