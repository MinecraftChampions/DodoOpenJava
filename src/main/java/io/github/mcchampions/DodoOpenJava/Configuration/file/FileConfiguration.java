package io.github.mcchampions.DodoOpenJava.Configuration.file;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import io.github.mcchampions.DodoOpenJava.Configuration.Configuration;
import io.github.mcchampions.DodoOpenJava.Configuration.InvalidConfigurationException;
import io.github.mcchampions.DodoOpenJava.Configuration.MemoryConfiguration;
import io.github.mcchampions.DodoOpenJava.Utils.ConfigUtil;
import org.apache.commons.lang3.Validate;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * ����һ��ʵ���� Configuration �������ļ��Ļ���
 */
public abstract class FileConfiguration extends MemoryConfiguration {
    /**
     * ��ֵָ��Ӧ��ȫ����ϵͳĬ�ϱ��룬��Ϊ���޷�����ASCII�ַ������������Ѿ���UTF8���ϸ��Ӽ�����ASCII����
     *
     * @deprecated temporary compatibility measure
     */
    @Deprecated
    public static final boolean UTF8_OVERRIDE;
    /**
     * ��ֵָ��ϵͳĬ�ϱ����Ƿ�Ϊunicode�����޷�������׼ASCI
     *
     * @deprecated temporary compatibility measure
     */
    @Deprecated
    public static final boolean UTF_BIG;
    /**
     * ��ֵָ��ϵͳ�Ƿ�֧��unicode��
     *
     * @deprecated temporary compatibility measure
     */
    @Deprecated
    public static final boolean SYSTEM_UTF;
    static {
        final byte[] testBytes = Base64Coder.decode("ICEiIyQlJicoKSorLC0uLzAxMjM0NTY3ODk6Ozw9Pj9AQUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVpbXF1eX2BhYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ent8fX4NCg==");
        final String testString = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\r\n";
        final Charset defaultCharset = Charset.defaultCharset();
        final String resultString = new String(testBytes, defaultCharset);
        final boolean trueUTF = defaultCharset.name().contains("UTF");
        UTF8_OVERRIDE = !testString.equals(resultString) || defaultCharset.equals(StandardCharsets.US_ASCII);
        SYSTEM_UTF = trueUTF || UTF8_OVERRIDE;
        UTF_BIG = trueUTF && UTF8_OVERRIDE;
    }

    /**
     * ����һ���յģ�û��ֵĬ��ֵ�� FileConfiguration.
     */
    public FileConfiguration() {
        super();
    }

    /**
     * ����һ���յ� FileConfiguration ����ʹ�� Configuration �ڵ�����Ĭ��ֵ������.
     *
     * @param defaults Ϊ�䴴���ṩȱʡֵ��Configuration.
     */
    public FileConfiguration(Configuration defaults) {
        super(defaults);
    }

