package tu.netfix_n_chill.base.torrent.client;

public interface ClientConnectionListener {

    public void onClientConnected();

    public void onClientDisconnected();
}