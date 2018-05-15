package demo.lz4;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

import java.io.UnsupportedEncodingException;

/**
 * Created by Bennett Dong <br>
 * Date : 2018/3/20 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class LZ4Utils {

    public static byte[] compress(String data) {
        LZ4Factory factory = LZ4Factory.fastestInstance();

        try {
            byte[] dataBytes = data.getBytes("UTF-8");
            final int dataLen = dataBytes.length;
            System.out.println("DataLen="+dataLen);

            LZ4Compressor compressor = factory.fastCompressor();
            final int maxCompressedLen = compressor.maxCompressedLength(dataLen);
            System.out.println("maxCompressedLen="+maxCompressedLen);
            byte[] compressedBytes = new byte[maxCompressedLen];

            int compressedLen = compressor.compress(dataBytes, 0, dataLen, compressedBytes, 0, maxCompressedLen);

            System.out.println("CompressedLen="+compressedLen);
            return compressedBytes;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static byte[] decompress(final byte[] compressedBytes, final int decompressedLen) {
        LZ4Factory factory = LZ4Factory.fastestInstance();

        LZ4FastDecompressor decompressor = factory.fastDecompressor();
        byte[] restore = new byte[decompressedLen];

        try {
            final int compressedLen = compressedBytes.length;
            System.out.println("compressedLen="+compressedLen);

            int realDecompressedLen = decompressor.decompress(compressedBytes, 0, restore, 0, decompressedLen);
            System.out.println("realDecompressedLen="+realDecompressedLen);
            return restore;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] decompress(final String data, final int decompressedLen) {
        LZ4Factory factory = LZ4Factory.fastestInstance();

        LZ4FastDecompressor decompressor = factory.fastDecompressor();
        byte[] restore = new byte[decompressedLen];

        try {
            byte[] compressedBytes = data.getBytes("UTF-8");
            final int compressedLen = compressedBytes.length;
            System.out.println("compressedLen="+compressedLen);

            int realDecompressedLen = decompressor.decompress(compressedBytes, 0, restore, 0, decompressedLen);
            System.out.println("decompressedLen="+realDecompressedLen);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
