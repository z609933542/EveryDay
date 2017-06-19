package everyday.zxz.com.everyday.utils;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * RxBus
 * Created by YoKeyword on 2015/6/17.
 * update by hugo: 静态内部类
 */
public class RxBus {
    // 主题
    private final Subject<Object> mBus;

    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    private RxBus() {
        mBus = PublishSubject.create().toSerialized();
    }

    public static RxBus getDefault() {
        return RxBusHolder.sInstance;
    }

    private static class RxBusHolder {
        private static final RxBus sInstance = new RxBus();
    }


    // 提供了一个新的事件
    public void post(Object o) {
        mBus.onNext(o);
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return mBus.ofType(eventType);
    }
}
