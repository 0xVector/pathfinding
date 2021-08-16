package sk.infivi.pathfinding.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.Style;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import sk.infivi.pathfinding.Manager;
import sk.infivi.pathfinding.algorithms.PathfindingAlgorithmType;
import sk.infivi.pathfinding.graph.Graph;
import sk.infivi.pathfinding.visualization.BlockPlane;
import sk.infivi.pathfinding.visualization.DrawMode;

import static net.kyori.adventure.text.Component.*;
import static net.kyori.adventure.text.format.NamedTextColor.*;
import static net.kyori.adventure.text.format.TextDecoration.BOLD;
import static net.kyori.adventure.text.format.TextDecoration.ITALIC;

public class ChatOptions implements CommandExecutor  {

    // Constant components
    static final Component choiceHoverComponent = text("Click to select", GREEN, BOLD);
    static final Component space = text("   ");
    static final Component tick = text("✔", GREEN, BOLD);
    static final Component cross = text("✘", RED, BOLD);

    // Styles
    final static Style titleStyle = Style.style(GOLD, BOLD);
    final static Style headerFooterStyle = Style.style(AQUA);
    static final Style optionNameStyle = Style.style(BLUE, BOLD);
    static final Style optionChoiceStyle = Style.style(GREEN, BOLD);
    static final Style notSetStyle = Style.style(RED, ITALIC);
    static final Style trueStyle = Style.style(GREEN);
    static final Style falseStyle = Style.style(RED);

    private final Manager manager;

    public ChatOptions(Manager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        final TextComponent.Builder optionsMessage = empty().toBuilder();

        optionsMessage.append(

            // Title
            newline(),
            text("=".repeat(8) + "[ ", headerFooterStyle),
            text("Options", titleStyle),
            text(" ]" + "=".repeat(8), headerFooterStyle),
            newline(),

            // Ready state
            text("Ready: ", optionNameStyle), space,
            text(manager.getReady(), LIGHT_PURPLE, ITALIC),
            newline(), newline(),


            // Algorithm selection
            text("Algorithm: ", optionNameStyle), space,
            getChoices(PathfindingAlgorithmType.values()),
            newline(),


            // Drawing mode selection
            text("Drawing mode: ", optionNameStyle), space,
            getChoices(DrawMode.values()),
            newline(),

            // Block plane selection
            text("Block plane: ", optionNameStyle), space);


        // Block plane status
        final BlockPlane blockPlane = manager.getBlockPlane();
        final TextComponent.Builder blockPlaneComponent = empty().toBuilder();

        if (blockPlane != null) {
            blockPlaneComponent.append(
                    text("Set ", optionChoiceStyle),
                    text(String.format("([%d, %d] to [%d, %d])",
                            blockPlane.xCoord1, blockPlane.zCoord1, blockPlane.xCoord2, blockPlane.zCoord2), GREEN))
            .hoverEvent(HoverEvent.showText(text("Click to change.", GREEN)));

            // Block plane validity check
            Component checkComponent;
            TextComponent.Builder hoverComponent = empty().toBuilder();
            Graph graph = manager.getBlockPlane().getGraph(); // TODO: Performance??

            if (graph.startNode != null && graph.endNode != null) {
                checkComponent = tick;
            } else {
                checkComponent = cross;
            }

            if (graph.startNode != null) {
                hoverComponent.append(text("Start ", trueStyle), tick, space);
            } else {
                hoverComponent.append(text("Start ", falseStyle), cross, space);
            }

            if (graph.endNode != null) {
                hoverComponent.append(text("End ", trueStyle), tick);
            } else {
                hoverComponent.append(text("End ", falseStyle), cross);
            }

            blockPlaneComponent.append(space, checkComponent.hoverEvent(HoverEvent.showText(hoverComponent)));

        } else {
            blockPlaneComponent
                    .append(text("Not set", notSetStyle))
            .hoverEvent(HoverEvent.showText(text("Click to select the block plane.", GREEN)));
        }

        blockPlaneComponent.clickEvent(ClickEvent.suggestCommand("/selectblockplane "));


        optionsMessage.append(

            blockPlaneComponent, newline(),

            // Drawing speed selection
            text("Speed: ", optionNameStyle), space,
            text(manager.getSpeed(), optionChoiceStyle)
                .clickEvent(ClickEvent.suggestCommand("/speed "))
                .hoverEvent(HoverEvent.showText(
                    text("Click to set the drawing speed.", GREEN))),
            newline(), newline(),


            // Start button
            text("Start", DARK_GREEN, BOLD)
                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/startsearch"))
                .hoverEvent(HoverEvent.showText(
                    text("Click to start the search.", GREEN))), space,

            // Items button
            text("Items", GOLD, BOLD)
                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/getitems"))
                .hoverEvent(HoverEvent.showText(text("Click to get the items for building.", YELLOW))), space,

            // Clear button
            text("Clear", DARK_RED, BOLD)
                .clickEvent(ClickEvent.runCommand("/clearplane"))
                .hoverEvent(HoverEvent.showText(text("Click to clear the block plane", RED)
                    .append(newline())
                    .append(text("Doesn't destroy walls or alter your terrain in any way.", RED)))),

            // Footer
            newline(), text("=".repeat(27), headerFooterStyle));

        sender.sendMessage(optionsMessage);
        return true;
    }

    public Component getChoices(MenuOption[] choices) {
        TextComponent.Builder component = Component.empty().toBuilder();

        for (MenuOption choice : choices) {
            component.append(
                getChoiceComponent(
                    choice.getName(),
                    choice.getDescription(),
                    choice.getCommand()), space);
        }

        return component.build();
    }

    private Component getChoiceComponent(String choiceName, String hoverDescription, String command) {
        TextComponent.Builder hoverComponent = Component.empty().toBuilder();
        hoverComponent.append(choiceHoverComponent);

        // Add description (if we have any)
        if (!hoverDescription.isEmpty()) {
            hoverComponent.append(newline(), text(hoverDescription, GREEN));
        }

        return text(choiceName, optionChoiceStyle)
            .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, command))
            .hoverEvent(HoverEvent.showText(hoverComponent));
    }
}
