package xyz.yakdmt.followmeradio.data

import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

import xyz.yakdmt.followmeradio.network.NetworkWorker
import xyz.yakdmt.followmeradio.utils.Event

/**
 * Created by yakdmt on 20/04/16.
 */
class DataManager
@Inject
constructor(private val mNetworkWorker: NetworkWorker) {
    init {
        EventBus.getDefault().register(this);
    }
}

@SuppressWarnings("unused")
fun onEvent(event: Event.OnAudioStateChanged) {

}

