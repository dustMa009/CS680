package edu.umb.cs680.hw05;

public interface State {
    public void openCloseButtonPushed(DVDPlayer player);
    public void playButtonPushed(DVDPlayer player);
    public void stopButtonPushed(DVDPlayer player);
}
