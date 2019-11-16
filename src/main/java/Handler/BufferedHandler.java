package Handler;

import Entity.ReadData1997;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/15 15:28
 * 缓冲区handler
 * 按照645协议对缓冲区内的字节流进行初步解码
 */
public class BufferedHandler extends ByteToMessageDecoder {


    /**
     * 问题：重置readerIndex，为啥缓冲区里面没有之前的数据
     * @param channelHandlerContext
     * @param in
     * @param out
     * @throws Exception
     */
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        int rd = in.readerIndex();                          //获取最初readerIndex
        if (in.readableBytes() < 12) return;                //如果小于最小长度则返回
        byte[] bytes = new byte[in.readableBytes()];
        in.readBytes(bytes);                                //读取in里面的字节，readerIndex发生变化
        int curIdx = 0;
        while (curIdx < bytes.length && bytes[curIdx] != 0x68) {
            curIdx++;
        }
        while (curIdx < bytes.length) {
            try{
                if (bytes[curIdx] == 0x68 && bytes[curIdx + 7] == 0x68) {
                    int lengthOfData = bytes[curIdx + 9];
                    if (bytes[curIdx + 11 + lengthOfData] == 0x16) {
                        byte[] message = new byte[12 + lengthOfData];
                        System.arraycopy(bytes, curIdx, message, 0, 12 + lengthOfData);
                        ReadData1997 readData1997 = new ReadData1997(message);
                        if (readData1997 != null) out.add(readData1997);
                    }
                    curIdx += 12 + lengthOfData;
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

    }
}
