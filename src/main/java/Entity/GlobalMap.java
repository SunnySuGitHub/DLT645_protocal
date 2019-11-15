package Entity;

import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/15 15:43
 * 全局单例map
 */
public class GlobalMap {

    private static ConcurrentHashMap<String, ChannelHandlerContext> MAP = null;

    private GlobalMap(){

    }

    public static ConcurrentHashMap getMap(){
        if(MAP == null){
            synchronized (GlobalMap.class){
                if(MAP == null){
                    MAP = new ConcurrentHashMap<String, ChannelHandlerContext>();
                }
            }
        }
        return MAP;
    }
}
