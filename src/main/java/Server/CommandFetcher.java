package Server;

import Entity.Command;
import Entity.Device;
import Entity.GlobalMap;
import Params.CommandState;
import Utils.DBUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 获取命令类，不断遍历每个设备，找出所有未分配的命令，将其加入到待执行队列中
 */
public class CommandFetcher implements Runnable{

    @Override
    public void run() {
        System.out.println("命令获取线程启动！");
        while(true){
            ConcurrentHashMap<String, Device> map= GlobalMap.getMap();
            Iterator<Map.Entry<String,Device>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String,Device> deviceEntry = iterator.next();
                Device device = deviceEntry.getValue();
                List<Command> commands = DBUtil.getCommandsByDeviceAddressAndCommandState(device.getDeviceNo(),CommandState.UN_ENQUEUED);
                for (Command command:commands) {
                    command.setState(CommandState.WAITING_IN_QUEUE);
                    device.getCommandsQueue().offer(command);
                    DBUtil.updateCommandState(command.getId(),CommandState.WAITING_IN_QUEUE);
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
