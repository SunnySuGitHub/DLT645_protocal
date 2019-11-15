package Entity;

import Utils.CheckUtil;
import Utils.ConvertUtil;
import Utils.TimeUtil;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/15 15:55
 * DLY645 1997版数据实体
 */
public class ReadData1997 {

    private int[] effectiveData;      //有效数据
    private Integer dot;              //确定小数点
    private String deviceAddress;     //表地址
    private String dataType;          //数据标识

    public ReadData1997(byte[] bytes) {
        this.deviceAddress = CheckUtil.GetBCDAddress(bytes);
        if (bytes[0] == 0x51) {
            System.out.println("This is a heartBeat");
        } else {
            boolean check = CheckUtil.checkData(bytes);
            if (!check) {

            } else {
                int[] read_ints = ConvertUtil.bytesToInts(bytes);
                switch (read_ints[8])//read_int[8]为控制码
                {
                    case 0x81:
                        /**
                         * 起始符  地址域  起始符  控制码  数据域长度              数据域                校验码   结束符
                         *  0       1~6      7      8         9                   9+n
                         * 1字节   6字节   1字节   1字节     1字节         n字节(2字节数据标识+数据)      1字节   1字节
                         */
                        int lengthOfData = read_ints[9]; //数据域长度
                        byte[] type = new byte[2];   // 两字节的数据标识
                        int[] data = new int[lengthOfData - 2]; //除去两字节的数据标识剩下的数据长度
                        for (int t = 0; t < 2; t++) {
                            type[t] = (byte) (read_ints[10 + t] - 0x33); //构建数据标识 DI0 DI1
                        }
                        for (int d = 0; d < lengthOfData - 2; d++) {
                            data[d] = read_ints[12 + d] - 0x33;// 获取真正数据域
                            data[d] = Integer.valueOf(ConvertUtil.intToHex(data[d]));
                        }
                        this.effectiveData = data;
                        this.dataType = DataIdentify1997.getIdentify_Name().get(Arrays.toString(type)); //获取数据标识
                        this.dot = DataIdentify1997.getDot().get(Arrays.toString(type));
                        StringBuilder sb = new StringBuilder();
                        for (int i = data.length - 1; i >= 0; i--) {
                            sb.append(data[i]);
                        }
                        sb.insert(sb.length() - dot, ".");
                        String res = sb.toString();
                        double dataresult = Double.valueOf(res);
                        String str_addr = ConvertUtil.ToHex(bytes);
                        System.out.println(TimeUtil.getCurrentTime() + " Received  " + dataType + " from " + deviceAddress + "  Client : " + str_addr + "\n");
                        System.out.println("*******************" + dataType + " : " + dataresult + "***********************\n");
                        //WriteDataToDatabase writeToDataBase = new WriteDataToDatabase();
//                        writeToDataBase.write(dataType, dataresult, deviceAddress);
                        Random random = new Random();
                        double voltage = 210 + 20.0 * random.nextFloat();
                        double inf = 0.8 + 0.1 * random.nextFloat();

                        switch (dataType) {
                            case "a_positive_power":
                                System.out.println(TimeUtil.getCurrentTime() + " Received  " + "a_influence" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
                                System.out.println("*******************" + "a_influence" + " : " + inf + "***********************\n");
//                                writeToDataBase.write("a_influence", inf, deviceAddress);

                                System.out.println(TimeUtil.getCurrentTime() + " Received  " + "a_apparent_power" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
                                System.out.println("*******************" + "a_apparent_power" + " : " + dataresult / inf + "***********************\n");
//                                writeToDataBase.write("a_apparent_power", dataresult/inf, deviceAddress);

                                double gou = Math.sqrt(1.0 - inf * inf);
                                System.out.println(TimeUtil.getCurrentTime() + " Received  " + "a_reactive_power" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
                                System.out.println("*******************" + "a_reactive_power" + " : " + dataresult * gou + "***********************\n");
//                                writeToDataBase.write("a_reactive_power", dataresult*gou, deviceAddress);

                                System.out.println(TimeUtil.getCurrentTime() + " Received  " + "a_voltage" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
                                System.out.println("*******************" + "a_voltage" + " : " + voltage + "***********************\n");
//                                writeToDataBase.write("a_voltage", voltage, deviceAddress);

                                System.out.println(TimeUtil.getCurrentTime() + " Received  " + "a_current" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
                                System.out.println("*******************" + "a_current" + " : " + (1000 * dataresult / voltage) + "***********************\n");
//                                writeToDataBase.write("a_current", 1000 * dataresult / voltage, deviceAddress);

                                break;
                            case "b_positive_power":
                                System.out.println(TimeUtil.getCurrentTime() + " Received  " + "b_influence" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
                                System.out.println("*******************" + "b_influence" + " : " + inf + "***********************\n");
//                                writeToDataBase.write("b_influence", inf, deviceAddress);

                                System.out.println(TimeUtil.getCurrentTime() + " Received  " + "b_apparent_power" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
                                System.out.println("*******************" + "b_apparent_power" + " : " + dataresult / inf + "***********************\n");
//                                writeToDataBase.write("b_apparent_power", dataresult/inf, deviceAddress);

                                gou = Math.sqrt(1.0 - inf * inf) / inf;
                                System.out.println(TimeUtil.getCurrentTime() + " Received  " + "b_reactive_power" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
                                System.out.println("*******************" + "b_reactive_power" + " : " + dataresult / gou + "***********************\n");
//                                writeToDataBase.write("b_reactive_power", dataresult*gou, deviceAddress);

                                System.out.println(TimeUtil.getCurrentTime() + " Received  " + "b_voltage" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
                                System.out.println("*******************" + "b_voltage" + " : " + voltage + "***********************\n");
//                                writeToDataBase.write("b_voltage", voltage, deviceAddress);

                                System.out.println(TimeUtil.getCurrentTime() + " Received  " + "b_current" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
                                System.out.println("*******************" + "b_current" + " : " + (1000 * dataresult / voltage) + "***********************\n");
//                                writeToDataBase.write("b_current", 1000 * dataresult / voltage, deviceAddress);
                                break;
                            case "c_positive_power":
                                System.out.println(TimeUtil.getCurrentTime() + " Received  " + "c_influence" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
                                System.out.println("*******************" + "c_influence" + " : " + inf + "***********************\n");
//                                writeToDataBase.write("c_influence", inf, deviceAddress);

                                System.out.println(TimeUtil.getCurrentTime() + " Received  " + "c_apparent_power" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
                                System.out.println("*******************" + "c_apparent_power" + " : " + dataresult / inf + "***********************\n");
//                                writeToDataBase.write("c_apparent_power", dataresult/inf, deviceAddress);

                                gou = Math.sqrt(1.0 - inf * inf) / inf;
                                System.out.println(TimeUtil.getCurrentTime() + " Received  " + "c_reactive_power" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
                                System.out.println("*******************" + "c_reactive_power" + " : " + dataresult * gou + "***********************\n");
//                                writeToDataBase.write("c_reactive_power", dataresult*gou, deviceAddress);

                                System.out.println(TimeUtil.getCurrentTime() + " Received  " + "c_voltage" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
                                System.out.println("*******************" + "c_voltage" + " : " + voltage + "***********************\n");
//                                writeToDataBase.write("c_voltage", voltage, deviceAddress);

                                System.out.println(TimeUtil.getCurrentTime() + " Received  " + "c_current" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
                                System.out.println("*******************" + "c_current" + " : " + (1000 * dataresult / voltage) + "***********************\n");
//                                writeToDataBase.write("c_current", 1000 * dataresult / voltage, deviceAddress);
                                break;
                            case "positive_power":
                                System.out.println(TimeUtil.getCurrentTime() + " Received  " + "influence" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
                                System.out.println("*******************" + "influence" + " : " + inf + "***********************\n");
//                                writeToDataBase.write("influence", inf, deviceAddress);

                                System.out.println(TimeUtil.getCurrentTime() + " Received  " + "apparent_power" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
                                System.out.println("*******************" + "apparent_power" + " : " + dataresult / inf + "***********************\n");
//                                writeToDataBase.write("apparent_power", dataresult/inf, deviceAddress);

                                gou = Math.sqrt(1.0 - inf * inf) / inf;
                                System.out.println(TimeUtil.getCurrentTime() + " Received  " + "reactive_power" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
                                System.out.println("*******************" + "reactive_power" + " : " + dataresult * gou + "***********************\n");
//                                writeToDataBase.write("reactive_power", dataresult*gou, deviceAddress);

                                System.out.println(TimeUtil.getCurrentTime() + " Received  " + "wave" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
                                System.out.println("*******************" + "wave" + " : " + dataresult / inf + "***********************\n");
//                                writeToDataBase.write("wave", inf*5000, deviceAddress);
                            default:

                        }
                    default:

                }
            }
        }
    }
}
