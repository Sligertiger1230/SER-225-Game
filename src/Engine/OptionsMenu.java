package Engine;

import javax.swing.*;

import Level.Ambience;
import Level.Sound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsMenu extends JMenuBar {
    private JMenu optionsMenu;
    private JMenuItem fullscreenToggle;
    private JSlider musicVolumeSlider;
    private JSlider ambientVolumeSlider;
    private JMenuItem controlsList;

    private Sound soundManager;
    private Ambience ambienceManager;

    private boolean menuVisible;

    private JFrame gameWindow;

    public OptionsMenu() {
        optionsMenu = new JMenu("Options");

        // Fullscreen toggle button
        fullscreenToggle = new JMenuItem("Toggle Fullscreen");
        fullscreenToggle.addActionListener(e -> toggleFullscreen());
        optionsMenu.add(fullscreenToggle);

        // Volume slider for music
        musicVolumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        musicVolumeSlider.setMajorTickSpacing(10);
        musicVolumeSlider.setMinorTickSpacing(5);
        musicVolumeSlider.setPaintTicks(true);
        musicVolumeSlider.setPaintLabels(true);
        optionsMenu.add(new JLabel("Music Volume:"));
        optionsMenu.add(musicVolumeSlider);

        // Volume slider for ambient sounds
        ambientVolumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        ambientVolumeSlider.setMajorTickSpacing(10);
        ambientVolumeSlider.setMinorTickSpacing(5);
        ambientVolumeSlider.setPaintTicks(true);
        ambientVolumeSlider.setPaintLabels(true);
        optionsMenu.add(new JLabel("Ambient Volume:"));
        optionsMenu.add(ambientVolumeSlider);

        // List of controls
        controlsList = new JMenuItem("Controls");
        controlsList.addActionListener(e -> showControlsDialog());
        optionsMenu.add(controlsList);

        add(optionsMenu);

        // Initialize Sound and Ambience managers
        soundManager = new Sound();
        ambienceManager = new Ambience();

        // Adjust volume based on slider values
        musicVolumeSlider.addChangeListener(e -> updateMusicVolume());
        ambientVolumeSlider.addChangeListener(e -> updateAmbientVolume());

        menuVisible = false;
    }

    private void toggleFullscreen() {
        if (gameWindow != null) {
            if (gameWindow.getExtendedState() != JFrame.MAXIMIZED_BOTH) {
                gameWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
                gameWindow.setUndecorated(true); // Optionally make the window undecorated
            } else {
                gameWindow.setExtendedState(JFrame.NORMAL);
                gameWindow.setUndecorated(false); // Restore normal window mode with decorations
            }
        }
    }

    private void showControlsDialog() {
        // Display controls when the "Controls" menu item is clicked
        JLabel creditsLabel = new JLabel("Controls");
        JLabel escLabel = new JLabel("ESC = Options");
        JLabel upLabel = new JLabel("Up Arrow = Move Up");
        JLabel downLabel = new JLabel("Down Arrow = Move Down");
        JLabel leftLabel = new JLabel("Left Arrow = Move Left");
        JLabel rightLabel = new JLabel("Right Arrow = Move Right");
        JLabel shiftLabel = new JLabel("Shift = Sprint");
        JLabel questLabel = new JLabel("Q = Quest Menu");
        JLabel bikeLabel = new JLabel("E = Bike");

        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));
        controlsPanel.add(creditsLabel);
        controlsPanel.add(escLabel);
        controlsPanel.add(upLabel);
        controlsPanel.add(downLabel);
        controlsPanel.add(leftLabel);
        controlsPanel.add(rightLabel);
        controlsPanel.add(shiftLabel);
        controlsPanel.add(questLabel);
        controlsPanel.add(bikeLabel);

        JOptionPane.showMessageDialog(null, controlsPanel, "Controls List", JOptionPane.PLAIN_MESSAGE);
    }

    // Method to update music volume based on the slider value
    private void updateMusicVolume() {
        int volume = musicVolumeSlider.getValue();
        soundManager.setVolumeScale(volume); // Update the volume scale in Sound class
        soundManager.checkVolume(); // Apply the updated volume to the looping sound
    }

    // Method to update ambient sound volume based on the slider value
    private void updateAmbientVolume() {
        int volume = ambientVolumeSlider.getValue();
        ambienceManager.setVolumeScale(volume);
        ambienceManager.checkVolume(); // Apply the updated volume to the looping sound
    }

    public void toggleOptionsMenuVisibility() {
        menuVisible = !menuVisible;
        setVisible(menuVisible);
    }
}
