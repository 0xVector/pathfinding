package sk.infivi.pathfinding.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import sk.infivi.pathfinding.Manager;
import sk.infivi.pathfinding.algorithms.PathfindingAlgorithm;
import sk.infivi.pathfinding.algorithms.PathfindingAlgorithmType;
import sk.infivi.pathfinding.visualization.BlockPlane;

import static net.kyori.adventure.text.Component.newline;
import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.*;
import static net.kyori.adventure.text.format.TextDecoration.BOLD;
import static net.kyori.adventure.text.format.TextDecoration.ITALIC;

public class Options implements CommandExecutor  {

    private final Manager manager;

    // Constants
    final Component hoverSelect = text("Click to select", GREEN, BOLD);
    final Component space = text("   ");

    final Style titleStyle = Style.style(NamedTextColor.BLUE, BOLD);
    final Style textStyle = Style.style(NamedTextColor.WHITE, BOLD);
    final Style textEmphasisStyle = Style.style(NamedTextColor.AQUA, TextDecoration.ITALIC);

    final Style optionName = Style.style(BLUE, BOLD);
    final Style optionChoice = Style.style(GREEN, BOLD);

    public Options(Manager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            // Algorithms
            TextComponent.Builder algorithmsComponent = Component.empty().toBuilder();
            for (PathfindingAlgorithmType algorithm : PathfindingAlgorithmType.values()) {
                algorithmsComponent.append(
                getChoiceComponent(algorithm.name, "/setalgorithm " + algorithm.name, algorithm.description), space);
            }


            // Block plane
            final BlockPlane blockPlane = manager.getBlockPlane();
            final TextComponent.Builder blockPlaneComponent = text("Block plane: ").style(optionName)
                                                                .append(space).toBuilder();

            if (blockPlane != null) {
                blockPlaneComponent.append(text(String.format("[%d, %d] to [%d, %d]",
                        blockPlane.xCoord1, blockPlane.zCoord1, blockPlane.xCoord2, blockPlane.zCoord2), optionChoice));
            } else {
                blockPlaneComponent.append(text("not set", RED, ITALIC));
            }

            blockPlaneComponent
                    .clickEvent(ClickEvent.suggestCommand("/selectblockplane "))
                    .hoverEvent(HoverEvent.showText(text("Click to select the block plane.").color(GREEN)));


            final Component optionsMessage =
                    newline()
                    .append(text("========[ ", AQUA))
                    .append(text("Options", GOLD, BOLD))
                    .append(text(" ]========", AQUA))
                    .append(newline())


                    .append(text("Ready: ", AQUA, BOLD)).append(space)
                            .append(text(manager.getReady(), LIGHT_PURPLE, ITALIC))
                    .append(newline()).append(newline())


                    .append(text("Algorithm: ", optionName)).append(space)
                    .append(algorithmsComponent)
                    .append(newline())


                    .append(text("Drawing mode: ", optionName)).append(space)
                    .append(getChoiceComponent("Path-only", "/mode path", "Draw only the final path.")).append(space)
                    .append(getChoiceComponent("Visits-only", "/mode visits", "Draw only visits the algorithm makes.")).append(space)
                    .append(getChoiceComponent("All", "/mode all", "Draw everything."))
                    .append(newline())


                    .append(blockPlaneComponent)
                    .append(newline())


                    .append(text("Speed: ", optionName)).append(space)
                    .append(text(manager.getSpeed(), optionChoice).clickEvent(ClickEvent.suggestCommand("/speed "))
                    .hoverEvent(HoverEvent.showText(text("Click to set the drawing speed.").color(GREEN))))
                    .append(newline()).append(newline())


                    .append(text("Start", DARK_GREEN, BOLD)
                            .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/startsearch"))
                            .hoverEvent(HoverEvent.showText(
                                    text("Click to start the search.", GREEN)))
                            .append(space)

                    .append(text("Items", GOLD, BOLD)
                            .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/getitems"))
                            .hoverEvent(HoverEvent.showText(text("Click to get the items for building.", YELLOW))))
                            .append(space)

                    .append(text("Clear", DARK_RED, BOLD)
                            .clickEvent(ClickEvent.runCommand("/clearplane"))
                            .hoverEvent(HoverEvent.showText(
                                    text("Click to clear the block plane", RED)
                                            .append(newline())
                                            .append(text("Doesn't destroy walls or alter your terrain in any way.", RED))))))

                    .append(newline())
                    .append(text("===========================", AQUA));

            player.sendMessage(optionsMessage);
            return true;
        }
        return false;
    }

    private Component getChoiceComponent(String choiceText, String command, String hoverDescription) {
        TextComponent.Builder hoverComponent = Component.empty().toBuilder();
        hoverComponent.append(hoverSelect);
        if (!hoverDescription.isEmpty()) {
            hoverComponent.append(newline(), text(hoverDescription, GREEN));
        }

        return text(choiceText, optionChoice)
                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, command))
                .hoverEvent(HoverEvent.showText(hoverComponent));
    }
}
