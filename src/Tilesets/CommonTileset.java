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

        MapTileBuilder treeTrunkBaseTile = new MapTileBuilder(grassFrame)
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

        MapTileBuilder waterAlgaeTile = new MapTileBuilder(grassFrame)
               .withTopLayer(waterAlgaeFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(waterAlgaeTile);

        // water with ducks
       Frame waterDucksFrame = new FrameBuilder(getSubImage(6, 1))
               .withScale(tileScale)
               .build();

        MapTileBuilder waterDucksTile = new MapTileBuilder(grassFrame)
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

        MapTileBuilder brickWallTile = new MapTileBuilder(grassFrame)
               .withTopLayer(brickWallFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(brickWallTile);

         // brick wall
       Frame brickWallWithLightFrame = new FrameBuilder(getSubImage(9, 0))
               .withScale(tileScale)
               .build();

        MapTileBuilder brickWallWithLightTile = new MapTileBuilder(brickWallFrame)
               .withTopLayer(brickWallWithLightFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(brickWallWithLightTile);

        // glass panes base 
       Frame glassPanesBaseFrame = new FrameBuilder(getSubImage(9, 1))
               .withScale(tileScale)
               .build();

        MapTileBuilder glassPanesBaseTile = new MapTileBuilder(glassPanesBaseFrame)
               .withTopLayer(glassPanesBaseFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(glassPanesBaseTile);

        // glass panes top 
       Frame glassPanesTopFrame = new FrameBuilder(getSubImage(9, 2))
               .withScale(tileScale)
               .build();

        MapTileBuilder glassPanesTopTile = new MapTileBuilder(brickWallFrame)
               .withTopLayer(glassPanesTopFrame)
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
               .withTopLayer(glassPanesFourFrame)
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

        MapTileBuilder brickOutlineCornerBottomRightTile = new MapTileBuilder(brickOutlineCornerBottomRightFrame);

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

        MapTileBuilder brickWallDarkTile = new MapTileBuilder(grassFrame)
               .withTopLayer(brickWallDarkFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(brickWallDarkTile);

        // brick wall border
       Frame brickWallBorderFrame = new FrameBuilder(getSubImage(14, 0))
               .withScale(tileScale)
               .build();

        MapTileBuilder brickWallBorderTile = new MapTileBuilder(horizontalBrickFrame)
               .withTopLayer(brickWallBorderFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(brickWallBorderTile);

        // brick wall light
       Frame brickWallLightFrame = new FrameBuilder(getSubImage(13, 6))
               .withScale(tileScale)
               .build();

        MapTileBuilder brickWallLightTile = new MapTileBuilder(grassFrame)
               .withTopLayer(brickWallLightFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(brickWallLightTile);

       //  flag pole base
       Frame flagPoleBaseFrame = new FrameBuilder(getSubImage(8, 7))
               .withScale(tileScale)
               .withBounds(0, 6, 16, 4)
               .build();

        MapTileBuilder flagPoleBaseTile = new MapTileBuilder(grassFrame)
               .withTopLayer(flagPoleBaseFrame)
               .withTileType(TileType.PASSABLE);

       mapTiles.add(flagPoleBaseTile);

       //  flag pole base 2 
       Frame flagPoleBase2Frame = new FrameBuilder(getSubImage(8, 7))
               .withScale(tileScale)
               .withBounds(0, 6, 16, 4)
               .build();

        MapTileBuilder flagPoleBase2Tile = new MapTileBuilder(brickOutlineBottomFrame)
               .withTopLayer(flagPoleBase2Frame)
               .withTileType(TileType.PASSABLE);

       mapTiles.add(flagPoleBase2Tile);

        //  flag pole base 3 
       Frame flagPoleBase3Frame = new FrameBuilder(getSubImage(8, 7))
               .withScale(tileScale)
               .withBounds(0, 6, 16, 4)
               .build();

        MapTileBuilder flagPoleBase3Tile = new MapTileBuilder(horizontalBrickFrame)
               .withTopLayer(flagPoleBase3Frame)
               .withTileType(TileType.PASSABLE);

       mapTiles.add(flagPoleBase3Tile);

        //  usa flag
       Frame usaFlagFrame = new FrameBuilder(getSubImage(7, 7))
               .withScale(tileScale)
               .withBounds(0, 6, 16, 4)
               .build();

        MapTileBuilder usaFlagTile = new MapTileBuilder(horizontalBrickFrame)
               .withTopLayer(usaFlagFrame)
               .withTileType(TileType.PASSABLE);

       mapTiles.add(usaFlagTile);

        //  usa flag topper
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

        MapTileBuilder studentCenterDoorTile = new MapTileBuilder(brickWallFrame)
               .withTopLayer(studentCenterDoorFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(studentCenterDoorTile);


        // whiteboard bottom no markers
       Frame whiteboardBottomFrame = new FrameBuilder(getSubImage(15, 7))
               .withScale(tileScale)
               .build();

        MapTileBuilder whiteboardBottomTile = new MapTileBuilder(whiteboardBottomFrame)
               .withTopLayer(whiteboardBottomFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(whiteboardBottomTile);

        // whiteboard bottom red marker
       Frame whiteboardBottomRedFrame = new FrameBuilder(getSubImage(0, 8))
               .withScale(tileScale)
               .build();

        MapTileBuilder whiteboardBottomRedTile = new MapTileBuilder(whiteboardBottomRedFrame)
               .withTopLayer(whiteboardBottomRedFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(whiteboardBottomRedTile);

        // whiteboard bottom blue marker
       Frame whiteboardBottomBlueFrame = new FrameBuilder(getSubImage(1, 8))
               .withScale(tileScale)
               .build();

        MapTileBuilder whiteboardBottomBlueTile = new MapTileBuilder(whiteboardBottomBlueFrame)
               .withTopLayer(whiteboardBottomBlueFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(whiteboardBottomBlueTile);

         // whiteboard top no marks
       Frame whiteboardTopFrame = new FrameBuilder(getSubImage(14, 7))
               .withScale(tileScale)
               .build();

        MapTileBuilder whiteboardTopTile = new MapTileBuilder(whiteboardTopFrame)
               .withTopLayer(whiteboardTopFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(whiteboardTopTile);

         // cceDoor bottom
       Frame cceDoorBottomFrame = new FrameBuilder(getSubImage(11, 8))
               .withScale(tileScale)
               .build();

        MapTileBuilder cceDoorBottomTile = new MapTileBuilder(cceDoorBottomFrame)
               .withTopLayer(cceDoorBottomFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(cceDoorBottomTile);


         // cceDoor left top
       Frame cceDoorLeftTopFrame = new FrameBuilder(getSubImage(8, 8))
               .withScale(tileScale)
               .build();

        MapTileBuilder cceDoorLeftTopTile = new MapTileBuilder(cceDoorLeftTopFrame)
               .withTopLayer(cceDoorLeftTopFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(cceDoorLeftTopTile);

         // cceDoor right top
       Frame cceDoorRightTopFrame = new FrameBuilder(getSubImage(10, 8))
               .withScale(tileScale)
               .build();

        MapTileBuilder cceDoorRightTopTile = new MapTileBuilder(cceDoorRightTopFrame)
               .withTopLayer(cceDoorRightTopFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(cceDoorRightTopTile);

         // cceDoor right top
       Frame cceWallFrame = new FrameBuilder(getSubImage(7, 8))
               .withScale(tileScale)
               .build();

        MapTileBuilder cceWallTile = new MapTileBuilder(cceWallFrame)
               .withTopLayer(cceWallFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(cceWallTile);

        //  cce pole
       Frame ccePoleBaseFrame = new FrameBuilder(getSubImage(6, 8))
               .withScale(tileScale)
               .withBounds(0, 6, 16, 4)
               .build();

        MapTileBuilder ccePoleBaseTile = new MapTileBuilder(cceFloorFrame)
               .withTopLayer(ccePoleBaseFrame)
               .withTileType(TileType.PASSABLE);

       mapTiles.add(ccePoleBaseTile);

        //  cce pole with screen right side
       Frame ccePoleRightScreenSideFrame = new FrameBuilder(getSubImage(5, 8))
               .withScale(tileScale)
               .withBounds(0, 6, 16, 4)
               .build();

        MapTileBuilder ccePoleRightScreenSideTile = new MapTileBuilder(cceFloorFrame)
               .withTopLayer(ccePoleRightScreenSideFrame)
               .withTileType(TileType.PASSABLE);

       mapTiles.add(ccePoleRightScreenSideTile);

        //  cce pole with screen right side
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

        MapTileBuilder studentCenterRoofOutlineLeftCornerTile = new MapTileBuilder(studentCenterRoofOutlineLeftCornerFrame)
               .withTopLayer(studentCenterRoofOutlineLeftCornerFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(studentCenterRoofOutlineLeftCornerTile);

        // student center roof outline right corner
       Frame studentCenterRoofOutlineRightCornerFrame = new FrameBuilder(getSubImage(9, 9))
               .withScale(tileScale)
               .build();

        MapTileBuilder studentCenterRoofOutlineRightCornerTile = new MapTileBuilder(studentCenterRoofOutlineRightCornerFrame)
               .withTopLayer(studentCenterRoofOutlineRightCornerFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(studentCenterRoofOutlineRightCornerTile);

       // student center roof outline bottom 
       Frame studentCenterRoofOutlineBottomFrame = new FrameBuilder(getSubImage(8, 9))
               .withScale(tileScale)
               .build();

        MapTileBuilder studentCenterRoofOutlineBottomTile = new MapTileBuilder(studentCenterRoofOutlineBottomFrame)
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

        MapTileBuilder studentCenterRoofOutlineRightTile = new MapTileBuilder(studentCenterRoofOutlineRightFrame)
               .withTopLayer(studentCenterRoofOutlineRightFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(studentCenterRoofOutlineRightTile);

        // student center roof outline left top corner 
       Frame studentCenterRoofOutlineLeftTopCornerFrame = new FrameBuilder(getSubImage(6, 10))
               .withScale(tileScale)
               .build();

        MapTileBuilder studentCenterRoofOutlineLeftTopCornerTile = new MapTileBuilder(studentCenterRoofOutlineLeftTopCornerFrame)
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
               .withTopLayer(arcRightBottomFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(arcRightBottomTile);

          // arc left bottom
       Frame arcLeftBottomFrame = new FrameBuilder(getSubImage(6, 11))
               .withScale(tileScale)
               .build();

        MapTileBuilder arcLeftBottomTile = new MapTileBuilder(brickWallFrame)
               .withTopLayer(arcLeftBottomFrame)
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














      


        // tree trunk with full hole
       // Frame treeTrunkWithFullHoleFrame = new FrameBuilder(getSubImage(2, 2))
        //        .withScale(tileScale)
        //        .build();

        //MapTileBuilder treeTrunkWithFullHoleTile = new MapTileBuilder(grassFrame)
        //        .withTopLayer(treeTrunkWithFullHoleFrame)
        //        .withTileType(TileType.PASSABLE);

        // mapTiles.add(treeTrunkWithFullHoleTile);

        // left end branch
        //Frame leftEndBranchFrame = new FrameBuilder(getSubImage(2, 4))
        //        .withScale(tileScale)
        //        .withBounds(0, 6, 16, 4)
        //        .build();

       // MapTileBuilder leftEndBranchTile = new MapTileBuilder(grassFrame)
       //         .withTopLayer(leftEndBranchFrame)
        //        .withTileType(TileType.PASSABLE);

       // mapTiles.add(leftEndBranchTile);

        // right end branch
       // Frame rightEndBranchFrame = new FrameBuilder(getSubImage(2, 4))
       //         .withScale(tileScale)
       //         .withBounds(0, 6, 16, 4)
       //         .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
        //        .build();

       // MapTileBuilder rightEndBranchTile = new MapTileBuilder(grassFrame)
       //         .withTopLayer(rightEndBranchFrame)
       //         .withTileType(TileType.PASSABLE);

       // mapTiles.add(rightEndBranchTile);
        
        // tree trunk
       // Frame treeTrunkFrame = new FrameBuilder(getSubImage(1, 0))
       //         .withScale(tileScale)
       //         .build();

      //  MapTileBuilder treeTrunkTile = new MapTileBuilder(grassFrame)
       //         .withTopLayer(treeTrunkFrame)
       //         .withTileType(TileType.PASSABLE);

       // mapTiles.add(treeTrunkTile);

        // tree top leaves
       // Frame treeTopLeavesFrame = new FrameBuilder(getSubImage(1, 1))
       //         .withScale(tileScale)
       //         .build();

      //  MapTileBuilder treeTopLeavesTile = new MapTileBuilder(grassFrame)
      //          .withTopLayer(treeTopLeavesFrame)
      //          .withTileType(TileType.PASSABLE);

      //  mapTiles.add(treeTopLeavesTile);
        
        // yellow flower
      //  Frame[] yellowFlowerFrames = new Frame[] {
       //         new FrameBuilder(getSubImage(1, 2), 65)
       //             .withScale(tileScale)
        //            .build(),
        //        new FrameBuilder(getSubImage(1, 3), 65)
        //                .withScale(tileScale)
        //                .build(),
        //        new FrameBuilder(getSubImage(1, 2), 65)
        //                .withScale(tileScale)
        //                .build(),
         //       new FrameBuilder(getSubImage(1, 4), 65)
        //                .withScale(tileScale)
        //                .build()
        //};

       // MapTileBuilder yellowFlowerTile = new MapTileBuilder(yellowFlowerFrames);

        //mapTiles.add(yellowFlowerTile);

        // purple flower
       // Frame[] purpleFlowerFrames = new Frame[] {
       //         new FrameBuilder(getSubImage(0, 2), 65)
        //                .withScale(tileScale)
       //                 .build(),
       //        new FrameBuilder(getSubImage(0, 3), 65)
       //                 .withScale(tileScale)
        //                .build(),
        //        new FrameBuilder(getSubImage(0, 2), 65)
        //                .withScale(tileScale)
        //                .build(),
        //        new FrameBuilder(getSubImage(0, 4), 65)
        //                .withScale(tileScale)
        //                .build()
      //  };

       // MapTileBuilder purpleFlowerTile = new MapTileBuilder(purpleFlowerFrames);

       // mapTiles.add(purpleFlowerTile);

        // middle branch
       // Frame middleBranchFrame = new FrameBuilder(getSubImage(2, 3))
        //        .withScale(tileScale)
        //        .withBounds(0, 6, 16, 4)
        //        .build();

       // MapTileBuilder middleBranchTile = new MapTileBuilder(grassFrame)
       //         .withTopLayer(middleBranchFrame)
        //        .withTileType(TileType.PASSABLE);

       // mapTiles.add(middleBranchTile);

        // tree trunk bottom
        //Frame treeTrunkBottomFrame = new FrameBuilder(getSubImage(2, 0))
        //        .withScale(tileScale)
       //         .build();

        //MapTileBuilder treeTrunkBottomTile = new MapTileBuilder(treeTrunkBottomFrame)
        //        .withTileType(TileType.NOT_PASSABLE);

        //mapTiles.add(treeTrunkBottomTile);

        // mushrooms
       // Frame mushroomFrame = new FrameBuilder(getSubImage(2, 1))
       //         .withScale(tileScale)
       //         .build();

       // MapTileBuilder mushroomTile = new MapTileBuilder(mushroomFrame)
       //         .withTileType(TileType.PASSABLE);

        // mapTiles.add(mushroomTile);


        // grey rock
        //Frame greyRockFrame = new FrameBuilder(getSubImage(3, 2))
        //        .withScale(tileScale)
       //         .build();

       // MapTileBuilder greyRockTile = new MapTileBuilder(greyRockFrame)
       //         .withTileType(TileType.PASSABLE);

       // mapTiles.add(greyRockTile);

        // house body
       // Frame houseBodyFrame = new FrameBuilder(getSubImage(3, 4))
       //         .withScale(tileScale)
        //        .build();

       // MapTileBuilder houseBodyTile = new MapTileBuilder(houseBodyFrame)
       //         .withTileType(TileType.NOT_PASSABLE);

       // mapTiles.add(houseBodyTile);

        // house roof body
       //Frame houseRoofBodyFrame = new FrameBuilder(getSubImage(4, 0))
       //         .withScale(tileScale)
       //        .build();

       // MapTileBuilder houseRoofBodyTile = new MapTileBuilder(grassFrame)
       //         .withTopLayer(houseRoofBodyFrame)
       //         .withTileType(TileType.PASSABLE);

        //mapTiles.add(houseRoofBodyTile);

        // left house roof
        //Frame leftHouseRoofFrame = new FrameBuilder(getSubImage(4, 1))
        //        .withScale(tileScale)
       //         .build();

        //MapTileBuilder leftHouseRoofTile = new MapTileBuilder(grassFrame)
       //        .withTopLayer(leftHouseRoofFrame)
       //         .withTileType(TileType.PASSABLE);

       // mapTiles.add(leftHouseRoofTile);

        // right house roof
        //Frame rightHouseRoofFrame = new FrameBuilder(getSubImage(4, 1))
        //        .withScale(tileScale)
        //        .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
        //        .build();

        //MapTileBuilder rightHouseRoofTile = new MapTileBuilder(grassFrame)
        //        .withTopLayer(rightHouseRoofFrame)
        //        .withTileType(TileType.PASSABLE);

        //mapTiles.add(rightHouseRoofTile);

        // left window
       // Frame leftWindowFrame = new FrameBuilder(getSubImage(4, 2))
       //         .withScale(tileScale)
       //         .build();

       // MapTileBuilder leftWindowTile = new MapTileBuilder(leftWindowFrame)
       //         .withTileType(TileType.NOT_PASSABLE);

      //  mapTiles.add(leftWindowTile);

        // right window
       // Frame rightWindowFrame = new FrameBuilder(getSubImage(4, 2))
       //         .withScale(tileScale)
       //         .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
       //         .build();

       // MapTileBuilder rightWindowTile = new MapTileBuilder(rightWindowFrame)
      //          .withTileType(TileType.NOT_PASSABLE);

      //  mapTiles.add(rightWindowTile);

        // door
       // Frame doorFrame = new FrameBuilder(getSubImage(4, 3))
        //        .withScale(tileScale)
       //         .build();

       // MapTileBuilder doorTile = new MapTileBuilder(doorFrame)
       //         .withTileType(TileType.NOT_PASSABLE);

       // mapTiles.add(doorTile);

        // top water
       // Frame[] topWaterFrames = new Frame[] {
       //     new FrameBuilder(getSubImage(5, 0), 65)
       //             .withScale(tileScale)
       //             .build(),
       //     new FrameBuilder(getSubImage(5, 1), 65)
       //             .withScale(tileScale)
       //             .build(),
       //     new FrameBuilder(getSubImage(5, 2), 65)
       //             .withScale(tileScale)
       //             .build(),
       //     new FrameBuilder(getSubImage(5, 1), 65)
       //             .withScale(tileScale)
       //             .build(),
       //     new FrameBuilder(getSubImage(5, 0), 65)
       //             .withScale(tileScale)
       //             .build(),
       //     new FrameBuilder(getSubImage(5, 3), 65)
       //             .withScale(tileScale)
       //             .build(),
       //     new FrameBuilder(getSubImage(5, 4), 65)
       //             .withScale(tileScale)
       //             .build(),
       //     new FrameBuilder(getSubImage(5, 3), 65)
       //             .withScale(tileScale)
       //             .build()
       // };

       // MapTileBuilder topWaterTile = new MapTileBuilder(topWaterFrames)
       //         .withTileType(TileType.NOT_PASSABLE);

       // mapTiles.add(topWaterTile);


        return mapTiles;
    }
}
