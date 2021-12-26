package edu.umb.cs680.hw06;

public interface State {
    public void openCloseButtonPushed(DVDPlayer player);
    public void playButtonPushed(DVDPlayer player);
    public void stopButtonPushed(DVDPlayer player);
}
