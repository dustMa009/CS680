package edu.umb.cs680.hw05;

public class DrawerClosedPlaying implements State {
    private static DrawerClosedPlaying instance = null;
    private DrawerClosedPlaying(){};
    public static DrawerClosedPlaying getInstance() {
        if (instance == null) {
            instance = new DrawerClosedPlaying();
        }
        return instance;
    }

    @Override
    public void openCloseButtonPushed(DVDPlayer player) {
        player.close();
        player.open();
        player.changeState(DrawerOpen.getInstance());
    }

    @Override
    public void playButtonPushed(DVDPlayer player) {
    }

    @Override
    public void stopButtonPushed(DVDPlayer player) {
        player.stop();
        player.changeState(DrawerClosedNotPlaying.getInstance());
    }
}
