package edu.umb.cs680.hw06;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

public class DrawerClosedNotPlayingTest {
    private static DVDPlayer player;
    private static DrawerClosedNotPlaying drawerClosedNotPlaying;

    @BeforeAll
    public static void setUp() {
        player = DVDPlayer.getInstance();
        drawerClosedNotPlaying = DrawerClosedNotPlaying.getInstance();
    }

    @Test
    public void singletonTest() {
        DrawerClosedNotPlaying drawerClosedNotPlaying2 = DrawerClosedNotPlaying.getInstance();
        assertSame(drawerClosedNotPlaying, drawerClosedNotPlaying2);
    }

    @Test
    public void openClosedButtonPushed() {
        // expect to print: drawer is open.
        player.changeState(drawerClosedNotPlaying);
        drawerClosedNotPlaying.openCloseButtonPushed(player);
        assertSame(player.getState(), DrawerOpen.getInstance());
    }

    @Test
    public void playButtonPushed() {
        // expect to print: video is playing.
        player.changeState(drawerClosedNotPlaying);
        drawerClosedNotPlaying.playButtonPushed(player);
        assertSame(player.getState(), DrawerClosedPlaying.getInstance());
    }

    @Test
    public void stopButtonPushed() {
        // expect to print: None
        player.changeState(drawerClosedNotPlaying);
        drawerClosedNotPlaying.stopButtonPushed(player);
        assertSame(player.getState(), drawerClosedNotPlaying);
    }
}
