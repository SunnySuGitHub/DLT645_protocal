package mappers;

import Entity.Command;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceMapper {
    void initiateDevice();
    void updateDeviceState(@Param("deviceNo") String deviceAddress,@Param("state") int state);


}
