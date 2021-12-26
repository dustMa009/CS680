package edu.umb.cs680.hw06;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DVDPlayerTest {
    @Test
    public void getInstance_SingletonTest() {
        DVDPlayer player1 = DVDPlayer.getInstance();
        DVDPlayer player2 = DVDPlayer.getInstance();
        assertSame(player1, player2);
    }

    @Test
    public void getStateTest() {
        DVDPlayer player = DVDPlayer.getInstance();
        assertSame(player.getState(), DrawerClosedNotPlaying.getInstance());
    }

    @Test
    public void changeStates() {
        DVDPlayer player = DVDPlayer.getInstance();
        player.changeState(DrawerOpen.getInstance());
        assertSame(player.getState(), DrawerOpen.getInstance());
        player.changeState(DrawerClosedPlaying.getInstance());
        assertSame(player.getState(), DrawerClosedPlaying.getInstance());
    }

    @Test
    public void OpenCloseButtonPushed() {
        DVDPlayer player = DVDPlayer.getInstance();
        player.changeState(DrawerClosedPlaying.getInstance());
        // expect printing lines: Drawer is closed. Drawer is open.
        player.openCloseButtonPushed();
        assertSame(player.getState(), DrawerOpen.getInstance());

        // expect printing line: Drawer is closed.
        player.openCloseButtonPushed();
        assertSame(player.getState(), DrawerClosedNotPlaying.getInstance());

        // expect printing line: Drawer is open.
        player.openCloseButtonPushed();
        assertSame(player.getState(), DrawerOpen.getInstance());
    }

    @Test
    public void playButtonPushed() {
        DVDPlayer player = DVDPlayer.getInstance();
        player.changeState(DrawerOpen.getInstance());
        // expect printing line: Drawer is closed. Video is playing.
        player.playButtonPushed();
        assertSame(player.getState(), DrawerClosedPlaying.getInstance());

        // expect printing line: None.
        player.playButtonPushed();
        assertSame(player.getState(), DrawerClosedPlaying.getInstance());

        // expect printing line: Video is playing.
        player.changeState(DrawerClosedNotPlaying.getInstance());
        player.playButtonPushed();
        assertSame(player.getState(), DrawerClosedPlaying.getInstance());
    }

    @Test
    public void stopButtonPushed() {
        DVDPlayer player = DVDPlayer.getInstance();
        player.changeState(DrawerClosedPlaying.getInstance());
        // expect printing line: Video stopped playing.
        player.stopButtonPushed();
        assertSame(player.getState(), DrawerClosedNotPlaying.getInstance());

        // expect printing line: None
        player.stopButtonPushed();
        assertSame(player.getState(), DrawerClosedNotPlaying.getInstance());

        // expect printing line: None
        player.changeState(DrawerOpen.getInstance());
        assertSame(player.getState(), DrawerOpen.getInstance());
    }
}
