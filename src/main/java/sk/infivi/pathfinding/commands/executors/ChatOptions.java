package sk.infivi.pathfinding.commands.executors;

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
import sk.infivi.pathfinding.commands.MenuOption;
import sk.infivi.pathfinding.commands.constants.GlobalStyles;
import sk.infivi.pathfinding.graph.Graph;
import sk.infivi.pathfinding.visualization.BlockPlane;
import sk.infivi.pathfinding.visualization.DrawMode;

import static net.kyori.adventure.text.Component.*;
import static net.kyori.adventure.text.format.NamedTextColor.*;
import static net.kyori.adventure.text.format.TextDecoration.BOLD;
import static sk.infivi.pathfinding.commands.constants.GlobalStyles.space;
import static sk.infivi.pathfinding.commands.constants.GlobalStyles.*;

public class ChatOptions implements CommandExecutor  {

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
            text("=".repeat(8) + "[ ", headerFooter),
            text("Options", chatTitle)
                .clickEvent(ClickEvent.runCommand("/options"))
                .hoverEvent(HoverEvent.showText(text("Click to refresh.", hoverPrompt))),
            text(" ]" + "=".repeat(8), headerFooter),
            newline());

            // Ready state
            if (manager.getReady()) {
                optionsMessage.append(text("READY!", success.decorate(BOLD)));
            } else {
                optionsMessage.append(text("Not ready", fail.decorate(BOLD)));
            }

        optionsMessage.append(
            newline(), newline(),


            // Algorithm selection
            text("Algorithm: ", menuOptionName), space,
            getChoices(PathfindingAlgorithmType.values(), manager.getAlgorithmType()),
            newline(),


            // Drawing mode selection
            text("Drawing mode: ", menuOptionName), space,
            getChoices(DrawMode.values(), manager.getDrawMode()),
            newline(),

            // Block plane selection
            text("Block plane: ", menuOptionName), space);


        // Block plane status
        final BlockPlane blockPlane = manager.getBlockPlane();
        final TextComponent.Builder blockPlaneComponent = empty().toBuilder();

        if (blockPlane != null) {
            blockPlaneComponent.append(
                    text("Set ", menuOptionChoice),
                    text(String.format("([%d, %d] to [%d, %d])",
                            blockPlane.x1, blockPlane.z1, blockPlane.x2, blockPlane.z2), GREEN))
            .hoverEvent(HoverEvent.showText(text("Click to change.", GREEN)));

            // Block plane validity check
            Component checkComponent;
            TextComponent.Builder hoverComponent = empty().toBuilder();
            Graph graph = manager.getBlockPlane().getGraph();

            if (graph.getStartNode() != null && graph.getEndNode() != null) {
                checkComponent = tick;
            } else {
                checkComponent = cross;
            }

            if (graph.getStartNode() != null) {
                hoverComponent.append(text("Start ", trueStyle), tick, space);
            } else {
                hoverComponent.append(text("Start ", falseStyle), cross, space);
            }

            if (graph.getEndNode() != null) {
                hoverComponent.append(text("End ", trueStyle), tick);
            } else {
                hoverComponent.append(text("End ", falseStyle), cross);
            }

            blockPlaneComponent.append(space, checkComponent.hoverEvent(HoverEvent.showText(hoverComponent)));

        } else {
            blockPlaneComponent
                    .append(text("Not set", notSet))
            .hoverEvent(HoverEvent.showText(text("Click to select the block plane.", hoverPrompt)));
        }

        blockPlaneComponent.clickEvent(ClickEvent.suggestCommand("/selectblockplane "));


        optionsMessage.append(

            blockPlaneComponent, newline(),

            // Drawing speed selection
            text("Speed: ", menuOptionName), space,
            text(manager.getSpeed(), menuOptionChoice)
                .clickEvent(ClickEvent.suggestCommand("/speed "))
                .hoverEvent(HoverEvent.showText(
                    text("Click to set the drawing speed.", hoverPrompt))),
            newline(),


            // Silent status
            text("Silent: ", menuOptionName), space,
            text(manager.isSilent(), GlobalStyles.trueOrFalse(manager.isSilent()))
                .clickEvent(ClickEvent.runCommand("/silent"))
                .hoverEvent(HoverEvent.showText(
                    text("Click to toggle silent status.", hoverPrompt))),
            space, space, space,

            // Refresh status
            text("Refresh mode: ", menuOptionName), space,
            text(manager.getRefresh(), GlobalStyles.trueOrFalse(manager.getRefresh()))
                    .clickEvent(ClickEvent.runCommand("/refresh"))
                    .hoverEvent(HoverEvent.showText(
                            text("Click to toggle refresh mode.", hoverPrompt))),
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
            newline(), text("=".repeat(27), headerFooter));

        sender.sendMessage(optionsMessage);
        return true;
    }

    private Component getChoices(MenuOption[] choices, Enum selected) {
        TextComponent.Builder component = Component.empty().toBuilder();

        for (MenuOption choice : choices) {
            component.append(
                getSingleChoice(
                    choice.getName(),
                    choice.getDescription(),
                    choice.getCommand(), choice == selected), space);
        }

        return component.build();
    }

    private @NotNull Component getSingleChoice(String choiceName, String hoverDescription, String command, boolean selected) {
        TextComponent.Builder hoverComponent = Component.empty().toBuilder();
        hoverComponent.append(choiceHoverComponent);

        // Add description (if we have any)
        if (!hoverDescription.isEmpty()) {
            hoverComponent.append(newline(), text(hoverDescription, hoverText));
        }

        Style style;
        if (selected) {
            style = menuOptionSelected;
        } else {
            style = menuOptionChoice;
        }

        return text(choiceName, style)
            .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, command))
            .hoverEvent(HoverEvent.showText(hoverComponent));
    }
}
