package tu.netfix_n_chill.base.torrent.client;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import tu.netfix_n_chill.base.model.WatchInfo;
import tu.netfix_n_chill.base.torrent.TorrentService;
import tu.netfix_n_chill.base.torrent.watch.WatchListener;

public class WatchClient extends BaseClient {

    public WatchClient(Context context) {
        super(context);
    }

    public void startWatch(WatchInfo watchInfo, WatchListener listener) {
        if (bound) {
            torrentService.startWatch(watchInfo, listener);
        }
    }

    public void stopWatch() {
        Intent intent = TorrentService.createIntent(context);
        intent.setAction(TorrentService.ACTION_STOP_WATCH);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    public void removeWatchListener(WatchListener listener) {
        if (bound) {
            torrentService.removeWatchListener(listener);
        }
    }
}