    /**
     * ��һ�� FileConfiguration ���ø÷��������ļ����浽ָ��λ��.
     * �����ָ�����������ļ�������,�������������Զ�����һ��. �������ļ�����,��ô�÷����������δ����ĸ���ֱ��д���ļ� ����ֱ�Ӹ���ԭ�ļ�. ���������ߴ���ʧ��,�����׳�һ���쳣
     *
     * ����������ϵͳĬ�ϵı��봢��,����Ҳ�п�����UTF-8������
     *
     * @param file Ҫ������ļ�
     * @throws IOException Ȼ�������޷��������߱����ԭ��.
     * @throws IllegalArgumentException  ����ļ�Ϊ�գ��׳����쳣
     */
    public void save(File file) throws IOException {
        Validate.notNull(file, "�ļ�����Ϊ��");

        Files.createParentDirs(file);

        String data = saveToString();

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), UTF8_OVERRIDE && !UTF_BIG ? Charsets.UTF_8 : Charset.defaultCharset())) {
            writer.write(data);
        }
    }

    /**
     * ��һ�� FileConfiguration ���ø÷��������ļ����浽ָ��λ��.
     * �����ָ�����������ļ�������,�������������Զ�����һ��. �������ļ�����,��ô�÷����������δ����ĸ���ֱ��д���ļ� ����ֱ�Ӹ���ԭ�ļ�. ���������ߴ���ʧ��,�����׳�һ���쳣
     *
     * ����������ϵͳĬ�ϵı��봢��,����Ҳ�п�����UTF-8������
     *
     * @param file Ҫ������ļ�
     * @throws IOException Ȼ�������޷��������߱����ԭ��.
     * @throws IllegalArgumentException ����ļ�Ϊ�գ��׳����쳣
     */
    public void save(String file) throws IOException {
        Validate.notNull(file, "�ļ�����Ϊ��");

        save(new File(file));
    }

    /**
     * ����� FileConfiguration ת��ΪString�����ҷ���t.
     *
     * @return ���FileConfiguration����������String
     */
    public abstract String saveToString();

    /**
     * ��ָ��λ�ü��� FileConfiguration
     *
     * @param file �ļ�
     * @throws FileNotFoundException �޷��򿪸����ļ�ʱ�׳�
     * @throws IOException �޷���ȡ�����ļ�ʱ�׳�
     * @throws InvalidConfigurationException �������ļ����� Configuration ʱ�׳�
     * @throws IllegalArgumentException �ļ�Ϊ��ʱ�׳�
     */
    public void load(File file) throws FileNotFoundException, IOException, InvalidConfigurationException {
        Validate.notNull(file, "�ļ�����Ϊ��");

        loadFromString(ConfigUtil.readFile(file));
    }

    /**
     * ��ָ���Ķ�ȡ�����ش��ļ����á�
     *
     * ��ɾ���������а���������ֵ��ֻ�������ú�Ĭ��ֵ�����ҽ��Ӹ�����������ֵ��
     */
    @Deprecated
    public void load(InputStream stream) throws IOException, InvalidConfigurationException {
        Validate.notNull(stream, "Stream cannot be null");

        load(new InputStreamReader(stream, UTF8_OVERRIDE ? Charsets.UTF_8 : Charset.defaultCharset()));
    }

    /**
     * ��ָ����Reader���ش��ļ����á�
     *
     * ��ɾ���������а���������ֵ��ֻ�������ú�Ĭ��ֵ�����ҽ��Ӹ�����������ֵ��
     *
     * @param reader Reader
     * @throws IOException ��Reader����IOExceptionʱ����
     * @throws InvalidConfigurationException ��Reader���� Configuration ʱ����
     * @throws IllegalArgumentException ReaderΪ��ʱ����
     */
    public void load(Reader reader) throws IOException, InvalidConfigurationException {
        BufferedReader input = reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);

        StringBuilder builder = new StringBuilder();

        try {
            String line;

            while ((line = input.readLine()) != null) {
                builder.append(line);
                builder.append('\n');
            }
        } finally {
            input.close();
        }

        loadFromString(builder.toString());
    }

    /**
     * ��ָ��λ�ü��ش��ļ����á�
     *
     * ��ɾ���������а���������ֵ��ֻ�������ú�Ĭ��ֵ�����ҽ��Ӹ����ļ�������ֵ��
     *
     *
     *
     * ��������κ�ԭ���޷������ļ����������쳣��
     *
     * @param file Ҫ���м��ص��ļ���
     * @throws FileNotFoundException �޷��򿪸����ļ�ʱ������
     * @throws IOException �޷���ȡ�����ļ�ʱ������
     * @throws InvalidConfigurationException �������ļ����� Configuration ʱ����
     * @throws IllegalArgumentException Thrown when file is null.
     */
    public void load(String file) throws FileNotFoundException, IOException, InvalidConfigurationException {
        Validate.notNull(file, "File cannot be null");

        load(new File(file));
    }

    /**
     * ��ָ�����ַ������ش��ļ����ã������Ǵ��ļ����ء�
     *
     * ��ɾ���������а���������ֵ��ֻ�������ú�Ĭ��ֵ�����ҽ��Ӹ����ַ���������ֵ��
     *
     * ����ַ������κη�ʽ��Ч���������쳣��
     *
     * @param contents �ַ���
     * @throws InvalidConfigurationException ���ָ�����ַ�����Ч�����׳�
     * @throws IllegalArgumentException �ַ���Ϊnullʱ�׳�
     */
    public abstract void loadFromString(String contents) throws InvalidConfigurationException;

    /**
     * ����� FileConfiguration �� header �����ؽ��
     * @return Compiled header
     */
    protected abstract String buildHeader();

    @Override
    public FileConfigurationOptions options() {
        if (options == null) {
            options = new FileConfigurationOptions(this);
        }

        return (FileConfigurationOptions) options;
    }
}