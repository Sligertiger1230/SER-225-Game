package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.TileType;
import Level.Tileset;

import java.util.ArrayList;

// This class represents a "common" tileset of standard tiles defined in the CommonTileset.png file
public class CommonTileset extends Tileset {

        public CommonTileset() {
                super(ImageLoader.load("CommonTileset.png"), 16, 16, 3);
        }

        @Override
        public ArrayList<MapTileBuilder> defineTiles() {
                ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

                // grass
                Frame grassFrame = new FrameBuilder(getSubImage(0, 0))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder grassTile = new MapTileBuilder(grassFrame);

                mapTiles.add(grassTile);

                // grass pieces
                Frame grassPiecesFrame = new FrameBuilder(getSubImage(3, 1))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder grassPiecesTile = new MapTileBuilder(grassPiecesFrame);

                mapTiles.add(grassPiecesTile);

                // horizontal brick
                Frame horizontalBrickFrame = new FrameBuilder(getSubImage(11, 3))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder horizontalBrickTile = new MapTileBuilder(horizontalBrickFrame);

                mapTiles.add(horizontalBrickTile);

                // grass with white flowers
                Frame grassFlowerFrame = new FrameBuilder(getSubImage(0, 2))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder grassFlowerTile = new MapTileBuilder(grassFlowerFrame);

                mapTiles.add(grassFlowerTile);

                // tree trunk base
                Frame treeTrunkBaseFrame = new FrameBuilder(getSubImage(5, 3))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder treeTrunkBaseTile = new MapTileBuilder(treeTrunkBaseFrame)
                                .withTopLayer(treeTrunkBaseFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(treeTrunkBaseTile);

                // tree trunk
                Frame treeTrunkFrame = new FrameBuilder(getSubImage(4, 0))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder treeTrunkTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(treeTrunkFrame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(treeTrunkTile);

                // tree leaves middle
                Frame treeBaseLeavesFrame = new FrameBuilder(getSubImage(4, 1))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder treeBaseLeavesTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(treeBaseLeavesFrame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(treeBaseLeavesTile);

                // tree leaves right side
                Frame treeLeavesRightFrame = new FrameBuilder(getSubImage(4, 4))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder treeLeavesRightTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(treeLeavesRightFrame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(treeLeavesRightTile);

                // tree leaves left side
                Frame treeLeavesLeftFrame = new FrameBuilder(getSubImage(4, 3))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder treeLeavesLeftTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(treeLeavesLeftFrame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(treeLeavesLeftTile);

                // tree leaves top
                Frame treeLeavesTopFrame = new FrameBuilder(getSubImage(3, 4))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder treeLeavesTopTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(treeLeavesTopFrame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(treeLeavesTopTile);

                // tree leaves bottom right
                Frame treeLeavesBottomRightFrame = new FrameBuilder(getSubImage(4, 6))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder treeLeavesBottomRightTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(treeLeavesBottomRightFrame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(treeLeavesBottomRightTile);

                // tree leaves bottom left
                Frame treeLeavesBottomLeftFrame = new FrameBuilder(getSubImage(5, 6))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder treeLeavesBottomLeftTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(treeLeavesBottomLeftFrame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(treeLeavesBottomLeftTile);

                // water
                Frame waterFrame = new FrameBuilder(getSubImage(5, 1))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder waterTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(waterFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(waterTile);

                // water with algae
                Frame waterAlgaeFrame = new FrameBuilder(getSubImage(5, 0))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder waterAlgaeTile = new MapTileBuilder(waterFrame)
                                .withTopLayer(waterAlgaeFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(waterAlgaeTile);

                // water with ducks
                Frame waterDucksFrame = new FrameBuilder(getSubImage(6, 1))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder waterDucksTile = new MapTileBuilder(waterFrame)
                                .withTopLayer(waterDucksFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(waterDucksTile);

                // water top right corner
                Frame waterTopRightCornerFrame = new FrameBuilder(getSubImage(6, 4))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder waterTopRightCornerTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(waterTopRightCornerFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(waterTopRightCornerTile);

                // water top left corner
                Frame waterTopLeftCornerFrame = new FrameBuilder(getSubImage(6, 3))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder waterTopLeftCornerTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(waterTopLeftCornerFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(waterTopLeftCornerTile);

                // water bottom right corner
                Frame waterBottomRightCornerFrame = new FrameBuilder(getSubImage(10, 3))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder waterBottomRightCornerTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(waterBottomRightCornerFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(waterBottomRightCornerTile);

                // water bottom left corner
                Frame waterBottomLeftCornerFrame = new FrameBuilder(getSubImage(10, 2))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder waterBottomLeftCornerTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(waterBottomLeftCornerFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(waterBottomLeftCornerTile);

                // cce 030 floor
                Frame cceFloorFrame = new FrameBuilder(getSubImage(3, 0))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder cceFloorTile = new MapTileBuilder(cceFloorFrame);

                mapTiles.add(cceFloorTile);

                // table left tile
                Frame tableLeftFrame = new FrameBuilder(getSubImage(11, 5))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder tableLeftTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(tableLeftFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(tableLeftTile);

                // table right tile
                Frame tableRightFrame = new FrameBuilder(getSubImage(11, 6))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder tableRightTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(tableRightFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(tableRightTile);

                // trash can
                Frame trashCanFrame = new FrameBuilder(getSubImage(8, 2))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder trashCanTile = new MapTileBuilder(grassFrame)
                                 .withTopLayer(trashCanFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(trashCanTile);

                // fire pit
                Frame firePitFrame = new FrameBuilder(getSubImage(8, 1))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder firePitTile = new MapTileBuilder(grassFrame)
                                 .withTopLayer(firePitFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(firePitTile);

                // bench left
                Frame benchLeftFrame = new FrameBuilder(getSubImage(11, 0))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder benchLeftTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(benchLeftFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(benchLeftTile);

                // bench right
                Frame benchRightFrame = new FrameBuilder(getSubImage(11, 1))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder benchRightTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(benchRightFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(benchRightTile);

                // sewer grate
                Frame sewerFrame = new FrameBuilder(getSubImage(10, 6))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder sewerTile = new MapTileBuilder(sewerFrame);

                mapTiles.add(sewerTile);

                // lawn chair front
                Frame lawnChairFrontFrame = new FrameBuilder(getSubImage(10, 5))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder lawnChairFrontTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(lawnChairFrontFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(lawnChairFrontTile);

                // lawn chair side right
                Frame lawnChairSideRightFrame = new FrameBuilder(getSubImage(10, 4))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder lawnChairSideRightTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(lawnChairSideRightFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(lawnChairSideRightTile);

                // outside circle table
                Frame outsideCircleTableFrame = new FrameBuilder(getSubImage(5, 2))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder outsideCircleTableTile = new MapTileBuilder(horizontalBrickFrame)
                                .withTopLayer(outsideCircleTableFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(outsideCircleTableTile);

                // vertical brick
                Frame verticalBrickFrame = new FrameBuilder(getSubImage(11, 2))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder verticalBrickTile = new MapTileBuilder(verticalBrickFrame);

                mapTiles.add(verticalBrickTile);

                // bobcat sign; use for teleporter
                Frame bobcatSignFrame = new FrameBuilder(getSubImage(12, 3))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bobcatSignTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(bobcatSignFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(bobcatSignTile);

                // brick wall
                Frame brickWallFrame = new FrameBuilder(getSubImage(1, 6))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder brickWallTile = new MapTileBuilder(brickWallFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(brickWallTile);

                // brick wall
                Frame brickWallWithLightFrame = new FrameBuilder(getSubImage(9, 0))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder brickWallWithLightTile = new MapTileBuilder(brickWallWithLightFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(brickWallWithLightTile);

                // glass panes base
                Frame glassPanesBaseFrame = new FrameBuilder(getSubImage(9, 1))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder glassPanesBaseTile = new MapTileBuilder(glassPanesBaseFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(glassPanesBaseTile);

                // glass panes top
                Frame glassPanesTopFrame = new FrameBuilder(getSubImage(9, 2))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder glassPanesTopTile = new MapTileBuilder(glassPanesTopFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(glassPanesTopTile);

                // light post base
                Frame lightPostBaseFrame = new FrameBuilder(getSubImage(8, 4))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder lightPostBaseTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(lightPostBaseFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(lightPostBaseTile);

                // light post
                Frame lightPostFrame = new FrameBuilder(getSubImage(8, 3))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder lightPostTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(lightPostFrame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(lightPostTile);

                // light post top
                Frame lightPostTopFrame = new FrameBuilder(getSubImage(8, 5))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder lightPostTopTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(lightPostTopFrame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(lightPostTopTile);

                // glass 4 panes
                Frame glassPanesFourFrame = new FrameBuilder(getSubImage(10, 0))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder glassPanesFourTile = new MapTileBuilder(brickWallFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(glassPanesFourTile);

                // tree leaves right side
                Frame treeLeavesRight2Frame = new FrameBuilder(getSubImage(4, 4))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder treeLeavesRight2Tile = new MapTileBuilder(horizontalBrickFrame)
                                .withTopLayer(treeLeavesRight2Frame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(treeLeavesRight2Tile);

                // tree leaves left side
                Frame treeLeavesLeft2Frame = new FrameBuilder(getSubImage(4, 3))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder treeLeavesLeft2Tile = new MapTileBuilder(horizontalBrickFrame)
                                .withTopLayer(treeLeavesLeft2Frame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(treeLeavesLeft2Tile);

                // tree leaves top
                Frame treeLeavesTop2Frame = new FrameBuilder(getSubImage(3, 4))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder treeLeavesTop2Tile = new MapTileBuilder(horizontalBrickFrame)
                                .withTopLayer(treeLeavesTop2Frame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(treeLeavesTop2Tile);

                // tree leaves bottom right
                Frame treeLeavesBottomRight2Frame = new FrameBuilder(getSubImage(4, 6))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder treeLeavesBottomRight2Tile = new MapTileBuilder(horizontalBrickFrame)
                                .withTopLayer(treeLeavesBottomRight2Frame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(treeLeavesBottomRight2Tile);

                // tree leaves bottom left
                Frame treeLeavesBottomLeft2Frame = new FrameBuilder(getSubImage(5, 6))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder treeLeavesBottomLeft2Tile = new MapTileBuilder(horizontalBrickFrame)
                                .withTopLayer(treeLeavesBottomLeft2Frame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(treeLeavesBottomLeft2Tile);

                // tree trunk
                Frame treeTrunk2Frame = new FrameBuilder(getSubImage(4, 0))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder treeTrunk2Tile = new MapTileBuilder(horizontalBrickFrame)
                                .withTopLayer(treeTrunk2Frame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(treeTrunk2Tile);

                // flower patch
                Frame flowerPatchFrame = new FrameBuilder(getSubImage(7, 1))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder flowerPatchTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(flowerPatchFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(flowerPatchTile);

                // blue light top
                Frame blueLightTopFrame = new FrameBuilder(getSubImage(5, 5))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder blueLightTopTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(blueLightTopFrame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(blueLightTopTile);

                // blue light base
                Frame blueLightBaseFrame = new FrameBuilder(getSubImage(5, 4))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder blueLightBaseTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(blueLightBaseFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(blueLightBaseTile);

                // brick outline
                Frame brickOutlinerRightFrame = new FrameBuilder(getSubImage(6, 5))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder brickOutlinerRightTile = new MapTileBuilder(brickOutlinerRightFrame);

                mapTiles.add(brickOutlinerRightTile);

                // brick outline left side
                Frame brickOutlineLeftFrame = new FrameBuilder(getSubImage(6, 6))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder brickOutlineLeftTile = new MapTileBuilder(brickOutlineLeftFrame);

                mapTiles.add(brickOutlineLeftTile);

                // brick outline top
                Frame brickOutlineTopFrame = new FrameBuilder(getSubImage(7, 6))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder brickOutlineTopTile = new MapTileBuilder(brickOutlineTopFrame);

                mapTiles.add(brickOutlineTopTile);

                // brick outline bottom
                Frame brickOutlineBottomFrame = new FrameBuilder(getSubImage(8, 6))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder brickOutlineBottomTile = new MapTileBuilder(brickOutlineBottomFrame);

                mapTiles.add(brickOutlineBottomTile);

                // brick outline corner bottom right
                Frame brickOutlineCornerBottomRightFrame = new FrameBuilder(getSubImage(13, 1))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder brickOutlineCornerBottomRightTile = new MapTileBuilder(
                                brickOutlineCornerBottomRightFrame);

                mapTiles.add(brickOutlineCornerBottomRightTile);

                // brick outline corner bottom left
                Frame brickOutlineCornerBottomLeftFrame = new FrameBuilder(getSubImage(13, 2))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder brickOutlineCornerBottomLeftTile = new MapTileBuilder(brickOutlineCornerBottomLeftFrame);

                mapTiles.add(brickOutlineCornerBottomLeftTile);

                // brick outline corner top left
                Frame brickOutlineCornerTopLeftFrame = new FrameBuilder(getSubImage(13, 3))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder brickOutlineCornerTopLeftTile = new MapTileBuilder(brickOutlineCornerTopLeftFrame);

                mapTiles.add(brickOutlineCornerTopLeftTile);

                // brick outline corner top right
                Frame brickOutlineCornerTopRightFrame = new FrameBuilder(getSubImage(13, 4))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder brickOutlineCornerTopRightTile = new MapTileBuilder(brickOutlineCornerTopRightFrame);

                mapTiles.add(brickOutlineCornerTopRightTile);

                // lawn chair side left
                Frame lawnChairSideLeftFrame = new FrameBuilder(getSubImage(14, 1))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder lawnChairSideLeftTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(lawnChairSideLeftFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(lawnChairSideLeftTile);

                // brick wall dark
                Frame brickWallDarkFrame = new FrameBuilder(getSubImage(13, 5))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder brickWallDarkTile = new MapTileBuilder(brickWallDarkFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(brickWallDarkTile);

                // brick wall border
                Frame brickWallBorderFrame = new FrameBuilder(getSubImage(14, 0))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder brickWallBorderTile = new MapTileBuilder(brickWallBorderFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(brickWallBorderTile);

                // brick wall light
                Frame brickWallLightFrame = new FrameBuilder(getSubImage(13, 6))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder brickWallLightTile = new MapTileBuilder(brickWallLightFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(brickWallLightTile);

                // flag pole base
                Frame flagPoleBaseFrame = new FrameBuilder(getSubImage(8, 7))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder flagPoleBaseTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(flagPoleBaseFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(flagPoleBaseTile);

                // flag pole base 2
                Frame flagPoleBase2Frame = new FrameBuilder(getSubImage(8, 7))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder flagPoleBase2Tile = new MapTileBuilder(brickOutlineBottomFrame)
                                .withTopLayer(flagPoleBase2Frame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(flagPoleBase2Tile);

                // flag pole base 3
                Frame flagPoleBase3Frame = new FrameBuilder(getSubImage(8, 7))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder flagPoleBase3Tile = new MapTileBuilder(horizontalBrickFrame)
                                .withTopLayer(flagPoleBase3Frame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(flagPoleBase3Tile);

                // usa flag
                Frame usaFlagFrame = new FrameBuilder(getSubImage(7, 7))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder usaFlagTile = new MapTileBuilder(horizontalBrickFrame)
                                .withTopLayer(usaFlagFrame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(usaFlagTile);

                // usa flag topper
                Frame usaFlagTopperFrame = new FrameBuilder(getSubImage(10, 7))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder usaFlagTopperTile = new MapTileBuilder(horizontalBrickFrame)
                                .withTopLayer(usaFlagTopperFrame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(usaFlagTopperTile);

                // table umbrella
                Frame umbrellaTopFrame = new FrameBuilder(getSubImage(15, 6))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder umbrellaTopTile = new MapTileBuilder(brickWallFrame)
                                .withTopLayer(umbrellaTopFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(umbrellaTopTile);

                // student center door
                Frame studentCenterDoorFrame = new FrameBuilder(getSubImage(15, 4))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder studentCenterDoorTile = new MapTileBuilder(studentCenterDoorFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(studentCenterDoorTile);

                // whiteboard bottom no markers
                Frame whiteboardBottomFrame = new FrameBuilder(getSubImage(15, 7))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder whiteboardBottomTile = new MapTileBuilder(whiteboardBottomFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(whiteboardBottomTile);

                // whiteboard bottom red marker
                Frame whiteboardBottomRedFrame = new FrameBuilder(getSubImage(0, 8))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder whiteboardBottomRedTile = new MapTileBuilder(whiteboardBottomRedFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(whiteboardBottomRedTile);

                // whiteboard bottom blue marker
                Frame whiteboardBottomBlueFrame = new FrameBuilder(getSubImage(1, 8))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder whiteboardBottomBlueTile = new MapTileBuilder(whiteboardBottomBlueFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(whiteboardBottomBlueTile);

                // whiteboard top no marks
                Frame whiteboardTopFrame = new FrameBuilder(getSubImage(14, 7))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder whiteboardTopTile = new MapTileBuilder(whiteboardTopFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(whiteboardTopTile);

                // cceDoor bottom
                Frame cceDoorBottomFrame = new FrameBuilder(getSubImage(11, 8))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder cceDoorBottomTile = new MapTileBuilder(cceDoorBottomFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(cceDoorBottomTile);

                // cceDoor left top
                Frame cceDoorLeftTopFrame = new FrameBuilder(getSubImage(8, 8))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder cceDoorLeftTopTile = new MapTileBuilder(cceDoorLeftTopFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(cceDoorLeftTopTile);

                // cceDoor right top
                Frame cceDoorRightTopFrame = new FrameBuilder(getSubImage(10, 8))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder cceDoorRightTopTile = new MapTileBuilder(cceDoorRightTopFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(cceDoorRightTopTile);

                // cceDoor right top
                Frame cceWallFrame = new FrameBuilder(getSubImage(7, 8))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder cceWallTile = new MapTileBuilder(cceWallFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(cceWallTile);

                // cce pole
                Frame ccePoleBaseFrame = new FrameBuilder(getSubImage(6, 8))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder ccePoleBaseTile = new MapTileBuilder(cceFloorFrame)
                                .withTopLayer(ccePoleBaseFrame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(ccePoleBaseTile);

                // cce pole with screen right side
                Frame ccePoleRightScreenSideFrame = new FrameBuilder(getSubImage(5, 8))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder ccePoleRightScreenSideTile = new MapTileBuilder(cceFloorFrame)
                                .withTopLayer(ccePoleRightScreenSideFrame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(ccePoleRightScreenSideTile);

                // cce pole with screen right side
                Frame ccePoleFrame = new FrameBuilder(getSubImage(4, 8))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder ccePoleTile = new MapTileBuilder(cceFloorFrame)
                                .withTopLayer(ccePoleFrame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(ccePoleTile);

                // student center roof
                Frame studentCenterRoofFrame = new FrameBuilder(getSubImage(5, 7))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder studentCenterRoofTile = new MapTileBuilder(studentCenterRoofFrame)
                                .withTopLayer(studentCenterRoofFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(studentCenterRoofTile);

                // student center roof vertical
                Frame studentCenterRoofVerticalFrame = new FrameBuilder(getSubImage(4, 9))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder studentCenterRoofVerticalTile = new MapTileBuilder(studentCenterRoofVerticalFrame)
                                .withTopLayer(studentCenterRoofVerticalFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(studentCenterRoofVerticalTile);

                // student center roof outline left corner
                Frame studentCenterRoofOutlineLeftCornerFrame = new FrameBuilder(getSubImage(6, 9))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder studentCenterRoofOutlineLeftCornerTile = new MapTileBuilder(
                                studentCenterRoofOutlineLeftCornerFrame)
                                .withTopLayer(studentCenterRoofOutlineLeftCornerFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(studentCenterRoofOutlineLeftCornerTile);

                // student center roof outline right corner
                Frame studentCenterRoofOutlineRightCornerFrame = new FrameBuilder(getSubImage(9, 9))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder studentCenterRoofOutlineRightCornerTile = new MapTileBuilder(
                                studentCenterRoofOutlineRightCornerFrame)
                                .withTopLayer(studentCenterRoofOutlineRightCornerFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(studentCenterRoofOutlineRightCornerTile);

                // student center roof outline bottom
                Frame studentCenterRoofOutlineBottomFrame = new FrameBuilder(getSubImage(8, 9))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder studentCenterRoofOutlineBottomTile = new MapTileBuilder(
                                studentCenterRoofOutlineBottomFrame)
                                .withTopLayer(studentCenterRoofOutlineBottomFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(studentCenterRoofOutlineBottomTile);

                // student center roof outline left
                Frame studentCenterRoofOutlineLeftFrame = new FrameBuilder(getSubImage(7, 9))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder studentCenterRoofOutlineLeftTile = new MapTileBuilder(studentCenterRoofOutlineLeftFrame)
                                .withTopLayer(studentCenterRoofOutlineLeftFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(studentCenterRoofOutlineLeftTile);

                // student center sign
                Frame studentCenterSignFrame = new FrameBuilder(getSubImage(0, 7))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder studentCenterSignTile = new MapTileBuilder(horizontalBrickFrame)
                                .withTopLayer(studentCenterSignFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(studentCenterSignTile);

                // student center table top
                Frame studentCenterTableTopFrame = new FrameBuilder(getSubImage(12, 8))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder studentCenterTableTopTile = new MapTileBuilder(horizontalBrickFrame)
                                .withTopLayer(studentCenterTableTopFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(studentCenterTableTopTile);

                // student center table bottom
                Frame studentCenterTableBottomFrame = new FrameBuilder(getSubImage(13, 8))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder studentCenterTableBottomTile = new MapTileBuilder(horizontalBrickFrame)
                                .withTopLayer(studentCenterTableBottomFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(studentCenterTableBottomTile);

                // student center table left front view
                Frame studentCenterTableFrontLeftFrame = new FrameBuilder(getSubImage(11, 5))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder studentCenterTableFrontLeftTile = new MapTileBuilder(horizontalBrickFrame)
                                .withTopLayer(studentCenterTableFrontLeftFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(studentCenterTableFrontLeftTile);

                // student center table right front view
                Frame studentCenterTableFrontRightFrame = new FrameBuilder(getSubImage(11, 6))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder studentCenterTableFrontRightTile = new MapTileBuilder(horizontalBrickFrame)
                                 .withTopLayer(studentCenterTableFrontRightFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(studentCenterTableFrontRightTile);

                // student center roof outline right
                Frame studentCenterRoofOutlineRightFrame = new FrameBuilder(getSubImage(7, 10))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder studentCenterRoofOutlineRightTile = new MapTileBuilder(
                                studentCenterRoofOutlineRightFrame)
                                .withTopLayer(studentCenterRoofOutlineRightFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(studentCenterRoofOutlineRightTile);

                // student center roof outline left top corner
                Frame studentCenterRoofOutlineLeftTopCornerFrame = new FrameBuilder(getSubImage(6, 10))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder studentCenterRoofOutlineLeftTopCornerTile = new MapTileBuilder(
                                studentCenterRoofOutlineLeftTopCornerFrame)
                                .withTopLayer(studentCenterRoofOutlineLeftTopCornerFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(studentCenterRoofOutlineLeftTopCornerTile);

                // brick wall border right
                Frame brickWallBorderRightFrame = new FrameBuilder(getSubImage(10, 10))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder brickWallBorderRightTile = new MapTileBuilder(horizontalBrickFrame)
                                .withTopLayer(brickWallBorderRightFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(brickWallBorderRightTile);

                // brick wall border left
                Frame brickWallBorderLeftFrame = new FrameBuilder(getSubImage(10, 9))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder brickWallBorderLeftTile = new MapTileBuilder(horizontalBrickFrame)
                                .withTopLayer(brickWallBorderLeftFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(brickWallBorderLeftTile);

                // dark dirt
                Frame darkDirtFrame = new FrameBuilder(getSubImage(4, 7))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder darkDirtTile = new MapTileBuilder(darkDirtFrame);

                mapTiles.add(darkDirtTile);

                // red flowers
                Frame redFlowersFrame = new FrameBuilder(getSubImage(2, 7))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder redFlowersTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(redFlowersFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(redFlowersTile);

                // yellow flowers
                Frame yellowFlowersFrame = new FrameBuilder(getSubImage(1, 7))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder yellowFlowersTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(yellowFlowersFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(yellowFlowersTile);

                // bush
                Frame bushFrame = new FrameBuilder(getSubImage(1, 10))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bushTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(bushFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(bushTile);

                // bush with white leaves
                Frame bushWhiteFrame = new FrameBuilder(getSubImage(1, 9))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bushWhiteTile = new MapTileBuilder(grassFrame)                             
                                .withTopLayer(bushWhiteFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(bushWhiteTile);

                // arc right top
                Frame arcRightTopFrame = new FrameBuilder(getSubImage(7, 11))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder arcRightTopTile = new MapTileBuilder(glassPanesFourFrame)
                                .withTopLayer(arcRightTopFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(arcRightTopTile);

                // arc right bottom
                Frame arcRightBottomFrame = new FrameBuilder(getSubImage(8, 11))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder arcRightBottomTile = new MapTileBuilder(brickWallFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(arcRightBottomTile);

                // arc left bottom
                Frame arcLeftBottomFrame = new FrameBuilder(getSubImage(6, 11))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder arcLeftBottomTile = new MapTileBuilder(brickWallFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(arcLeftBottomTile);

                // arc left top
                Frame arcLeftTopFrame = new FrameBuilder(getSubImage(5, 11))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder arcLeftTopTile = new MapTileBuilder(glassPanesFourFrame)
                                .withTopLayer(arcLeftTopFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(arcLeftTopTile);

                // green trash can
                Frame greenTrashFrame = new FrameBuilder(getSubImage(4, 11))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder greenTrashTile = new MapTileBuilder(arcRightBottomFrame)
                                 .withTopLayer(greenTrashFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(greenTrashTile);

                // cream trash can
                Frame creamTrashFrame = new FrameBuilder(getSubImage(3, 11))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder creamTrashTile = new MapTileBuilder(arcLeftBottomFrame)
                                .withTopLayer(creamTrashFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(creamTrashTile);

                // cce pole with screen left side
                Frame ccePoleLeftScreenSideFrame = new FrameBuilder(getSubImage(5, 9))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder ccePoleLeftScreenSideTile = new MapTileBuilder(cceFloorFrame)
                                .withTopLayer(ccePoleLeftScreenSideFrame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(ccePoleLeftScreenSideTile);

                // cce chair front
                Frame cceChairFrontFrame = new FrameBuilder(getSubImage(2, 9))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder cceChairFrontTile = new MapTileBuilder(cceFloorFrame)
                                .withTopLayer(cceChairFrontFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(cceChairFrontTile);

                // cce chair back
                Frame cceChairBackFrame = new FrameBuilder(getSubImage(11, 9))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder cceChairBackTile = new MapTileBuilder(cceFloorFrame)
                                .withTopLayer(cceChairBackFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(cceChairBackTile);

                // cce table left horizontal
                Frame cceTableLeftHorizontalFrame = new FrameBuilder(getSubImage(12, 0))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder cceTableLeftHorizontalTile = new MapTileBuilder(cceFloorFrame)
                                .withTopLayer(cceTableLeftHorizontalFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(cceTableLeftHorizontalTile);

                // cce table right horizontal
                Frame cceTableRightHorizontalFrame = new FrameBuilder(getSubImage(12, 1))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder cceTableRightHorizontalTile = new MapTileBuilder(cceFloorFrame)
                                .withTopLayer(cceTableRightHorizontalFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(cceTableRightHorizontalTile);

                // cce professor podium
                Frame ccePodiumBaseFrame = new FrameBuilder(getSubImage(3, 8))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder ccePodiumBaseTile = new MapTileBuilder(cceFloorFrame)
                                .withTopLayer(ccePodiumBaseFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(ccePodiumBaseTile);

                // cce professor podium
                Frame ccePodiumTopFrame = new FrameBuilder(getSubImage(2, 8))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder ccePodiumTopTile = new MapTileBuilder(cceFloorFrame)
                                .withTopLayer(ccePodiumTopFrame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(ccePodiumTopTile);

                // cce chair side right
                Frame cceChairRightSideFrame = new FrameBuilder(getSubImage(3, 9))
                
                                .withScale(tileScale)
                                .build();

                MapTileBuilder cceChairRightSideTile = new MapTileBuilder(cceFloorFrame)
                                .withTopLayer(cceChairRightSideFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(cceChairRightSideTile);

                // cce chair side left
                Frame cceChairLeftSideFrame = new FrameBuilder(getSubImage(2, 11))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder cceChairLeftSideTile = new MapTileBuilder(cceFloorFrame)
                                .withTopLayer(cceChairLeftSideFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(cceChairLeftSideTile);

                // cce table middle horizontal
                Frame cceTableMiddleHorizontalFrame = new FrameBuilder(getSubImage(1, 11))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder cceTableMiddleHorizontalTile = new MapTileBuilder(cceFloorFrame)
                                .withTopLayer(cceTableMiddleHorizontalFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(cceTableMiddleHorizontalTile);

                // cce table top vertical
                Frame cceTableTopVerticalFrame = new FrameBuilder(getSubImage(0, 12))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder cceTableTopVerticalTile = new MapTileBuilder(cceFloorFrame)
                                .withTopLayer(cceTableTopVerticalFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(cceTableTopVerticalTile);

                // cce table bottom vertical
                Frame cceTableBottomVerticalFrame = new FrameBuilder(getSubImage(2, 12))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder cceTableBottomVerticalTile = new MapTileBuilder(cceFloorFrame)
                                .withTopLayer(cceTableBottomVerticalFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(cceTableBottomVerticalTile);

                // cce table middle vertical
                Frame cceTableMiddleVerticalFrame = new FrameBuilder(getSubImage(1, 12))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder cceTableMiddleVerticalTile = new MapTileBuilder(cceFloorFrame)
                                .withTopLayer(cceTableMiddleVerticalFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(cceTableMiddleVerticalTile);

                // whiteboard bottom with marks
                Frame whiteboardBottomMarksOneFrame = new FrameBuilder(getSubImage(15, 8))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder whiteboardBottomMarksOneTile = new MapTileBuilder(whiteboardBottomMarksOneFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(whiteboardBottomMarksOneTile);

                // whiteboard bottom with marks
                Frame whiteboardBottomMarksTwoFrame = new FrameBuilder(getSubImage(12, 7))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder whiteboardBottomMarksTwoTile = new MapTileBuilder(whiteboardBottomMarksTwoFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(whiteboardBottomMarksTwoTile);

                // whiteboard top with marks
                Frame whiteboardTopMarksOneFrame = new FrameBuilder(getSubImage(14, 8))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder whiteboardTopMarksOneTile = new MapTileBuilder(whiteboardTopMarksOneFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(whiteboardTopMarksOneTile);

                // Whiteboard top with marks
                Frame whiteboardTopMarksTwoFrame = new FrameBuilder(getSubImage(14, 9))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder whiteboardTopMarksTwoTile = new MapTileBuilder(whiteboardTopMarksTwoFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(whiteboardTopMarksTwoTile);

                // Student center dark panel
                Frame scDarkPanelFrame = new FrameBuilder(getSubImage(9, 11))
                                .withScale(tileScale)
                                .build();
                MapTileBuilder scDarkPanelTile = new MapTileBuilder(scDarkPanelFrame)
                                .withTileType(TileType.NOT_PASSABLE);
                mapTiles.add(scDarkPanelTile);

                // Student center dark panel side left
                Frame scDarkPanelSideLeftFrame = new FrameBuilder(getSubImage(11, 11))
                                .withScale(tileScale)
                                .build();
                MapTileBuilder scDarkPanelSideLeftTile = new MapTileBuilder(scDarkPanelSideLeftFrame)
                                .withTopLayer(scDarkPanelSideLeftFrame)
                                .withTileType(TileType.NOT_PASSABLE);
                mapTiles.add(scDarkPanelSideLeftTile);

                // quad white thing
                Frame quadWhiteBumpFrame = new FrameBuilder(getSubImage(14, 5))
                                .withScale(tileScale)
                                .build();
                MapTileBuilder quadWhiteBumpTile = new MapTileBuilder(grassFrame)
                                .withTopLayer(quadWhiteBumpFrame)
                                .withTileType(TileType.NOT_PASSABLE);
                mapTiles.add(quadWhiteBumpTile);
                // outside table chair
                Frame outsideTableChairRightFrame = new FrameBuilder(getSubImage(14, 10))
                                .withScale(tileScale)
                                .build();
                MapTileBuilder outsideTableChairRightTile = new MapTileBuilder(horizontalBrickFrame)
                                .withTopLayer(outsideTableChairRightFrame)
                                .withTileType(TileType.NOT_PASSABLE);
                mapTiles.add(outsideTableChairRightTile);
                // outside table chair left
                Frame outsideTableChairLeftFrame = new FrameBuilder(getSubImage(15, 9))
                                .withScale(tileScale)
                                .build();
                MapTileBuilder outsideTableChairLeftTile = new MapTileBuilder(horizontalBrickFrame)
                                .withTopLayer(outsideTableChairLeftFrame)
                                .withTileType(TileType.NOT_PASSABLE);
                mapTiles.add(outsideTableChairLeftTile);
                // sewer cap frame
                Frame sewerCapFrame = new FrameBuilder(getSubImage(13, 7))
                                .withScale(tileScale)
                                .build();
                MapTileBuilder sewerCapTile = new MapTileBuilder(sewerCapFrame);
                mapTiles.add(sewerCapTile);

                // ice blank
                Frame iceBlankFrame = new FrameBuilder(getSubImage(3, 15))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder iceBlankTile = new MapTileBuilder(iceBlankFrame);
                mapTiles.add(iceBlankTile);

                // ice with red stripe right side
                Frame iceRedStripeRightFrame = new FrameBuilder(getSubImage(11, 15))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder iceRedStripeRightTile = new MapTileBuilder(iceRedStripeRightFrame);

                mapTiles.add(iceRedStripeRightTile);

                // ice with red stripe left side
                Frame iceRedStripeLeftFrame = new FrameBuilder(getSubImage(11, 13))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder iceRedStripeLeftTile = new MapTileBuilder(iceRedStripeLeftFrame);

                mapTiles.add(iceRedStripeLeftTile);

                // ice with red stripe
                Frame iceRedStripeMiddleFrame = new FrameBuilder(getSubImage(3, 13))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder iceRedStripeMiddleTile = new MapTileBuilder(iceRedStripeMiddleFrame);

                mapTiles.add(iceRedStripeMiddleTile);

                // ice with thin red stripe vertical
                Frame iceThinRedVerticalStripeMiddleFrame = new FrameBuilder(getSubImage(15, 12))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder iceThinRedVerticalStripeMiddleTile = new MapTileBuilder(
                                iceThinRedVerticalStripeMiddleFrame);

                mapTiles.add(iceThinRedVerticalStripeMiddleTile);

                // ice with thin red stripe horizontal
                Frame iceThinRedHorizontalStripeMiddleFrame = new FrameBuilder(getSubImage(15, 13))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder iceThinRedHorizontalStripeMiddleTile = new MapTileBuilder(
                                iceThinRedHorizontalStripeMiddleFrame);

                mapTiles.add(iceThinRedHorizontalStripeMiddleTile);

                // ice with blue stripe right side
                Frame iceBlueStripeRightFrame = new FrameBuilder(getSubImage(11, 12))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder iceBlueStripeRightTile = new MapTileBuilder(iceBlueStripeRightFrame);

                mapTiles.add(iceBlueStripeRightTile);

                // ice with blue stripe left side
                Frame iceBlueStripeLeftFrame = new FrameBuilder(getSubImage(11, 14))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder iceBlueStripeLeftTile = new MapTileBuilder(iceBlueStripeLeftFrame);

                mapTiles.add(iceBlueStripeLeftTile);

                // ice with blue stripe middle
                Frame iceBlueStripeMiddleFrame = new FrameBuilder(getSubImage(3, 14))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder iceBlueStripeMiddleTile = new MapTileBuilder(iceBlueStripeMiddleFrame);

                mapTiles.add(iceBlueStripeMiddleTile);

                // m and t bank logo top
                Frame bankTopLogoFrame = new FrameBuilder(getSubImage(12, 14))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bankTopLogoTile = new MapTileBuilder(bankTopLogoFrame);

                mapTiles.add(bankTopLogoTile);

                // m and t bank logo bottom
                Frame bankBottomLogoFrame = new FrameBuilder(getSubImage(12, 15))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bankBottomLogoTile = new MapTileBuilder(bankBottomLogoFrame);

                mapTiles.add(bankBottomLogoTile);

                // quinnipiac logo left hand side
                Frame quLogoLeftFrame = new FrameBuilder(getSubImage(13, 12))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder quLogoLeftTile = new MapTileBuilder(quLogoLeftFrame);

                mapTiles.add(quLogoLeftTile);

                // quinnipiac logo left mid hand side
                Frame quLogoLeftMidFrame = new FrameBuilder(getSubImage(13, 13))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder quLogoLeftMidTile = new MapTileBuilder(quLogoLeftMidFrame);

                mapTiles.add(quLogoLeftMidTile);

                // quinnipiac logo right mid hand side
                Frame quLogoRightMidFrame = new FrameBuilder(getSubImage(13, 14))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder quLogoRightMidTile = new MapTileBuilder(quLogoRightMidFrame);

                mapTiles.add(quLogoRightMidTile);

                // quinnipiac logo right hand side
                Frame quLogoRightFrame = new FrameBuilder(getSubImage(13, 15))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder quLogoRightTile = new MapTileBuilder(quLogoRightFrame);

                mapTiles.add(quLogoRightTile);

                // quinnipiac logo left hand side flipped
                Frame quLogoLeftFlippedFrame = new FrameBuilder(getSubImage(14, 12))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder quLogoLeftFlippedTile = new MapTileBuilder(quLogoLeftFlippedFrame);

                mapTiles.add(quLogoLeftFlippedTile);

                // quinnipiac logo left mid hand side flipped
                Frame quLogoLeftMidFlippedFrame = new FrameBuilder(getSubImage(14, 13))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder quLogoLeftMidFlippedTile = new MapTileBuilder(quLogoLeftMidFlippedFrame);

                mapTiles.add(quLogoLeftMidFlippedTile);

                // quinnipiac logo right mid hand side flipped
                Frame quLogoRightMidFlippedFrame = new FrameBuilder(getSubImage(14, 14))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder quLogoRightMidFlippedTile = new MapTileBuilder(quLogoRightMidFlippedFrame);

                mapTiles.add(quLogoRightMidFlippedTile);

                // quinnipiac logo right hand side
                Frame quLogoRightFlippedFrame = new FrameBuilder(getSubImage(14, 15))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder quLogoRightFlippedTile = new MapTileBuilder(quLogoRightFlippedFrame);

                mapTiles.add(quLogoRightFlippedTile);

                mapTiles.add(quLogoRightMidFlippedTile);

                // ecac logo top
                Frame ecacLogoTopFrame = new FrameBuilder(getSubImage(15, 14))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder ecacLogoTopTile = new MapTileBuilder(ecacLogoTopFrame);

                mapTiles.add(ecacLogoTopTile);

                // ecac logo bottom
                Frame ecacLogoBottomFrame = new FrameBuilder(getSubImage(15, 15))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder ecacLogoBottomTile = new MapTileBuilder(ecacLogoBottomFrame);

                mapTiles.add(ecacLogoBottomTile);

                // concrete
                Frame concreteFrame = new FrameBuilder(getSubImage(7, 3))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder concreteTile = new MapTileBuilder(concreteFrame);

                mapTiles.add(concreteTile);

                // shuttle top frame
                Frame shuttleTopFrontFrame = new FrameBuilder(getSubImage(1, 13))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder shuttleTopFrontTile = new MapTileBuilder(concreteFrame)
                                .withTopLayer(shuttleTopFrontFrame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(shuttleTopFrontTile);

                // shuttle top frame middle
                Frame shuttleTopMidFrame = new FrameBuilder(getSubImage(1, 14))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder shuttleTopMidTile = new MapTileBuilder(concreteFrame)
                                .withTopLayer(shuttleTopMidFrame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(shuttleTopMidTile);

                // shuttle top frame right
                Frame shuttleTopRightFrame = new FrameBuilder(getSubImage(1, 15))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder shuttleTopRightTile = new MapTileBuilder(concreteFrame)
                                .withTopLayer(shuttleTopRightFrame)
                                .withTileType(TileType.PASSABLE);

                mapTiles.add(shuttleTopRightTile);

                // shuttle bottom frame right
                Frame shuttleBottomRightFrame = new FrameBuilder(getSubImage(2, 15))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder shuttleBottomRightTile = new MapTileBuilder(concreteFrame)
                                .withTopLayer(shuttleBottomRightFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(shuttleBottomRightTile);

                // shuttle bottom frame mid
                Frame shuttleBottomMidFrame = new FrameBuilder(getSubImage(2, 14))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder shuttleBottomMidTile = new MapTileBuilder(concreteFrame)
                                .withTopLayer(shuttleBottomMidFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(shuttleBottomMidTile);

                // shuttle bottom frame left
                Frame shuttleBottomLeftFrame = new FrameBuilder(getSubImage(2, 13))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder shuttleBottomLeftTile = new MapTileBuilder(concreteFrame)
                                 .withTopLayer(shuttleBottomLeftFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(shuttleBottomLeftTile);

                // boomer frame 1
                Frame boomer1Frame = new FrameBuilder(getSubImage(7, 13))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder boomer1Tile = new MapTileBuilder(boomer1Frame);

                mapTiles.add(boomer1Tile);

                // boomer frame 2
                Frame boomer2Frame = new FrameBuilder(getSubImage(7, 14))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder boomer2Tile = new MapTileBuilder(boomer2Frame);

                mapTiles.add(boomer2Tile);

                // boomer frame 3
                Frame boomer3Frame = new FrameBuilder(getSubImage(7, 15))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder boomer3Tile = new MapTileBuilder(boomer3Frame);

                mapTiles.add(boomer3Tile);

                // boomer frame 4
                Frame boomer4Frame = new FrameBuilder(getSubImage(8, 13))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder boomer4Tile = new MapTileBuilder(boomer4Frame);

                mapTiles.add(boomer4Tile);

                // boomer frame 5
                Frame boomer5Frame = new FrameBuilder(getSubImage(8, 14))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder boomer5Tile = new MapTileBuilder(boomer5Frame);

                mapTiles.add(boomer5Tile);

                // boomer frame 6
                Frame boomer6Frame = new FrameBuilder(getSubImage(8, 15))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder boomer6Tile = new MapTileBuilder(boomer6Frame);

                mapTiles.add(boomer6Tile);

                // boomer frame 7
                Frame boomer7Frame = new FrameBuilder(getSubImage(9, 13))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder boomer7Tile = new MapTileBuilder(boomer7Frame);

                mapTiles.add(boomer7Tile);

                // boomer frame 8
                Frame boomer8Frame = new FrameBuilder(getSubImage(9, 14))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder boomer8Tile = new MapTileBuilder(boomer8Frame);

                mapTiles.add(boomer8Tile);

                // boomer frame 9
                Frame boomer9Frame = new FrameBuilder(getSubImage(9, 15))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder boomer9Tile = new MapTileBuilder(boomer9Frame);

                mapTiles.add(boomer9Tile);

                // boomer frame 10
                Frame boomer10Frame = new FrameBuilder(getSubImage(10, 13))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder boomer10Tile = new MapTileBuilder(boomer10Frame);

                mapTiles.add(boomer10Tile);

                // boomer frame 11
                Frame boomer11Frame = new FrameBuilder(getSubImage(10, 14))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder boomer11Tile = new MapTileBuilder(boomer11Frame);

                mapTiles.add(boomer11Tile);

                // boomer frame 12
                Frame boomer12Frame = new FrameBuilder(getSubImage(10, 15))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder boomer12Tile = new MapTileBuilder(boomer12Frame);

                mapTiles.add(boomer12Tile);

                // red right top circle
                Frame redTopRightCircleFrame = new FrameBuilder(getSubImage(4, 13))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder redTopRightCircleTile = new MapTileBuilder(redTopRightCircleFrame);

                mapTiles.add(redTopRightCircleTile);

                // red mid top circle
                Frame redTopMidCircleFrame = new FrameBuilder(getSubImage(4, 14))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder redTopMidCircleTile = new MapTileBuilder(redTopMidCircleFrame);

                mapTiles.add(redTopMidCircleTile);

                // red mid top circle
                Frame redTopLeftCircleFrame = new FrameBuilder(getSubImage(4, 15))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder redTopLeftCircleTile = new MapTileBuilder(redTopLeftCircleFrame);

                mapTiles.add(redTopLeftCircleTile);

                // red mid left circle
                Frame redMidLeftCircleFrame = new FrameBuilder(getSubImage(5, 13))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder redMidLeftCircleTile = new MapTileBuilder(redMidLeftCircleFrame);

                mapTiles.add(redMidLeftCircleTile);

                // red mid mid circle
                Frame redMidMidCircleFrame = new FrameBuilder(getSubImage(5, 14))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder redMidMidCircleTile = new MapTileBuilder(redMidMidCircleFrame);

                mapTiles.add(redMidMidCircleTile);

                // red mid right circle
                Frame redMidRightCircleFrame = new FrameBuilder(getSubImage(5, 15))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder redMidRightCircleTile = new MapTileBuilder(redMidRightCircleFrame);

                mapTiles.add(redMidRightCircleTile);

                // red bottom right circle
                Frame redBotRightCircleFrame = new FrameBuilder(getSubImage(6, 15))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder redBotRightCircleTile = new MapTileBuilder(redBotRightCircleFrame);

                mapTiles.add(redBotRightCircleTile);

                // red bottom mid circle
                Frame redBotMidCircleFrame = new FrameBuilder(getSubImage(6, 14))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder redBotMidCircleTile = new MapTileBuilder(redBotMidCircleFrame);

                mapTiles.add(redBotMidCircleTile);

                // red bottom left circle
                Frame redBotLeftCircleFrame = new FrameBuilder(getSubImage(6, 13))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder redBotLeftCircleTile = new MapTileBuilder(redBotLeftCircleFrame);

                mapTiles.add(redBotLeftCircleTile);

                // goalie box top
                Frame goalieBoxTopFrame = new FrameBuilder(getSubImage(12, 13))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder goalieBoxTopTile = new MapTileBuilder(goalieBoxTopFrame);

                mapTiles.add(goalieBoxTopTile);

                // goalie box bottom
                Frame goalieBoxBotFrame = new FrameBuilder(getSubImage(12, 12))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder goalieBoxBotTile = new MapTileBuilder(goalieBoxBotFrame);

                mapTiles.add(goalieBoxBotTile);

                // top corner rink wall
                Frame rinkWallTopCornerRightFrame = new FrameBuilder(getSubImage(4, 16))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder rinkWallTopCornerRightTile = new MapTileBuilder(rinkWallTopCornerRightFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(rinkWallTopCornerRightTile);

                // top corner rink edge wall
                Frame rinkWallTopCornerRightEdgeFrame = new FrameBuilder(getSubImage(5, 16))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder rinkWallTopCornerRightEdgeTile = new MapTileBuilder(rinkWallTopCornerRightEdgeFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(rinkWallTopCornerRightEdgeTile);

                // top corner rink wall
                Frame rinkWallTopCornerLeftFrame = new FrameBuilder(getSubImage(6, 16))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder rinkWallTopCornerLeftTile = new MapTileBuilder(rinkWallTopCornerLeftFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(rinkWallTopCornerLeftTile);

                // top corner rink edge wall
                Frame rinkWallTopCornerLeftEdgeFrame = new FrameBuilder(getSubImage(7, 16))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder rinkWallTopCornerLeftEdgeTile = new MapTileBuilder(rinkWallTopCornerLeftEdgeFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(rinkWallTopCornerLeftEdgeTile);

                // rink wall right
                Frame rinkWallRightFrame = new FrameBuilder(getSubImage(8, 16))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder rinkWallRightTile = new MapTileBuilder(rinkWallRightFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(rinkWallRightTile);

                // rink wall left
                Frame rinkWallLeftFrame = new FrameBuilder(getSubImage(9, 16))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder rinkWallLeftTile = new MapTileBuilder(rinkWallLeftFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(rinkWallLeftTile);

                // rink wall top
                Frame rinkWallTopFrame = new FrameBuilder(getSubImage(10, 16))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder rinkWallTopTile = new MapTileBuilder(rinkWallTopFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(rinkWallTopTile);

                // rink wall bottom edge
                Frame rinkWallBottomEdgeLeftFrame = new FrameBuilder(getSubImage(11, 16))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder rinkWallBottomEdgeLeftTile = new MapTileBuilder(rinkWallBottomEdgeLeftFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(rinkWallBottomEdgeLeftTile);

                // rink wall bottom corner
                Frame rinkWallBottomCornerLeftFrame = new FrameBuilder(getSubImage(12, 16))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder rinkWallBottomCornerLeftTile = new MapTileBuilder(rinkWallBottomCornerLeftFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(rinkWallBottomCornerLeftTile);

                // rink wall bottom edge
                Frame rinkWallBottomEdgeRightFrame = new FrameBuilder(getSubImage(13, 16))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder rinkWallBottomEdgeRightTile = new MapTileBuilder(rinkWallBottomEdgeRightFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(rinkWallBottomEdgeRightTile);

                // rink wall bottom corner
                Frame rinkWallBottomCornerRightFrame = new FrameBuilder(getSubImage(14, 16))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder rinkWallBottomCornerRightTile = new MapTileBuilder(rinkWallBottomCornerRightFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(rinkWallBottomCornerRightTile);

                // rink wall bottom corner
                Frame rinkWallBottomFrame = new FrameBuilder(getSubImage(15, 16))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder rinkWallBottomTile = new MapTileBuilder(rinkWallBottomFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(rinkWallBottomTile);

                // white tile for CCE Quest
                Frame whiteColorFrame = new FrameBuilder(getSubImage(3, 16))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder whiteColorTile = new MapTileBuilder(whiteColorFrame);

                mapTiles.add(whiteColorTile);

                // white tile for CCE Quest
                Frame blueColorFrame = new FrameBuilder(getSubImage(2, 16))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder blueColorTile = new MapTileBuilder(blueColorFrame);

                mapTiles.add(blueColorTile);

                // white tile for CCE Quest
                Frame redColorFrame = new FrameBuilder(getSubImage(1, 16))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder redColorTile = new MapTileBuilder(redColorFrame);

                mapTiles.add(redColorTile);

                // white tile for CCE Quest
                Frame yellowColorFrame = new FrameBuilder(getSubImage(0, 16))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder yellowColorTile = new MapTileBuilder(yellowColorFrame);

                mapTiles.add(yellowColorTile);

                 // burt kahn floor
                Frame bkFloorFrame = new FrameBuilder(getSubImage(0, 15))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorTile = new MapTileBuilder(bkFloorFrame);

                mapTiles.add(bkFloorTile);

                 // burt kahn floor corner top right
                Frame bkFloorCornerTopRightFrame = new FrameBuilder(getSubImage(0, 17))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorCornerTopRightTile = new MapTileBuilder(bkFloorCornerTopRightFrame);

                mapTiles.add(bkFloorCornerTopRightTile);

                 // burt kahn floor corner top left
                Frame bkFloorCornerTopLeftFrame = new FrameBuilder(getSubImage(4, 19))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorCornerTopLeftTile = new MapTileBuilder(bkFloorCornerTopLeftFrame);

                mapTiles.add(bkFloorCornerTopLeftTile);

                 // burt kahn floor corner bottom left
                Frame bkFloorCornerBottomLeftFrame = new FrameBuilder(getSubImage(4, 17))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorCornerBottomLeftTile = new MapTileBuilder(bkFloorCornerBottomLeftFrame);

                mapTiles.add(bkFloorCornerBottomLeftTile);

                 // burt kahn floor corner bottom right
                Frame bkFloorCornerBottomRightFrame = new FrameBuilder(getSubImage(4, 18))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorCornerBottomRightTile = new MapTileBuilder(bkFloorCornerBottomRightFrame);

                mapTiles.add(bkFloorCornerBottomRightTile);

                 // burt kahn floor left side 
                Frame bkFloorLeftFrame = new FrameBuilder(getSubImage(0, 19))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorLeftTile = new MapTileBuilder(bkFloorLeftFrame);

                mapTiles.add(bkFloorLeftTile);

                 // burt kahn floor right side 
                Frame bkFloorRightFrame = new FrameBuilder(getSubImage(0, 18))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorRightTile = new MapTileBuilder(bkFloorRightFrame);

                mapTiles.add(bkFloorRightTile);

                 // burt kahn floor stripe  
                Frame bkFloorStripeFrame = new FrameBuilder(getSubImage(3, 17))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorStripeTile = new MapTileBuilder(bkFloorStripeFrame);

                mapTiles.add(bkFloorStripeTile);

                 // burt kahn floor stripe white 
                Frame bkFloorStripeWhiteFrame = new FrameBuilder(getSubImage(2, 18))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorStripeWhiteTile = new MapTileBuilder(bkFloorStripeWhiteFrame);

                mapTiles.add(bkFloorStripeWhiteTile);

                 // burt kahn floor stripe white 
                Frame bkFloorStripeBlueMidFrame = new FrameBuilder(getSubImage(3, 18))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorStripeBlueMidTile = new MapTileBuilder(bkFloorStripeBlueMidFrame);

                mapTiles.add(bkFloorStripeBlueMidTile);

                  // burt kahn floor stripe bottom with white line
                Frame bkFloorStripeWhiteBottomFrame = new FrameBuilder(getSubImage(3, 19))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorStripeWhiteBottomTile = new MapTileBuilder(bkFloorStripeWhiteBottomFrame);

                mapTiles.add(bkFloorStripeWhiteBottomTile);

                 // bk door left top
                Frame bkDoorLeftTopFrame = new FrameBuilder(getSubImage(10, 18))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkDoorLeftTopTile = new MapTileBuilder(bkDoorLeftTopFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(bkDoorLeftTopTile);

                  // bk door left bottom
                Frame bkDoorLeftBottomFrame = new FrameBuilder(getSubImage(11, 18))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkDoorLeftBottomTile = new MapTileBuilder(bkDoorLeftBottomFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(bkDoorLeftBottomTile);

                  // bk door right top
                Frame bkDoorRightTopFrame = new FrameBuilder(getSubImage(10, 19))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkDoorRightTopTile = new MapTileBuilder(bkDoorRightTopFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(bkDoorRightTopTile);

                   // bk door right bottom
                Frame bkDoorRightBottomFrame = new FrameBuilder(getSubImage(11, 19))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkDoorRightBottomTile = new MapTileBuilder(bkDoorRightBottomFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(bkDoorRightBottomTile);

                    // bk wall
                Frame bkWallFrame = new FrameBuilder(getSubImage(11, 17))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkWallTile = new MapTileBuilder(bkWallFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(bkWallTile);

                    // bk curtain top
                Frame bkCurtainTop1Frame = new FrameBuilder(getSubImage(12, 19))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkCurtainTop1Tile = new MapTileBuilder(bkCurtainTop1Frame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(bkCurtainTop1Tile);

                 // bk curtain top
                Frame bkCurtainTop2Frame = new FrameBuilder(getSubImage(12, 18))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkCurtainTop2Tile = new MapTileBuilder(bkCurtainTop2Frame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(bkCurtainTop2Tile);

                   // bk curtain top
                Frame bkCurtainTop3Frame = new FrameBuilder(getSubImage(12, 17))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkCurtainTop3Tile = new MapTileBuilder(bkCurtainTop3Frame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(bkCurtainTop3Tile);

                     // bk curtain bottom
                Frame bkCurtainBottom1Frame = new FrameBuilder(getSubImage(13, 17))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkCurtainBottom1Tile = new MapTileBuilder(bkCurtainBottom1Frame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(bkCurtainBottom1Tile);

                      // bk curtain bottom
                Frame bkCurtainBottom2Frame = new FrameBuilder(getSubImage(13, 18))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkCurtainBottom2Tile = new MapTileBuilder(bkCurtainBottom2Frame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(bkCurtainBottom2Tile);

                        // bk curtain bottom
                Frame bkCurtainBottom3Frame = new FrameBuilder(getSubImage(13, 19))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkCurtainBottom3Tile = new MapTileBuilder(bkCurtainBottom3Frame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(bkCurtainBottom3Tile);

                Frame bkStageEdgeFrame = new FrameBuilder(getSubImage(15, 18))
                                .withScale(tileScale)
                                .build();
                MapTileBuilder bkStageEdgeTile = new MapTileBuilder(bkStageEdgeFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(bkStageEdgeTile);

                 Frame bkStageBottomCornerFrame = new FrameBuilder(getSubImage(15, 17))
                                .withScale(tileScale)
                                .build();
                MapTileBuilder bkStageBottomCornerTile = new MapTileBuilder(bkStageBottomCornerFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(bkStageBottomCornerTile);

                  Frame bkStageBottomCornerLeftTopFrame = new FrameBuilder(getSubImage(14, 17))
                                .withScale(tileScale)
                                .build();
                MapTileBuilder bkStageBottomCornerLeftTopTile = new MapTileBuilder(bkStageBottomCornerLeftTopFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(bkStageBottomCornerLeftTopTile);

                   Frame bkStageBottomCornerRightTopFrame = new FrameBuilder(getSubImage(14, 19))
                                .withScale(tileScale)
                                .build();
                MapTileBuilder bkStageBottomCornerRightTopTile = new MapTileBuilder(bkStageBottomCornerRightTopFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(bkStageBottomCornerRightTopTile);

                Frame bkStageMidFrame = new FrameBuilder(getSubImage(14, 18))
                                .withScale(tileScale)
                                .build();
                MapTileBuilder bkStageMidTile = new MapTileBuilder(bkStageMidFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(bkStageMidTile);

                 Frame bkStageBottomRightFrame = new FrameBuilder(getSubImage(15, 19))
                                .withScale(tileScale)
                                .build();
                MapTileBuilder bkStageBottomRightTile = new MapTileBuilder(bkStageBottomRightFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(bkStageBottomRightTile);

                // burt kahn floor qu  
                Frame bkFloorQU1RightFrame = new FrameBuilder(getSubImage(8, 19))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorQU1RightTile = new MapTileBuilder(bkFloorQU1RightFrame);

                mapTiles.add(bkFloorQU1RightTile);

                // burt kahn floor qu  
                Frame bkFloorQU2RightFrame = new FrameBuilder(getSubImage(9, 19))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorQU2RightTile = new MapTileBuilder(bkFloorQU2RightFrame);

                mapTiles.add(bkFloorQU2RightTile);

                // burt kahn floor qu  
                Frame bkFloorQU3RightFrame = new FrameBuilder(getSubImage(8, 18))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorQU3RightTile = new MapTileBuilder(bkFloorQU3RightFrame);

                mapTiles.add(bkFloorQU3RightTile);

                 // burt kahn floor qu  
                Frame bkFloorQU4RightFrame = new FrameBuilder(getSubImage(9, 18))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorQU4RightTile = new MapTileBuilder(bkFloorQU4RightFrame);

                mapTiles.add(bkFloorQU4RightTile);

                 // burt kahn floor qu  
                Frame bkFloorQU5RightFrame = new FrameBuilder(getSubImage(9, 17))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorQU5RightTile = new MapTileBuilder(bkFloorQU5RightFrame);

                mapTiles.add(bkFloorQU5RightTile);

                  // burt kahn floor qu  
                Frame bkFloorQU6RightFrame = new FrameBuilder(getSubImage(8, 17))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorQU6RightTile = new MapTileBuilder(bkFloorQU6RightFrame);

                mapTiles.add(bkFloorQU6RightTile);

                  // burt kahn floor qu  
                Frame bkFloorQU7RightFrame = new FrameBuilder(getSubImage(5, 12))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorQU7RightTile = new MapTileBuilder(bkFloorQU7RightFrame);

                mapTiles.add(bkFloorQU7RightTile);

                  // burt kahn floor qu  
                Frame bkFloorQU8RightFrame = new FrameBuilder(getSubImage(6, 12))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorQU8RightTile = new MapTileBuilder(bkFloorQU8RightFrame);

                mapTiles.add(bkFloorQU8RightTile);

                  // burt kahn floor   
                Frame bkFloorTopYellowFrame = new FrameBuilder(getSubImage(0, 11))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorTopYellowTile = new MapTileBuilder(bkFloorTopYellowFrame);

                mapTiles.add(bkFloorTopYellowTile);

                  // burt kahn floor with white stripe   
                Frame bkFloorWhiteStripeFrame = new FrameBuilder(getSubImage(2, 19))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorWhiteStripeTile = new MapTileBuilder(bkFloorWhiteStripeFrame);

                mapTiles.add(bkFloorWhiteStripeTile);

                  // burt kahn floor with white stripe   
                Frame bkFloorTopYellowStripeFrame = new FrameBuilder(getSubImage(1, 18))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorTopYellowStripeTile = new MapTileBuilder(bkFloorTopYellowStripeFrame);

                mapTiles.add(bkFloorTopYellowStripeTile);

                 // burt kahn floor with white stripe   
                Frame bkFloorWhiteStripe2Frame = new FrameBuilder(getSubImage(2, 18))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorWhiteStripe2Tile = new MapTileBuilder(bkFloorWhiteStripe2Frame);

                mapTiles.add(bkFloorWhiteStripe2Tile);

                  // burt kahn floor with white stripe   
                Frame bkFloorWhiteStripe3Frame = new FrameBuilder(getSubImage(1, 19))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorWhiteStripe3Tile = new MapTileBuilder(bkFloorWhiteStripe3Frame);

                mapTiles.add(bkFloorWhiteStripe3Tile);

                  // burt kahn floor with white stripe   
                Frame bkFloorWhiteStripe4Frame = new FrameBuilder(getSubImage(2, 17))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorWhiteStripe4Tile = new MapTileBuilder(bkFloorWhiteStripe4Frame);

                mapTiles.add(bkFloorWhiteStripe4Tile);

                  // burt kahn floor with white stripe   
                Frame bkFloorBottomYellowFrame = new FrameBuilder(getSubImage(1, 17))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bkFloorBottomYellowTile = new MapTileBuilder(bkFloorBottomYellowFrame);

                mapTiles.add(bkFloorBottomYellowTile);

                  //bobcat logo floor
                Frame bobcatFloor1Frame = new FrameBuilder(getSubImage(5, 19))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bobcatFloor1Tile = new MapTileBuilder(bobcatFloor1Frame);

                mapTiles.add(bobcatFloor1Tile);

                  //bobcat logo floor
                Frame bobcatFloor2Frame = new FrameBuilder(getSubImage(5, 18))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bobcatFloor2Tile = new MapTileBuilder(bobcatFloor2Frame);

                mapTiles.add(bobcatFloor2Tile);

                  //bobcat logo floor
                Frame bobcatFloor3Frame = new FrameBuilder(getSubImage(5, 17))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bobcatFloor3Tile = new MapTileBuilder(bobcatFloor3Frame);

                mapTiles.add(bobcatFloor3Tile);

                  //bobcat logo floor
                Frame bobcatFloor4Frame = new FrameBuilder(getSubImage(6, 17))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bobcatFloor4Tile = new MapTileBuilder(bobcatFloor4Frame);

                mapTiles.add(bobcatFloor4Tile);

                  //bobcat logo floor
                Frame bobcatFloor5Frame = new FrameBuilder(getSubImage(6, 18))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bobcatFloor5Tile = new MapTileBuilder(bobcatFloor5Frame);

                mapTiles.add(bobcatFloor5Tile);

                  //bobcat logo floor
                Frame bobcatFloor6Frame = new FrameBuilder(getSubImage(6, 19))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bobcatFloor6Tile = new MapTileBuilder(bobcatFloor6Frame);

                mapTiles.add(bobcatFloor6Tile);

                  //bobcat logo floor
                Frame bobcatFloor7Frame = new FrameBuilder(getSubImage(7, 18))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bobcatFloor7Tile = new MapTileBuilder(bobcatFloor7Frame);

                mapTiles.add(bobcatFloor7Tile);

                    //bobcat logo floor
                Frame bobcatFloor8Frame = new FrameBuilder(getSubImage(7, 17))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bobcatFloor8Tile = new MapTileBuilder(bobcatFloor8Frame);

                mapTiles.add(bobcatFloor8Tile);

                    //bobcat logo floor
                Frame bobcatFloor9Frame = new FrameBuilder(getSubImage(7, 19))
                                .withScale(tileScale)
                                .build();

                MapTileBuilder bobcatFloor9Tile = new MapTileBuilder(bobcatFloor9Frame);

                mapTiles.add(bobcatFloor9Tile);

                 // bk floor chair
                Frame orientationChairFrame = new FrameBuilder(getSubImage(10, 17))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder orientationChairTile = new MapTileBuilder(bkFloorFrame)
                                .withTopLayer(orientationChairFrame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(orientationChairTile);

                 // bk floor chair
                Frame orientationChair2Frame = new FrameBuilder(getSubImage(10, 17))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder orientationChair2Tile = new MapTileBuilder(bobcatFloor9Frame)
                                .withTopLayer(orientationChair2Frame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(orientationChair2Tile);

                  // bk floor chair
                Frame orientationChair3Frame = new FrameBuilder(getSubImage(10, 17))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder orientationChair3Tile = new MapTileBuilder(bobcatFloor6Frame)
                                .withTopLayer(orientationChair3Frame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(orientationChair3Tile);

                  // bk floor chair
                Frame orientationChair4Frame = new FrameBuilder(getSubImage(10, 17))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder orientationChair4Tile = new MapTileBuilder(bobcatFloor1Frame)
                                .withTopLayer(orientationChair4Frame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(orientationChair4Tile);

                  // bk floor chair
                Frame orientationChair5Frame = new FrameBuilder(getSubImage(10, 17))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder orientationChair5Tile = new MapTileBuilder(bobcatFloor6Frame)
                                .withTopLayer(orientationChair5Frame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(orientationChair5Tile);

                 // bk floor chair
                Frame orientationChair6Frame = new FrameBuilder(getSubImage(10, 17))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder orientationChair6Tile = new MapTileBuilder(bobcatFloor4Frame)
                                .withTopLayer(orientationChair6Frame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(orientationChair6Tile);

                 // bk floor chair
                Frame orientationChair7Frame = new FrameBuilder(getSubImage(10, 17))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder orientationChair7Tile = new MapTileBuilder(bobcatFloor3Frame)
                                .withTopLayer(orientationChair7Frame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(orientationChair7Tile);

                  // bk floor chair
                Frame orientationChair8Frame = new FrameBuilder(getSubImage(10, 17))
                                .withScale(tileScale)
                                .withBounds(0, 6, 16, 4)
                                .build();

                MapTileBuilder orientationChair8Tile = new MapTileBuilder(bobcatFloor8Frame)
                                .withTopLayer(orientationChair8Frame)
                                .withTileType(TileType.NOT_PASSABLE);

                mapTiles.add(orientationChair8Tile);

















                // tree trunk with full hole
                // Frame treeTrunkWithFullHoleFrame = new FrameBuilder(getSubImage(2, 2))
                // .withScale(tileScale)
                // .build();

                // MapTileBuilder treeTrunkWithFullHoleTile = new MapTileBuilder(grassFrame)
                // .withTopLayer(treeTrunkWithFullHoleFrame)
                // .withTileType(TileType.PASSABLE);

                // mapTiles.add(treeTrunkWithFullHoleTile);

                // left end branch
                // Frame leftEndBranchFrame = new FrameBuilder(getSubImage(2, 4))
                // .withScale(tileScale)
                // .withBounds(0, 6, 16, 4)
                // .build();

                // MapTileBuilder leftEndBranchTile = new MapTileBuilder(grassFrame)
                // .withTopLayer(leftEndBranchFrame)
                // .withTileType(TileType.PASSABLE);

                // mapTiles.add(leftEndBranchTile);

                // right end branch
                // Frame rightEndBranchFrame = new FrameBuilder(getSubImage(2, 4))
                // .withScale(tileScale)
                // .withBounds(0, 6, 16, 4)
                // .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                // .build();

                // MapTileBuilder rightEndBranchTile = new MapTileBuilder(grassFrame)
                // .withTopLayer(rightEndBranchFrame)
                // .withTileType(TileType.PASSABLE);

                // mapTiles.add(rightEndBranchTile);

                // tree trunk
                // Frame treeTrunkFrame = new FrameBuilder(getSubImage(1, 0))
                // .withScale(tileScale)
                // .build();

                // MapTileBuilder treeTrunkTile = new MapTileBuilder(grassFrame)
                // .withTopLayer(treeTrunkFrame)
                // .withTileType(TileType.PASSABLE);

                // mapTiles.add(treeTrunkTile);

                // tree top leaves
                // Frame treeTopLeavesFrame = new FrameBuilder(getSubImage(1, 1))
                // .withScale(tileScale)
                // .build();

                // MapTileBuilder treeTopLeavesTile = new MapTileBuilder(grassFrame)
                // .withTopLayer(treeTopLeavesFrame)
                // .withTileType(TileType.PASSABLE);

                // mapTiles.add(treeTopLeavesTile);

                // yellow flower
                // Frame[] yellowFlowerFrames = new Frame[] {
                // new FrameBuilder(getSubImage(1, 2), 65)
                // .withScale(tileScale)
                // .build(),
                // new FrameBuilder(getSubImage(1, 3), 65)
                // .withScale(tileScale)
                // .build(),
                // new FrameBuilder(getSubImage(1, 2), 65)
                // .withScale(tileScale)
                // .build(),
                // new FrameBuilder(getSubImage(1, 4), 65)
                // .withScale(tileScale)
                // .build()
                // };

                // MapTileBuilder yellowFlowerTile = new MapTileBuilder(yellowFlowerFrames);

                // mapTiles.add(yellowFlowerTile);

                // purple flower
                // Frame[] purpleFlowerFrames = new Frame[] {
                // new FrameBuilder(getSubImage(0, 2), 65)
                // .withScale(tileScale)
                // .build(),
                // new FrameBuilder(getSubImage(0, 3), 65)
                // .withScale(tileScale)
                // .build(),
                // new FrameBuilder(getSubImage(0, 2), 65)
                // .withScale(tileScale)
                // .build(),
                // new FrameBuilder(getSubImage(0, 4), 65)
                // .withScale(tileScale)
                // .build()
                // };

                // MapTileBuilder purpleFlowerTile = new MapTileBuilder(purpleFlowerFrames);

                // mapTiles.add(purpleFlowerTile);

                // middle branch
                // Frame middleBranchFrame = new FrameBuilder(getSubImage(2, 3))
                // .withScale(tileScale)
                // .withBounds(0, 6, 16, 4)
                // .build();

                // MapTileBuilder middleBranchTile = new MapTileBuilder(grassFrame)
                // .withTopLayer(middleBranchFrame)
                // .withTileType(TileType.PASSABLE);

                // mapTiles.add(middleBranchTile);

                // tree trunk bottom
                // Frame treeTrunkBottomFrame = new FrameBuilder(getSubImage(2, 0))
                // .withScale(tileScale)
                // .build();

                // MapTileBuilder treeTrunkBottomTile = new MapTileBuilder(treeTrunkBottomFrame)
                // .withTileType(TileType.NOT_PASSABLE);

                // mapTiles.add(treeTrunkBottomTile);

                // mushrooms
                // Frame mushroomFrame = new FrameBuilder(getSubImage(2, 1))
                // .withScale(tileScale)
                // .build();

                // MapTileBuilder mushroomTile = new MapTileBuilder(mushroomFrame)
                // .withTileType(TileType.PASSABLE);

                // mapTiles.add(mushroomTile);

                // grey rock
                // Frame greyRockFrame = new FrameBuilder(getSubImage(3, 2))
                // .withScale(tileScale)
                // .build();

                // MapTileBuilder greyRockTile = new MapTileBuilder(greyRockFrame)
                // .withTileType(TileType.PASSABLE);

                // mapTiles.add(greyRockTile);

                // house body
                // Frame houseBodyFrame = new FrameBuilder(getSubImage(3, 4))
                // .withScale(tileScale)
                // .build();

                // MapTileBuilder houseBodyTile = new MapTileBuilder(houseBodyFrame)
                // .withTileType(TileType.NOT_PASSABLE);

                // mapTiles.add(houseBodyTile);

                // house roof body
                // Frame houseRoofBodyFrame = new FrameBuilder(getSubImage(4, 0))
                // .withScale(tileScale)
                // .build();

                // MapTileBuilder houseRoofBodyTile = new MapTileBuilder(grassFrame)
                // .withTopLayer(houseRoofBodyFrame)
                // .withTileType(TileType.PASSABLE);

                // mapTiles.add(houseRoofBodyTile);

                // left house roof
                // Frame leftHouseRoofFrame = new FrameBuilder(getSubImage(4, 1))
                // .withScale(tileScale)
                // .build();

                // MapTileBuilder leftHouseRoofTile = new MapTileBuilder(grassFrame)
                // .withTopLayer(leftHouseRoofFrame)
                // .withTileType(TileType.PASSABLE);

                // mapTiles.add(leftHouseRoofTile);

                // right house roof
                // Frame rightHouseRoofFrame = new FrameBuilder(getSubImage(4, 1))
                // .withScale(tileScale)
                // .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                // .build();

                // MapTileBuilder rightHouseRoofTile = new MapTileBuilder(grassFrame)
                // .withTopLayer(rightHouseRoofFrame)
                // .withTileType(TileType.PASSABLE);

                // mapTiles.add(rightHouseRoofTile);

                // left window
                // Frame leftWindowFrame = new FrameBuilder(getSubImage(4, 2))
                // .withScale(tileScale)
                // .build();

                // MapTileBuilder leftWindowTile = new MapTileBuilder(leftWindowFrame)
                // .withTileType(TileType.NOT_PASSABLE);

                // mapTiles.add(leftWindowTile);

                // right window
                // Frame rightWindowFrame = new FrameBuilder(getSubImage(4, 2))
                // .withScale(tileScale)
                // .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                // .build();

                // MapTileBuilder rightWindowTile = new MapTileBuilder(rightWindowFrame)
                // .withTileType(TileType.NOT_PASSABLE);

                // mapTiles.add(rightWindowTile);

                // door
                // Frame doorFrame = new FrameBuilder(getSubImage(4, 3))
                // .withScale(tileScale)
                // .build();

                // MapTileBuilder doorTile = new MapTileBuilder(doorFrame)
                // .withTileType(TileType.NOT_PASSABLE);

                // mapTiles.add(doorTile);

                // top water
                // Frame[] topWaterFrames = new Frame[] {
                // new FrameBuilder(getSubImage(5, 0), 65)
                // .withScale(tileScale)
                // .build(),
                // new FrameBuilder(getSubImage(5, 1), 65)
                // .withScale(tileScale)
                // .build(),
                // new FrameBuilder(getSubImage(5, 2), 65)
                // .withScale(tileScale)
                // .build(),
                // new FrameBuilder(getSubImage(5, 1), 65)
                // .withScale(tileScale)
                // .build(),
                // new FrameBuilder(getSubImage(5, 0), 65)
                // .withScale(tileScale)
                // .build(),
                // new FrameBuilder(getSubImage(5, 3), 65)
                // .withScale(tileScale)
                // .build(),
                // new FrameBuilder(getSubImage(5, 4), 65)
                // .withScale(tileScale)
                // .build(),
                // new FrameBuilder(getSubImage(5, 3), 65)
                // .withScale(tileScale)
                // .build()
                // };

                // MapTileBuilder topWaterTile = new MapTileBuilder(topWaterFrames)
                // .withTileType(TileType.NOT_PASSABLE);

                // mapTiles.add(topWaterTile);

                return mapTiles;
        }
}
