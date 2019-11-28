package mappers;

import Entity.Command;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommandMapper {
    List<Command> getCommandsByDeviceAddressAndCommandState(@Param("deviceNo") String deviceAddress,@Param("state")int commandState);
    void updateCommandState(@Param("id") int commandId,@Param("state")int state);
}
