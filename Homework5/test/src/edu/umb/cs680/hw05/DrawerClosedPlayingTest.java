package edu.umb.cs680.hw05;
import static org.junit.jupiter.api.Assertions.*;

import edu.umb.cs680.hw05.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

public class DrawerClosedPlayingTest {
    private static DVDPlayer player;
    private static DrawerClosedPlaying drawerClosedPlaying;

    @BeforeAll
    public static void setUp() {
        player = DVDPlayer.getInstance();
        drawerClosedPlaying = DrawerClosedPlaying.getInstance();
    }

    @Test
    public void singletonTest() {
        DrawerClosedPlaying drawerClosedPlaying2 = DrawerClosedPlaying.getInstance();
        assertSame(drawerClosedPlaying, drawerClosedPlaying2);
    }

    @Test
    public void openCloseButtonPushed() {
        // expect to print: Drawer is closed. Drawer is open.
        player.changeState(drawerClosedPlaying);
        drawerClosedPlaying.openCloseButtonPushed(player);
        assertSame(player.getState(), DrawerOpen.getInstance());
    }

    @Test
    public void playButtonPushed() {
        // expect to print: None
        player.changeState(drawerClosedPlaying);
        drawerClosedPlaying.playButtonPushed(player);
        assertSame(player.getState(), drawerClosedPlaying);
    }

    @Test
    public void stopButtonPushed() {
        // expect to print: Video stopped playing.
        player.changeState(drawerClosedPlaying);
        drawerClosedPlaying.stopButtonPushed(player);
        assertSame(player.getState(), DrawerClosedNotPlaying.getInstance());
    }
}
