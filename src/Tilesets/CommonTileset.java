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
                .withBounds(0, 6, 16, 4)
               .build();

        MapTileBuilder grassPiecesTile = new MapTileBuilder(grassFrame)
               .withTopLayer(grassPiecesFrame)
                .withTileType(TileType.PASSABLE);

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

        MapTileBuilder sewerTile = new MapTileBuilder(grassFrame)
               .withTopLayer(sewerFrame)
               .withTileType(TileType.PASSABLE);

       mapTiles.add(sewerTile);

        // lawn chair front
       Frame lawnChairFrontFrame = new FrameBuilder(getSubImage(10, 5))
               .withScale(tileScale)
               .build();

        MapTileBuilder lawnChairFrontTile = new MapTileBuilder(grassFrame)
               .withTopLayer(lawnChairFrontFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(lawnChairFrontTile);

       // lawn chair side
       Frame lawnChairSideFrame = new FrameBuilder(getSubImage(10, 4))
               .withScale(tileScale)
               .build();

        MapTileBuilder lawnChairSideTile = new MapTileBuilder(grassFrame)
               .withTopLayer(lawnChairSideFrame)
               .withTileType(TileType.NOT_PASSABLE);

       mapTiles.add(lawnChairSideTile);

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
               .withBounds(0, 6, 16, 4)
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

        MapTileBuilder brickOutlinerRightTile = new MapTileBuilder(horizontalBrickFrame)
               .withTopLayer(brickOutlinerRightFrame)
               .withTileType(TileType.PASSABLE);

       mapTiles.add(brickOutlinerRightTile);

       // brick outline left side
       Frame brickOutlineLeftFrame = new FrameBuilder(getSubImage(6, 6))
               .withScale(tileScale)
               .build();

        MapTileBuilder brickOutlineLeftTile = new MapTileBuilder(horizontalBrickFrame)
               .withTopLayer(brickOutlineLeftFrame)
               .withTileType(TileType.PASSABLE);

       mapTiles.add(brickOutlineLeftTile);

        // brick outline top
       Frame brickOutlineTopFrame = new FrameBuilder(getSubImage(7, 6))
               .withScale(tileScale)
               .build();

        MapTileBuilder brickOutlineTopTile = new MapTileBuilder(horizontalBrickFrame)
               .withTopLayer(brickOutlineTopFrame)
               .withTileType(TileType.PASSABLE);

       mapTiles.add(brickOutlineTopTile);

       // brick outline bottom
       Frame brickOutlineBottomFrame = new FrameBuilder(getSubImage(8, 6))
               .withScale(tileScale)
               .build();

        MapTileBuilder brickOutlineBottomTile = new MapTileBuilder(horizontalBrickFrame)
               .withTopLayer(brickOutlineBottomFrame)
               .withTileType(TileType.PASSABLE);

       mapTiles.add(brickOutlineBottomTile);






      


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
