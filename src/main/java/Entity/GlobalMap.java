package Entity;

import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/15 15:43
 * 全局单例map
 */
public class GlobalMap {

    //存放设备号和对应的设备
    private static ConcurrentHashMap<String, Device> MAP = null;

    private GlobalMap(){

    }

    public static ConcurrentHashMap getMap(){
        if(MAP == null){
            synchronized (GlobalMap.class){
                if(MAP == null){
                    MAP = new ConcurrentHashMap<String, Device>();
                }
            }
        }
        return MAP;
    }
}
