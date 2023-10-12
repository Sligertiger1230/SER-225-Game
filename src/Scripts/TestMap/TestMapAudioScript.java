package Scripts.TestMap;

import Level.*;

public class TestMapAudioScript extends Script<MapEntity> {

    private float previousX;
    private float previousY;
    private TileDetector tileDetector; // Reference to TileDetector

    public TestMapAudioScript(MapEntity entity, TileDetector tileDetector) {
        this.entity = entity;
        this.tileDetector = tileDetector;
    }

    @Override
    protected void setup() {
        Audio.playBackgroundMusic();
        previousX = entity.getX();
        previousY = entity.getY();
    }

    @Override
    protected void cleanup() {

    }

    @Override
    public ScriptState execute() {
        float currentX = entity.getX();
        float currentY = entity.getY();

        if (currentX != previousX || currentY != previousY) {
            TileType currentTileType = tileDetector.detectTileTypeAtPosition(currentX, currentY);

            switch (currentTileType) {
                case GRASS:
                    Audio.playFootstepSound(AudioState.GRASS);
                    break;
                case BRICK:
                    Audio.playFootstepSound(AudioState.BRICK);
                    break;
                case CARPET:
                    Audio.playFootstepSound(AudioState.CARPET);
                    break;
                default:
                    Audio.playFootstepSound(AudioState.GRASS);
                    break;
            }

            previousX = currentX;
            previousY = currentY;
        }

        return ScriptState.RUNNING;
    }
}
