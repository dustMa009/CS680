package edu.umb.cs680.hw06;

public class DVDPlayer {
    private State state;
    private static DVDPlayer instance = null;

    private DVDPlayer(){
        state = DrawerClosedNotPlaying.getInstance();
    };

    public static DVDPlayer getInstance(){
        if (instance == null) {
            instance = new DVDPlayer();
        }
        return instance;
    }

    public void openCloseButtonPushed() {
        state.openCloseButtonPushed(this);
    }

    public void  playButtonPushed() {
        state.playButtonPushed(this);
    }

    public void stopButtonPushed() {
        state.stopButtonPushed(this);
    }

    public void changeState(State newState) {
        this.state = newState;
    }

    public State getState() {
        return state;
    }

    public void open() {
        System.out.println("Drawer is open.");
    }

    public void close() {
        System.out.println("Drawer is closed.");
    }

    public void play() {
        System.out.println("Video is playing.");
    }

    public void stop() {
        System.out.println("Video stopped playing.");
    }
}
