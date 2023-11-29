////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231129WED_113600] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.utils;

import java.util.concurrent.SynchronousQueue;

public class BlueThreadEx extends BlueThread {
    // -------------------------------------------------------------------------

    BlueThreadExListener mListener;

    SynchronousQueue<BlueThreadExMessage> mMsgQueue = new SynchronousQueue<>();

    // -------------------------------------------------------------------------

    // 생성자
    public BlueThreadEx(BlueThreadExListener listener, Object param) {
        super(new BlueThread.Listener() {

            @Override
            public void onStart(BlueThread sender, Object param) {
                if (listener != null)
                    listener.onStart(sender, param);
            }

            @Override
            public boolean onRun(BlueThread sender, Object param) {
                if (listener != null && !listener.onRun(sender, param))
                    return false;

                var t = (BlueThreadEx) sender;
                if (!t.mMsgQueue.isEmpty()) {
                    var msg = t.mMsgQueue.poll();
                    listener.onReceviedMessage(t, param, msg);
                }

                return true;
            }

            @Override
            public void onStop(BlueThread sender, Object param, boolean interrupted) {
                if (listener != null)
                    listener.onStop(sender, param, interrupted);
            }

        }, param);
    }

    // 생성자
    public BlueThreadEx(BlueThreadExListener listener) {
        this(listener, null);
    }

    // -------------------------------------------------------------------------

    // 메시지 전송
    public void sendMessage(BlueThreadExMessage msg) {
        this.mMsgQueue.add(msg);
    }
}
