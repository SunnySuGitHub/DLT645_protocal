package Entity;

import Params.Constants;

import java.util.Arrays;

/**
 * 用于发送数据
 */
    public class SendData1997 {
        private int[] address; //地址域
        private int control; //控制码
        private int length; //数据长度
        private int[] data; //数据，包括数据标识
        int cs; //校验码


    public  SendData1997(int[] address, int control, int[] data){
        this.address = address;
        this.control = control;
        this.data = data;
        this.length=data.length;
        }

    //构建完整的帧，发送前调用
    public byte[] send() {
        int[] temp = new int[12+data.length];
        temp[0] = Constants.START;//帧起始符
        temp[7] = temp[0];//帧结束符
        for (int i = 1; i <= address.length; i++) {//地址域
            temp[i] =  address[i-1];
        }
        temp[8] =  control;//控制码
        temp[9] = length;//数据长度
        if (data.length>0){
            for (int i = 0; i <data.length ; i++) {//数据
                temp[10+i] =  data[i] + 0x33;
            }
        }
        //得到校验码
        cs =0;
        for(int i=0;i<temp.length-2;i++) {
            cs += (temp[i] & 0xff) % 256;
        }
        temp[temp.length-2] =  cs; //校验码
        temp[temp.length-1]=Constants.ENDING;//结束符
        byte[] msg = new byte[temp.length];
        //转化为字节
        for (int i = 0; i < temp.length; i++) {
            msg[i] = (byte) temp[i];
        }
        return msg;
    }

    @Override
    public String toString() {
        return "SendData1997{" +
                "address=" + Arrays.toString(address) +
                ", control=" + control +
                ", length=" + length +
                ", data=" + Arrays.toString(data) +
                ", cs=" + cs +
                '}';
    }
}
