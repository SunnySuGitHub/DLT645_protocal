package Handler;

import Entity.ReadData1997;
import Utils.CheckUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.Arrays;
import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/15 15:28
 * 缓冲区handler
 * 按照645协议对缓冲区内的字节流进行初步解码
 */
public class BufferedHandler extends ByteToMessageDecoder {
    /**
     * @param channelHandlerContext
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        int rd = in.readerIndex(); //获取最初readerIndex
        if (in.readableBytes() < 9)
            return;  //如果小于最小长度则返回
        byte[] bytes = new byte[in.readableBytes()];
        in.readBytes(bytes);                                //读取in里面的字节，readerIndex发生变化
        int curIdx = 0;
        while (curIdx < bytes.length && bytes[curIdx] != 0x68) {
            curIdx++;
        }
        while (curIdx < bytes.length) {
            try{
                if(bytes[curIdx] == 0x68 && bytes[curIdx+8] == 0x16) {
                    byte[] message = new byte[9];
                    System.arraycopy(bytes, curIdx, message,0, 9);
                    ReadData1997 readData1997 = new ReadData1997(message, 5);
                    if (readData1997 != null) out.add(readData1997);
                } else if (bytes[curIdx] == 0x68 && bytes[curIdx + 3] == 0x68) {
                    byte[] lengthBytes = new byte[2];
                    for(int i = 0; i < 2; i++) {
                        lengthBytes[i] = bytes[curIdx+2+i];
                    }
                    int dataLength = CheckUtil.getDataLength(lengthBytes);
                    if (bytes[curIdx + 6 + dataLength] == 0x16) {
                        byte[] message = new byte[12 + dataLength];
                        System.arraycopy(bytes, curIdx, message, 0, 12 + dataLength);
                        ReadData1997 readData1997 = new ReadData1997(message, dataLength);
                        if (readData1997 != null) out.add(readData1997);
                    }
                    curIdx += 12 + dataLength;
                } else {
                    curIdx++;
                }
            } catch (IndexOutOfBoundsException e){
                in.readerIndex(rd + curIdx);
                in.discardReadBytes();
                return;
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        System.out.println("有错误啦");
        ctx.close();
    }
}
