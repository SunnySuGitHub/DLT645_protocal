package Utils;

import Entity.Command;
import Entity.Device;
import Params.CommandState;
import mappers.CommandMapper;
import mappers.DeviceMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DBUtil {
    //初始化设备，使state设为0
    public static void initiateDevice(){
        SqlSession sqlSession = MyBatis.getSqlSessionFactory().openSession();
        DeviceMapper deviceMapper= sqlSession.getMapper(DeviceMapper.class);
        deviceMapper.initiateDevice();
        sqlSession.commit();
        sqlSession.close();
    }

    //更新设备状态上线状态
    public static void updateDeviceState(String deviceAddress,int state){
        SqlSession sqlSession = MyBatis.getSqlSessionFactory().openSession();
        DeviceMapper deviceMapper = sqlSession.getMapper(DeviceMapper.class);
        deviceMapper.updateDeviceState(deviceAddress,state);
        sqlSession.commit();
        sqlSession.close();
    }

    //通过设备地址查找未执行命令,即state为0的命令
    public static List<Command> getCommandsByDeviceAddressAndCommandState(String deviceAddress, int commandState){
        SqlSession sqlSession = MyBatis.getSqlSessionFactory().openSession();
        CommandMapper commandMapper = sqlSession.getMapper(CommandMapper.class);
        List<Command> commands =commandMapper.getCommandsByDeviceAddressAndCommandState(deviceAddress,commandState);
        sqlSession.commit();
        sqlSession.close();
        return commands;
    }

    //更新命令执行状态
    public static void updateCommandState(int commandId,int state){
        SqlSession sqlSession = MyBatis.getSqlSessionFactory().openSession();
        CommandMapper commandMapper = sqlSession.getMapper(CommandMapper.class);
        commandMapper.updateCommandState(commandId,state);
        sqlSession.commit();
        sqlSession.close();
    }
    //根据设备地址从数据库获得设备信息，可能根本用不到
    public static Device getDeviceByAddress(String deviceAddress) {

        return null;
    }
}
