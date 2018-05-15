package demo.lz4;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;


/**
 * Created by Bennett Dong <br>
 * Date : 2018/3/20 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class LZ4UtilsTest {

    @Test
    public void testCompress() throws Exception {
        String data = "Compressors might not generate the same compressed streams on all platforms, especially if CPU endianness differs, but the compressed streams can be safely decompressed by any decompressor implementation on any platform.\n"+
                "Compressors and decompressors are interchangeable: it is perfectly correct to compress with the JNI bindings and to decompress with a Java port, or the other way around.";
        byte[] compressedBytes = LZ4Utils.compress(data);
//        String compressedStr = new String(compressedBytes, "UTF-8");
        String compressedStr = Base64.encodeBase64String(compressedBytes);
        System.out.println("compressedStrLen="+compressedStr.length() + "\ncompressedStr=" + compressedStr);

        byte[] decompressedBytes = LZ4Utils.decompress(Base64.decodeBase64(compressedStr), data.getBytes().length);

//        byte[] decompressedBytes = LZ4Utils.decompress(compressedBytes, data.getBytes().length);

        System.out.println(new String(decompressedBytes, "UTF-8"));
    }

    @Test
    public void testDecompress() {
        String data = "";
        byte[] decompressedBytes = LZ4Utils.compress(data);
        System.out.println(new String(decompressedBytes));
    }
}
