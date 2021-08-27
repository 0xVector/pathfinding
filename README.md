# Pathfinding visualization in Minecraft
Pathfinding is a cool concept that is best visualized on grids,
so that's why I decided to implement it in Minecraft. 

This is a Minecraft plugin using the [Paper API](https://github.com/PaperMC/Paper "PaperMC") which visualizes
different pathfinding algorithms on a 2D surface (for now).
Developed and tested in Minecraft 1.17, may work in older versions.

It is my free-time project, and it's work in progress.

## How to run it
You can either try to build it on your own with Maven, or wait.
Soon, I'll make releases with pre-compiled jars.

To build with Maven, run `mvn clean package`. The jar file will be in the `target` directory.

When you have the jar file, just drop it in the `plugins` folder
of your Paper server.

## Commands
**/help Pathfinding** - view in-game help with all the commands.

### Setting up
**/pathfinding** - interactive, clickable and easy to use chat menu with all options and controls.

**/algorithm** - set the algorithm used to pathfind.

**/selectblockplane <y-level> <x1> <z1> <x2> <z2>**- select the
plane corner coordinates where the pathfinding will take place.

**/mode** - set the drawing mode. You can choose from `path`, `visits` and `all`.

### General commands
**/start** - start the search and drawing.

**/speed** - tweak the drawing speed (1 - 10). Default is 10 (20 blocks per second).

**/clear** - clear the plane. It is also cleared automatically before each search.

**/getitems** - get the blocks which you can use to build your own walls, mazes etc.