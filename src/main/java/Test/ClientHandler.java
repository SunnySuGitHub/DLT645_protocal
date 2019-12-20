package Test;

import Entity.SendData1997;
import Utils.ConvertUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Arrays;
import java.util.logging.Logger;

public class ClientHandler extends ChannelHandlerAdapter {
    private static final Logger logger =Logger.getLogger(ClientHandler.class.getName());
    private  ByteBuf firstMessage;
    public ClientHandler(){
        String str = "123456789012";
        int[] address = ConvertUtil.addressToBCD(str);
        int control = 0x81;
        int[] data = new int[]{0x10,0x90,23,34,23,41};
        SendData1997 sendData1997 = new SendData1997(address,control,data);
        System.out.println(sendData1997.toString());
        byte[] req = sendData1997.send();
        System.out.println(Arrays.toString(req));
        for (int i = 0; i < 3; i++) {
            firstMessage= Unpooled.buffer(req.length);
            firstMessage.writeBytes(req) ;
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(buf.readerIndex());
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        System.out.println(buf.readerIndex()+"***********");
        String body = new String(req,"UTF-8");
        System.out.println("接收数据为 : "+body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        logger.warning("Unexpected exception from downstream: " +cause.getMessage());
        ctx.close();
    }
}
